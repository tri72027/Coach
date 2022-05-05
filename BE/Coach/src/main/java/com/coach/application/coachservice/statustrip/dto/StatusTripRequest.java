package com.coach.application.coachservice.statustrip.dto;

public class StatusTripRequest {

	private Long statusID;

	private String name;

	public StatusTripRequest() {
	}

	public StatusTripRequest(Long statusID, String name) {
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
