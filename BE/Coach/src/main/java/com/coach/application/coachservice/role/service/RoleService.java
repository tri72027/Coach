package com.coach.application.coachservice.role.service;

import com.coach.application.coachservice.role.dto.RoleRequest;
import com.coach.application.common.base.BaseResponse;

public interface RoleService {
	void save(RoleRequest req);
	BaseResponse getAll();
	BaseResponse getByID(RoleRequest req);
}
