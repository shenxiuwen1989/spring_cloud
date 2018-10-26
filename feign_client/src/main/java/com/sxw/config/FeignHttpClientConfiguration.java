/*
 * @(#)ApplicationSignature.java 1.0 2018年7月16日
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018年7月16日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
package com.sxw.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="feign.httpclient.config")
public class FeignHttpClientConfiguration {

    private int socketTimeout = 10000;
    private int connectTimeout = 6000;
    
    private int maxTotal = 5000;
    private int maxPerRoute = 100;
    private int validateAfterInactivity = 1000;
    
    private int retryTimes = 3;
    
    private boolean requestSentRetryEnabled = true;
    
    private int connectionTimerRepeat = 3000;
    
}

