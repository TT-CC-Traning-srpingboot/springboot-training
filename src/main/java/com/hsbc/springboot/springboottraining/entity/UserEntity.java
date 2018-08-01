package com.hsbc.springboot.springboottraining.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 用户表
 * @author Leo
 * @version 1.0.0
 */
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /** STAFFID 登录使用 */
    private String staffId;

    /** 用户姓名 */
    private String userName;

    /** 密码 */
    private String passWord;

}
