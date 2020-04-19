package com.pikav.mainpage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

/**
 *  实体映射表
 *
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)   // 插入数据时能主动填充更新时间、创建时间
@Table(name = "mainpage_course")
public class MainpageCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic     //标注为一列
    @Column(name = "course_name", nullable = false)
    private String courseName;

    // 课程类型： 0免费课  1实战课
    @Basic     //标注为一列
    @Column(name = "course_type", nullable = false)
    private int courseType;

    // 课程图标
    @Basic     //标注为一列
    @Column(name = "course_icon", nullable = false)
    private String courseIcon;

    // 课程介绍
    @Basic     //标注为一列
    @Column(name = "course_intro", nullable = false)
    private String courseIntro;

    // 创建时间
    @Basic     //标注为一列
    @Column(name = "create_time", nullable = false)
    @CreatedDate
    private Date createTime;

    // 更新时间
    @Basic     //标注为一列
    @Column(name = "update_time", nullable = false)
    @LastModifiedDate
    private Date updateTime;

    public MainpageCourse(String courseName, int courseType, String courseIcon, String courseIntro) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.courseIcon = courseIcon;
        this.courseIntro = courseIntro;
    }

    // 返回一个无效的课程
    public static MainpageCourse invalid() {
        MainpageCourse invalid = new MainpageCourse("",0,"","");
        invalid.setId(-1L);
        return invalid;
    }

}
