package com.julong.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.julong.dao.entity.CityInfo;

/**
 * 城市数据库访问类
 * @author julong
 * @date 2020年2月17日 下午10:34:36
 * @desc 
 */
public interface CityRepository extends CrudRepository<CityInfo, Integer> {

	/**
	 * 分页查询
	 * @param pageable 分页参数
	 * @return
	 * @author julong
	 * @date 2020年2月17日 下午10:34:49
	 * @desc
	 */
	public abstract Page<CityInfo> findAll(Pageable pageable); 
	
	/**
	 * 查询单个信息
	 * @param cityId
	 * @return
	 * @author julong
	 * @date 2020年2月17日 下午10:35:02
	 * @desc
	 */
	public abstract CityInfo findByCityId(int cityId); 
	
}
