package com.coach.application.coachservice.car.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.CarEntity;
@Repository
public interface IRepoCar extends JpaRepository<CarEntity, Long>{

}
