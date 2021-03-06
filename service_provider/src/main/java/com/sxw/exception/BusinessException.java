package com.sxw.exception;

public class BusinessException extends Exception{

    private static final long serialVersionUID = -8287282996306795257L;
    private String error_code ;  //异常对应的返回码
    private String error_msg;  //异常对应的描述信息

    public BusinessException() {
        super();
    }

    public BusinessException(String error_msg) {
        super(error_msg);
        this.error_msg = error_msg;
    }

    public BusinessException(String error_code, String error_msg) {
        super();
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public String getError_code() {
        return error_code;
    }

    public String getError_msg() {
        return error_msg;
    }


}
