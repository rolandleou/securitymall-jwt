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
	

	//���o���b�����v��
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

	//���Ҧ��b���O�_���L���A�ثe�S���n�Ψ���]return true
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//���Ҧ��b���O�_���Q����A�ثe�S���n�Ψ���]return true
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//���Ҧ��b�����ҬO�_���L���A�ثe�S���n�Ψ���]return true
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//���Ҧ��b���O�_�i�H�ϥΡA�ثe�S���n�Ψ���]return true
	@Override
	public boolean isEnabled() {
		return true;
	}

}
