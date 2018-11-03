/*
 * @Copyright:   Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Date:        2018年7月14日 下午5:46:54
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: Initialize
 */
package com.sxw.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -2561995767220667941L;

    /**
     * 请求结果返回码
     */
    private String errCode;

    /**
     * 备注信息 如请求失败原因等
     */
    private String errMsg;

    public ServiceException() {
        super("业务异常");
    }

    public ServiceException(String errMsg) {
        super(errMsg);
    }

    public ServiceException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
