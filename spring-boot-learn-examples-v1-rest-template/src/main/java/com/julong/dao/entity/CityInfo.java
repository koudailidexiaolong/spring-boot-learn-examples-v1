package com.julong.dao.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 数据库映射类
 * @author julong
 * @date 2020年2月17日 下午10:35:13
 * @desc 
 */
@Entity
@Table(name="city") //指定对应的表结构
public class CityInfo implements Serializable{

	/**
	 * @author julong
	 * @date 2020年2月17日 下午8:53:16
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 * @author julong
	 * @date 2020年2月17日 下午8:51:54
	 */
	@Id //主键
	@GeneratedValue //自动序列
	@Column(name="city_id") //对应的列对应的数据库的列
	private int cityId;
	
	/**
	 * 城市名称
	 * @author julong
	 * @date 2020年2月17日 下午8:52:04
	 */
	@Column(name="city")
	private String city;
	
	/**
	 * 国家编号
	 * @author julong
	 * @date 2020年2月17日 下午8:52:21
	 */
	@Column(name="country_id")
	private int countryId;
	
	/**
	 * 最后更新时间
	 * @author julong
	 * @date 2020年2月17日 下午8:52:41
	 */
	@Column(name="last_update")
	private Date lastUpdate;

	/**
	 * @return int the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return String the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return int the countryId
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return Date the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityInfo [cityId=" + cityId + ", city=" + city + ", countryId="
				+ countryId + ", lastUpdate=" + lastUpdate + "]";
	}

}
