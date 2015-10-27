/*
 * Copyright 2015 the original author or authors.
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

package com.jayway.restassured.itest.java;

import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.itest.java.support.WithJetty;
import org.apache.commons.io.output.WriterOutputStream;
import org.junit.Test;

import java.io.PrintStream;
import java.io.StringWriter;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.LogConfig.logConfig;
import static com.jayway.restassured.config.RestAssuredConfig.config;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GivenWhenThenLoggingITest extends WithJetty {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Test
    public void logsEverythingResponseUsingGivenWhenThenSyntax() throws Exception {
        final StringWriter writer = new StringWriter();
        final PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        given().
                config(config().logConfig(logConfig().defaultStream(captor).and().enablePrettyPrinting(false))).
                pathParam("firstName", "John").
                pathParam("lastName", "Doe").
        when().
                get("/{firstName}/{lastName}").
        then().
                log().all().
                body("fullName", equalTo("John Doe"));

        assertThat(writer.toString(), equalTo("HTTP/1.1 200 OK\nContent-Type: application/json;charset=utf-8\nContent-Length: 59\nServer: Jetty(9.3.2.v20150730)\n\n{\"firstName\":\"John\",\"lastName\":\"Doe\",\"fullName\":\"John Doe\"}" + LINE_SEPARATOR));
    }

    @Test
    public void logResponseThatHasCookiesWithLogDetailCookiesUsingGivenWhenThenSyntax() throws Exception {
        final StringWriter writer = new StringWriter();
        final PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);
        given().
                config(config().logConfig(logConfig().defaultStream(captor).and().enablePrettyPrinting(false))).
        when().
                get("/multiCookie").
        then().
                log().cookies().
                body(equalTo("OK"));
        assertThat(writer.toString(), allOf(startsWith("cookie1=cookieValue1;Domain=localhost\ncookie1=cookieValue2;Comment=\"My Purpose\";Path=/;Domain=localhost;Max-Age=1234567;Secure;Expires="), endsWith(";Version=1" + LINE_SEPARATOR)));
    }

    @Test
    public void logOnlyHeadersUsingResponseUsingLogSpecWithGivenWhenThenSyntax() throws Exception {
        final StringWriter writer = new StringWriter();
        final PrintStream captor = new PrintStream(new WriterOutputStream(writer), true);

        given().
                config(config().logConfig(new LogConfig(captor, true))).
                pathParam("firstName", "John").
                pathParam("lastName", "Doe").
        when().
                get("/{firstName}/{lastName}").
        then().
                log().headers().
                body("fullName", equalTo("John Doe"));


        assertThat(writer.toString(), equalTo("Content-Type: application/json;charset=utf-8\nContent-Length: 59\nServer: Jetty(9.3.2.v20150730)" + LINE_SEPARATOR));
    }
}
