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


package com.jayway.restassured.internal.filter

import com.jayway.restassured.filter.Filter
import com.jayway.restassured.filter.FilterContext
import com.jayway.restassured.internal.RequestSpecificationImpl
import com.jayway.restassured.internal.http.Method
import com.jayway.restassured.response.Response
import com.jayway.restassured.specification.FilterableRequestSpecification
import com.jayway.restassured.specification.FilterableResponseSpecification
import com.jayway.restassured.specification.RequestSender
import org.codehaus.groovy.runtime.ReflectionMethodInvoker

class FilterContextImpl implements FilterContext {
  def private Iterator<Filter> filters
  def private requestUri;
  def private substituedPath;
  def private originalPath;
  def private Method method;
  def assertionClosure
  def properties = [:]
  // The difference between internalRequestUri and requestUri is that query parameters defined outside the path is not included
  def private internalRequestUri
  def private Object[] unnamedPathParams
  def private String userDefinedPath

  /**
   * @param requestUri The full request uri including query params and encoding etc
   * @param fullOriginalPath The original path without any path parameters applied (merged with base path)
   * @param fullSubstitutedPath The substituted path with path parameters applied (merged with base path)
   * @param internalRequestUri An internal request URI where query parameters not explicitly defined in the path are missing
   * @param userDefinedPath The path that the user defined as a part of the request (for example if get("/x") then "/x" is the user defined path)
   * @param unnamedPathParams the unnamed path parameters passed to the invocation method (for example if get("/{x}/{y}", "z", "w") then ["z", "w"] are the unnamed path params)
   * @param method The method (e.g. GET, POST, PUT etc)
   * @param assertionClosure (the assertions that should be performed after the request)
   * @param filters The remaining filters to invoke
   */
  FilterContextImpl(String requestUri, String fullOriginalPath, String fullSubstitutedPath, String internalRequestUri, String userDefinedPath, Object[] unnamedPathParams,
                    Method method, assertionClosure, Iterator<Filter> filters) {
    this.userDefinedPath = userDefinedPath
    this.unnamedPathParams = unnamedPathParams
    this.internalRequestUri = internalRequestUri
    this.filters = filters
    this.requestUri = requestUri
    this.originalPath = fullOriginalPath
    this.substituedPath = fullSubstitutedPath
    this.method = method
    this.assertionClosure = assertionClosure
  }

  Response next(FilterableRequestSpecification request, FilterableResponseSpecification response) {
    if (filters.hasNext()) {
      def next = filters.next();
      def filterContext = (request as RequestSpecificationImpl).newFilterContext(userDefinedPath, unnamedPathParams, method, assertionClosure, filters)
      return next.filter(request, response, filterContext)
    }
  }

  String getRequestPath() {
    substituedPath
  }

  String getOriginalRequestPath() {
    originalPath
  }

  Method getRequestMethod() {
    method
  }

  String getRequestURI() {
    requestUri
  }

  String getInternalRequestURI() {
    internalRequestUri
  }

  String getCompleteRequestPath() {
    getRequestURI()
  }

  Response send(RequestSender requestSender) {
    ReflectionMethodInvoker.invoke(requestSender, method.toString().toLowerCase(), internalRequestUri)
  }

  void setValue(String name, Object value) {
    properties.put(name, value)
  }

  boolean hasValue(String name) {
    return getValue(name) != null
  }

  def getValue(String name) {
    return properties.get(name)
  }
}
