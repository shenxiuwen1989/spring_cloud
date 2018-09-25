package com.sxw.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class HellowService {

    private static final Logger logger = LoggerFactory.getLogger(HellowService.class);
    //通过注入RestTemplate对象
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String sayHello(){
        return restTemplate.getForEntity("http://SERVICE-PROVIDER/test/hello",String.class).getBody();
    }

    //测试回调
    public String helloFallback(){
        return "error。。。";
    }

    //测试延时回调
    @HystrixCommand(fallbackMethod = "testDelayFallback")
    public String testDelay(){

        //延迟
        int sleepTime = new Random().nextInt(30000);
        logger.info("sleepTime:"+sleepTime);
        try{
            Thread.sleep(sleepTime);
        }catch (Exception e){
            logger.error("睡眠异常");
        }

        //远程调用
        return "xxx";
    }

    //测试回调
    public String testDelayFallback(){
        return "error。。。helloFallback";
    }
}
