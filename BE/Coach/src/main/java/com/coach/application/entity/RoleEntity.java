package com.coach.application.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.coach.application.common.security.ERole;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class RoleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", nullable = false)
	private Long roleID;

	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	
	@Column(name = "description", length = 600)
	private String description;
	
	@JsonBackReference
	@OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
	private List<UserAndRolesEntity> userAndRoles;
	public RoleEntity() {
	}


	public RoleEntity(Long roleID, String code, ERole name, String description) {
		super();
		this.roleID = roleID;
		this.code = code;
		this.name = name;
		this.description = description;
	}


	public RoleEntity(Long roleID, String code, ERole name, String description, List<UserAndRolesEntity> userAndRoles) {
		super();
		this.roleID = roleID;
		this.code = code;
		this.name = name;
		this.description = description;
		this.userAndRoles = userAndRoles;
	}


	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ERole getName() {
		return name;
	}


	public void setName(ERole name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<UserAndRolesEntity> getUserAndRoles() {
		return userAndRoles;
	}


	public void setUserAndRoles(List<UserAndRolesEntity> userAndRoles) {
		this.userAndRoles = userAndRoles;
	}

	

}
