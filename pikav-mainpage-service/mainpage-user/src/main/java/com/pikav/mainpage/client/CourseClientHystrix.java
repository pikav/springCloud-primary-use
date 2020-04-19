package com.pikav.mainpage.client;

import com.pikav.mainpage.CourseInfo;
import com.pikav.mainpage.CourseInfosRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 *  熔断降级策略
 *
 * */
@Component
@Slf4j
public class CourseClientHystrix implements CourseClient{

    @Override
    public CourseInfo getCourseInfo(Long id) {
        log.error("getCourseInfo() 出现熔断");
        return CourseInfo.invalid();
    }

    @Override
    public List<CourseInfo> getCourseInfos(CourseInfosRequest request) {
        log.error("getCourseInfos() 出现熔断");
        return Collections.emptyList();
    }

}
