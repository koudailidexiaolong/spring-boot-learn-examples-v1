package com.julong.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 交互类
 * @author julong
 * @date 2020年1月16日 下午7:51:19
 * @desc 
 */
@Controller
public class HelloAction {

	private static final Logger logger =Logger.getLogger(HelloAction.class);
	
	@RequestMapping("/")
	@ResponseBody
	public String sayHello(){
		logger.info("info日志");
		logger.debug("debug日志");
		logger.fatal("fatal日志");
		logger.error("error日志");
		logger.warn("warn日志");
		return "Hello springboot";
	}
	
	/**
	 * 自定义参数
	 * @author julong
	 * @date 2020年1月16日 下午7:58:05
	 */
	@Value("${system.param.name}")
	private String username;
	
	/**
	 * 参数的使用
	 * @return
	 * @author julong
	 * @date 2020年1月16日 下午7:57:55
	 * @desc
	 */
	@RequestMapping("/useParam")
	@ResponseBody
	public String useParamSayHello(){
		return username;
	}
}
