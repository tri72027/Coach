package com.coach.application.coachservice.route.dto;

import com.coach.application.entity.RouteEntity;

public class SearchDropdownRouteResponse {

	private Long routeID;

	private String route;

	private String departureTime;

	private String journeyTime;


	public SearchDropdownRouteResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SearchDropdownRouteResponse(Long routeID, String route, String departureTime, String journeyTime) {
		super();
		this.routeID = routeID;
		this.route = route;
		this.departureTime = departureTime;
		this.journeyTime = journeyTime;
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


	public Long getRouteID() {
		return routeID;
	}

	public void setRouteID(Long routeID) {
		this.routeID = routeID;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	

}
