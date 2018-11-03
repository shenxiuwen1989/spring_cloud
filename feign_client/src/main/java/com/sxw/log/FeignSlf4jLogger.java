/*
 * @(#)FeignSlf4jLogger.java 1.0 2018年8月23日
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018年8月23日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
package com.sxw.log;

import com.sxw.util.JsonUtils;
import com.sxw.util.MapUtils;
import feign.Request;
import feign.Response;
import feign.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import static feign.Util.UTF_8;
import static feign.Util.decodeOrDefault;

public class FeignSlf4jLogger extends feign.Logger {

    public static final feign.Logger LOG = new FeignSlf4jLogger();

    private final Logger logger;

    public FeignSlf4jLogger() {
        this(feign.Logger.class);
    }

    public FeignSlf4jLogger(Class<?> clazz) {
        this(LoggerFactory.getLogger(clazz));
    }

    public FeignSlf4jLogger(String name) {
        this(LoggerFactory.getLogger(name));
    }

    FeignSlf4jLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        if (logger.isDebugEnabled()) {
            log(configKey, "---> %s %s HTTP/1.1", request.method(), request.url());
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
                Map<String, Collection<String>> headers = request.headers();
                if (MapUtils.isNotEmpty(headers)) {
                    log(configKey, "---> FeignClient request headers : %s", JsonUtils.toJson(headers));
                }
                if (request.body() != null) {
                    if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                        String bodyText = request.charset() != null ? new String(request.body(), request.charset()) : null;
                        log(configKey, "---> FeignClient request body : %s", bodyText != null ? bodyText : "Binary data");
                    }
                }
            }
        }
    }

    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime)
            throws IOException {
        if (logger.isDebugEnabled()) {
            String reason = response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason() : "";
            int status = response.status();
            log(configKey, "<--- HTTP/1.1 %s%s (%sms)", status, reason, elapsedTime);
            if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {
                Map<String, Collection<String>> headers = response.headers();
                if (MapUtils.isNotEmpty(headers)) {
                    log(configKey, "<--- FeignClient response headers : %s", JsonUtils.toJson(headers));
                }
                if (response.body() != null && !(status == 204 || status == 205)) {
                    byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                    if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyData.length > 0) {
                        log(configKey, "<--- FeignClient response body : %s", decodeOrDefault(bodyData, UTF_8, "Binary data"));
                    }
                    return response.toBuilder().body(bodyData).build();
                } else {
                }
            }
            return response;
        }
        return response;
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format(methodTag(configKey) + format, args));
        }
    }

}

