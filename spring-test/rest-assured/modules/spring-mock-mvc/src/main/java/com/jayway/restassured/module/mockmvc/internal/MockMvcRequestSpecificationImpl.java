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

package com.jayway.restassured.module.mockmvc.internal;

import com.jayway.restassured.config.EncoderConfig;
import com.jayway.restassured.config.HeaderConfig;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.MapCreator;
import com.jayway.restassured.internal.http.CharsetExtractor;
import com.jayway.restassured.internal.log.LogRepository;
import com.jayway.restassured.internal.mapper.ObjectMapperType;
import com.jayway.restassured.internal.mapping.ObjectMapperSerializationContextImpl;
import com.jayway.restassured.internal.mapping.ObjectMapping;
import com.jayway.restassured.internal.support.ParameterUpdater;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.module.mockmvc.config.AsyncConfig;
import com.jayway.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import com.jayway.restassured.module.mockmvc.intercept.MockHttpServletRequestBuilderInterceptor;
import com.jayway.restassured.module.mockmvc.response.MockMvcResponse;
import com.jayway.restassured.module.mockmvc.specification.*;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Cookies;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.specification.ResponseSpecification;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.security.Principal;
import java.util.*;

import static com.jayway.restassured.internal.assertion.AssertParameter.notNull;
import static com.jayway.restassured.internal.serialization.SerializationSupport.isSerializableCandidate;
import static com.jayway.restassured.module.mockmvc.internal.ConfigConverter.convertToRestAssuredConfig;
import static com.jayway.restassured.module.mockmvc.internal.SpringSecurityClassPathChecker.isSpringSecurityInClasspath;
import static com.jayway.restassured.module.mockmvc.internal.UpdateStrategyConverter.convert;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class MockMvcRequestSpecificationImpl implements MockMvcRequestSpecification, MockMvcAuthenticationSpecification {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";

    private LogRepository logRepository;

    private MockMvcFactory mockMvcFactory;

    private String basePath;

    private final ResponseSpecification responseSpecification;

    private final Map<String, Object> params = new LinkedHashMap<String, Object>();
    private final Map<String, Object> queryParams = new LinkedHashMap<String, Object>();
    private final Map<String, Object> formParams = new LinkedHashMap<String, Object>();
    private final Map<String, Object> attributes = new LinkedHashMap<String, Object>();

    private Object requestBody = null;

    private RestAssuredMockMvcConfig cfg;

    private Headers requestHeaders = new Headers();

    private Cookies cookies = new Cookies();

    private List<MockMvcMultiPart> multiParts = new ArrayList<MockMvcMultiPart>();

    private RequestLoggingFilter requestLoggingFilter;

    private final ParameterUpdater parameterUpdater = new ParameterUpdater(new ParameterUpdater.Serializer() {
        public String serializeIfNeeded(Object value) {
            return MockMvcRequestSpecificationImpl.this.serializeIfNeeded(value);
        }
    });

    private final List<ResultHandler> resultHandlers = new ArrayList<ResultHandler>();

    private final List<RequestPostProcessor> requestPostProcessors = new ArrayList<RequestPostProcessor>();

    private MockHttpServletRequestBuilderInterceptor interceptor;

    private Object authentication;

    private AsyncConfig asyncConfig;

    public MockMvcRequestSpecificationImpl(MockMvcFactory mockMvcFactory, RestAssuredMockMvcConfig config, List<ResultHandler> resultHandlers,
                                           List<RequestPostProcessor> requestPostProcessors, String basePath,
                                           MockMvcRequestSpecification requestSpecification, ResponseSpecification responseSpecification,
                                           MockMvcAuthenticationScheme authentication) {
        this.logRepository = new LogRepository();
        this.mockMvcFactory = mockMvcFactory == null ? new MockMvcFactory() : mockMvcFactory;
        this.basePath = basePath;
        this.responseSpecification = responseSpecification;
        assignConfig(config);

        if (resultHandlers != null) {
            this.resultHandlers.addAll(resultHandlers);
        }

        if (requestPostProcessors != null) {
            this.requestPostProcessors.addAll(requestPostProcessors);
        }

        if (requestSpecification != null) {
            spec(requestSpecification);
        }

        if (authentication != null) {
            authentication.authenticate(this);
        }
    }

    public MockMvcRequestSpecification mockMvc(MockMvc mockMvc) {
        notNull(mockMvc, MockMvc.class);
        return changeMockMvcFactoryTo(new MockMvcFactory(mockMvc));
    }

    public MockMvcRequestSpecification standaloneSetup(Object... controllerOrMockMvcConfigurer) {
        MockMvcFactory mockMvcFactory = StandaloneMockMvcFactory.of(controllerOrMockMvcConfigurer);
        return changeMockMvcFactoryTo(mockMvcFactory);
    }

    public MockMvcRequestSpecification standaloneSetup(MockMvcBuilder builder) {
        notNull(builder, MockMvcBuilder.class);
        return changeMockMvcFactoryTo(new MockMvcFactory(builder));
    }

    public MockMvcRequestSpecification webAppContextSetup(WebApplicationContext context, MockMvcConfigurer... mockMvcConfigurers) {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(context);
        if (mockMvcConfigurers != null && mockMvcConfigurers.length > 0) {
            for (MockMvcConfigurer mockMvcConfigurer : mockMvcConfigurers) {
                builder.apply(mockMvcConfigurer);
            }
        }
        return changeMockMvcFactoryTo(new MockMvcFactory(builder));
    }

    public MockMvcRequestSpecification interceptor(MockHttpServletRequestBuilderInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }

    public MockMvcRequestSpecification and() {
        return this;
    }

    public MockMvcRequestSpecification postProcessors(RequestPostProcessor postProcessor, RequestPostProcessor... additionalPostProcessors) {
        notNull(postProcessor, RequestPostProcessor.class);
        this.requestPostProcessors.add(postProcessor);
        if (additionalPostProcessors != null && additionalPostProcessors.length >= 1) {
            Collections.addAll(this.requestPostProcessors, additionalPostProcessors);
        }
        return this;
    }

    public MockMvcAuthenticationSpecification auth() {
        return this;
    }

    public MockMvcRequestSpecification contentType(ContentType contentType) {
        notNull(contentType, "contentType");
        return header(CONTENT_TYPE, contentType.toString());
    }

    public MockMvcRequestSpecification contentType(String contentType) {
        notNull(contentType, "contentType");
        return header(CONTENT_TYPE, contentType);
    }

    public MockMvcRequestSpecification accept(ContentType contentType) {
        notNull(contentType, "contentType");
        return header(ACCEPT, contentType.getAcceptHeader());
    }

    public MockMvcRequestSpecification accept(String mediaTypes) {
        notNull(mediaTypes, "mediaTypes");
        return header(ACCEPT, mediaTypes);
    }

    public MockMvcRequestSpecification headers(String firstHeaderName, Object firstHeaderValue, Object... headerNameValuePairs) {
        return headers(MapCreator.createMapFromParams(firstHeaderName, firstHeaderValue, headerNameValuePairs));
    }

    public MockMvcRequestSpecification headers(Map<String, ?> headers) {
        notNull(headers, "headers");
        List<Header> headerList = new ArrayList<Header>();
        if (this.requestHeaders.exist()) {
            for (Header requestHeader : this.requestHeaders) {
                headerList.add(requestHeader);
            }
        }

        for (Map.Entry<String, ?> stringEntry : headers.entrySet()) {
            headerList.add(new Header(stringEntry.getKey(), serializeIfNeeded(stringEntry.getValue())));
        }

        this.requestHeaders = new Headers(headerList);
        return this;
    }

    public MockMvcRequestSpecification headers(Headers headers) {
        notNull(headers, "Headers");
        if (headers.exist()) {
            List<Header> headerList = new ArrayList<Header>();
            if (this.requestHeaders.exist()) {
                for (Header requestHeader : this.requestHeaders) {
                    headerList.add(requestHeader);
                }
            }

            for (Header requestHeader : headers) {
                headerList.add(requestHeader);
            }

            this.requestHeaders = new Headers(removeMergedHeadersIfNeeded(headerList));
        }
        return this;
    }

    private List<Header> removeMergedHeadersIfNeeded(List<Header> headerList) {
        HeaderConfig headerConfig = cfg.getHeaderConfig();
        List<Header> filteredList = new ArrayList<Header>();
        for (Header header : headerList) {
            String headerName = header.getName();
            if (headerConfig.shouldOverwriteHeaderWithName(headerName)) {
                int index = -1;
                for (int i = 0; i < filteredList.size(); i++) {
                    Header filteredHeader = filteredList.get(i);
                    if (filteredHeader.hasSameNameAs(header)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    filteredList.remove(index);
                }
            }

            filteredList.add(header);
        }
        return filteredList;
    }

    public MockMvcRequestSpecification header(final String headerName, final Object headerValue, Object... additionalHeaderValues) {
        notNull(headerName, "Header name");
        notNull(headerValue, "Header value");

        List<Header> headerList = new ArrayList<Header>() {{
            add(new Header(headerName, serializeIfNeeded(headerValue)));
        }};

        if (additionalHeaderValues != null) {
            for (Object additionalHeaderValue : additionalHeaderValues) {
                headerList.add(new Header(headerName, serializeIfNeeded(additionalHeaderValue)));
            }
        }

        return headers(new Headers(headerList));
    }


    public MockMvcRequestSpecification header(Header header) {
        notNull(header, "Header");
        return headers(new Headers(Collections.singletonList(header)));
    }

    public MockMvcRequestLogSpecification log() {
        return new MockMvcRequestLogSpecificationImpl(this);
    }

    public MockMvcRequestSpecification params(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        notNull(firstParameterName, "firstParameterName");
        notNull(firstParameterValue, "firstParameterValue");
        return params(MapCreator.createMapFromParams(firstParameterName, firstParameterValue, parameterNameValuePairs));
    }

    public MockMvcRequestSpecification params(Map<String, ?> parametersMap) {
        notNull(parametersMap, "parametersMap");
        parameterUpdater.updateParameters(convert(cfg.getParamConfig().requestParamsUpdateStrategy()), (Map<String, Object>) parametersMap, params);
        return this;
    }

    public MockMvcRequestSpecification param(String parameterName, Object... parameterValues) {
        notNull(parameterName, "parameterName");
        parameterUpdater.updateZeroToManyParameters(convert(cfg.getParamConfig().requestParamsUpdateStrategy()), params, parameterName, parameterValues);
        return this;
    }

    public MockMvcRequestSpecification param(String parameterName, Collection<?> parameterValues) {
        notNull(parameterName, "parameterName");
        notNull(parameterValues, "parameterValues");
        parameterUpdater.updateCollectionParameter(convert(cfg.getParamConfig().requestParamsUpdateStrategy()), params, parameterName, (Collection<Object>) parameterValues);
        return this;
    }

    public MockMvcRequestSpecification queryParams(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        notNull(firstParameterName, "firstParameterName");
        notNull(firstParameterValue, "firstParameterValue");
        return queryParams(MapCreator.createMapFromParams(firstParameterName, firstParameterValue, parameterNameValuePairs));
    }

    public MockMvcRequestSpecification queryParams(Map<String, ?> parametersMap) {
        notNull(parametersMap, "parametersMap");
        parameterUpdater.updateParameters(convert(cfg.getParamConfig().queryParamsUpdateStrategy()), (Map<String, Object>) parametersMap, queryParams);
        return this;
    }

    public MockMvcRequestSpecification queryParam(String parameterName, Object... parameterValues) {
        notNull(parameterName, "parameterName");
        parameterUpdater.updateZeroToManyParameters(convert(cfg.getParamConfig().queryParamsUpdateStrategy()), queryParams, parameterName, parameterValues);
        return this;
    }

    public MockMvcRequestSpecification queryParam(String parameterName, Collection<?> parameterValues) {
        notNull(parameterName, "parameterName");
        notNull(parameterValues, "parameterValues");
        parameterUpdater.updateCollectionParameter(convert(cfg.getParamConfig().queryParamsUpdateStrategy()), queryParams, parameterName, (Collection<Object>) parameterValues);
        return this;
    }

    public MockMvcRequestSpecification formParams(String firstParameterName, Object firstParameterValue, Object... parameterNameValuePairs) {
        notNull(firstParameterName, "firstParameterName");
        notNull(firstParameterValue, "firstParameterValue");
        return formParams(MapCreator.createMapFromParams(firstParameterName, firstParameterValue, parameterNameValuePairs));
    }

    public MockMvcRequestSpecification formParams(Map<String, ?> parametersMap) {
        notNull(parametersMap, "parametersMap");
        parameterUpdater.updateParameters(convert(cfg.getParamConfig().formParamsUpdateStrategy()), (Map<String, Object>) parametersMap, formParams);
        return this;
    }

    public MockMvcRequestSpecification formParam(String parameterName, Object... parameterValues) {
        notNull(parameterName, "parameterName");
        parameterUpdater.updateZeroToManyParameters(convert(cfg.getParamConfig().formParamsUpdateStrategy()), formParams, parameterName, parameterValues);
        return this;
    }

    public MockMvcRequestSpecification formParam(String parameterName, Collection<?> parameterValues) {
        notNull(parameterName, "parameterName");
        notNull(parameterValues, "parameterValues");
        parameterUpdater.updateCollectionParameter(convert(cfg.getParamConfig().formParamsUpdateStrategy()), formParams, parameterName, (Collection<Object>) parameterValues);
        return this;
    }

    public MockMvcRequestSpecification attribute(String attributeName, Object attributeValue) {
        notNull(attributeName, "attributeName");
        notNull(attributeValue, "attributeValue");
        parameterUpdater.updateZeroToManyParameters(convert(cfg.getParamConfig().attributeUpdateStrategy()), attributes, attributeName, attributeValue);
        return this;
    }

    public MockMvcRequestSpecification attributes(Map<String, ?> attributesMap) {
        notNull(attributesMap, "attributesMap");
        parameterUpdater.updateParameters(convert(cfg.getParamConfig().attributeUpdateStrategy()), (Map<String, Object>) attributesMap, attributes);
        return this;
    }

    public MockMvcRequestSpecification body(String body) {
        this.requestBody = body;
        return this;
    }

    public MockMvcRequestSpecification body(byte[] body) {
        this.requestBody = body;
        return this;
    }

    public MockMvcRequestSpecification body(File body) {
        this.requestBody = body;
        return this;
    }

    public MockMvcRequestSpecification body(Object object) {
        notNull(object, "object");
        if (!isSerializableCandidate(object)) {
            return body(object.toString());
        }

        String requestContentType = getRequestContentType();
        this.requestBody = ObjectMapping.serialize(object, requestContentType, findEncoderCharsetOrReturnDefault(requestContentType), null, cfg.getObjectMapperConfig(), cfg.getEncoderConfig());
        return this;
    }

    public MockMvcRequestSpecification body(Object object, ObjectMapper mapper) {
        notNull(object, "object");
        notNull(mapper, "Object mapper");
        String requestContentType = getRequestContentType();
        ObjectMapperSerializationContextImpl ctx = new ObjectMapperSerializationContextImpl();
        ctx.setObject(object);
        ctx.setCharset(findEncoderCharsetOrReturnDefault(requestContentType));
        ctx.setContentType(requestContentType);
        this.requestBody = mapper.serialize(ctx);
        return this;
    }

    public MockMvcRequestSpecification body(Object object, ObjectMapperType mapperType) {
        notNull(object, "object");
        notNull(mapperType, "Object mapper type");
        String requestContentType = getRequestContentType();
        this.requestBody = ObjectMapping.serialize(object, requestContentType, findEncoderCharsetOrReturnDefault(requestContentType), mapperType, cfg.getObjectMapperConfig(), cfg.getEncoderConfig());
        return this;
    }

    public MockMvcRequestSpecification cookies(String firstCookieName, Object firstCookieValue, Object... cookieNameValuePairs) {
        return cookies(MapCreator.createMapFromParams(firstCookieName, firstCookieValue, cookieNameValuePairs));
    }

    public MockMvcRequestSpecification cookies(Map<String, ?> cookies) {
        notNull(cookies, "cookies");
        List<Cookie> cookieList = new ArrayList<Cookie>();
        if (this.cookies.exist()) {
            for (Cookie requestCookie : this.cookies) {
                cookieList.add(requestCookie);
            }
        }

        for (Map.Entry<String, ?> stringEntry : cookies.entrySet()) {
            cookieList.add(new Cookie.Builder(stringEntry.getKey(), serializeIfNeeded(stringEntry.getValue())).build());
        }

        this.cookies = new Cookies(cookieList);
        return this;
    }

    public MockMvcRequestSpecification cookies(Cookies cookies) {
        notNull(cookies, "Cookies");
        if (cookies.exist()) {
            List<Cookie> cookieList = new ArrayList<Cookie>();
            if (this.cookies.exist()) {
                for (Cookie cookie : this.cookies) {
                    cookieList.add(cookie);
                }
            }

            for (Cookie cookie : cookies) {
                cookieList.add(cookie);
            }

            this.cookies = new Cookies(cookieList);
        }
        return this;
    }

    public MockMvcRequestSpecification cookie(final String cookieName, final Object cookieValue, Object... additionalValues) {
        notNull(cookieName, "Cookie name");
        notNull(cookieValue, "Cookie value");

        List<Cookie> cookieList = new ArrayList<Cookie>() {{
            add(new Cookie.Builder(cookieName, serializeIfNeeded(cookieValue)).build());
        }};

        if (additionalValues != null) {
            for (Object additionalCookieValue : additionalValues) {
                cookieList.add(new Cookie.Builder(cookieName, serializeIfNeeded(additionalCookieValue)).build());
            }
        }

        return cookies(new Cookies(cookieList));
    }

    public MockMvcRequestSpecification cookie(Cookie cookie) {
        notNull(cookie, "Cookie");
        return cookies(new Cookies(Collections.singletonList(cookie)));
    }

    public MockMvcRequestSpecification multiPart(File file) {
        multiParts.add(new MockMvcMultiPart(cfg.getMultiPartConfig(), file));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, File file) {
        multiParts.add(new MockMvcMultiPart(controlName, file));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, File file, String mimeType) {
        multiParts.add(new MockMvcMultiPart(controlName, file, mimeType));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, Object object) {
        multiParts.add(new MockMvcMultiPart(cfg.getMultiPartConfig(), controlName, serializeIfNeeded(object)));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, Object object, String mimeType) {
        multiParts.add(new MockMvcMultiPart(cfg.getMultiPartConfig(), controlName, serializeIfNeeded(object, mimeType), mimeType));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String filename, Object object, String mimeType) {
        multiParts.add(new MockMvcMultiPart(controlName, filename, serializeIfNeeded(object, mimeType), mimeType));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String fileName, byte[] bytes) {
        multiParts.add(new MockMvcMultiPart(controlName, fileName, bytes));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String fileName, byte[] bytes, String mimeType) {
        multiParts.add(new MockMvcMultiPart(controlName, fileName, bytes, mimeType));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String fileName, InputStream stream) {
        multiParts.add(new MockMvcMultiPart(controlName, fileName, stream));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String fileName, InputStream stream, String mimeType) {
        multiParts.add(new MockMvcMultiPart(controlName, fileName, stream, mimeType));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String contentBody) {
        multiParts.add(new MockMvcMultiPart(cfg.getMultiPartConfig(), controlName, contentBody));
        return this;
    }

    public MockMvcRequestSpecification multiPart(String controlName, String contentBody, String mimeType) {
        multiParts.add(new MockMvcMultiPart(cfg.getMultiPartConfig(), controlName, contentBody, mimeType));
        return this;
    }

    public MockMvcRequestSpecification config(RestAssuredMockMvcConfig config) {
        assignConfig(config);
        return this;
    }

    public MockMvcRequestSpecification spec(MockMvcRequestSpecification requestSpecificationToMerge) {
        notNull(requestSpecificationToMerge, MockMvcRequestSpecification.class);

        if (!(requestSpecificationToMerge instanceof MockMvcRequestSpecificationImpl)) {
            throw new IllegalArgumentException("requestSpecificationToMerge must be an instance of " + MockMvcRequestSpecificationImpl.class.getName());
        }
        MockMvcRequestSpecificationImpl that = (MockMvcRequestSpecificationImpl) requestSpecificationToMerge;

        Object otherRequestBody = that.getRequestBody();
        if (otherRequestBody != null) {
            this.requestBody = otherRequestBody;

        }

        if (isNotEmpty(that.getBasePath())) {
            this.basePath = that.getBasePath();
        }

        MockMvcFactory otherMockMvcFactory = that.getMockMvcFactory();
        if (otherMockMvcFactory != null && otherMockMvcFactory.isAssigned()) {
            this.changeMockMvcFactoryTo(otherMockMvcFactory);
        }

        this.cookies(that.getCookies());

        this.headers(that.getRequestHeaders());

        mergeConfig(this, that);

        MockHttpServletRequestBuilderInterceptor otherInterceptor = that.getInterceptor();
        if (otherInterceptor != null) {
            this.interceptor = otherInterceptor;
        }

        this.formParams(that.getFormParams());
        this.queryParams(that.getQueryParams());
        this.params(that.getParams());
        this.attributes(that.getAttributes());

        this.multiParts.addAll(that.getMultiParts());
        this.resultHandlers.addAll(that.getResultHandlers());
        this.requestPostProcessors.addAll(that.getRequestPostProcessors());

        RequestLoggingFilter otherRequestLoggingFilter = that.getRequestLoggingFilter();
        if (otherRequestLoggingFilter != null) {
            this.requestLoggingFilter = otherRequestLoggingFilter;
        }

        Object otherAuth = that.getAuthentication();
        if (otherAuth != null) {
            this.authentication = otherAuth;
        }

        AsyncConfig otherAsyncConfig = that.getAsyncConfig();
        if (otherAsyncConfig != null) {
            this.asyncConfig = otherAsyncConfig;
        }

        return this;
    }

    private static void mergeConfig(MockMvcRequestSpecificationImpl thisOne, MockMvcRequestSpecificationImpl other) {
        RestAssuredMockMvcConfig thisConfig = thisOne.getRestAssuredMockMvcConfig();
        RestAssuredMockMvcConfig otherConfig = other.getRestAssuredMockMvcConfig();
        boolean thisIsUserConfigured = thisConfig.isUserConfigured();
        boolean otherIsUserConfigured = otherConfig.isUserConfigured();
        if (thisIsUserConfigured && otherIsUserConfigured) {
            if (otherConfig.getDecoderConfig().isUserConfigured()) {
                thisConfig = thisConfig.decoderConfig(otherConfig.getDecoderConfig());
            }

            if (otherConfig.getEncoderConfig().isUserConfigured()) {
                thisConfig = thisConfig.encoderConfig(otherConfig.getEncoderConfig());
            }

            if (otherConfig.getHeaderConfig().isUserConfigured()) {
                thisConfig = thisConfig.headerConfig(otherConfig.getHeaderConfig());
            }

            if (otherConfig.getJsonConfig().isUserConfigured()) {
                thisConfig = thisConfig.jsonConfig(otherConfig.getJsonConfig());
            }

            if (otherConfig.getLogConfig().isUserConfigured()) {
                thisConfig = thisConfig.logConfig(otherConfig.getLogConfig());
            }

            if (otherConfig.getObjectMapperConfig().isUserConfigured()) {
                thisConfig = thisConfig.objectMapperConfig(otherConfig.getObjectMapperConfig());
            }

            if (otherConfig.getSessionConfig().isUserConfigured()) {
                thisConfig = thisConfig.sessionConfig(otherConfig.getSessionConfig());
            }

            if (otherConfig.getXmlConfig().isUserConfigured()) {
                thisConfig = thisConfig.xmlConfig(otherConfig.getXmlConfig());
            }

            if (otherConfig.getAsyncConfig().isUserConfigured()) {
                thisConfig = thisConfig.asyncConfig(otherConfig.getAsyncConfig());
            }

            if (otherConfig.getMultiPartConfig().isUserConfigured()) {
                thisConfig = thisConfig.multiPartConfig(otherConfig.getMultiPartConfig());
            }

            if (otherConfig.getMockMvcConfig().isUserConfigured()) {
                thisConfig = thisConfig.mockMvcConfig(otherConfig.getMockMvcConfig());
            }

            if (otherConfig.getParamConfig().isUserConfigured()) {
                thisConfig = thisConfig.paramConfig(otherConfig.getParamConfig());
            }

            thisOne.config(thisConfig);
        } else if (!thisIsUserConfigured && otherIsUserConfigured) {
            thisOne.config(otherConfig);
        }
    }

    public MockMvcRequestSpecification sessionId(String sessionIdValue) {
        return sessionId(cfg.getSessionConfig().sessionIdName(), sessionIdValue);
    }

    public MockMvcRequestSpecification sessionId(String sessionIdName, String sessionIdValue) {
        notNull(sessionIdName, "Session id name");
        notNull(sessionIdValue, "Session id value");
        if (cookies.hasCookieWithName(sessionIdName)) {
            List<Cookie> allOtherCookies = new ArrayList<Cookie>();
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equalsIgnoreCase(sessionIdName)) {
                    allOtherCookies.add(cookie);
                }
            }
            allOtherCookies.add(new Cookie.Builder(sessionIdName, sessionIdValue).build());
            this.cookies = new Cookies(allOtherCookies);
        } else {
            cookie(sessionIdName, sessionIdValue);
        }
        return this;
    }

    public MockMvcRequestSpecification resultHandlers(ResultHandler resultHandler, ResultHandler... resultHandlers) {
        notNull(resultHandler, ResultHandler.class);
        this.resultHandlers.add(resultHandler);
        if (resultHandlers != null && resultHandlers.length >= 1) {
            Collections.addAll(this.resultHandlers, resultHandlers);
        }
        return this;
    }

    public MockMvcRequestAsyncSender when() {
        LogConfig logConfig = cfg.getLogConfig();
        if (requestLoggingFilter == null && logConfig.isLoggingOfRequestAndResponseIfValidationFailsEnabled()) {
            log().ifValidationFails(logConfig.logDetailOfRequestAndResponseIfValidationFails(), logConfig.isPrettyPrintingEnabled());
        }
        MockMvc mockMvc = mockMvcFactory.build(cfg.getMockMvcConfig());
        return new MockMvcRequestSenderImpl(mockMvc, params, queryParams, formParams, attributes, cfg, requestBody,
                requestHeaders, cookies, multiParts, requestLoggingFilter, resultHandlers, requestPostProcessors, interceptor, basePath, responseSpecification, authentication,
                logRepository);
    }

    private String findEncoderCharsetOrReturnDefault(String contentType) {
        String charset = CharsetExtractor.getCharsetFromContentType(contentType);
        if (charset == null) {
            EncoderConfig encoderConfig = cfg.getEncoderConfig();
            if (encoderConfig.hasDefaultCharsetForContentType(contentType)) {
                charset = encoderConfig.defaultCharsetForContentType(contentType);
            } else {
                charset = encoderConfig.defaultContentCharset();
            }
        }
        return charset;
    }

    private String serializeIfNeeded(Object object) {
        return serializeIfNeeded(object, getRequestContentType());
    }

    private String serializeIfNeeded(Object object, String contentType) {
        return isSerializableCandidate(object) ? ObjectMapping.serialize(object, contentType, findEncoderCharsetOrReturnDefault(contentType), null, cfg.getObjectMapperConfig(), cfg.getEncoderConfig()) : object.toString();
    }

    public MockMvcResponse get(String path, Object... pathParams) {
        return when().get(path, pathParams);
    }

    public MockMvcResponse get(String path, Map<String, ?> pathParams) {
        return when().get(path, pathParams);
    }

    public MockMvcResponse post(String path, Object... pathParams) {
        return when().post(path, pathParams);
    }

    public MockMvcResponse post(String path, Map<String, ?> pathParams) {
        return when().post(path, pathParams);
    }

    public MockMvcResponse put(String path, Object... pathParams) {
        return when().put(path, pathParams);
    }

    public MockMvcResponse put(String path, Map<String, ?> pathParams) {
        return when().put(path, pathParams);
    }

    public MockMvcResponse delete(String path, Object... pathParams) {
        return when().delete(path, pathParams);
    }

    public MockMvcResponse delete(String path, Map<String, ?> pathParams) {
        return when().delete(path, pathParams);
    }

    public MockMvcResponse head(String path, Object... pathParams) {
        return when().head(path, pathParams);
    }

    public MockMvcResponse head(String path, Map<String, ?> pathParams) {
        return when().head(path, pathParams);
    }

    public MockMvcResponse patch(String path, Object... pathParams) {
        return when().patch(path, pathParams);
    }

    public MockMvcResponse patch(String path, Map<String, ?> pathParams) {
        return when().patch(path, pathParams);
    }

    public MockMvcResponse options(String path, Object... pathParams) {
        return when().options(path, pathParams);
    }

    public MockMvcResponse options(String path, Map<String, ?> pathParams) {
        return when().options(path, pathParams);
    }

    public MockMvcResponse get(URI uri) {
        return when().get(uri);
    }

    public MockMvcResponse post(URI uri) {
        return when().post(uri);
    }

    public MockMvcResponse put(URI uri) {
        return when().put(uri);
    }

    public MockMvcResponse delete(URI uri) {
        return when().delete(uri);
    }

    public MockMvcResponse head(URI uri) {
        return when().head(uri);
    }

    public MockMvcResponse patch(URI uri) {
        return when().patch(uri);
    }

    public MockMvcResponse options(URI uri) {
        return when().options(uri);
    }

    public MockMvcResponse get(URL url) {
        return when().get(url);
    }

    public MockMvcResponse post(URL url) {
        return when().post(url);
    }

    public MockMvcResponse put(URL url) {
        return when().put(url);
    }

    public MockMvcResponse delete(URL url) {
        return when().delete(url);
    }

    public MockMvcResponse head(URL url) {
        return when().head(url);
    }

    public MockMvcResponse patch(URL url) {
        return when().patch(url);
    }

    public MockMvcResponse options(URL url) {
        return when().options(url);
    }

    public MockMvcResponse get() {
        return when().get();
    }

    public MockMvcResponse post() {
        return when().post();
    }

    public MockMvcResponse put() {
        return when().put();
    }

    public MockMvcResponse delete() {
        return when().delete();
    }

    public MockMvcResponse head() {
        return when().head();
    }

    public MockMvcResponse patch() {
        return when().patch();
    }

    public MockMvcResponse options() {
        return when().options();
    }

    public RestAssuredConfig getRestAssuredConfig() {
        return convertToRestAssuredConfig(cfg);
    }

    public void setRequestLoggingFilter(RequestLoggingFilter requestLoggingFilter) {
        this.requestLoggingFilter = requestLoggingFilter;
    }

    private MockMvcRequestSpecification changeMockMvcFactoryTo(MockMvcFactory mockMvcFactory) {
        this.mockMvcFactory = mockMvcFactory;
        return this;
    }

    private void assignConfig(RestAssuredMockMvcConfig config) {
        if (config == null) {
            this.cfg = new RestAssuredMockMvcConfig();
        } else {
            this.cfg = config;
        }
    }

    public MockMvcFactory getMockMvcFactory() {
        return mockMvcFactory;
    }

    public String getBasePath() {
        return basePath;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public Map<String, Object> getFormParams() {
        return formParams;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public Object getAuthentication() {
        return authentication;
    }

    public RestAssuredMockMvcConfig getRestAssuredMockMvcConfig() {
        return cfg;
    }

    public Headers getRequestHeaders() {
        return requestHeaders;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public String getRequestContentType() {
        Header header = requestHeaders.get(CONTENT_TYPE);
        if (header != null) {
            return header.getValue();
        }
        return null;
    }

    public List<MockMvcMultiPart> getMultiParts() {
        return multiParts;
    }

    public RequestLoggingFilter getRequestLoggingFilter() {
        return requestLoggingFilter;
    }

    public List<ResultHandler> getResultHandlers() {
        return resultHandlers;
    }

    public List<RequestPostProcessor> getRequestPostProcessors() {
        return requestPostProcessors;
    }

    public MockHttpServletRequestBuilderInterceptor getInterceptor() {
        return interceptor;
    }

    public MockMvcRequestSpecification basePath(String path) {
        notNull(path, "Base path");
        this.basePath = path;
        return this;
    }

    public MockMvcRequestSpecification principal(Principal principal) {
        notNull(principal, Principal.class);
        this.authentication = principal;
        return this;
    }

    public MockMvcRequestSpecification with(RequestPostProcessor requestPostProcessor, RequestPostProcessor... additionalRequestPostProcessor) {
        notNull(requestPostProcessor, RequestPostProcessor.class);
        this.authentication = null;
        this.requestPostProcessors.add(requestPostProcessor);
        if (!(additionalRequestPostProcessor == null || additionalRequestPostProcessor.length == 0)) {
            this.requestPostProcessors.addAll(Arrays.asList(additionalRequestPostProcessor));
        }
        return this;
    }

    public MockMvcRequestSpecification principal(Object principal) {
        return principalWithCredentials(principal, "");
    }

    public MockMvcRequestSpecification principalWithCredentials(Object principal, Object credentials, String... authorities) {
        return authentication(new org.springframework.security.authentication.TestingAuthenticationToken(principal, credentials, authorities));
    }

    public MockMvcRequestSpecification authentication(Object authentication) {
        if (!isSpringSecurityInClasspath()) {
            throw new IllegalArgumentException("Cannot use this authentication method since Spring Security was not found in classpath.");
        }
        notNull(authentication, org.springframework.security.core.Authentication.class);
        if (!(authentication instanceof org.springframework.security.core.Authentication)) {
            throw new IllegalArgumentException("authentication object must be an instance of " + org.springframework.security.core.Authentication.class.getName());
        }
        this.authentication = authentication;
        return this;
    }

    public MockMvcRequestSpecification none() {
        this.authentication = null;
        return this;
    }

    public LogRepository getLogRepository() {
        return logRepository;
    }

    public AsyncConfig getAsyncConfig() {
        return asyncConfig;
    }
}
