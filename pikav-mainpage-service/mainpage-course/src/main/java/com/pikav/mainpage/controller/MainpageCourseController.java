package com.pikav.mainpage.controller;

import com.alibaba.fastjson.JSON;
import com.pikav.mainpage.CourseInfo;
import com.pikav.mainpage.CourseInfosRequest;
import com.pikav.mainpage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  对外服务接口
 *
 * */

@Slf4j
@RestController
public class MainpageCourseController {

    // 课程服务接口
    private final ICourseService courseService;

    @Autowired
    public MainpageCourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    /**
     *  若不通过网关调用： 127.0.0.1：7001/mainpage-course/get/course?id=
     *  通过网关调用：     127.0.0.1：9000/pikav/mainpage-course/get/course?id=
     *  (先进入网关服务, 然后调用相应服务)
     * */
    @GetMapping("/get/course")
    public CourseInfo getCourseInfo(Long id) {
        log.info("<homepage-course>: get course -> {}", id);
        return courseService.getCourseInfo(id);
    }

    @PostMapping("/get/courses")
    public List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request) {
        log.info("<homepage-course> : get courses - > {}", JSON.toJSONString(request));
        return courseService.getCourseInfos(request);
    }

}
