package com.julong.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.julong.dao.entity.CityInfo;

public interface CityService {

	/**
	 * 分页查询所有的数据
	 * @param pageable 分页参数
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月18日 上午9:16:28
	 * @desc
	 */
	public abstract Page<CityInfo> getAll(Pageable pageable) throws Exception;
	
	/**
	 * 根据编号查询数据
	 * @param cityId
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月18日 上午9:16:43
	 * @desc
	 */
	public abstract CityInfo getByCityId(int cityId) throws Exception;
	
	
	/**
	 * 删除
	 * @param cityId
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月22日 上午11:27:55
	 * @desc
	 */
	public abstract int delete(int cityId) throws Exception;
	
	/**
	 * 新增
	 * @param cityInfo
	 * @return
	 * @throws Exception
	 * @author julong
	 * @date 2020年2月22日 上午11:27:58
	 * @desc
	 */
	public abstract CityInfo save(CityInfo cityInfo) throws Exception;
}
