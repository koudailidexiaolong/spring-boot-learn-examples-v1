package com.julong.hsqldb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julong.hsqldb.dao.CityRepository;
import com.julong.hsqldb.entity.City;
import com.julong.hsqldb.service.CityService;

/**
 * 数据处理层
 * @author julong
 * @date 2020年4月28日 下午3:04:08
 * @desc 
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public City findById(Long id) {
		// TODO Auto-generated method stub
		return this.cityRepository.findById(id);
	}
	
	
	
}
