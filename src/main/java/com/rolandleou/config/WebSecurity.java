package com.rolandleou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
		.antMatchers("/user/testBlock").authenticated()
		.antMatchers("/user/testUnblock").permitAll()    
		.antMatchers("/user/create").permitAll()
		.and()
		.csrf().disable(); 
    }
    
    //加密器注入容器
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
