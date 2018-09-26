package com.sxw.controller;


import com.sxw.service.HellowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/test")
public class HellowController {

    private final Logger logger = LoggerFactory.getLogger(HellowController.class);

    @Autowired
    private HellowService hellowService;

    @Value("${name}")
    private String name;
    @Value("${age}")
    private String age;
    @Value("${version}")
    private String version="开发环境";

    @RequestMapping(value = "/callHello",method = RequestMethod.GET)
    public String sayHellow(){
        logger.info("测试是否从config配置中心取值用户【{}】年龄【{}】环境【{}】",name,age,version);
        return hellowService.sayHello();
    }

    @RequestMapping(value = "/testDelay",method = RequestMethod.GET)
    public String testDelay(){
        return hellowService.testDelay();
    }
}
