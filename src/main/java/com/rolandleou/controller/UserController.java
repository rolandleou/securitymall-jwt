package com.rolandleou.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rolandleou.model.APIReturnObject;
import com.rolandleou.model.User;
import com.rolandleou.service.StockUserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private StockUserService stockUserService;
	
	@GetMapping("testBlock")
	public String testBlock() {
		return "testBlock";
	}
	
	@GetMapping("testUnBlock")
	public String testUnblock() {
		return "testUnblock";
	}
	
	@PostMapping("create")
	public APIReturnObject create(@RequestBody User user) {
	APIReturnObject result = new APIReturnObject();
	Map<String, Object> data = new HashMap<String, Object>();
	Integer user_id = stockUserService.addUser(user);
	data.put("user_id", user_id);
	result.setMessage("用戶資料新增成功");
	result.setData(data);
	return result;
	}
}
