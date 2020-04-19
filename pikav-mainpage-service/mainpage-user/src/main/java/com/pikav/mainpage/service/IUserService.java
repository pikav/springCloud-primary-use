package com.pikav.mainpage.service;

import com.pikav.mainpage.UserInfo;
import com.pikav.mainpage.vo.CreateUserRequest;
import com.pikav.mainpage.vo.UserCourseInfo;

/**
 *  用户相关服务接口定义
 *
 * */
public interface IUserService {

    // 创建用户
    UserInfo createUser(CreateUserRequest request);

    // 根据 id 获取用户信息
    UserInfo getUserInfo(Long id);

    // 获取用户和课程信息
    UserCourseInfo getUserCourseInfo(Long id);

}
