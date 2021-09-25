package com.julong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.julong.dao.CityRepository;
import com.julong.dao.entity.CityInfo;
import com.julong.service.CityService;

/**
 * 业务处理类
 * @author julong
 * @date 2020年2月17日 下午10:35:29
 * @desc 
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepositoryImpl;
	
	@Override
	public Page<CityInfo> getAll(Pageable pageable) throws Exception {
		// TODO Auto-generated method stub
		return this.cityRepositoryImpl.findAll(pageable);
	}

	@Override
	public CityInfo getByCityId(int cityId) throws Exception {
		// TODO Auto-generated method stub
		return this.cityRepositoryImpl.findByCityId(cityId);
	}

}
