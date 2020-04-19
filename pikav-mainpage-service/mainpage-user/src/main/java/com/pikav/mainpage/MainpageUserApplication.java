package com.pikav.mainpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

/**
 *  用户服务启动入口
 *
 * */

@EnableJpaAuditing
@EnableFeignClients    // 通过feign的 声明式调用服务
@EnableCircuitBreaker  // 通过hystrix 实现服务熔断、容错（ 熔断： 当某个服务不可用、或者响应慢、则使该服务降级，快速返回一个错误信息，保证其他微服务运行正常）
@EnableEurekaClient
@SpringBootApplication
public class MainpageUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainpageUserApplication.class, args);
    }

}
