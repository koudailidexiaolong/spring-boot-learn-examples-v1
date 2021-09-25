package com.julong.service.dto;

import java.util.Date;

public class UserDTO {

	private String userName;
	
	private String password;
	
	private int userAge;
	
	private Date userCreateTime;

	/**
	 * @return String the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return String the password
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

	/**
	 * @return int the userAge
	 */
	public int getUserAge() {
		return userAge;
	}

	/**
	 * @param userAge the userAge to set
	 */
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	/**
	 * @return Date the userCreateTime
	 */
	public Date getUserCreateTime() {
		return userCreateTime;
	}

	/**
	 * @param userCreateTime the userCreateTime to set
	 */
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", password=" + password
				+ ", userAge=" + userAge + "]";
	}
	
	
	
}
