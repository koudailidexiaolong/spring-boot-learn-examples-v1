package com.julong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julong.dao.UserDao;
import com.julong.dao.entity.DepartmentInfo;
import com.julong.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDaoImpl;

	@Override
	public List<DepartmentInfo> selectListByPage() throws Exception {
		// TODO Auto-generated method stub
		return this.userDaoImpl.selectBySelective();
	}

}
