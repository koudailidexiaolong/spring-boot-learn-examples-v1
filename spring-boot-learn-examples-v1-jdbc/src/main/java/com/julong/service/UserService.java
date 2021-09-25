package com.julong.service;

import com.julong.service.dto.UserDTO;

/**
 * 用户业务模块
 * @author julong
 * @date 2020年2月21日 下午6:41:51
 * @desc 
 */
public interface UserService {

	/**
	 * 获取单个用户信息
	 * @param userId
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月21日 下午6:42:07
	 * @desc
	 */
	public abstract UserDTO getUser(String userId) throws Exception;
	
	/**
	 * 新增用户信息
	 * @param userDTO
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月21日 下午7:36:42
	 * @desc
	 */
	public abstract int saveUser(UserDTO userDTO) throws Exception;
	/**
	 * 更新用户信息
	 * @param userDTO
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月21日 下午7:36:42
	 * @desc
	 */
	public abstract int updateUser(UserDTO userDTO) throws Exception;
	/**
	 * 删除用户信息
	 * @param userDTO
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月21日 下午7:36:42
	 * @desc
	 */
	public abstract int deleteUser(UserDTO userDTO) throws Exception;
	
}
