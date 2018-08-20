package com.hsbc.springboot.springboottraining.service.impl;

import com.hsbc.springboot.springboottraining.entity.UserEntity;
import com.hsbc.springboot.springboottraining.repository.UserRepository;
import com.hsbc.springboot.springboottraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Lucy
 * @Description 用户管理
 * @Date 23:12 2018/8/5
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    /**
     * @Author Rarer
     * @Description 增加用户
     * @param user
     */
    @Override
    public UserEntity saveUser(UserEntity user){
        return  userRepository.save(user);
    }

    @Override
    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserEntity findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }
}
