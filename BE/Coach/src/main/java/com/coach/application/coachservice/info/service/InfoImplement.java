package com.coach.application.coachservice.info.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.info.dto.InfoRequest;
import com.coach.application.coachservice.info.dto.InfoResponse;
import com.coach.application.coachservice.user.service.IRepoUser;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.InfoEntity;
import com.coach.application.entity.UserEntity;
@Service
public class InfoImplement implements InfoService {

	@Autowired
	public IRepoInfo repo;
	@Autowired
	public IRepoUser repoUser;
	@Override
	public void save(InfoRequest req) {
		// TODO Auto-generated method stub
		InfoEntity entity  = new InfoEntity();
		entity.setFullName(req.getFullName());
		entity.setPhone(req.getPhone());
		entity.setEmail(req.getEmail());
		entity.setAddress(req.getAddress());
		entity.setIdentificationNumber(req.getIdentificationNumber());
		if(req.getUserID()!=null)
		{
			Optional<UserEntity> userEntity = repoUser.findById(req.getUserID());
			entity.setUserId(userEntity.get());
		}
		repo.save(entity);
	}

	@Override
	public BaseResponse getAll() {
		BaseResponse cmRep = new BaseResponse();
		List<InfoEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		List<InfoResponse> rep = list.stream().map(InfoResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(InfoRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<InfoEntity> entity = repo.findById(req.getInfoID());
		if(entity.isEmpty())
		{
			cmRep.setError("ko ton tai");
			return cmRep;
		}
		InfoResponse rep = new InfoResponse();
		rep.setInfoID(entity.get().getInfoID());
		rep.setFullName(entity.get().getFullName());
		rep.setPhone(entity.get().getPhone());
		rep.setEmail(entity.get().getEmail());
		rep.setAddress(entity.get().getAddress());
		rep.setIdentificationNumber(entity.get().getIdentificationNumber());
		if(entity.get().getUserId()!=null)
		{
		rep.setUserID(entity.get().getUserId().getUserID());
		// TODO Auto-generated method stub
		}else
		{
			rep.setUserID((long) 0);
		}
		cmRep.setContent(rep);
		return cmRep;
	}

}
