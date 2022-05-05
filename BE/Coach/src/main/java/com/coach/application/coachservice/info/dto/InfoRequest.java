package com.coach.application.coachservice.info.dto;

public class InfoRequest {
	private Long infoID;
	private String fullName;
	private String phone;
	private String identificationNumber;
	private String email;
	private String address;
	private Long userID;
	public InfoRequest() {
	}
	public InfoRequest(Long infoID, String fullName, String phone, String identificationNumber, String email,
			String address, Long userID) {
		this.infoID = infoID;
		this.fullName = fullName;
		this.phone = phone;
		this.identificationNumber = identificationNumber;
		this.email = email;
		this.address = address;
		this.userID = userID;
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
