package com.pikav.mainpage.dao;

import com.pikav.mainpage.entity.MainpageCourse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * jpa 已经定义了模板的curd
 *
 * */
public interface MainpageCourseDao extends JpaRepository<MainpageCourse, Long> {

}
