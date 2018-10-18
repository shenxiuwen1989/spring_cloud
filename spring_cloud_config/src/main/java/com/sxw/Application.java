package com.sxw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.ConfigServerApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableConfigServer //开启spring cloud config服务端支持
@SpringBootApplication
@EnableDiscoveryClient //启用了服务发现的功能，只要 Eureka Client 启动了，就能被 Eureka Server 所感知。
@EnableEurekaClient //不管是消费者还是提供者，对应eureka server来说都是客户端client
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
