package com.julong.hsqldb.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.julong.hsqldb.entity.City;

/**
 * 此处使用spring jpa
 * @author julong
 * @date 2020年4月28日 下午3:03:14
 * @desc 
 */
@org.springframework.stereotype.Repository
public interface CityRepository extends Repository<City, Long> {

	/**
	 * 分页查询所有数据
	 * @param pageable
	 * @return
	 * @author julong
	 * @date 2020年4月28日 下午3:03:48
	 * @desc
	 */
	public abstract Page<City> findAll(Pageable pageable);

	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 * @author julong
	 * @date 2020年4月28日 下午3:03:35
	 * @desc
	 */
	public abstract City findById(Long id);
}
