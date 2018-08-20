package com.hsbc.springboot.springboottraining.service;

import com.hsbc.springboot.springboottraining.entity.UserEntity;
/**
 * @Author Lucy
 * @Description 用户管理
 * @Date 23:12 2018/8/5
 **/
public interface UserService {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity findByUserName(String userName);

    UserEntity findByUserId(long userId);


}
