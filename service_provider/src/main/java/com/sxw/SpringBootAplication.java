package com.sxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //不管是消费者还是提供者，对应eureka server来说都是客户端client
@EnableDiscoveryClient //启用了服务发现的功能，只要 Eureka Client 启动了，就能被 Eureka Server 所感知。
public class SpringBootAplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAplication.class,args);
    }
}
