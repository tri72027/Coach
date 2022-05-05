package com.coach.application.coachservice.info.service;

import com.coach.application.coachservice.info.dto.InfoRequest;
import com.coach.application.common.base.BaseResponse;

public interface InfoService {

	void save(InfoRequest req);
	BaseResponse getAll();
	BaseResponse getByID(InfoRequest req);
	
}
