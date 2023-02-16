package com.dragon.entity;

import java.io.Serializable; 
/**  
 * 用户实体类  
 * @author Administrator  
 */ 
public class User implements Serializable {  
	public int getUserId() {   
		return userId;  
		}  
	public void setUserId(int userId) {   
		this.userId = userId;  
		}  
	public String getUserName() {   
		return userName; 
		} 
	public void setUserName(String userName) {   
		this.userName = userName;  
		}  
	public String getUserPassword() {   
		return userPassword;  
		}  
	public void setUserPassword(String userPassword) {   
		this.userPassword = userPassword;  
		}  
	public static long getSerialversionuid() {   
		return serialVersionUID;  
		} 
	/**   *   */  
	private static final long serialVersionUID = 1L;  
	private int userId;  
	private String userName; 
	private String userPassword;  
	public User() {  
		super();  
	}

}