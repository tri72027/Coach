package com.coach.application.coachservice.status.dto;

import com.coach.application.entity.StatusEntity;

public class StatusResponse {

	private Long statusID;

	private String name;

	public StatusResponse() {
	}

	
	public StatusResponse(StatusEntity entity) {
		
		this.statusID = entity.getStatusID();
		this.name = entity.getName();
	}


	public StatusResponse(Long statusID, String name) {
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
