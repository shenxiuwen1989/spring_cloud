package com.sxw.entry; /*
 * @(#)ErpResFollowFlyAgent.java 1.0 2018/10/24
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

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ErpResFollowFlyAgent implements Serializable {
    private static final long serialVersionUID = -7223754989756582114L;
    /**
     * 代理单号
     */
    private String agentNumber;
    /**
     * 起落时间
     */
    private String flightTime;
    /**
     * 起飞时间
     */
    private String flightTakeoffTime;
    /**
     * 落地时间
     */
    private String	flightLandingTime;
    /**
     * 改配航班号
     */
    private String changeFlightNumber;
    /**
     * 改配航班日期
     */
    private String changeFlightDate;
    /**
     * 改配起飞时间
     */
    private String changeFlightTakeoffTime;
    /**
     * 改配落地时间
     */
    private String changeFlightLandingTime;
    /**
     * 提货日期
     */
    private String ladingDate;
    /**
     * 运输模式（10空；20直飞；30陆运；40陆对空；50空对空）(tms_ff_agent_transportMode)
     */
    private String transportMode;
    /**
     * 航班号
     */
    private  String flightNumber;
    /**
     * 航班日期
     */
    private String flightDate;

    /**
     *费率
     */
    private Double rate;
    /**
     * 提货费率
     */
    private Double ladingRate;
}
