package com.coach.application.coachservice.chooseseats.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.CarEntity;
import com.coach.application.entity.ChooseSeatsDisableEntity;
import com.coach.application.entity.TripEntity;
@Repository
public interface IRepoChooseSeats  extends JpaRepository<ChooseSeatsDisableEntity, Long>{


	public ChooseSeatsDisableEntity  findByTripAndCarAndSeats(TripEntity tripEntity, CarEntity carEntity, String seats);
}
