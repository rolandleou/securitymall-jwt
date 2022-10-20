package com.rolandleou.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.rolandleou.model.User;
import com.rolandleou.rowmapper.UserRowMapper;

@Repository
public class UserRepository {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public Integer add(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource sqlParameterSource=new BeanPropertySqlParameterSource(user);
		String sql = " INSERT INTO securityjwtmall.users ( ACCOUNT, NAME, PASSWORD ) "
				   + " VALUE ( :account, :name, :password ) " ;
		namedParameterJdbcTemplate.update(sql, sqlParameterSource,keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public User getDataByAccount(String account) {
		String sql = "SELECT ID, ACCOUNT, NAME, PASSWORD, AUTHORITY "
				+ "FROM securityjwtmall.users WHERE ACCOUNT = :account OR NAME = :account";
		
		Map<String, Object> map = new HashMap<>();
		map.put("account", account);
        
		List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
		
		if (userList.size() > 0) {
			return userList.get(0);
		} else {
			return null;			
		}
	}
}