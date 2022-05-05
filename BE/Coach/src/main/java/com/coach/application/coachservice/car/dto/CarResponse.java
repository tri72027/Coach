package com.coach.application.coachservice.car.dto;

import com.coach.application.entity.CarEntity;

public class CarResponse {
	private Long carID;

	private String code;

	private String name;

	private String licensePlates;

	private double price;

	private int amount;
	private Long statusID;
	private String statusName;

	public CarResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CarResponse(Long carID, String code, String name, String licensePlates, double price, int amount,
			Long statusID, String statusName) {
		super();
		this.carID = carID;
		this.code = code;
		this.name = name;
		this.licensePlates = licensePlates;
		this.price = price;
		this.amount = amount;
		this.statusID = statusID;
		this.statusName = statusName;
	}


	public CarResponse(CarEntity entity) {
		super();
		this.carID = entity.getCarID();
		this.code = entity.getCode();
		this.name = entity.getName();
		this.licensePlates = entity.getLicensePlates();
		this.price = entity.getPrice();
		this.amount = entity.getAmount();
		this.statusID = entity.getStatus().getStatusID();
	}

	public Long getStatusID() {
		return statusID;
	}

	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}

	public Long getCarID() {
		return carID;
	}

	public void setCarID(Long carID) {
		this.carID = carID;
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

	public String getLicensePlates() {
		return licensePlates;
	}

	public void setLicensePlates(String licensePlates) {
		this.licensePlates = licensePlates;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getStatusName() {
		return statusName;
	}


	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	

}
