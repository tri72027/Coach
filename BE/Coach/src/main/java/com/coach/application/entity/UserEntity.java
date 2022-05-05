package com.coach.application.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	
	private Long userID;

	@Column(name = "username", length = 45, nullable = false)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Column(name = "modify_time")
	private Date modifyTime;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles", 
//				joinColumns = @JoinColumn(name = "user_id"), 
//				inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<RoleEntity> roles = new HashSet<>();

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<UserAndRolesEntity> userandroles;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private List<InfoEntity> infos;

	
	
//	@ManyToOne
//	@JoinColumn(name = "role_id", nullable = false)
//	private RoleEntity roleID;

	public UserEntity() {
	}

	

	


public UserEntity(Long userID, String userName, String password, Date createTime, Date modifyTime,
		List<UserAndRolesEntity> userandroles, List<InfoEntity> infos) {
	super();
	this.userID = userID;
	this.userName = userName;
	this.password = password;
	this.createTime = createTime;
	this.modifyTime = modifyTime;
	this.userandroles = userandroles;
	this.infos = infos;
}






//
//	public UserEntity(Long userID, String userName, String password, Date createTime, Date modifyTime,
//			Set<RoleEntity> roles, List<InfoEntity> infos, RoleEntity roleID) {
//		super();
//		this.userID = userID;
//		this.userName = userName;
//		this.password = password;
//		this.createTime = createTime;
//		this.modifyTime = modifyTime;
////		this.roles = roles;
//		this.infos = infos;
////		this.roleID = roleID;
//	}
	public UserEntity(String userName, String password, Date createTime) {
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
	}
	



	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<InfoEntity> getInfos() {
		return infos;
	}

	public void setInfos(List<InfoEntity> infos) {
		this.infos = infos;
	}






	public List<UserAndRolesEntity> getUserandroles() {
		return userandroles;
	}






	public void setUserandroles(List<UserAndRolesEntity> userandroles) {
		this.userandroles = userandroles;
	}




//	public RoleEntity getRoleID() {
//		return roleID;
//	}
//
//	public void setRoleID(RoleEntity roleID) {
//		this.roleID = roleID;
//	}


//	public Set<RoleEntity> getRoles() {
//		return roles;
//	}
//
//
//	public void setRoles(Set<RoleEntity> roles) {
//		this.roles = roles;
//	}


	
}
