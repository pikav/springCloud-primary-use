package com.pikav.mainpage.service.impl;

import com.pikav.mainpage.CourseInfo;
import com.pikav.mainpage.CourseInfosRequest;
import com.pikav.mainpage.dao.MainpageCourseDao;
import com.pikav.mainpage.entity.MainpageCourse;
import com.pikav.mainpage.service.ICourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourseServiceImpl implements ICourseService {

    private final MainpageCourseDao mainpageCourseDao;

    @Autowired
    public CourseServiceImpl(MainpageCourseDao mainpageCourseDao) {
        this.mainpageCourseDao = mainpageCourseDao;
    }

    @Override
    public CourseInfo getCourseInfo(Long id) {

        Optional<MainpageCourse> course = mainpageCourseDao.findById(id);
        return buildCourseInfo(course.orElse(MainpageCourse.invalid()));
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {

        if(CollectionUtils.isEmpty(request.getIds())) {
            return Collections.emptyList();
        }
        List<MainpageCourse> courses = mainpageCourseDao.findAllById(request.getIds());
        if(CollectionUtils.isEmpty(courses)) {
            return Collections.emptyList();
        }
        // 使用 lamada 将泛型 MainpageCourse 转化为 CourseInfo
        return courses.stream()
                .map(this::buildCourseInfo)
                .collect(Collectors.toList());
    }

    /**
     *  根据数据记录构造对象信息
     * */
    private CourseInfo buildCourseInfo(MainpageCourse course) {

        return CourseInfo.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType() == 0
                        ? "免费课程" : "实战课程")
                .courseIcon(course.getCourseIcon() )
                .courseIntro(course.getCourseIntro())
                .build();
    }
}
