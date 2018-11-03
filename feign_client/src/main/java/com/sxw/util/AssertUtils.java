/*
 * @Copyright:   Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Date:        2018年7月17日 下午2:02:33
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: Initialize
 */
package com.sxw.util;


import com.sxw.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public final class AssertUtils {

    public static final Charset UTF8 = StandardCharsets.UTF_8;
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /** 
     * 本机的 cpu 核心数
     */
    public static final int CPU_NUM = Runtime.getRuntime().availableProcessors();

    public static final String EMPTY = "";
    public static final String SPACE = " ";

    /** 
     * 对象为 null, 或者其字符串形态为 空白符, "null" 时返回 true
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        return obj == null || StringUtils.isBlank(obj.toString()) || "null".equalsIgnoreCase(obj.toString().trim());
    }
    /** 
     * 对象非空时返回 true
     * @param obj
     * @return
     */
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    /** 
     * 对象为 null、空白符、"null" 字符串时, 则抛出异常
     * @param obj
     * @param msg
     */
    public static void assertNil(Object obj, String msg) {
        assertException(isBlank(obj), msg);
    }
    
    /** 
     * 对象为 null、空白符、"null" 字符串时, 则抛出异常
     * @param obj
     * @param errorCode
     * @param errorMsg
     */
    public static void assertNil(Object obj, String errorCode,String errorMsg) {
        assertException(isBlank(obj), errorCode,errorMsg);
    }

    /** 
     * 条件为 true 则抛出业务异常
     * @param flag 判断条件
     * @param msg 错误消息
     */
    public static void assertException(boolean flag, String msg) {
        if (flag) {throw new ServiceException(msg);}
    }
    
    /** 
     * 抛出业务异常
     * @param errorCode 错误吗
     * @param errorMsg 错误消息
     * @throws RuntimeException
     */
    public static void assertException(String errorCode, String errorMsg) throws RuntimeException {
        throw new ServiceException(errorCode,errorMsg);
    }
    
    /** 
     * 抛出业务异常
     */
    public static void assertException(boolean flag, RuntimeException e) throws RuntimeException {
        if(flag) { throw e; }
    }
    
    /** 
     * 条件为 true 则抛出业务异常
     * @param flag 判断条件
     * @param errorCode 错误吗
     * @param errorMsg 错误消息
     */
    public static void assertException(boolean flag,String errorCode, String errorMsg){
        if (flag) {throw new ServiceException(errorCode,errorMsg);}
    }

    /** 
     * 条件为 true 则抛出业务异常
     * @param flag 判断条件
     * @param errorCode 错误吗
     * @param errorMsg 错误消息
     * @param t 抛出的异常类型
     */
    public static <T extends RuntimeException> void assertException(boolean flag,String errorCode, String errorMsg,Class<T> t){
        if (flag) {
            assertException(errorCode,errorMsg,t);
        }
    }
    
    public static <T extends RuntimeException> void assertException(String errorCode, String errorMsg,Class<T> t){
        T e = null;
        try {
            Constructor<T> c = t.getConstructor(String.class,String.class);//获取有参构造
            c.setAccessible(true);
            e = c.newInstance(errorCode,errorMsg);
        } catch (Exception e1) {
            log.error("AssertUtils.assertException异常",e);
            throw new ServiceException("1008","系统内部错误");
        }
        throw e;
    }

}
