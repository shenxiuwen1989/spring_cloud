package com.sxw.controller;


import com.sxw.entry.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api-a")
public class HellowController {

    private final Logger logger = LoggerFactory.getLogger(HellowController.class);

    //通过注入DiscoveryClient对象，在日志中打印服务的相关内容；注意引入的包路径
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHellow(){
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hollo,host:{};service_id:{}",instance.getHost(),instance.getServiceId());
        return "hello world";
    }

    /**
     *  验证javax.validation校验框架
     *  必须加上注解@Valid才能生效;必须使用@Valid标注我们需要校验的参数student，否则Spring不会对它进行校验。
     */
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    public String updateStudent(@Valid @RequestBody Student student){
        return  "success";
    }
}
