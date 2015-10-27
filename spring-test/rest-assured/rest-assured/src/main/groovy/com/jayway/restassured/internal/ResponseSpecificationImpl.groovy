/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.jayway.restassured.internal

import com.jayway.restassured.assertion.BodyMatcher
import com.jayway.restassured.assertion.BodyMatcherGroup
import com.jayway.restassured.assertion.CookieMatcher
import com.jayway.restassured.assertion.HeaderMatcher
import com.jayway.restassured.config.RestAssuredConfig
import com.jayway.restassured.function.RestAssuredFunction
import com.jayway.restassured.http.ContentType
import com.jayway.restassured.internal.log.LogRepository
import com.jayway.restassured.parsing.Parser
import com.jayway.restassured.response.Response
import com.jayway.restassured.specification.*
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.Validate
import org.hamcrest.Matcher
import org.hamcrest.Matchers

import static com.jayway.restassured.http.ContentType.ANY
import static com.jayway.restassured.internal.assertion.AssertParameter.notNull
import static org.apache.commons.lang3.StringUtils.substringAfter
import static org.hamcrest.Matchers.equalTo

class ResponseSpecificationImpl implements FilterableResponseSpecification {

  private static final String EMPTY = ""
  private static final String DOT = "."
  private Matcher<Integer> expectedStatusCode;
  private Matcher<String> expectedStatusLine;
  private BodyMatcherGroup bodyMatchers = new BodyMatcherGroup()
  private HamcrestAssertionClosure assertionClosure = new HamcrestAssertionClosure();
  private def headerAssertions = []
  private def cookieAssertions = []
  private RequestSpecification requestSpecification;
  private def contentType;
  private Response restAssuredResponse;
  private String bodyRootPath;
  def ResponseParserRegistrar rpr;
  def RestAssuredConfig config
  private Response response

  private contentParser
  def LogRepository logRepository

  ResponseSpecificationImpl(String bodyRootPath, ResponseSpecification defaultSpec, ResponseParserRegistrar rpr,
                            RestAssuredConfig config, LogRepository logRepository) {
    this(bodyRootPath, defaultSpec, rpr, config, null, logRepository)
  }

  ResponseSpecificationImpl(String bodyRootPath, ResponseSpecification defaultSpec, ResponseParserRegistrar rpr,
                            RestAssuredConfig config, Response response, LogRepository logRepository) {
    Validate.notNull(config, "RestAssuredConfig cannot be null")
    this.config = config
    this.response = response;
    rootPath(bodyRootPath)
    this.rpr = rpr
    if (defaultSpec != null) {
      spec(defaultSpec)
    }
    this.logRepository = logRepository
  }

  def ResponseSpecification content(List<Argument> arguments, Matcher matcher, Object... additionalKeyMatcherPairs) {
    throwIllegalStateExceptionIfRootPathIsNotDefined("specify arguments")
    content("", arguments, matcher, additionalKeyMatcherPairs)
  }

  def Response validate(Response response) {
    assertionClosure.validate(response)
    response
  }

  def ResponseSpecification content(Matcher matcher, Matcher... additionalMatchers) {
    notNull(matcher, "matcher")
    validateResponseIfRequired {
      bodyMatchers << new BodyMatcher(key: null, matcher: matcher, rpr: rpr)
      additionalMatchers?.each { hamcrestMatcher ->
        bodyMatchers << new BodyMatcher(key: null, matcher: hamcrestMatcher, rpr: rpr)
      }
    }
    return this
  }

  def ResponseSpecification content(String key, Matcher matcher, Object... additionalKeyMatcherPairs) {
    content(key, Collections.emptyList(), matcher, additionalKeyMatcherPairs)
  }

  def ResponseSpecification statusCode(Matcher<? super Integer> expectedStatusCode) {
    notNull(expectedStatusCode, "expectedStatusCode")
    validateResponseIfRequired {
      this.expectedStatusCode = expectedStatusCode
    }
    return this
  }

  def ResponseSpecification statusCode(int expectedStatusCode) {
    notNull(expectedStatusCode, "expectedStatusCode")
    return statusCode(equalTo(expectedStatusCode));
  }

  def ResponseSpecification statusLine(Matcher<? super String> expectedStatusLine) {
    notNull(expectedStatusLine, "expectedStatusLine")
    validateResponseIfRequired {
      this.expectedStatusLine = expectedStatusLine
    }
    return this
  }

  def ResponseSpecification headers(Map expectedHeaders) {
    notNull(expectedHeaders, "expectedHeaders")
    validateResponseIfRequired {
      expectedHeaders.each { headerName, matcher ->
        headerAssertions << new HeaderMatcher(headerName: headerName, matcher: matcher instanceof Matcher ? matcher : equalTo(matcher))
      }
    }
    return this
  }

  def ResponseSpecification headers(String firstExpectedHeaderName, Object firstExpectedHeaderValue, Object... expectedHeaders) {
    notNull firstExpectedHeaderName, "firstExpectedHeaderName"
    notNull firstExpectedHeaderValue, "firstExpectedHeaderValue"
    return headers(MapCreator.createMapFromParams(firstExpectedHeaderName, firstExpectedHeaderValue, expectedHeaders))
  }

  def <T> ResponseSpecification header(String headerName, RestAssuredFunction<String, T> mappingFunction, Matcher<? super T> expectedValueMatcher) {
    notNull headerName, "Header name"
    notNull mappingFunction, "Mapping function"
    notNull expectedValueMatcher, "Hamcrest matcher"
    validateResponseIfRequired {
      headerAssertions << new HeaderMatcher(headerName: headerName, mappingFunction: mappingFunction, matcher: expectedValueMatcher)
    }
    this
  }

  def ResponseSpecification header(String headerName, Matcher expectedValueMatcher) {
    notNull headerName, "headerName"
    notNull expectedValueMatcher, "expectedValueMatcher"

    validateResponseIfRequired {
      headerAssertions << new HeaderMatcher(headerName: headerName, matcher: expectedValueMatcher)
    }
    this;
  }

  def ResponseSpecification header(String headerName, String expectedValue) {
    return header(headerName, equalTo(expectedValue))
  }

  def ResponseSpecification cookies(Map expectedCookies) {
    notNull expectedCookies, "expectedCookies"

    validateResponseIfRequired {
      expectedCookies.each { cookieName, matcher ->
        cookieAssertions << new CookieMatcher(cookieName: cookieName, matcher: matcher instanceof Matcher ? matcher : equalTo(matcher))
      }
    }
    return this
  }

  def ResponseSpecification cookies(String firstExpectedCookieName, Object firstExpectedCookieValue, Object... expectedCookieNameValuePairs) {
    notNull firstExpectedCookieName, "firstExpectedCookieName"
    notNull firstExpectedCookieValue, "firstExpectedCookieValue"
    notNull expectedCookieNameValuePairs, "expectedCookieNameValuePairs"

    return cookies(MapCreator.createMapFromParams(firstExpectedCookieName, firstExpectedCookieValue, expectedCookieNameValuePairs))
  }

  def ResponseSpecification cookie(String cookieName, Matcher expectedValueMatcher) {
    notNull cookieName, "cookieName"
    notNull expectedValueMatcher, "expectedValueMatcher"
    validateResponseIfRequired {
      cookieAssertions << new CookieMatcher(cookieName: cookieName, matcher: expectedValueMatcher)
    }
    this;
  }

  def ResponseSpecification cookie(String cookieName) {
    notNull cookieName, "cookieName"
    return cookie(cookieName, Matchers.<String> anything())
  }

  def ResponseSpecification cookie(String cookieName, Object expectedValue) {
    return cookie(cookieName, equalTo(expectedValue))
  }

  def ResponseSpecification spec(ResponseSpecification responseSpecificationToMerge) {
    SpecificationMerger.merge(this, responseSpecificationToMerge);
    return this
  }

  def ResponseSpecification specification(ResponseSpecification responseSpecificationToMerge) {
    return spec(responseSpecificationToMerge)
  }

  def ResponseSpecification statusLine(String expectedStatusLine) {
    return statusLine(equalTo(expectedStatusLine))
  }

  def ResponseSpecification body(Matcher matcher, Matcher... additionalMatchers) {
    return content(matcher, additionalMatchers);
  }

  public ResponseSpecification body(String key, Matcher matcher, Object... additionalKeyMatcherPairs) {
    return content(key, Collections.emptyList(), matcher, additionalKeyMatcherPairs);
  }

  def ResponseSpecification body(String key, List<Argument> arguments, Matcher matcher, Object... additionalKeyMatcherPairs) {
    return content(key, arguments, matcher, additionalKeyMatcherPairs)
  }

  def ResponseSpecification body(List<Argument> arguments, Matcher matcher, Object... additionalKeyMatcherPairs) {
    return content(arguments, matcher, additionalKeyMatcherPairs)
  }

  def ResponseSpecification content(String key, List<Argument> arguments, Matcher matcher, Object... additionalKeyMatcherPairs) {
    notNull(key, "key")
    notNull(matcher, "matcher")

    def mergedPath = mergeKeyWithRootPath(key)
    mergedPath = applyArguments(mergedPath, arguments)
    validateResponseIfRequired {
      bodyMatchers << new BodyMatcher(key: mergedPath, matcher: matcher, rpr: rpr)
      if (additionalKeyMatcherPairs?.length > 0) {
        def pairs = MapCreator.createMapFromObjects(additionalKeyMatcherPairs)
        pairs.each { matchingKey, hamcrestMatcher ->
          def keyWithRoot = mergeKeyWithRootPath(matchingKey)
          bodyMatchers << new BodyMatcher(key: keyWithRoot, matcher: hamcrestMatcher, rpr: rpr)
        }
      }
    }
    return this
  }

  def ResponseLogSpecification log() {
    return new ResponseLogSpecificationImpl(responseSpecification: this, logRepository: logRepository)
  }

  def ResponseSpecification when() {
    return this;
  }

  def ResponseSpecification response() {
    return this;
  }

  def RequestSpecification given() {
    return requestSpecification;
  }

  def ResponseSpecification that() {
    return this;
  }

  def RequestSpecification request() {
    return requestSpecification;
  }

  Response get(String path, Object... pathParams) {
    requestSpecification.get(path, pathParams);
  }

  Response post(String path, Object... pathParams) {
    requestSpecification.post(path, pathParams);
  }

  Response put(String path, Object... pathParams) {
    requestSpecification.put(path, pathParams);
  }

  Response delete(String path, Object... pathParams) {
    requestSpecification.delete(path, pathParams);
  }

  Response patch(String path, Object... pathParams) {
    requestSpecification.patch(path, pathParams);
  }

  Response options(String path, Object... pathParams) {
    requestSpecification.options(path, pathParams);
  }


  Response get(URI uri) {
    get(notNull(uri, "URI").toString())
  }


  Response post(URI uri) {
    post(notNull(uri, "URI").toString())
  }


  Response put(URI uri) {
    put(notNull(uri, "URI").toString())
  }


  Response delete(URI uri) {
    delete(notNull(uri, "URI").toString())
  }


  Response head(URI uri) {
    head(notNull(uri, "URI").toString())
  }


  Response patch(URI uri) {
    patch(notNull(uri, "URI").toString())
  }


  Response options(URI uri) {
    options(notNull(uri, "URI").toString())
  }

  def Response get(URL url) {
    get(notNull(url, "URL").toString())
  }

  def Response post(URL url) {
    post(notNull(url, "URL").toString())
  }

  def Response put(URL url) {
    put(notNull(url, "URL").toString())
  }

  def Response delete(URL url) {
    delete(notNull(url, "URL").toString())
  }

  def Response head(URL url) {
    head(notNull(url, "URL").toString())
  }

  def Response patch(URL url) {
    patch(notNull(url, "URL").toString())
  }

  def Response options(URL url) {
    options(notNull(url, "URL").toString())
  }

  def Response get() {
    get("")
  }

  def Response post() {
    post("")
  }

  def Response put() {
    put("")
  }

  def Response delete() {
    delete("")
  }

  def Response head() {
    head("")
  }

  def Response patch() {
    patch("")
  }

  def Response options() {
    options("")
  }

  def Response head(String path, Object... pathParams) {
    requestSpecification.head(path, pathParams);
  }

  def Response get(String path, Map pathParams) {
    requestSpecification.get(path, pathParams);
  }

  def Response post(String path, Map pathParams) {
    requestSpecification.post(path, pathParams);
  }

  def Response put(String path, Map pathParams) {
    requestSpecification.put(path, pathParams);
  }

  def Response delete(String path, Map pathParams) {
    requestSpecification.delete(path, pathParams);
  }

  def Response head(String path, Map pathParams) {
    requestSpecification.head(path, pathParams);
  }

  def Response patch(String path, Map pathParams) {
    requestSpecification.patch(path, pathParams);
  }

  def Response options(String path, Map pathParams) {
    requestSpecification.options(path, pathParams);
  }

  def ResponseSpecification parser(String contentType, Parser parser) {
    rpr.registerParser(contentType, parser)
    this
  }

  def ResponseSpecification and() {
    return this;
  }

  def RequestSpecification with() {
    return given();
  }

  def ResponseSpecification then() {
    return this;
  }

  def ResponseSpecification expect() {
    return this;
  }

  def ResponseSpecification rootPath(String rootPath) {
    return this.rootPath(rootPath, [])
  }

  def ResponseSpecification root(String rootPath) {
    return this.rootPath(rootPath);
  }

  def ResponseSpecification noRoot() {
    return noRootPath()
  }

  def ResponseSpecification noRootPath() {
    return rootPath("")
  }

  def ResponseSpecification appendRoot(String pathToAppend) {
    return appendRoot(pathToAppend, [])
  }

  def ResponseSpecification appendRoot(String pathToAppend, List<Argument> arguments) {
    notNull pathToAppend, "Path to append to root path"
    notNull arguments, "Arguments for path to append"
    def mergedPath = mergeKeyWithRootPath(pathToAppend)
    rootPath(mergedPath, arguments)
  }

  def ResponseSpecification detachRoot(String pathToDetach) {
    notNull pathToDetach, "Path to detach from root path"
    throwIllegalStateExceptionIfRootPathIsNotDefined("detach path")
    pathToDetach = StringUtils.trim(pathToDetach);
    if (!bodyRootPath.endsWith(pathToDetach)) {
      throw new IllegalStateException("Cannot detach path '$pathToDetach' since root path '$bodyRootPath' doesn't end with '$pathToDetach'.");
    }
    bodyRootPath = StringUtils.substringBeforeLast(bodyRootPath, pathToDetach);
    if (bodyRootPath.endsWith(".")) {
      bodyRootPath = bodyRootPath.substring(0, bodyRootPath.length() - 1);
    }
    this
  }

  def ResponseSpecification rootPath(String rootPath, List<Argument> arguments) {
    notNull rootPath, "Root path"
    notNull arguments, "Arguments"
    this.bodyRootPath = applyArguments(rootPath, arguments)
    return this
  }

  def ResponseSpecification root(String rootPath, List<Argument> arguments) {
    return this.rootPath(rootPath, arguments)
  }

  def boolean hasBodyAssertionsDefined() {
    return bodyMatchers.containsMatchers()
  }

  def boolean hasAssertionsDefined() {
    return hasBodyAssertionsDefined() || !headerAssertions.isEmpty() ||
            !cookieAssertions.isEmpty() || expectedStatusCode != null || expectedStatusLine != null ||
            contentType != null
  }

  def ResponseSpecification defaultParser(Parser parser) {
    notNull parser, "Parser"
    rpr.defaultParser = parser
    return this
  }

  ResponseSpecification contentType(ContentType contentType) {
    notNull contentType, "contentType"
    validateResponseIfRequired {
      this.contentType = contentType
    }
    return this
  }

  ResponseSpecification contentType(String contentType) {
    notNull contentType, "contentType"
    validateResponseIfRequired {
      this.contentType = contentType
    }
    return this
  }

  ResponseSpecification contentType(Matcher<? super String> contentType) {
    notNull contentType, "contentType"
    validateResponseIfRequired {
      this.contentType = contentType
    }
    return this
  }

  class HamcrestAssertionClosure {

    def call(response, content) {
      return getClosure().call(response, content)
    }

    def call(response) {
      return getClosure().call(response, null)
    }

    def getResponseContentType() {
      return contentType ?: ANY;
    }

    private boolean requiresPathParsing() {
      return bodyMatchers.requiresPathParsing()
    }

    def getClosure() {
      return { response, content ->
        restAssuredResponse.parseResponse(response, content, hasBodyAssertionsDefined(), rpr)
      }
    }

    def validate(Response response) {
      if (hasAssertionsDefined()) {
        def validations = []
        try {
          validations.addAll(validateStatusCodeAndStatusLine(response))
          validations.addAll(validateHeadersAndCookies(response))
          validations.addAll(validateContentType(response))
          if (hasBodyAssertionsDefined()) {
            RestAssuredConfig cfg = config ?: new RestAssuredConfig()
            if (requiresPathParsing() && (!isEagerAssert() || contentParser == null)) {
              contentParser = new ContentParser().parse(response, rpr, cfg, isEagerAssert())
            }
            validations.addAll(bodyMatchers.validate(response, contentParser, cfg))
          }
        } catch (Exception e) {
          logRequestAndResponseIfEnabled();
          throw e;
        }

        def errors = validations.findAll { !it.success }
        def numberOfErrors = errors.size()
        if (numberOfErrors > 0) {
          logRequestAndResponseIfEnabled()
          def errorMessage = errors.collect { it.errorMessage }.join("\n")
          def s = numberOfErrors > 1 ? "s" : ""
          throw new AssertionError("$numberOfErrors expectation$s failed.\n$errorMessage")
        }
      }
    }

    private def void logRequestAndResponseIfEnabled() {
      if (logRepository != null) {
        def stream = config.getLogConfig().defaultStream()
        def requestLog = logRepository.requestLog
        def responseLog = logRepository.responseLog
        def requestLogHasText = StringUtils.isNotEmpty(requestLog)
        if (requestLogHasText) {
          stream.print(requestLog)
        }
        if (StringUtils.isNotEmpty(responseLog)) {
          if (requestLogHasText) {
            stream.print("\n");
          }
          stream.print(responseLog)
        }
      }
    }

    private def validateContentType(Response response) {
      def errors = []
      if (contentType != null) {
        def actualContentType = response.getContentType()
        if (contentType instanceof Matcher) {
          if (!contentType.matches(actualContentType)) {
            errors << [success: false, errorMessage: String.format("Expected content-type %s doesn't match actual content-type \"%s\".\n", contentType, actualContentType)]
          }
        } else if (contentType instanceof String) {
          if (!StringUtils.startsWithIgnoreCase(actualContentType, contentType.toString())) {
            errors << [success: false, errorMessage: String.format("Expected content-type \"%s\" doesn't match actual content-type \"%s\".\n", contentType, actualContentType)];
          }
        } else {
          def name = contentType.name()
          def pattern = ~/(^[\w\d_\-]+\/[\w\d_\-]+)\s*(?:;)/
          def matcher = pattern.matcher(actualContentType ?: "");
          def contentTypeToMatch
          if (matcher.find()) {
            contentTypeToMatch = matcher.group(1);
          } else {
            contentTypeToMatch = actualContentType
          }
          if (ContentType.fromContentType(contentTypeToMatch) != contentType) {
            errors << [success: false, errorMessage: String.format("Expected content-type \"%s\" doesn't match actual content-type \"%s\".\n", name, actualContentType)];
          }
        }
      }
      errors
    }

    private def validateStatusCodeAndStatusLine(Response response) {
      def errors = []
      if (expectedStatusCode != null) {
        def actualStatusCode = response.getStatusCode()
        if (!expectedStatusCode.matches(actualStatusCode)) {
          def errorMessage = String.format("Expected status code %s doesn't match actual status code <%s>.\n", expectedStatusCode.toString(), actualStatusCode)
          errors << [success: false, errorMessage: errorMessage];
        }
      }

      if (expectedStatusLine != null) {
        def actualStatusLine = response.getStatusLine()
        if (!expectedStatusLine.matches(actualStatusLine)) {
          def errorMessage = String.format("Expected status line %s doesn't match actual status line \"%s\".\n", expectedStatusLine.toString(), actualStatusLine)
          errors << [success: false, errorMessage: errorMessage];
        }
      }
      errors
    }

    private def validateHeadersAndCookies(Response response) {
      def validations = []
      validations.addAll(headerAssertions.collect { matcher ->
        matcher.validateHeader(response.getHeaders())
      })

      validations.addAll(cookieAssertions.collect { matcher ->
        def cookies = response.getHeaders().getValues("Set-Cookie")
        matcher.validateCookie(cookies)
      })
      validations
    }
  }

  Matcher<Integer> getStatusCode() {
    return expectedStatusCode
  }

  Matcher<String> getStatusLine() {
    return expectedStatusLine
  }

  boolean hasHeaderAssertions() {
    return !headerAssertions.isEmpty()
  }

  boolean hasCookieAssertions() {
    return !cookieAssertions.isEmpty()
  }

  String getResponseContentType() {
    return responseContentType != null ? responseContentType.toString() : ANY.toString()
  }

  String getRootPath() {
    return bodyRootPath
  }

  private String applyArguments(String path, List<Argument> arguments) {
    if (arguments?.size() > 0) {
      def numberArgsOfAfterMerge = StringUtils.countMatches(path, "%s");
      if (numberArgsOfAfterMerge > arguments.size()) {
        arguments = new ArrayList<>(arguments);
        for (int i = 0; i < (numberArgsOfAfterMerge - arguments.size()); i++) {
          arguments.add(new Argument("%s"));
        }
      }
      path = String.format(path, arguments.collect { it.getArgument() }.toArray(new Object[arguments.size()]))
    }
    return path
  }

  private String mergeKeyWithRootPath(String key) {
    if (bodyRootPath != null && bodyRootPath != EMPTY) {
      if (bodyRootPath.endsWith(DOT) && key.startsWith(DOT)) {
        return bodyRootPath + substringAfter(key, DOT);
      } else if (!bodyRootPath.endsWith(DOT) && !key.startsWith(DOT)) {
        return bodyRootPath + DOT + key
      }
      return bodyRootPath + key
    }
    key
  }

  def void throwIllegalStateExceptionIfRootPathIsNotDefined(String description) {
    if (rootPath == null || rootPath.isEmpty()) {
      throw new IllegalStateException("Cannot $description when root path is empty")
    }
  }

  private isEagerAssert() {
    return response != null
  }

  private void validateResponseIfRequired(Closure closure) {
    if (isEagerAssert()) {
      bodyMatchers.reset()
      // Reset the body matchers before each validation to avoid testing multiple matchers on each invocation
    }
    closure.call()
    if (isEagerAssert()) {
      assertionClosure.validate(response)
    }
  }
}