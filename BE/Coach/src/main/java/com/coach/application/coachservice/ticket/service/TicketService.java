package com.coach.application.coachservice.ticket.service;

import java.util.List;

import com.coach.application.coachservice.ticket.dto.TicketRequest;
import com.coach.application.common.base.BaseResponse;

public interface TicketService {

	void save(TicketRequest req);
	BaseResponse getAll();
	BaseResponse getByID(TicketRequest req);
	BaseResponse getByTripID(TicketRequest req);
	BaseResponse getManagerTicket(TicketRequest req);
	BaseResponse getDetailTicket(TicketRequest req);
	void delete(TicketRequest req);
	void deleteCheck(List<TicketRequest>req);
	void update(TicketRequest req);
	
	
}


