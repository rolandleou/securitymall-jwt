package com.rolandleou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	private Integer id;
	
	private String account;
	
	private String name;
	
	private String password;
	
	private String authority;

  //需加入無參數的建構子，不然BeanRowMapper無法初始化
	public User(){
	
	}
	
	
	public User(String account,String name,String password,String authority) {
		this.account=account;
		this.name=name;
		this.password=password;
		this.authority=authority;
	}

}