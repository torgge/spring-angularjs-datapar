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
package com.jayway.restassured.internal.support

import com.jayway.restassured.internal.RestAssuredResponseOptionsImpl
import com.jayway.restassured.internal.path.json.JsonPrettifier
import com.jayway.restassured.internal.path.xml.XmlPrettifier
import com.jayway.restassured.parsing.Parser
import com.jayway.restassured.response.ResponseBody
import com.jayway.restassured.response.ResponseOptions
import com.jayway.restassured.specification.FilterableRequestSpecification

import static org.apache.commons.lang3.StringUtils.isBlank

class Prettifier {

  def String getPrettifiedBodyIfPossible(FilterableRequestSpecification request) {
    def body = request.getBody();
    if (body == null) {
      return null
    } else if (!(body instanceof String)) {
      return body.toString()
    }
    def parser = Parser.fromContentType(request.getRequestContentType())
    prettify(body as String, parser)
  }

  def String getPrettifiedBodyIfPossible(ResponseOptions responseOptions, ResponseBody responseBody) {
    def contentType = responseOptions.getContentType()
    def responseAsString = responseBody.asString()
    if (isBlank(contentType) || !responseOptions instanceof RestAssuredResponseOptionsImpl) {
      return responseAsString
    }

    RestAssuredResponseOptionsImpl responseImpl = responseOptions as RestAssuredResponseOptionsImpl;
    def rpr = responseImpl.getRpr();
    def parser = rpr.getParser(contentType)
    prettify(responseAsString, parser);
  }

  def String prettify(String body, Parser parser) {
    def String prettifiedBody;
    try {
      switch (parser) {
        case Parser.JSON:
          prettifiedBody = JsonPrettifier.prettifyJson(body)
          break
        case Parser.XML:
          prettifiedBody = XmlPrettifier.prettify(new XmlParser(false, false), body)
          break
        case Parser.HTML:
          prettifiedBody = XmlPrettifier.prettify(new XmlParser(new org.ccil.cowan.tagsoup.Parser()), body)
          break
        default:
          prettifiedBody = body
          break
      }
    } catch (Exception e) {
      // Parsing failed, probably because the content was not of expected type.
      prettifiedBody = body
    }
    return prettifiedBody
  }
}