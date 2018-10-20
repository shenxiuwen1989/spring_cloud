package com.sxw.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> result(String errCode,String errMsg,T data) {
        Result<T> result = new Result<T>();
        result.setCode(errCode);
        result.setMsg(errMsg);
        result.setData(data);
        return result;
    }

}
