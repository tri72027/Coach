package com.coach.application.coachservice.info.dto;

import com.coach.application.entity.InfoEntity;

public class InfoResponse {
	private Long infoID;
	private String fullName;
	private String phone;
	private String identificationNumber;
	private String email;
	private String address;
	private Long userID;

	public InfoResponse() {
	}

	public InfoResponse(Long infoID, String fullName, String phone, String identificationNumber, String email,
			String address, Long userID) {
		this.infoID = infoID;
		this.fullName = fullName;
		this.phone = phone;
		this.identificationNumber = identificationNumber;
		this.email = email;
		this.address = address;
		this.userID = userID;
	}

	public InfoResponse(InfoEntity entity) {
		this.infoID = entity.getInfoID();
		this.fullName = entity.getFullName();
		this.phone = entity.getPhone();
		this.identificationNumber = entity.getIdentificationNumber();
		this.email = entity.getEmail();
		this.address = entity.getAddress();
		if(!(entity.getUserId() == null))
		{
			this.userID = entity.getUserId().getUserID();
		}else
		{
			this.userID = (long) 0;
		}
	}

	public Long getInfoID() {
		return infoID;
	}

	public void setInfoID(Long infoID) {
		this.infoID = infoID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

}
