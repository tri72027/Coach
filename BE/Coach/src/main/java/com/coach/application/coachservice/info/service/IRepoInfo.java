package com.coach.application.coachservice.info.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.InfoEntity;
@Repository
public interface IRepoInfo  extends JpaRepository<InfoEntity, Long>{
	Boolean existsByEmail (String email);
}
