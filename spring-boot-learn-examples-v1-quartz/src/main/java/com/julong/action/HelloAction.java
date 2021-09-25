package com.julong.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试类
 * @author julong
 * @date 2020年1月16日 下午5:48:01
 * @desc 
 */
@Controller
public class HelloAction {


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	private String username;

	private static final Logger logger =LoggerFactory.getLogger(HelloAction.class);

	public HelloAction() {
	}

	@RequestMapping("/")
	@ResponseBody
	public String sayHello(){
		logger.info("info日志");
		logger.debug("debug日志");
		logger.error("error日志");
		logger.warn("warn日志");
		logger.trace("trace日志");
		return "Hello springboot";
	}
}
