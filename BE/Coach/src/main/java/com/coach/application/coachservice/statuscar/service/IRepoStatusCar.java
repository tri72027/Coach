package com.coach.application.coachservice.statuscar.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.StatusCarEntity;
@Repository
public interface IRepoStatusCar  extends JpaRepository<StatusCarEntity, Long>{

}
