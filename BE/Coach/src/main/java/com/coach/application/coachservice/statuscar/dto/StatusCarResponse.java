package com.coach.application.coachservice.statuscar.dto;

import com.coach.application.entity.StatusCarEntity;

public class StatusCarResponse {

	private Long statusID;

	private String name;

	public StatusCarResponse() {
	}

	
	public StatusCarResponse(StatusCarEntity entity) {
		
		this.statusID = entity.getStatusID();
		this.name = entity.getName();
	}


	public StatusCarResponse(Long statusID, String name) {
		super();
		this.statusID = statusID;
		this.name = name;
	}


	public Long getStatusID() {
		return statusID;
	}


	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



}
