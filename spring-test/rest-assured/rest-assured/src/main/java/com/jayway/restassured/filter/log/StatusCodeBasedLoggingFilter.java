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

package com.jayway.restassured.filter.log;

import com.jayway.restassured.builder.ResponseBuilder;
import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.filter.FilterContext;
import com.jayway.restassured.internal.RestAssuredResponseImpl;
import com.jayway.restassured.internal.print.ResponsePrinter;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.FilterableRequestSpecification;
import com.jayway.restassured.specification.FilterableResponseSpecification;
import org.apache.commons.lang3.Validate;
import org.hamcrest.Matcher;

import java.io.PrintStream;

import static com.jayway.restassured.RestAssured.config;
import static com.jayway.restassured.filter.log.LogDetail.*;

class StatusCodeBasedLoggingFilter implements Filter {

    private final PrintStream stream;
    private final Matcher<?> matcher;
    private final LogDetail logDetail;
    private final boolean shouldPrettyPrint;

    /**
     * Log to system out
     *
     * @param matcher The matcher for the logging to take place
     */
    public StatusCodeBasedLoggingFilter(Matcher<? super Integer> matcher) {
        this(System.out, matcher);
    }

    /**
     * Instantiate a error logger using a specific print stream
     *
     * @param stream  The stream to log errors to.
     * @param matcher The matcher for the logging to take place
     */
    public StatusCodeBasedLoggingFilter(PrintStream stream, Matcher<? super Integer> matcher) {
        this(ALL, stream, matcher);
    }

    /**
     * Instantiate a logger using a specific print stream and a specific log detail
     *
     * @param logDetail The log detail
     * @param stream    The stream to log errors to.
     * @param matcher   The matcher for the logging to take place
     */
    public StatusCodeBasedLoggingFilter(LogDetail logDetail, PrintStream stream, Matcher<? super Integer> matcher) {
        this(logDetail, isPrettyPrintingEnabled(), stream, matcher);
    }

    /**
     * Instantiate a logger using a specific print stream and a specific log detail  and the option to pretty printing
     *
     * @param logDetail   The log detail
     * @param prettyPrint Enabled pretty printing if possible
     * @param stream      The stream to log errors to.
     * @param matcher     The matcher for the logging to take place
     */
    public StatusCodeBasedLoggingFilter(LogDetail logDetail, boolean prettyPrint, PrintStream stream, Matcher<? super Integer> matcher) {
        Validate.notNull(logDetail, "Log details cannot be null");
        Validate.notNull(stream, "Print stream cannot be null");
        Validate.notNull(matcher, "Matcher cannot be null");
        if (logDetail == PARAMS || logDetail == PATH || logDetail == METHOD) {
            throw new IllegalArgumentException(String.format("%s is not a valid %s for a response.", logDetail, LogDetail.class.getSimpleName()));
        }
        this.shouldPrettyPrint = prettyPrint;
        this.logDetail = logDetail;
        this.stream = stream;
        this.matcher = matcher;
    }

    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        final int statusCode = response.statusCode();
        if (matcher.matches(statusCode)) {
            ResponsePrinter.print(response, response, stream, logDetail, shouldPrettyPrint);
            final byte[] responseBody;
            if (logDetail == BODY || logDetail == ALL) {
                responseBody = response.asByteArray();
            } else {
                responseBody = null;
            }
            response = cloneResponseIfNeeded(response, responseBody);
        }

        return response;
    }

    /*
     * If body expectations are defined we need to return a new Response otherwise the stream
     * has been closed due to the logging.
     */
    private Response cloneResponseIfNeeded(Response response, byte[] responseAsString) {
        if (responseAsString != null && response instanceof RestAssuredResponseImpl && !((RestAssuredResponseImpl) response).getHasExpectations()) {
            final Response build = new ResponseBuilder().clone(response).setBody(responseAsString).build();
            ((RestAssuredResponseImpl) build).setHasExpectations(true);
            return build;
        }
        return response;
    }

    private static boolean isPrettyPrintingEnabled() {
        return config == null || config.getLogConfig().isPrettyPrintingEnabled();
    }

    private void throwIAE(LogDetail params) {
        throw new IllegalArgumentException(String.format("%s is not a valid %s for a response.", params, LogDetail.class.getSimpleName()));
    }
}
