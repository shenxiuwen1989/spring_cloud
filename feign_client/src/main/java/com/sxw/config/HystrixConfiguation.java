/*
 * @(#)HystrixConfiguation.java 1.0 2018年8月1日
 * @Copyright:  Copyright © 2007-2018 ky-express.com.All Rights Reserved.
 * @Description: 
 * 
 * @Modification History:
 * @Date:        2018年8月1日
 * @Author:      lucius.lv
 * @Version:     1.0.0.0
 * @Description: (Initialize)
 * @Reviewer:    
 * @Review Date: 
 */
package com.sxw.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;

@Configuration
public class HystrixConfiguation {
    
    @Bean
    public ServletRegistrationBean<Servlet> getServlet(){
       HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
       ServletRegistrationBean<Servlet> registrationBean = new ServletRegistrationBean<Servlet>(streamServlet);
       registrationBean.setLoadOnStartup(1);
       registrationBean.addUrlMappings("/actuator/hystrix.stream");
       registrationBean.setName("HystrixMetricsStreamServlet");
       return registrationBean;
    }

}

