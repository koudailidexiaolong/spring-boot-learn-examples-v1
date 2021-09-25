package com.julong.dao.entity;

import java.util.Date;

/**
 * 用户类
 * @author julong
 * @date 2020/2/21 19:07
 * @desc
 */
public class UserInfo {
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
}
