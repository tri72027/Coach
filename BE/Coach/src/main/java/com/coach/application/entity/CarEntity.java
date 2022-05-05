package com.coach.application.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id", nullable = false)
	private Long carID;

	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "license_plates", length = 45, nullable = false)
	private String licensePlates;

	@Column(name = "price", length = 10, nullable = false)
	private double price;

	@Column(name = "amount", nullable = false)
	private int amount;

	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<TripEntity> trips;

	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<ChooseSeatsDisableEntity> chooseSeatsDisableEntity;
	@ManyToOne
	@JoinColumn(name = "status")
	private StatusCarEntity status;

	public CarEntity() {

	}

	public CarEntity(Long carID, String code, String name, String licensePlates, double price, int amount,
			List<TripEntity> trips) {
		this.carID = carID;
		this.code = code;
		this.name = name;
		this.licensePlates = licensePlates;
		this.price = price;
		this.amount = amount;
		this.trips = trips;
	}

	public Long getCarID() {
		return carID;
	}

	public void setCarID(Long carID) {
		this.carID = carID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicensePlates() {
		return licensePlates;
	}

	public void setLicensePlates(String licensePlates) {
		this.licensePlates = licensePlates;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<TripEntity> getTrips() {
		return trips;
	}

	public void setTrips(List<TripEntity> trips) {
		this.trips = trips;
	}

	public List<ChooseSeatsDisableEntity> getChooseSeatsDisableEntity() {
		return chooseSeatsDisableEntity;
	}

	public void setChooseSeatsDisableEntity(List<ChooseSeatsDisableEntity> chooseSeatsDisableEntity) {
		this.chooseSeatsDisableEntity = chooseSeatsDisableEntity;
	}

	public StatusCarEntity getStatus() {
		return status;
	}

	public void setStatus(StatusCarEntity status) {
		this.status = status;
	}



}
