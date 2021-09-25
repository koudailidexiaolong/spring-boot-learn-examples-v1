package com.julong.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.julong.dao.entity.DepartmentInfo;
import com.julong.service.UserService;

@Controller
public class HelloAction {

	private static final Logger logger = LoggerFactory.getLogger(HelloAction.class);
	

	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping("")
	@ResponseBody
	public List<DepartmentInfo> sayHello(){
		
		logger.debug("debug");
		logger.error("error");
		logger.info("info");
		logger.warn("warn");
		
		try {
			PageHelper.startPage(1, 10,true);
//			Page<DepartmentInfo> pages = new Page<DepartmentInfo>(1, 10, true);
			List<DepartmentInfo> list = this.userServiceImpl.selectListByPage();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
