package com.coach.application.coachservice.car.dto;

import com.coach.application.common.base.BaseRequest;

public class CarRequest  extends BaseRequest{
	private Long carID;

	private String code;

	private String name;

	private String licensePlates;

	private Double price;

	private Integer amount;
	private Long statusID;

	public CarRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CarRequest(Long carID, String code, String name, String licensePlates, Double price, Integer amount,
			Long statusID) {
		super();
		this.carID = carID;
		this.code = code;
		this.name = name;
		this.licensePlates = licensePlates;
		this.price = price;
		this.amount = amount;
		this.statusID = statusID;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}


}
