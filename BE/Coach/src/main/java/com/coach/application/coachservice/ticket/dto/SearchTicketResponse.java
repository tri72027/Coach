package com.coach.application.coachservice.ticket.dto;

import java.sql.Date;

public class SearchTicketResponse {

	private Long ticketID;
	private Date dateBuyTicket;
	private Date dateGo;
	private String ticketCode;
	private String route;
	private double price;
	private Long infoID;
	private String status;
	private String fullName;
	private String phone;
	private String seats;
	private Long provinceStartID;
	private Long provinceEndID;
	private Long statusID;
	private Long tripID;
	
	public SearchTicketResponse() {
	}
	
	




	public SearchTicketResponse(Long ticketID, Date dateBuyTicket, Date dateGo, String ticketCode, String route,
			double price, Long infoID, String status, String fullName, String phone, String seats, Long provinceStartID,
			Long provinceEndID, Long statusID, Long tripID) {
		super();
		this.ticketID = ticketID;
		this.dateBuyTicket = dateBuyTicket;
		this.dateGo = dateGo;
		this.ticketCode = ticketCode;
		this.route = route;
		this.price = price;
		this.infoID = infoID;
		this.status = status;
		this.fullName = fullName;
		this.phone = phone;
		this.seats = seats;
		this.provinceStartID = provinceStartID;
		this.provinceEndID = provinceEndID;
		this.statusID = statusID;
		this.tripID = tripID;
	}






	public Long getTripID() {
		return tripID;
	}






	public void setTripID(Long tripID) {
		this.tripID = tripID;
	}






	public String getSeats() {
		return seats;
	}



	public void setSeats(String seats) {
		this.seats = seats;
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



	public Long getStatusID() {
		return statusID;
	}



	public void setStatusID(Long statusID) {
		this.statusID = statusID;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Long getInfoID() {
		return infoID;
	}

	public void setInfoID(Long infoID) {
		this.infoID = infoID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
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

}
