package com.coach.application.coachservice.province.dto;

import java.util.ArrayList;
import java.util.List;

public class ListProvinceRequest {

	private List<ProvinceRequest> listCreateProvinceRequest = new ArrayList<ProvinceRequest>();

	public ListProvinceRequest() {
		super();
	}

	public ListProvinceRequest(List<ProvinceRequest> listCreateProvinceRequest) {
		super();
		this.listCreateProvinceRequest = listCreateProvinceRequest;
	}

	public List<ProvinceRequest> getListCreateProvinceRequest() {
		return listCreateProvinceRequest;
	}

	public void setListCreateProvinceRequest(List<ProvinceRequest> listCreateProvinceRequest) {
		this.listCreateProvinceRequest = listCreateProvinceRequest;
	}

}
