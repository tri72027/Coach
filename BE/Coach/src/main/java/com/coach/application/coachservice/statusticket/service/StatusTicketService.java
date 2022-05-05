package com.coach.application.coachservice.statusticket.service;

import com.coach.application.coachservice.statusticket.dto.StatusTicketRequest;
import com.coach.application.common.base.BaseResponse;

public interface StatusTicketService {
	
	void save (StatusTicketRequest req);

	BaseResponse getAll();
	BaseResponse getByID(StatusTicketRequest req);
}
