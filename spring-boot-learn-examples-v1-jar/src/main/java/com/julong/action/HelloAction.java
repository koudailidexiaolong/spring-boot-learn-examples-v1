package com.julong.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@RequestMapping("/")
	@ResponseBody
	public String sayHello(){
		
		return "Hello springboot";
	}
}
