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



package com.jayway.restassured

import com.jayway.restassured.authentication.NoAuthScheme
import com.jayway.restassured.internal.RequestSpecificationImpl
import com.jayway.restassured.internal.log.LogRepository
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class ParameterMapBuilderTest {
  private RequestSpecificationImpl requestBuilder;

  @Before
  public void setup() throws Exception {
    requestBuilder = new RequestSpecificationImpl("baseURI", 20, "", new NoAuthScheme(), [], null, true, null, new LogRepository(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  def void mapThrowIAEWhenOddNumberOfStringsAreSupplied() throws Exception {
    requestBuilder.parameters("key1", "value1", "key2");
  }

  @Test
  def void mapBuildsAMapBasedOnTheSuppliedKeysAndValues() throws Exception {
    def map = requestBuilder.parameters("key1", "value1", "key2", "value2").requestParameters;

    assertEquals 2, map.size()
    assertEquals "value1", map.get("key1")
    assertEquals "value2", map.get("key2")
  }

  @Test
  def void removesParamOnRemoveParamMethod() throws Exception {
    requestBuilder.parameters("key1", "value1");
    def map = requestBuilder.removeParam("key1").requestParameters

    assertEquals 0, map.size()
  }

  @Test
  def void removesQueryParamOnRemoveQueryParamMethod() throws Exception {
    requestBuilder.queryParameters("key1", "value1");
    def map = requestBuilder.removeQueryParam("key1").queryParameters

    assertEquals 0, map.size()
  }

  @Test
  def void removesFormParamOnRemoveFormParamMethod() throws Exception {
    requestBuilder.queryParameters("key1", "value1");
    def map = requestBuilder.removeFormParam("key1").formParameters

    assertEquals 0, map.size()
  }

  @Test
  def void removesPathParamOnRemoveFormPathMethod() throws Exception {
    requestBuilder.pathParameters("key1", "value1");
    def map = requestBuilder.removePathParam("key1").pathParameters

    assertEquals 0, map.size()
  }
}
