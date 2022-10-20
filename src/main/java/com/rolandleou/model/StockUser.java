package com.rolandleou.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	
	private User user;


	public StockUser(User user) {
		this.user=user;
	}
	

	//取得此帳號的權限
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authority_list = new ArrayList<SimpleGrantedAuthority>();
		authority_list.add(new SimpleGrantedAuthority(user.getAuthority()));
		return authority_list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getAccount();
	}

	//驗證此帳號是否未過期，目前沒有要用到先設return true
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//驗證此帳號是否未被封鎖，目前沒有要用到先設return true
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//驗證此帳號憑證是否未過期，目前沒有要用到先設return true
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//驗證此帳號是否可以使用，目前沒有要用到先設return true
	@Override
	public boolean isEnabled() {
		return true;
	}

}
