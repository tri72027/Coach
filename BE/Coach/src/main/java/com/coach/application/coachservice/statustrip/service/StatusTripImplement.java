package com.coach.application.coachservice.statustrip.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.statustrip.dto.StatusResponse;
import com.coach.application.coachservice.statustrip.dto.StatusTripRequest;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.StatusTripEntity;

@Service
public class StatusTripImplement implements StatusTripService {
	@Autowired
	public IRepoStatusTrip repo;

	@Override
	public void save(StatusTripRequest req) {
	
		StatusTripEntity entity = new StatusTripEntity();
		System.out.println(req.getName());
		entity.setName(req.getName());
		repo.save(entity);
	
	}
	
	@Override
	public BaseResponse getAll() {
		List<StatusTripEntity> list = repo.findAll();
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
	public BaseResponse getByID(StatusTripRequest req) {
		// TODO Auto-generated method stub
		Optional<StatusTripEntity> list = repo.findById(req.getStatusID());
		BaseResponse cmRep = new BaseResponse();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		StatusResponse rep = new StatusResponse();
		rep.setStatusID(list.get().getStatusID());
		rep.setName(list.get().getName());
		cmRep.setContent(rep);
		return cmRep;
	}

}
