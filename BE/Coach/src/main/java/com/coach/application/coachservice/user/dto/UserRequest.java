package com.coach.application.coachservice.user.dto;

import java.sql.Date;
import java.util.Set;

public class UserRequest {
	private Long userID;

	private String userName;

	private String password;

	private Date createTime;

	private Date modifyTime;

	private Long roleID;
	private String role;

	public UserRequest() {
	}

	public UserRequest(Long userID, String userName, String password, Date createTime, Date modifyTime, Long roleID) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.roleID = roleID;
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

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
