package com.julong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julong.dao.UserDao;
import com.julong.dao.entity.UserInfo;
import com.julong.service.UserService;
import com.julong.service.dto.UserDTO;

/**
 * 用户业务模块
 * @author julong
 * @date 2020年2月21日 下午6:42:37
 * @desc 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;
	@Override
	public UserDTO getUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return this.userDaoImpl.getUser(userId);
	}
	/**
	 * 新增用户信息
	 * @param userDTO
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月21日 下午7:36:42
	 * @desc
	 */
	@Override
	public int saveUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userDTO.getUserName());
		userInfo.setPassword(userDTO.getPassword());
		userInfo.setUserAge(12);
		return this.userDaoImpl.saveUser(userInfo);
	}
	
	@Override
	public int updateUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(userDTO.getUserName());
		userInfo.setPassword(userDTO.getPassword());
		return this.userDaoImpl.updateUser(userInfo);
	}
	
	@Override
	public int deleteUser(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		return this.userDaoImpl.deleteUser(userDTO.getUserName());
	}

	
}
