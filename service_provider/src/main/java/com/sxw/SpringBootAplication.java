package com.sxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //不管是消费者还是提供者，对应eureka server来说都是客户端client
@EnableDiscoveryClient
public class SpringBootAplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAplication.class,args);
    }
}
