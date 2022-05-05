package com.coach.application.coachservice.route.dto;

import com.coach.application.entity.RouteEntity;

public class SearchRouteResponse {

	private Long routeID;

	private String departureTime;

	private String journeyTime;

	private double price;

	private Long provinceStart;

	private Long provinceEnd;

	private String provinceStartName;

	private String provinceEndName;
	private Long statusID;

	private String statusName;

	
	public SearchRouteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public SearchRouteResponse(Long routeID, String departureTime, String journeyTime, double price, Long provinceStart,
			Long provinceEnd, String provinceStartName, String provinceEndName, Long statusID, String statusName) {
		super();
		this.routeID = routeID;
		this.departureTime = departureTime;
		this.journeyTime = journeyTime;
		this.price = price;
		this.provinceStart = provinceStart;
		this.provinceEnd = provinceEnd;
		this.provinceStartName = provinceStartName;
		this.provinceEndName = provinceEndName;
		this.statusID = statusID;
		this.statusName = statusName;
	}



	public Long getStatusID() {
		return statusID;
	}



	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}



	public String getStatusName() {
		return statusName;
	}



	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}



	public String getProvinceStartName() {
		return provinceStartName;
	}

	public void setProvinceStartName(String provinceStartName) {
		this.provinceStartName = provinceStartName;
	}

	public String getProvinceEndName() {
		return provinceEndName;
	}

	public void setProvinceEndName(String provinceEndName) {
		this.provinceEndName = provinceEndName;
	}

	
	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getProvinceStart() {
		return provinceStart;
	}

	public void setProvinceStart(Long provinceStart) {
		this.provinceStart = provinceStart;
	}

	public Long getProvinceEnd() {
		return provinceEnd;
	}

	public void setProvinceEnd(Long provinceEnd) {
		this.provinceEnd = provinceEnd;
	}

	public void setRouteID(Long rooteID) {
		this.routeID = rooteID;
	}

	public Long getRouteID() {
		return routeID;
	}

}
