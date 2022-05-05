package com.coach.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="current_choose_seat")
public class ChooseSeatsDisableEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choose_seat_id", nullable = false)
	private Long chooseSeatID;
	
	
	@ManyToOne
	@JoinColumn(name = "trip_id")
	private TripEntity trip;
	@ManyToOne
	@JoinColumn(name = "car_id")
	private CarEntity car;
	private String seats;
	public ChooseSeatsDisableEntity() {
	}
	public ChooseSeatsDisableEntity(TripEntity trip, CarEntity car, String seats) {
		this.trip = trip;
		this.car = car;
		this.seats = seats;
	}
	
	
	public Long getChooseSeatID() {
		return chooseSeatID;
	}
	public void setChooseSeatID(Long chooseSeatID) {
		this.chooseSeatID = chooseSeatID;
	}
	public TripEntity getTrip() {
		return trip;
	}
	public void setTrip(TripEntity trip) {
		this.trip = trip;
	}
	public CarEntity getCar() {
		return car;
	}
	public void setCar(CarEntity car) {
		this.car = car;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	
	
	
	
}
