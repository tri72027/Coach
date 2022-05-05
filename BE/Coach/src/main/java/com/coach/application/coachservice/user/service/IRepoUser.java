package com.coach.application.coachservice.user.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.UserEntity;
@Repository
public interface IRepoUser extends JpaRepository<UserEntity, Long>{

	
	Optional<UserEntity>  findByUserName(String userName);
	Boolean existsByUserName (String userName);
	
}
