package com.coach.application.coachservice.user.service;

import com.coach.application.coachservice.user.dto.JwtResponse;
import com.coach.application.coachservice.user.dto.MessageResponse;
import com.coach.application.coachservice.user.dto.UserRequest;
import com.coach.application.common.base.BaseResponse;

public interface UserService {

	BaseResponse getAll();
	BaseResponse getByID(UserRequest req);
	
	JwtResponse signin (UserRequest req);
	MessageResponse signup (UserRequest req);
}
