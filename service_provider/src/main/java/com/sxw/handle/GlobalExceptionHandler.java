/*
 * @Copyright:   Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Date:        2018年7月14日 下午5:07:46
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: Initialize
 */
package com.sxw.handle;


import com.sxw.exception.BusinessException;
import com.sxw.exception.ValidatorException;
import com.sxw.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @RestControllerAdvice 和  @ExceptionHandler 同时配合使用能实现异常常统一管理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     * @param e
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> serviceException(BusinessException e, HttpServletResponse response) throws IOException {
        log.error("业务异常", e);
        return Result.result(e.getError_code(), e.getError_msg(),null);
    }



    
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidHandler(HttpServletRequest request, HttpServletResponse response,
                                                                     MethodArgumentNotValidException exception) throws Exception {
        Map<String, String> result = new HashMap<>();
        result.put("retMsg", "参数校验不通过。");
        
        String msg = "参数校验不通过。";
        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult!= null && bindingResult.hasErrors()) {
            msg = bindingResult.getFieldError().getField() + bindingResult.getFieldError().getDefaultMessage();
        }
        Result failure = Result.result("9997",msg,null);
        return failure;
    }


    
}
