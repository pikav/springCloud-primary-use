package com.pikav.mainpage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  测试用例启动入口
 *
 * */

@EnableFeignClients(basePackages = {"com.pikav.mainpage"})
@SpringBootApplication
public class Application {



}
