package com.coach.application.coachservice.province.service;

import com.coach.application.coachservice.province.dto.ListProvinceRequest;
import com.coach.application.coachservice.province.dto.ProvinceRequest;
import com.coach.application.common.base.BaseResponse;



public interface ProvinceService {
	void save(ProvinceRequest requestList);

	void savelist(ListProvinceRequest listCreateProvinceRequest);
	
	BaseResponse get();
	BaseResponse getid(ProvinceRequest ProvinceRequest);
	void update( ProvinceRequest updateProvinceRequest);

}
