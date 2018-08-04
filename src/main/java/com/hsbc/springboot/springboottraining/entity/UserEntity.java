package com.hsbc.springboot.springboottraining.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * 用户表
 * @author Leo
 * @version 1.0.0
 */
@Entity
@Data
@Table(name = "t_user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    /** STAFFID 登录使用 */
    private String staffId;

    /** 用户姓名 */
    private String userName;

    /** 密码 */
    private String passWord;

}
