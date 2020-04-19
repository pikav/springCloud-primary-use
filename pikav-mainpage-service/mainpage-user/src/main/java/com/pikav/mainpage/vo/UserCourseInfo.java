package com.pikav.mainpage.vo;

import com.pikav.mainpage.CourseInfo;
import com.pikav.mainpage.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 *  用户课程信息对象定义
 *
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseInfo {

    private UserInfo userInfo;

    private List<CourseInfo> courseInfos;

    public static UserCourseInfo invalid() {
        return new UserCourseInfo(UserInfo.invalid(),Collections.emptyList());
    }

}
