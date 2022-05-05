package com.coach.application.coachservice.statusticket.dto;

import com.coach.application.entity.StatusTicketEntity;

public class StatusTicketResponse {

	private Long statusID;

	private String name;

	public StatusTicketResponse() {
	}

	
	public StatusTicketResponse(StatusTicketEntity entity) {
		
		this.statusID = entity.getStatusID();
		this.name = entity.getName();
	}


	public StatusTicketResponse(Long statusID, String name) {
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
