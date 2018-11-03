/*
 * @(#)OpenApi.java 1.0 2018年8月4日
 * @Copyright: Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: Modification History:
 * @Date:        2018年8月4日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:
 * @Review Date:
 */
package com.sxw.api;


import com.sxw.entry.ErpResQueryLogisticsTakeTime;
import com.sxw.entry.ReturnObject;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;

@FeignClient(value = "openApiFeignClient", url = "${open.platform.url}", fallback = OpenApiFeignClientBreaker.class)
public interface OpenApiFeignClient {

    @Headers({"method: tms.followfly.cloudWarehouse.listEffectiveInfo"})
    @RequestLine("POST /router/rest")
    ReturnObject<List<ErpResQueryLogisticsTakeTime>> queryLogisticsTakeTime(Map<String, Object> requestParameter);


}

