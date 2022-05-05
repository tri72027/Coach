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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "info")
public class InfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_id", nullable = false)
	private Long infoID;

	@Column(name = "fullname", nullable = false)
	private String fullName;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "identification_number", length = 12,unique = true)
	private String identificationNumber;


	@Column(name = "email",unique = true)
	private String email;

	@Column(name = "address")
	private String address;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userId;
	
	@OneToMany(mappedBy = "infoID", cascade = CascadeType.ALL)
	private List<TicketEntity> tickets;
	
	public InfoEntity() {
	}

	public InfoEntity(Long infoID, String fullName, String phone, String identificationNumber, String email,
			String address, UserEntity userId) {
		super();
		this.infoID = infoID;
		this.fullName = fullName;
		this.phone = phone;
		this.identificationNumber = identificationNumber;
		this.email = email;
		this.address = address;
		this.userId = userId;
	}

	public Long getInfoID() {
		return infoID;
	}

	public void setInfoID(Long infoID) {
		this.infoID = infoID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserEntity getUserId() {
		return userId;
	}

	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}

	
}
