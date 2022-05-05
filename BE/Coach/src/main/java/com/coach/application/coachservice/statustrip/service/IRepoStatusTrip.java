package com.coach.application.coachservice.statustrip.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coach.application.entity.StatusTripEntity;

public interface IRepoStatusTrip  extends JpaRepository<StatusTripEntity, Long>{

}
