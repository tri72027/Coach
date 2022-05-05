package com.coach.application.coachservice.province.dto;

public class ProvinceRequest {
	private Long provinceID;

	private String code;

	private String name;

	public ProvinceRequest() {
	}

	public ProvinceRequest(Long provinceID, String code, String name) {
		super();
		this.provinceID = provinceID;
		this.code = code;
		this.name = name;
	}

	public Long getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(Long provinceID) {
		this.provinceID = provinceID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}