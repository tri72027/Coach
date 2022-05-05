package com.coach.application.coachservice.status.service;

import com.coach.application.coachservice.status.dto.StatusRequest;
import com.coach.application.common.base.BaseResponse;

public interface StatusService {
	
	void save (StatusRequest req);

	BaseResponse getAll();
	BaseResponse getByID(StatusRequest req);
}
