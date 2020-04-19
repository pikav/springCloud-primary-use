package com.pikav.mainpage.dao;

import com.pikav.mainpage.entity.MainpageUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  mainpageUser 数据表访问接口定义
 *
 * */
public interface MainpageUserDao extends JpaRepository<MainpageUser, Long> {

    // 通过用户名寻找数据记录
    MainpageUser findByUsername(String username);



}
