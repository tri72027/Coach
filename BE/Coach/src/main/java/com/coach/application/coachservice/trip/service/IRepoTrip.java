package com.coach.application.coachservice.trip.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coach.application.entity.CarEntity;
import com.coach.application.entity.RouteEntity;
import com.coach.application.entity.TripEntity;
@Repository
public interface IRepoTrip  extends JpaRepository<TripEntity, Long>{

	List<TripEntity> findByRouteAndDate(Long routeID, Date date);
	List<TripEntity> findByRoute(RouteEntity req);
	List<TripEntity> findByCar(CarEntity car);
	
}
