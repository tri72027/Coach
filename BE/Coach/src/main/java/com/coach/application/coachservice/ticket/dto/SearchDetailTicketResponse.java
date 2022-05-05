package com.coach.application.coachservice.ticket.dto;

import java.sql.Date;

public class SearchDetailTicketResponse {

	private Long ticketID;
	private Date dateBuyTicket;
	private Date dateGo;
	private String ticketCode;
	private int amountSeats;
	private String route;
	private String carName;
	private String licensePlates;
	private double price;
	private String fullName;
	private String phone;
	private String seats;
	private String status;
	private String departureTime;

	public SearchDetailTicketResponse() {
	}

	public SearchDetailTicketResponse(Long ticketID, Date dateBuyTicket, Date dateGo, String ticketCode,
			int amountSeats, String route, String carName, String licensePlates, double price, String fullName,
			String phone, String seats, String status, String departureTime) {
		super();
		this.ticketID = ticketID;
		this.dateBuyTicket = dateBuyTicket;
		this.dateGo = dateGo;
		this.ticketCode = ticketCode;
		this.amountSeats = amountSeats;
		this.route = route;
		this.carName = carName;
		this.licensePlates = licensePlates;
		this.price = price;
		this.fullName = fullName;
		this.phone = phone;
		this.seats = seats;
		this.status = status;
		this.departureTime = departureTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Long getTicketID() {
		return ticketID;
	}

	public void setTicketID(Long ticketID) {
		this.ticketID = ticketID;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public int getAmountSeats() {
		return amountSeats;
	}

	public void setAmountSeats(int amountSeats) {
		this.amountSeats = amountSeats;
	}

	public Date getDateBuyTicket() {
		return dateBuyTicket;
	}

	public void setDateBuyTicket(Date dateByTicket) {
		this.dateBuyTicket = dateByTicket;
	}

	public Date getDateGo() {
		return dateGo;
	}

	public void setDateGo(Date dateGo) {
		this.dateGo = dateGo;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

//	public String getProvinceStartName() {
//		return provinceStartName;
//	}
//
//	public void setProvinceStartName(String provinceStartName) {
//		this.provinceStartName = provinceStartName;
//	}
//
//	public String getProvinceEndName() {
//		return provinceEndName;
//	}
//
//	public void setProvinceEndName(String provinceEndName) {
//		this.provinceEndName = provinceEndName;
//	}

	public String getLicensePlates() {
		return licensePlates;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public void setLicensePlates(String licensePlates) {
		this.licensePlates = licensePlates;
	}

}
