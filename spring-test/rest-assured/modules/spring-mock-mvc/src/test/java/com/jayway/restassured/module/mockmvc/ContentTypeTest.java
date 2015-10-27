/*
 * Copyright 2014 the original author or authors.
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

package com.jayway.restassured.module.mockmvc;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.http.GreetingController;
import com.jayway.restassured.module.mockmvc.intercept.MockHttpServletRequestBuilderInterceptor;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.config;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ContentTypeTest {

    @Test public void
    adds_default_charset_to_content_type_by_default() {
        final AtomicReference<String> contentType = new AtomicReference<String>();

        given().
                standaloneSetup(new GreetingController()).
                contentType(ContentType.JSON).
                interceptor(new MockHttpServletRequestBuilderInterceptor() {
                    public void intercept(MockHttpServletRequestBuilder requestBuilder) {
                        MultiValueMap<String, Object> headers = Whitebox.getInternalState(requestBuilder, "headers");
                        contentType.set(String.valueOf(headers.getFirst("Content-Type")));
                    }
                }).
        when().
                get("/greeting?name={name}", "Johan").
        then().
               statusCode(200);

        assertThat(contentType.get()).isEqualTo("application/json;charset=" + config().getEncoderConfig().defaultContentCharset());
    }

    @Test public void
    adds_specific_charset_to_content_type_by_default() {
        final AtomicReference<String> contentType = new AtomicReference<String>();

        given().
                standaloneSetup(new GreetingController()).
                config(config().encoderConfig(encoderConfig().defaultCharsetForContentType(StandardCharsets.UTF_16.toString(), ContentType.JSON))).
                contentType(ContentType.JSON).
                interceptor(new MockHttpServletRequestBuilderInterceptor() {
                    public void intercept(MockHttpServletRequestBuilder requestBuilder) {
                        MultiValueMap<String, Object> headers = Whitebox.getInternalState(requestBuilder, "headers");
                        contentType.set(String.valueOf(headers.getFirst("Content-Type")));
                    }
                }).
        when().
                get("/greeting?name={name}", "Johan").
        then().
               statusCode(200);

        assertThat(contentType.get()).isEqualTo("application/json;charset=" + StandardCharsets.UTF_16.toString());
        assertThat(contentType.get()).doesNotContain(config().getEncoderConfig().defaultContentCharset());
    }

    @Test public void
    doesnt_add_default_charset_to_content_type_if_charset_is_defined_explicitly() {
        final AtomicReference<String> contentType = new AtomicReference<String>();

        given().
                standaloneSetup(new GreetingController()).
                contentType(ContentType.JSON.withCharset("UTF-16")).
                interceptor(new MockHttpServletRequestBuilderInterceptor() {
                    public void intercept(MockHttpServletRequestBuilder requestBuilder) {
                        MultiValueMap<String, Object> headers = Whitebox.getInternalState(requestBuilder, "headers");
                        contentType.set(String.valueOf(headers.getFirst("Content-Type")));
                    }
                }).
        when().
                get("/greeting?name={name}", "Johan").
        then().
               statusCode(200);

        assertThat(contentType.get()).isEqualTo("application/json;charset=UTF-16");
    }

    @Test public void
    doesnt_add_default_charset_to_content_type_if_configured_not_to_do_so() {
        final AtomicReference<String> contentType = new AtomicReference<String>();

        given().
                config(config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                standaloneSetup(new GreetingController()).
                contentType(ContentType.JSON).
                interceptor(new MockHttpServletRequestBuilderInterceptor() {
                    public void intercept(MockHttpServletRequestBuilder requestBuilder) {
                        MultiValueMap<String, Object> headers = Whitebox.getInternalState(requestBuilder, "headers");
                        contentType.set(String.valueOf(headers.getFirst("Content-Type")));
                    }
                }).
        when().
                get("/greeting?name={name}", "Johan").
        then().
                statusCode(200);

        assertThat(contentType.get()).isEqualTo("application/json");
    }
}
