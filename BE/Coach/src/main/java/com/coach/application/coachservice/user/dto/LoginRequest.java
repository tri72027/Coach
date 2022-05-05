package com.coach.application.coachservice.user.dto;

public class LoginRequest {
	private String userName;
	private String password;

	/**
	 * Create an empty LoginRequest object
	 */
	public LoginRequest() {
		super();
	}

	/**
	 * Create a LoginRequest object with full attributes
	 * 
	 * @param username user's user name
	 * @param password
	 */
	public LoginRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return userName;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.userName = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
