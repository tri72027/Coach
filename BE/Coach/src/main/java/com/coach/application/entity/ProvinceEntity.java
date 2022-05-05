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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "province")

public class ProvinceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "province_id", nullable = false)
	private Long provinceID;

	@Column(name = "code", length = 10, nullable = false)
	private String code;
	@Column(name = "name", nullable = false)
	private String name;

	@JsonBackReference
	@OneToMany(mappedBy = "provinceStart", cascade = CascadeType.ALL)
	private List<RouteEntity> routesStart;
	@JsonBackReference
	@OneToMany(mappedBy = "provinceEnd", cascade = CascadeType.ALL)
	private List<RouteEntity> routesEnd;

	public ProvinceEntity() {
	}

	public ProvinceEntity(Long provinceID, String code, String name, List<RouteEntity> routesStart,
			List<RouteEntity> routesEnd) {
		super();
		this.provinceID = provinceID;
		this.code = code;
		this.name = name;
		this.routesStart = routesStart;
		this.routesEnd = routesEnd;
	}

	public Long getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(Long provinceID) {
		this.provinceID = provinceID;
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

	public List<RouteEntity> getRoutesStart() {
		return routesStart;
	}

	public void setRoutesStart(List<RouteEntity> routesStart) {
		this.routesStart = routesStart;
	}

	public List<RouteEntity> getRoutesEnd() {
		return routesEnd;
	}

	public void setRoutesEnd(List<RouteEntity> routesEnd) {
		this.routesEnd = routesEnd;
	}

}
