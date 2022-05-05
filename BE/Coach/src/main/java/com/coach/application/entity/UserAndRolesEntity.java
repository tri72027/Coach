package com.coach.application.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_roles", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class UserAndRolesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userandrole_id")
	private Long userAndRoleID;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity users;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity roles;
	public UserAndRolesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAndRolesEntity(UserEntity users, RoleEntity roles) {
		super();
		this.users = users;
		this.roles = roles;
	}

	public UserEntity getUsers() {
		return users;
	}

	public void setUsers(UserEntity users) {
		this.users = users;
	}

	public RoleEntity getRoles() {
		return roles;
	}

	public void setRoles(RoleEntity roles) {
		this.roles = roles;
	}

	public Long getUserAndRoleID() {
		return userAndRoleID;
	}

	public void setUserAndRoleID(Long userAndRoleID) {
		this.userAndRoleID = userAndRoleID;
	}
	
	
}
