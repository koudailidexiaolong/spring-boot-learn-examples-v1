package com.julong.dao.entity;

public class DepartmentInfo {

	private String depId;

	/**
	 * @return String the depId
	 */
	public String getDepId() {
		return depId;
	}

	/**
	 * @param depId the depId to set
	 */
	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Override
	public String toString() {
		return "DepartmentInfo [depId=" + depId + "]";
	}
	
	
}
