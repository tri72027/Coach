package com.coach.application.coachservice.route.dto;

import com.coach.application.common.base.BaseRequest;

public class RouteRequest  extends BaseRequest{

	private Long routeID;

	private String departureTime;

	private String journeyTime;

	private double price;

	private Long provinceStart;

	private Long provinceEnd;

	private Long statusID;
	public RouteRequest() {
	}


	public RouteRequest(Long routeID, String departureTime, String journeyTime, double price, Long provinceStart,
			Long provinceEnd, Long statusID) {
		super();
		this.routeID = routeID;
		this.departureTime = departureTime;
		this.journeyTime = journeyTime;
		this.price = price;
		this.provinceStart = provinceStart;
		this.provinceEnd = provinceEnd;
		this.statusID = statusID;
	}


	public Long getStatusID() {
		return statusID;
	}


	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}


	public Long getRouteID() {
		return routeID;
	}

	public void setRouteID(Long rooteID) {
		this.routeID = rooteID;
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
	

}
