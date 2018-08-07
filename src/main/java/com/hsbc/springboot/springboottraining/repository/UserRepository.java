package com.hsbc.springboot.springboottraining.repository;

import com.hsbc.springboot.springboottraining.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
* @Author Leo
* @Description 用户管理
* @Date 23:12 2018/8/1
**/
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    /**
    * @Author Leo
    * @Description 通过StaffId和passWord查询用户
    * @Date 23:14 2018/8/1
    * @return UserEntity
    **/
    UserEntity findByStaffIdAndAndPassWord(String staffId,String psw);

    /**
     * @Author Lucy
     * @Description 根据用户ID查询用户信息
     * @Date 23:12 2018/8/5
     *  @return UserEntity
     **/
    UserEntity findByUserId(long id);

    /**
     * @Author Lucy
     * @Description 根据用户名称查询用户信息
     * @Date 23:12 2018/8/5
     *  @return UserEntity
     **/
    UserEntity findByUserName(String userName);

}
