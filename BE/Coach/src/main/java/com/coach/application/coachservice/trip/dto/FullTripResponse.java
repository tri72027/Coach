package com.coach.application.coachservice.trip.dto;



import java.util.Date;

import com.coach.application.entity.TripEntity;

public class FullTripResponse {
	private Long tripID;
	private Date date;
	private Long carID;
	private Long routeID;
	private String carCode;
	private String carName;
	private String licensePlates;
	private double carPrice;
	private int carAmount;
	private String departureTime;
	private String journeyTime;
	private double routePrice;
	private Long provinceStartID;
	private Long provinceEndID;
	private String provinceStartName;
	private String provinceEndName;
	private String route;
	private Long statusID;
	private String statusName;

	public FullTripResponse() {
	}


	public FullTripResponse(Long tripID, Date date, Long carID, Long routeID, String carCode, String carName,
			String licensePlates, double carPrice, int carAmount, String departureTime, String journeyTime,
			double routePrice, Long provinceStartID, Long provinceEndID, String provinceStartName,
			String provinceEndName, String route, Long statusID, String statusName) {
		super();
		this.tripID = tripID;
		this.date = date;
		this.carID = carID;
		this.routeID = routeID;
		this.carCode = carCode;
		this.carName = carName;
		this.licensePlates = licensePlates;
		this.carPrice = carPrice;
		this.carAmount = carAmount;
		this.departureTime = departureTime;
		this.journeyTime = journeyTime;
		this.routePrice = routePrice;
		this.provinceStartID = provinceStartID;
		this.provinceEndID = provinceEndID;
		this.provinceStartName = provinceStartName;
		this.provinceEndName = provinceEndName;
		this.route = route;
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


	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getLicensePlates() {
		return licensePlates;
	}

	public void setLicensePlates(String licensePlates) {
		this.licensePlates = licensePlates;
	}

	public double getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(double carPrice) {
		this.carPrice = carPrice;
	}

	public int getCarAmount() {
		return carAmount;
	}

	public void setCarAmount(int carAmount) {
		this.carAmount = carAmount;
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

	public double getRoutePrice() {
		return routePrice;
	}

	public void setRoutePrice(double routePrice) {
		this.routePrice = routePrice;
	}

	public Long getProvinceStartID() {
		return provinceStartID;
	}

	public void setProvinceStartID(Long provinceStartID) {
		this.provinceStartID = provinceStartID;
	}

	public Long getProvinceEndID() {
		return provinceEndID;
	}

	public void setProvinceEndID(Long provinceEndID) {
		this.provinceEndID = provinceEndID;
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



}
