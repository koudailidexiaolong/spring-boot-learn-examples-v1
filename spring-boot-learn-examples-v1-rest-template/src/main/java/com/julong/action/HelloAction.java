package com.julong.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julong.dao.entity.CityInfo;
import com.julong.service.CityService;

@RestController
public class HelloAction {

	@RequestMapping(value={"","/"})
	public String hello(){
		return "say hello world!";
	}
	
	@Autowired
	private CityService cityServiceImpl;
	
	@RequestMapping("/city")
	public CityInfo getCity(){
		CityInfo city = null;
		try {
			city = this.cityServiceImpl.getByCityId(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}
	
	@RequestMapping("/list")
	public Page<CityInfo> getCityList(){
		Pageable pageable = new PageRequest(1,10);
		try {
			Page<CityInfo> cityList = this.cityServiceImpl.getAll(pageable);
			return cityList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
