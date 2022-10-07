package com.julong.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.julong.dao.entity.CityInfo;
import com.julong.service.CityService;

/**
 * 城市模块
 * @author julong
 * @date 2020年3月10日 下午8:12:22
 * @desc 
 */
@CrossOrigin
@RestController
public class CityAction {

	@Autowired
	private CityService cityServiceImpl;
	
	/**
	 * 查询集合信息
	 * @return
	 * @author julong
	 * @date 2020年3月10日 下午8:12:42
	 * @desc
	 */
   
	@RequestMapping(value="/citys",method=RequestMethod.GET)
	public ResponseEntity<Object> getCityList(){
		Pageable pageable = new PageRequest(1, 10);
		try {
			Page<CityInfo> list = this.cityServiceImpl.getAll(pageable);
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常返回
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 根据编号查询
	 * @param cityId
	 * @return
	 * @author julong
	 * @date 2020年3月10日 下午8:13:30
	 * @desc
	 */
	@RequestMapping(value="/city/{cityId}",method=RequestMethod.GET)
	public ResponseEntity<Object> getCity(@PathVariable("cityId") int cityId){
		try {
			CityInfo cityInfo = this.cityServiceImpl.getByCityId(cityId);
			return new ResponseEntity<Object>(cityInfo,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//异常返回
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
