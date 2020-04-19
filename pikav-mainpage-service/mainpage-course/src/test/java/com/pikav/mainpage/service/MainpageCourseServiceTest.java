package com.pikav.mainpage.service;

import com.alibaba.fastjson.JSON;
import com.pikav.mainpage.Application;
import com.pikav.mainpage.CourseInfosRequest;
import com.pikav.mainpage.dao.MainpageCourseDao;
import com.pikav.mainpage.entity.MainpageCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 *  课程服务测试
 *
 * */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.NONE)  // 标注没有 web环境
public class MainpageCourseServiceTest {

    @Autowired
    private MainpageCourseDao courseDao;

    @Autowired
    private ICourseService courseService;

    @Test
    public void testCreateCourseInfo() {
        MainpageCourse course1 = new MainpageCourse(
                "从删库到跑路",
                0,
                "https://www.pikav.com",
                "删库不要慌, 跑路就完事了"
        );
        MainpageCourse course2 = new MainpageCourse(
                "从入门到放弃",
                1,
                "https://www.pikav2.com",
                "想入门? 没门儿"
        );
        System.out.println(courseDao.saveAll(
                Arrays.asList(course1,course2))
                .size());

    }

    @Test
    public void testGetCourseInfo() {
        System.out.println(JSON.toJSONString(courseService.getCourseInfo(6L)));
        System.out.println(JSON.toJSONString(courseService.getCourseInfo(7L)));
    }

    @Test
    public void testGetCourseInfos() {
        System.out.println(JSON.toJSONString(
                courseService.getCourseInfos(
                        new CourseInfosRequest(Arrays.asList(6L, 7L)))
        ));
    }

}
