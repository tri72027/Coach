package com.coach.application.coachservice.trip.dto;

import java.sql.Date;

import com.coach.application.common.base.BaseRequest;

public class TripRequest  extends BaseRequest{

	private Long tripID;

	private Date date;
	private Long carID;
	private Long routeID;
	private Long statusID;
	public TripRequest() {
	}
	
	public TripRequest(Long tripID, Date date, Long carID, Long routeID, Long statusID) {
		super();
		this.tripID = tripID;
		this.date = date;
		this.carID = carID;
		this.routeID = routeID;
		this.statusID = statusID;
	}

	
	public Long getTripID() {
		return tripID;
	}
	public void setTripID(Long tripID) {
		this.tripID = tripID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getCarID() {
		return carID;
	}
	public void setCarID(Long carID) {
		this.carID = carID;
	}
	public Long getRouteID() {
		return routeID;
	}
	public void setRouteID(Long routeID) {
		this.routeID = routeID;
	}
	public Long getStatusID() {
		return statusID;
	}
	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}
	
	

}
