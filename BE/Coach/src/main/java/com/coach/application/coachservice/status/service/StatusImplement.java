package com.coach.application.coachservice.status.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.status.dto.StatusRequest;
import com.coach.application.coachservice.status.dto.StatusResponse;
import com.coach.application.coachservice.statusticket.dto.StatusTicketResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.StatusEntity;


@Service
public class StatusImplement implements StatusService {
	@Autowired
	public IRepoStatus repo;

	@Override
	public void save(StatusRequest req) {
	
		StatusEntity entity = new StatusEntity();
		System.out.println(req.getName());
		entity.setName(req.getName());
		repo.save(entity);
	
	}

	
	@Override
	public BaseResponse getAll() {
		List<StatusEntity> list = repo.findAll();
		BaseResponse cmRep = new BaseResponse();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<StatusResponse> rep = list.stream().map(StatusResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(StatusRequest req) {
		// TODO Auto-generated method stub
		Optional<StatusEntity> list = repo.findById(req.getStatusID());
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
