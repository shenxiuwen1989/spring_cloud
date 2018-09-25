package com.sxw.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
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
}
