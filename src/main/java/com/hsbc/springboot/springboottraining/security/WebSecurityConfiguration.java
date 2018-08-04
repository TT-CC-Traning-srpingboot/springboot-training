package com.hsbc.springboot.springboottraining.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
* @Author Leo
* @Description security配置类
* @Date 23:53 2018/8/4
**/
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 授权
        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and().
                formLogin().usernameParameter("staffId") // 用户名参数
                .passwordParameter("passWord") // 密码参数
                .loginProcessingUrl("/loginAction") // 登录 Action 的 URI
                .loginPage("/login") // 登录页面 URI
                .failureForwardUrl("/error") // 登录失败后的页面URI
                .permitAll()
                .and().logout().permitAll();

    }

}
