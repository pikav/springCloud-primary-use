package com.pikav.mainpage.dao;

import com.pikav.mainpage.entity.MainpageUser;
import com.pikav.mainpage.entity.MainpageUserCours;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *  mainpageUser 数据表访问接口定义
 *
 * */
public interface MainpageUserCourseDao extends JpaRepository<MainpageUserCours, Long> {

    // 通过用户id 寻找数据记录
    List<MainpageUserCours> findAllByUserId(Long userId);

}
