package com.julong.hsqldb.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.julong.hsqldb.service.CityService;

@Controller
public class IndexController {

	@Autowired
	private CityService cityService;

	@RequestMapping("/")
	@ResponseBody
	public String helloWorld() {
		return this.cityService.findById((long) 1).getName();
	}
}
