package com.coach.application.coachservice.chooseseats.dto;

public class ChooseSeatsRequest {

	private Long chooseSeatID;

	private Long tripID;
	private Long carID;
	private String seats;
	
	
	public ChooseSeatsRequest() {
	}

	
	public ChooseSeatsRequest(Long chooseSeatID, Long trip, Long car, String seats) {
		this.chooseSeatID = chooseSeatID;
		this.tripID = trip;
		this.carID = car;
		this.seats = seats;
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


	public Long getChooseSeatID() {
		return chooseSeatID;
	}
	public void setChooseSeatID(Long chooseSeatID) {
		this.chooseSeatID = chooseSeatID;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	

}
