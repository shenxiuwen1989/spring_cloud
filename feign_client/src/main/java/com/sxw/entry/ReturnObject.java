/*
 * @(#)ReturnObject.java 1.0 2018年7月16日
 * @Copyright: Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: Modification History:
 * @Date:        2018年7月16日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:
 * @Review Date:
 */
package com.sxw.entry;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
public class ReturnObject<T> {

    private Integer code;
    private String msg;
    private T data;
    private Boolean success;

    public boolean hasData() {
        return this != null && this.data != null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static <T> ReturnObject<T> failbackFailure(){
        ReturnObject<T> r = new ReturnObject<T>();
        r.setCode(-1);
        r.setMsg("调用开放平台进入熔断器");
        r.setSuccess(false);
        return r;
    }

}

