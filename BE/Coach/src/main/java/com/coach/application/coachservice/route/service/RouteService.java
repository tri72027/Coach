package com.coach.application.coachservice.route.service;

import java.util.List;

import com.coach.application.coachservice.route.dto.RouteRequest;
import com.coach.application.common.base.BaseResponse;

public interface RouteService {
	void save(RouteRequest requestList);
	public BaseResponse getAll();
	public BaseResponse getProvinceStart(RouteRequest req);
	public BaseResponse getbyId(RouteRequest req);
	public BaseResponse searchRoute(RouteRequest req);
	void delete(RouteRequest req);
	void deleteCheck(List<RouteRequest>req);
	void update(RouteRequest req);
	public BaseResponse searchDropdown();

}
