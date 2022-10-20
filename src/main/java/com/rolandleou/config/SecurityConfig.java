package com.rolandleou.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rolandleou.service.StockUserService;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
		.antMatchers("/user/testBlock").authenticated()
		.antMatchers("/user/testUnblock").permitAll()    
		.antMatchers("/user/create").hasAuthority("ADMIN") //管理員可以新增使用者資料
		.antMatchers("/user/search/**").permitAll() //大家都可以查詢資料
		.and()
		.csrf().disable(); 
    }
    
    //加密器注入容器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
