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
package com.jayway.restassured.internal.print;

import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.internal.support.Prettifier;
import com.jayway.restassured.response.*;

import java.io.PrintStream;

import static com.jayway.restassured.filter.log.LogDetail.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * A response printer can be used to print a response.
 */
public class ResponsePrinter {

    private static final String HEADER_NAME_AND_VALUE_SEPARATOR = ": ";

    /**
     * Prints the response to the print stream
     *
     * @return A string of representing the response
     */
    public static String print(ResponseOptions responseOptions, ResponseBody responseBody, PrintStream stream, LogDetail logDetail, boolean shouldPrettyPrint) {
        final StringBuilder builder = new StringBuilder();
        if (logDetail == ALL || logDetail == STATUS) {
            builder.append(responseOptions.statusLine());
        }
        if (logDetail == ALL || logDetail == HEADERS) {
            final Headers headers = responseOptions.headers();
            if (headers.exist()) {
                appendNewLineIfAll(logDetail, builder).append(toString(headers));
            }
        } else if (logDetail == COOKIES) {
            final Cookies cookies = responseOptions.detailedCookies();
            if (cookies.exist()) {
                appendNewLineIfAll(logDetail, builder).append(cookies.toString());
            }
        }
        if (logDetail == ALL || logDetail == BODY) {
            String responseBodyToAppend;
            if (shouldPrettyPrint) {
                responseBodyToAppend = new Prettifier().getPrettifiedBodyIfPossible(responseOptions, responseBody);
            } else {
                responseBodyToAppend = responseBody.asString();
            }
            if (logDetail == ALL && !isBlank(responseBodyToAppend)) {
                builder.append("\n\n");
            }

            builder.append(responseBodyToAppend);
        }
        String response = builder.toString();
        stream.println(response);
        return response;
    }

    private static String toString(Headers headers) {
        if (!headers.exist()) {
            return "";
        }

        final StringBuilder builder = new StringBuilder();
        for (Header header : headers) {
            builder.append(header.getName()).append(HEADER_NAME_AND_VALUE_SEPARATOR).append(header.getValue()).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private static StringBuilder appendNewLineIfAll(LogDetail logDetail, StringBuilder builder) {
        if (logDetail == ALL) {
            builder.append("\n");
        }
        return builder;
    }


}
