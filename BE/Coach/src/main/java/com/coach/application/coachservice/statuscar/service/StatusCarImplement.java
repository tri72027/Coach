package com.coach.application.coachservice.statuscar.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.statuscar.dto.StatusCarRequest;
import com.coach.application.coachservice.statuscar.dto.StatusCarResponse;

import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.StatusCarEntity;


@Service
public class StatusCarImplement implements StatusCarService {
	@Autowired
	public IRepoStatusCar repo;

	@Override
	public void save(StatusCarRequest req) {
	
		StatusCarEntity entity = new StatusCarEntity();
		System.out.println(req.getName());
		entity.setName(req.getName());
		repo.save(entity);
	
	}

	
	@Override
	public BaseResponse getAll() {
		List<StatusCarEntity> list = repo.findAll();
		BaseResponse cmRep = new BaseResponse();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<StatusCarResponse> rep = list.stream().map(StatusCarResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(StatusCarRequest req) {
		// TODO Auto-generated method stub
		Optional<StatusCarEntity> list = repo.findById(req.getStatusID());
		BaseResponse cmRep = new BaseResponse();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		StatusCarResponse rep = new StatusCarResponse();
		rep.setStatusID(list.get().getStatusID());
		rep.setName(list.get().getName());
		cmRep.setContent(rep);
		return cmRep;
	}

}
