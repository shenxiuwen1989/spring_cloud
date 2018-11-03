/*
 * 熔断处理类
 * @Copyright:   Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Date:        2018年8月1日 上午10:24:51
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: Initialize
 */
package com.sxw.api;


import com.sxw.entry.ErpResQueryLogisticsTakeTime;
import com.sxw.entry.ReturnObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OpenApiFeignClientBreaker implements OpenApiFeignClient{

    @Override
    public ReturnObject<List<ErpResQueryLogisticsTakeTime>> queryLogisticsTakeTime(Map<String, Object> requestParameter) {
        return ReturnObject.failbackFailure();
    }
}

