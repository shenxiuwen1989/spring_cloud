package com.sxw.entry;
/*
 * @(#)ErpResFollowFlyWaybill.java 1.0 2018/10/24
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
public class ErpResFollowFlyWaybill implements Serializable {

    private static final long serialVersionUID = -5051325710338974224L;
    /**
     * 实配件数
     */
    private int stowageCount;
    /**
     * 提货件数
     */
    private int ladingCount;
    /**
     * 提货完成时间
     */
    private String ladingFinishDate;
    /**
     * 运单号
     */
    private String waybillNumber;

    /**
     * 分摊运费
     */
    private double shareFlyFreight;
}
