package com.julong.dao;

import java.util.List;

import com.julong.dao.entity.DepartmentInfo;

public interface UserDao {
	
	public abstract List<DepartmentInfo> selectBySelective();
	
}
