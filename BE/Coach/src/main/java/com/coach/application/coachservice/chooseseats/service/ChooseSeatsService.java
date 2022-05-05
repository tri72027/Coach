package com.coach.application.coachservice.chooseseats.service;

import com.coach.application.coachservice.chooseseats.dto.ChooseSeatsRequest;
import com.coach.application.common.base.BaseResponse;

public interface ChooseSeatsService {
	BaseResponse save(ChooseSeatsRequest req);

	BaseResponse getAll();

	BaseResponse getByID(ChooseSeatsRequest req);
	
	BaseResponse	getByTripAndCarAndSeats( ChooseSeatsRequest req);
	
	void delete(ChooseSeatsRequest req);
}
