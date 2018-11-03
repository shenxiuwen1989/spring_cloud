/*
 * @(#)JsonUtils.java 1.0 2018年7月14日
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018年7月14日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
package com.sxw.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sxw.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;

@Slf4j
public final class JsonUtils {
    
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    
    static{
        
        OBJECTMAPPER.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        OBJECTMAPPER.setDateFormat(dateFormat);
        // 如果为空则不输出
        OBJECTMAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 对于空的对象转json的时候不抛出错误
        OBJECTMAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用序列化日期为timestamps
        OBJECTMAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 禁用遇到未知属性抛出异常
        OBJECTMAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 视空字符传为null
        OBJECTMAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 低层级配置
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许属性名称没有引号
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 取消对非ASCII字符的转码
        OBJECTMAPPER.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
        
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        OBJECTMAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        
    }
    
    private JsonUtils(){
        
    }

    public static <T> T fromJsonThrowable(String json, Class<T> clazz) throws IOException {
        return OBJECTMAPPER.readValue(json, clazz);
    }
    
    public static <T> T fromJsonThrowable(String json, TypeReference<?> typeReference) throws IOException {
        return OBJECTMAPPER.readValue(json, typeReference);
    }
    
    public static <T> T fromJsonThrowable(ObjectMapper om,String json, TypeReference<?> typeReference) throws IOException {
        return om.readValue(json, typeReference);
    }
    
    public static <T> String toJsonThrowable(T src) throws IOException {
        return OBJECTMAPPER.writeValueAsString(src);
    }
    
    public static <T> String toJsonThrowable(ObjectMapper om,T src) throws IOException {
        return om.writeValueAsString(src);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return fromJsonThrowable(json, clazz);
        } catch (IOException e) {
            log.error("convert json to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> T fromJson(String json, TypeReference<?> typeReference) {
        try {
            return fromJsonThrowable(json, typeReference);
        } catch (IOException e) {
            log.error("convert json to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> T fromJson(ObjectMapper om,String json, TypeReference<?> typeReference) {
        try {
            return fromJsonThrowable(om,json, typeReference);
        } catch (IOException e) {
            log.error("convert json to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> String toJson(T src) {
        try {
            return toJsonThrowable(src);
        } catch (IOException e) {
            log.error("convert object to json exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> String toJson(ObjectMapper om,T src) {
        try {
            return toJsonThrowable(om,src);
        } catch (IOException e) {
            log.error("convert object to json exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> T fromJsonThrowable(Reader reader, Class<T> clazz) throws IOException {
        return OBJECTMAPPER.readValue(reader, clazz);
    }
    
    public static <T> T fromJsonThrowable(Reader reader, TypeReference<?> typeReference) throws IOException {
        return OBJECTMAPPER.readValue(reader, typeReference);
        
    }
    
    public static <T> T fromJson(Reader reader, Class<T> clazz) throws IOException {
        try {
            return fromJsonThrowable(reader, clazz);
        } catch (IOException e) {
            log.error("convert byte[] to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> T fromJson(Reader reader, TypeReference<?> typeReference) throws IOException {
        try {
            return fromJsonThrowable(reader, typeReference);
        } catch (IOException e) {
            log.error("convert byte[] to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> T fromJsonThrowable(InputStream in, Class<T> clazz) throws IOException {
        return OBJECTMAPPER.readValue(in, clazz);
    }
    
    public static <T> T fromJsonThrowable(InputStream in, TypeReference<?> typeReference) throws IOException {
        return OBJECTMAPPER.readValue(in, typeReference);
        
    }
    
    public static <T> T fromJson(InputStream in, Class<T> clazz) throws IOException {
        try {
            return fromJsonThrowable(in, clazz);
        } catch (IOException e) {
            log.error("convert byte[] to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
    public static <T> T fromJson(InputStream in, TypeReference<?> typeReference) throws IOException {
        try {
            return fromJsonThrowable(in, typeReference);
        } catch (IOException e) {
            log.error("convert byte[] to object exception : " , e);
            throw new ServiceException("9001","解析子系统返回的Json数据出现错误");
        }
    }
    
}




