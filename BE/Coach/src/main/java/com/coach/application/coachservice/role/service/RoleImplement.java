package com.coach.application.coachservice.role.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coach.application.coachservice.role.dto.RoleRequest;
import com.coach.application.coachservice.role.dto.RoleResponse;
import com.coach.application.common.base.BaseResponse;
import com.coach.application.entity.RoleEntity;

@Service
public class RoleImplement implements RoleService {
	@Autowired
	public IRepoRole repo;

	@Override
	public void save(RoleRequest req) {
		// TODO Auto-generated method stub
		RoleEntity entity = new RoleEntity();
		entity.setCode(req.getCode());
//		entity.setName(req.getName());
		entity.setDescription(req.getDescription());
		repo.save(entity);
		
	}
	@Override
	public BaseResponse getAll() {
		// TODO Auto-generated method stub
		BaseResponse cmRep = new BaseResponse();
		List<RoleEntity> list = repo.findAll();
		if (list.isEmpty()) {
			cmRep.setError("Không tồn tại");
			return cmRep;
		}
		List<RoleResponse> rep = list.stream().map(RoleResponse::new).collect(Collectors.toList());
		cmRep.setContent(rep);
		return cmRep;
	}

	@Override
	public BaseResponse getByID(RoleRequest req) {
		BaseResponse cmRep = new BaseResponse();
		Optional<RoleEntity> rep = repo.findById(req.getRoleID());
		if (rep.isEmpty()) {
			cmRep.setError("Không tồn tại");
			return cmRep;
		}
		RoleResponse resp = new RoleResponse();
		resp.setRoleID(rep.get().getRoleID());
		resp.setCode(rep.get().getCode());
	//	resp.setName(rep.get().getName());
		resp.setDescription(rep.get().getDescription());
		cmRep.setContent(resp);
		return cmRep;
	}

}
