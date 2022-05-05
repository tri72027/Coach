package com.coach.application.coachservice.role.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.common.security.ERole;
import com.coach.application.entity.RoleEntity;

@Repository
public interface IRepoRole  extends JpaRepository<RoleEntity, Long>{
	
	Optional<RoleEntity> findByName (ERole string);

}
