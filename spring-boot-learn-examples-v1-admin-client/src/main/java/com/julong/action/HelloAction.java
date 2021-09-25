package com.julong.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloAction {

	private static final Logger logger = LoggerFactory.getLogger(HelloAction.class);
	
	@ResponseBody
	@RequestMapping("/sayHello")
	public String hello(){
		logger.trace("这是 trace日志");
		logger.debug("这是 debug日志");
		logger.info("这是 info日志");
		logger.warn("这是 warn日志");
		logger.error("这是 error日志");
		return "Hello";
	}
}
