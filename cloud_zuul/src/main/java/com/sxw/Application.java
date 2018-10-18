package com.sxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
@EnableZuulProxy //开启zuul的API网关功能
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
