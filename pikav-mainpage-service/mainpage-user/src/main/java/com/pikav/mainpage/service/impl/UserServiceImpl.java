package com.pikav.mainpage.service.impl;

import com.pikav.mainpage.CourseInfo;
import com.pikav.mainpage.CourseInfosRequest;
import com.pikav.mainpage.UserInfo;
import com.pikav.mainpage.client.CourseClient;
import com.pikav.mainpage.dao.MainpageUserCourseDao;
import com.pikav.mainpage.dao.MainpageUserDao;
import com.pikav.mainpage.entity.MainpageUser;
import com.pikav.mainpage.entity.MainpageUserCours;
import com.pikav.mainpage.service.IUserService;
import com.pikav.mainpage.vo.CreateUserRequest;
import com.pikav.mainpage.vo.UserCourseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.applet.Main;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private final MainpageUserDao mainpageUserDao;

    @Autowired
    private final MainpageUserCourseDao mainpageUserCourseDao;

    @Autowired
    private final CourseClient courseClient;

    public UserServiceImpl(MainpageUserDao mainpageUserDao, MainpageUserCourseDao mainpageUserCourseDao, CourseClient courseClient) {
        this.mainpageUserDao = mainpageUserDao;
        this.mainpageUserCourseDao = mainpageUserCourseDao;
        this.courseClient = courseClient;
    }

    @Override
    public UserInfo createUser(CreateUserRequest request) {

        if (!request.validate()) {
            return UserInfo.invalid();
        }
        MainpageUser oldUser = mainpageUserDao.findByUsername(request.getUsername());

        if(null != oldUser) {
            return UserInfo.invalid();
        }

        MainpageUser newUser = mainpageUserDao.save(new MainpageUser(request.getUsername(),request.getEmail()));

        return new UserInfo(newUser.getId(),newUser.getUsername(),newUser.getEmail());
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        Optional<MainpageUser> user = mainpageUserDao.findById(id);
        if(!user.isPresent()) {
            return UserInfo.invalid();
        }
        MainpageUser curUser = user.get();

        return new UserInfo(curUser.getId(), curUser.getUsername(), curUser.getEmail());

    }

    @Override
    public UserCourseInfo getUserCourseInfo(Long id) {
        Optional<MainpageUser> user = mainpageUserDao.findById(id);
        if(!user.isPresent()) {
            return UserCourseInfo.invalid();
        }

        MainpageUser mainpageUser = user.get();
        UserInfo userInfo = new UserInfo(mainpageUser.getId(), mainpageUser.getUsername(), mainpageUser.getEmail());

        List<MainpageUserCours> userCours = mainpageUserCourseDao.findAllByUserId(id);

        if (CollectionUtils.isEmpty(userCours)) {
            return new UserCourseInfo(userInfo, Collections.emptyList());
        }

        List<CourseInfo> courseInfos = courseClient.getCourseInfos(new CourseInfosRequest(
                userCours.stream()
                        .map(MainpageUserCours::getCourseId)
                        .collect(Collectors.toList())
        ));

        return new UserCourseInfo(userInfo, courseInfos);
    }

}
