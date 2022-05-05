package com.coach.application.coachservice.chooseseats.dto;

import com.coach.application.entity.ChooseSeatsDisableEntity;

public class ChooseSeatsResponse {

	private Long chooseSeatID;

	private Long tripID;
	private Long carID;
	private String seats;
	public ChooseSeatsResponse() {
	}

	public ChooseSeatsResponse(Long chooseSeatID, Long trip, Long car, String seats) {
		super();
		this.chooseSeatID = chooseSeatID;
		this.tripID = trip;
		this.carID = car;
		this.seats = seats;
	}

	public ChooseSeatsResponse(ChooseSeatsDisableEntity entity) {
		super();
		this.chooseSeatID = entity.getChooseSeatID();
		this.tripID = entity.getTrip().getTripID();
		this.carID = entity.getCar().getCarID();
		this.seats = entity.getSeats();
	}
	public Long getChooseSeatID() {
		return chooseSeatID;
	}
	public void setChooseSeatID(Long chooseSeatID) {
		this.chooseSeatID = chooseSeatID;
	}


	public Long getTripID() {
		return tripID;
	}

	public void setTripID(Long tripID) {
		this.tripID = tripID;
	}

	public Long getCarID() {
		return carID;
	}

	public void setCarID(Long carID) {
		this.carID = carID;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
	}

}
