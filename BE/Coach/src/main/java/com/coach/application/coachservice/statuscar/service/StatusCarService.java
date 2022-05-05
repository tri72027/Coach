package com.coach.application.coachservice.statuscar.service;

import com.coach.application.coachservice.statuscar.dto.StatusCarRequest;
import com.coach.application.common.base.BaseResponse;

public interface StatusCarService {
	
	void save (StatusCarRequest req);

	BaseResponse getAll();
	BaseResponse getByID(StatusCarRequest req);
}
