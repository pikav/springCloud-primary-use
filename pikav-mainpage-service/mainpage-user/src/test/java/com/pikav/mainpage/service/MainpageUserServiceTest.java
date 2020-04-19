package com.pikav.mainpage.service;

import com.alibaba.fastjson.JSON;
import com.pikav.mainpage.Application;
import com.pikav.mainpage.dao.MainpageUserCourseDao;
import com.pikav.mainpage.entity.MainpageUserCours;
import com.pikav.mainpage.vo.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

/**
 *  用户服务测试用例
 *
 * */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MainpageUserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private MainpageUserCourseDao userCourseDao;

    @Test
    //@Transactional
    public void testCreateUser() {
        System.out.println(JSON.toJSONString(
                userService.createUser(new CreateUserRequest(
                        "pikav","1966172031@qq.com"
                ))
        ));
    }

    @Test
    public void testGetUserInfo() {
        System.out.println(JSON.toJSONString(
                userService.getUserInfo(7L)
        ));
    }

    @Test
    public void testCreateMainpageUserCourse() {

        MainpageUserCours cours1 = new MainpageUserCours();
        cours1.setUserId(7L);
        cours1.setCourseId(6L);

        MainpageUserCours cours2 = new MainpageUserCours();
        cours2.setUserId(7L);
        cours2.setCourseId(7L);

        System.out.println(userCourseDao.saveAll(
                Arrays.asList(cours1,cours2)
        ).size());

    }

}
