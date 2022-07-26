package com.han.delivery.config.auth;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String nickName;
	private int point;
	private String phone;
	private String rating;
	private String role;
	
//	Property [point] not found on type 자바 컴파일시 에러 부분으로 인하여 DB와 동일하게 셋팅
	
	//계정이 가지고 있는 권한을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.role));
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	
	//계정이 만료되었는지를 리턴  true : 만료되지 않음
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 잠겨있는지를 리턴
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//패스워드가 만료되었는지를 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정이 사용가능한지를 리턴
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}