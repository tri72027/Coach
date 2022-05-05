package com.coach.application.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "status_trip")
public class StatusTripEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id", nullable = false)
	private Long statusID;
	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	List<TripEntity> trips;

	public StatusTripEntity() {
	}

	public StatusTripEntity(Long statusID, String name, List<TripEntity> trips) {
		this.statusID = statusID;
		this.name = name;
		this.trips = trips;
	}

	public Long getStatusID() {
		return statusID;
	}

	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TripEntity> getTrips() {
		return trips;
	}

	public void setTrips(List<TripEntity> trips) {
		this.trips = trips;
	}
	

}
