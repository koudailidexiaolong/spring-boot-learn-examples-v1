package com.julong.service;

import java.util.List;

import com.julong.dao.entity.DepartmentInfo;

public interface UserService {
	
	public abstract List<DepartmentInfo> selectListByPage() throws Exception;
}
