package com.coach.application.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coach.application.coachservice.user.service.IRepoUser;
import com.coach.application.entity.UserEntity;
@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	@Autowired
	IRepoUser repoUser;
	
	
	@Override	
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 UserEntity user = repoUser.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("User not Found" + username));
		 
		return UserDetailsImpl.build(user);
	}

}
