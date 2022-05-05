package com.coach.application.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coach.application.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Long userID;
	private String userName;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private String role;

	public UserDetailsImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetailsImpl(Long userID, String userName, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.authorities = authorities;
	}
	
//	public static UserDetailsImpl build (UserEntity user)
//	{
//		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
//		return new UserDetailsImpl(user.getUserID(),user.getUserName(),user.getPassword(),authorities);
//	}
	
	public static UserDetailsImpl build (UserEntity user)
	{
		List<GrantedAuthority> authorities = user.getUserandroles().stream().map(role -> new SimpleGrantedAuthority(role.getRoles().getName().toString())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getUserID(),user.getUserName(),user.getPassword(),authorities);
	}

//	public static UserDetailsImpl build (UserEntity user)
//	{
//		
//		return new UserDetailsImpl(user.getUserID(),user.getUserName(),user.getPassword(),user.getUserandroles().getRoles().getRoleID());
//	}
	

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if(o ==null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(o, user.userID);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
