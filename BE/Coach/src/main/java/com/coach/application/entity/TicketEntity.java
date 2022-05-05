package com.coach.application.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class TicketEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id", nullable = false)
	private Long ticketID;

	@Column(name = "ticket_code", length = 20, nullable = false)
	private String ticketCode;

	@Column(name = "date", nullable = false)
	private Date date;

	@Column(name = "seats", nullable = false)
	private String seats;

	@Column(name = "amount_seats", nullable = false)
	private int amountSeat;

	@Column(name = "price", length = 10, nullable = false)
	private double price;;

	@ManyToOne
	@JoinColumn(name = "trip_id", nullable = false)
	private TripEntity trip;

	@ManyToOne
	@JoinColumn(name = "info_id")
	private InfoEntity infoID;

	@ManyToOne
	@JoinColumn(name = "status")
	private StatusTicketEntity status;

	public TicketEntity() {
	}

	

	public TicketEntity(Long ticketID, String ticketCode, Date date, String seats, int amountSeat, double price,
			TripEntity trip, InfoEntity infoID, StatusTicketEntity status) {
		super();
		this.ticketID = ticketID;
		this.ticketCode = ticketCode;
		this.date = date;
		this.seats = seats;
		this.amountSeat = amountSeat;
		this.price = price;
		this.trip = trip;
		this.infoID = infoID;
		this.status = status;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

	public int getAmountSeat() {
		return amountSeat;
	}

	public void setAmountSeat(int amountSeat) {
		this.amountSeat = amountSeat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public TripEntity getTrip() {
		return trip;
	}

	public void setTrip(TripEntity trip) {
		this.trip = trip;
	}






	public InfoEntity getInfoID() {
		return infoID;
	}



	public void setInfoID(InfoEntity infoID) {
		this.infoID = infoID;
	}



	public StatusTicketEntity getStatus() {
		return status;
	}

	public void setStatus(StatusTicketEntity status) {
		this.status = status;
	}

}
