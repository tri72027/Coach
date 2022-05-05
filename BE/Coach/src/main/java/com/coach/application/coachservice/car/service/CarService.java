package com.coach.application.coachservice.car.service;

import java.util.List;

import com.coach.application.coachservice.car.dto.CarRequest;
import com.coach.application.common.base.BaseResponse;

public interface CarService {

	void save(CarRequest req);

	BaseResponse getAll();

	BaseResponse getByID(CarRequest req);
	
	BaseResponse searchCar(CarRequest req);
	
	void delete(CarRequest req);
	void deleteCheck(List<CarRequest>req);
	void update(CarRequest req);

}
