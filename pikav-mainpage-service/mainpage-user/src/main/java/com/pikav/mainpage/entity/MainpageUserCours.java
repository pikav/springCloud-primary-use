package com.pikav.mainpage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "mainpage_user_course")
public class MainpageUserCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Basic
    @Column(name = "course_id", nullable = false)
    private Long courseId;

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
}
