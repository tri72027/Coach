package com.jwt.demojwt.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.jwt.demojwt.common.ERole;

import javax.persistence.Id;

@Entity
@Table(name = "roles")
public class Roles {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Roles(Long id, ERole name) {
		super();
		Id = id;
		this.name = name;
	}


	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
	
}
