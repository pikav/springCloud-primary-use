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
 *  表对应的实体类定义
 *
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "mainpage_user")
public class MainpageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增主键
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

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

    public MainpageUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
