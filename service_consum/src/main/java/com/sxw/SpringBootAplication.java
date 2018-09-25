package com.sxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableCircuitBreaker   //开启断路功能
@EnableDiscoveryClient //让应用注册为eruraka客户端应用
@EnableHystrixDashboard //开启仪表盘
public class SpringBootAplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAplication.class,args);
    }



}
