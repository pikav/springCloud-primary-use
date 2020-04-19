package com.pikav.mainpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing   // 自动更新  更新时间，创建时间
@EnableEurekaClient
@SpringBootApplication
public class MainpageCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainpageCourseApplication.class, args);
    }

}
