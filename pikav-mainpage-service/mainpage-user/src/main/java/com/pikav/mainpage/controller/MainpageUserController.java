package com.pikav.mainpage.controller;

import com.alibaba.fastjson.JSON;
import com.pikav.mainpage.UserInfo;
import com.pikav.mainpage.service.IUserService;
import com.pikav.mainpage.vo.CreateUserRequest;
import com.pikav.mainpage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  用户服务对外接口
 *
 * */
@Slf4j
@RestController
public class MainpageUserController {

    @Autowired
    private final IUserService userService;

    public MainpageUserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public UserInfo createUser(@RequestBody CreateUserRequest request) {
        log.info("<mainpage-user>: create user -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }

    @GetMapping("/get/user")
    public UserInfo getUserInfo(Long id) {
        log.info("mainpage-user: get user -> {}", id);
        return userService.getUserInfo(id);
    }

    @GetMapping("/get/user/course")
    public UserCourseInfo getUserCourseInfo(Long id) {
        log.info("<mainpage-user>: get user course info -> {}", id);
        return userService.getUserCourseInfo(id);
    }

}
