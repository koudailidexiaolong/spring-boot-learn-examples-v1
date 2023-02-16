package org.julong.spring.boot.v1.starter.action;

import org.julong.spring.boot.v1.service.JulongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloAction {

	@Autowired
	private JulongService julongService;
	
	@GetMapping("/")
	@ResponseBody
	public String index(){
		String str = this.julongService.sayHello("哈哈");
		return str;
	}
}
