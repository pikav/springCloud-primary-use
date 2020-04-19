package com.pikav.mainpage.client;

import com.pikav.mainpage.CourseInfo;
import com.pikav.mainpage.CourseInfosRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *  通过 feign 访问课程微服务
 *
 * */
@FeignClient(value = "eureka-client-mainpage-course",  // 要调用的服务名（通过http协议, 调用目标服务的controller）
    fallback = CourseClientHystrix.class)
public interface CourseClient {

    final String SERVICE_CONTEXT = "/mainpage-course";

    @RequestMapping(value = SERVICE_CONTEXT + "/get/course",
            method = RequestMethod.GET)
    CourseInfo getCourseInfo(Long id);

    @RequestMapping(value = SERVICE_CONTEXT + "/get/courses",
            method = RequestMethod.POST)
    List<CourseInfo> getCourseInfos(@RequestBody CourseInfosRequest request);

}
