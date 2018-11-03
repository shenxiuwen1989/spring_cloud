package com.sxw.service; /*
 * @(#)BaseService.java 1.0 2018/10/30
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018/10/30
 * @Author:      
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */

import com.sxw.api.OpenApiFeignClient;
import com.sxw.entry.ErpResQueryLogisticsTakeTime;
import com.sxw.entry.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseService {
    @Autowired
    protected OpenApiFeignClient openApiFeignClient;

    public ReturnObject<List<ErpResQueryLogisticsTakeTime>> queryLogisticsTakeTime(Map<String, Object> requestParameter){
        return openApiFeignClient.queryLogisticsTakeTime(requestParameter);
    }

}
