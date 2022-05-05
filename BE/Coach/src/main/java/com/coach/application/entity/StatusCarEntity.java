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
@Table(name = "status_car")
public class StatusCarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id", nullable = false)
	private Long statusID;
	@Column(name = "name", nullable = false)
	private String name;
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	List<CarEntity> cars;

	
	
	public StatusCarEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public StatusCarEntity(Long statusID, String name, List<CarEntity> cars) {
		super();
		this.statusID = statusID;
		this.name = name;
		this.cars = cars;
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

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}

}
