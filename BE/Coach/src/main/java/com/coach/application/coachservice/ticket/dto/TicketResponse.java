package com.coach.application.coachservice.ticket.dto;

import java.sql.Date;

import com.coach.application.entity.TicketEntity;

public class TicketResponse {

	private Long ticketID;
	private String ticketCode;
	private Date date;
	private String seat;
	private int amountSeats;
	private Long tripID;
	private Long infoID;
	private double price;
	private Long statusID;

	public TicketResponse() {
	}

	public TicketResponse(TicketEntity entity) {
		this.ticketID = entity.getTicketID();
		this.ticketCode = entity.getTicketCode();
		this.amountSeats = entity.getAmountSeat();
		this.date = entity.getDate();
		this.seat = entity.getSeats();
		this.price = entity.getPrice();
		this.tripID = entity.getTrip().getTripID();
		if(!(entity.getInfoID() == null))
		{
			this.infoID = entity.getInfoID().getInfoID();
		}else
		{
			this.infoID = (long) 0;
		}
		
		this.statusID = entity.getStatus().getStatusID();
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getTripID() {
		return tripID;
	}

	public void setTripID(Long tripID) {
		this.tripID = tripID;
	}


	public Long getInfoID() {
		return infoID;
	}

	public void setInfoID(Long infoID) {
		this.infoID = infoID;
	}

	public Long getStatusID() {
		return statusID;
	}

	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}


}
