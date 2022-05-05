package com.coach.application.coachservice.statustrip.service;

import com.coach.application.coachservice.statustrip.dto.StatusTripRequest;
import com.coach.application.common.base.BaseResponse;

public interface StatusTripService {
	void save (StatusTripRequest req);
	BaseResponse getAll();

	BaseResponse getByID(StatusTripRequest req);
}
