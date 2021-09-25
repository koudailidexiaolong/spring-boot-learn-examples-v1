package com.julong.dao;

import com.julong.dao.entity.UserInfo;
import com.julong.service.dto.UserDTO;

/**
 * 用户信息dao
 * @author julong
 * @date 2020年2月21日 下午7:28:27
 * @desc 
 */
public interface UserDao {

	/**
	 * 查询用户信息
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月21日 下午7:28:34
	 * @desc
	 */
	public abstract UserDTO getUser(String userName) throws Exception;
	/**
	 * 新增用户信息
	 * @param userInfo
	 * @return
	 * @author julong
	 * @date 2020年2月21日 下午7:29:00
	 * @desc
	 */
	public abstract int saveUser(UserInfo userInfo) throws Exception;
	/**
	 * 删除用户信息
	 * @param userName
	 * @return
	 * @author julong
	 * @date 2020年2月21日 下午7:29:00
	 * @desc
	 */
	public abstract int deleteUser(String userName) throws Exception;
	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 * @author julong
	 * @date 2020年2月21日 下午7:29:00
	 * @desc
	 */
	public abstract int updateUser(UserInfo userInfo) throws Exception;
}
