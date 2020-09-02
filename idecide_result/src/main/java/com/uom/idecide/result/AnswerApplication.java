package com.uom.idecide.result;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient      //开启服务发现，可以发现别的服务
@EnableFeignClients         //启用Feign，即远程调用功能
public class AnswerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnswerApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
