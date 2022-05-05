package com.coach.application.coachservice.statusticket.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.statusticket.dto.StatusTicketRequest;
import com.coach.application.coachservice.statusticket.dto.StatusTicketResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.StatusTicketEntity;


@Service
public class StatusTicketImplement implements StatusTicketService {
	@Autowired
	public IRepoStatusTicket repo;

	@Override
	public void save(StatusTicketRequest req) {
	
		StatusTicketEntity entity = new StatusTicketEntity();
		System.out.println(req.getName());
		entity.setName(req.getName());
		repo.save(entity);
	
	}

	
	@Override
	public BaseResponse getAll() {
		List<StatusTicketEntity> list = repo.findAll();
		BaseResponse cmRep = new BaseResponse();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<StatusTicketResponse> rep = list.stream().map(StatusTicketResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(StatusTicketRequest req) {
		// TODO Auto-generated method stub
		Optional<StatusTicketEntity> list = repo.findById(req.getStatusID());
		BaseResponse cmRep = new BaseResponse();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		StatusTicketResponse rep = new StatusTicketResponse();
		rep.setStatusID(list.get().getStatusID());
		rep.setName(list.get().getName());
		cmRep.setContent(rep);
		return cmRep;
	}

}
