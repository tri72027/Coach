package com.coach.application.coachservice.trip.service;

import java.util.List;

import com.coach.application.coachservice.trip.dto.TripRequest;
import com.coach.application.common.base.BaseResponse;

public interface TripService {
	void save(TripRequest req);
	BaseResponse getAll();
	BaseResponse getByID(TripRequest req);
	BaseResponse getByIDAndDate(TripRequest req);
	BaseResponse fullTrip(TripRequest req);
	BaseResponse getByRouteID(TripRequest req);
	
	void delete(TripRequest req);
	void deleteCheck(List<TripRequest>req);
	void update(TripRequest req);
}
