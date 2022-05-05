package com.coach.application.coachservice.status.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.StatusEntity;
@Repository
public interface IRepoStatus  extends JpaRepository<StatusEntity, Long>{

}
