package com.sxw.entry;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
/*
 * @(#)ErpResQueryLogisticsTakeTime.java 1.0 2018/10/24
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018/10/24
 * @Author:      
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@ToString
public class ErpResQueryLogisticsTakeTime implements Serializable{

    private static final long serialVersionUID = -662982313460661229L;
    
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 代理对象
     */
    private ErpResFollowFlyAgent followFlyAgent;
    /**
     * 跟飞运单列表信息
     */
    private List<ErpResFollowFlyWaybill>followFlyWaybills;

}
