package com.julong.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.julong.dao.UserDao;
import com.julong.dao.entity.UserInfo;
import com.julong.service.dto.UserDTO;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDTO getUser(String userName) throws Exception {
		// TODO Auto-generated method stub
		UserDTO userDTO = this.jdbcTemplate.query("select * from user where user_name = '"+userName+"'", new ResultSetExtractor<UserDTO>(){
			@Override
			public UserDTO extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				UserDTO user = new UserDTO();
				while (rs.next()) {
					user.setUserName(rs.getString("user_name"));
					user.setUserAge(rs.getInt("user_age"));
					user.setPassword(rs.getString("password"));
					user.setUserCreateTime(rs.getDate("user_create_time"));
				}
				return user;
			}
			
		});
		return userDTO;
	}

	@Override
	public int saveUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("insert into user(user_name,password,user_age,user_create_time) "
				+ "value('"+userInfo.getUserName()+"','"+userInfo.getPassword()+"',"+userInfo.getUserAge()+",now())");
	}

	@Override
	public int deleteUser(String userName) throws Exception {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("delete from user where user_name= '"+userName+"'");
	}

	@Override
	public int updateUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("update user set password = '"+userInfo.getPassword()+"' where user_name = '"+userInfo.getUserName()+"'");
	}


}
