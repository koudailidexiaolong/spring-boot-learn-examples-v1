package com.julong.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * 
 * @author julong
 * @date 2020年3月7日 下午11:09:31
 * @desc @ConfigurationProperties 此种方式为 对赋值的方式 支持松散绑定 类似 user-name 对应类中的 userName
 */
@Component
@ConfigurationProperties(prefix = "user") 
@Validated //JSR303数据校验
public class User {

//	@Value("${userName}") //此种方式 类似于 配置文件中的 <bean class="user"><property name="userName" value="aaa" /> </bean>
	private String userName;
	
//	@Email  // 数据格式校验
	private int userAge;
	
	private Date userBirth;
	
	
	private boolean userSex;
	
	private Map<String,Object> maps ;
	
	
	private List<String> list;


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public int getUserAge() {
		return userAge;
	}


	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}


	public Date getUserBirth() {
		return userBirth;
	}


	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}


	public boolean isUserSex() {
		return userSex;
	}


	public void setUserSex(boolean userSex) {
		this.userSex = userSex;
	}


	public Map<String, Object> getMaps() {
		return maps;
	}


	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}


	public List<String> getList() {
		return list;
	}


	public void setList(List<String> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + ", userAge=" + userAge + ", userBirth=" + userBirth + ", userSex="
				+ userSex + ", maps=" + maps + ", list=" + list + "]";
	}
	
	
}
