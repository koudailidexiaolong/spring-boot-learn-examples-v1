package com.julong.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.julong.service.UserService;
import com.julong.service.dto.UserDTO;

@Controller
public class HelloAction {

	private static final Logger logger = LoggerFactory.getLogger(HelloAction.class);
	
	@RequestMapping("")
	@ResponseBody
	public String sayHello(){
		logger.debug("debug");
		logger.error("error");
		logger.info("info");
		logger.warn("warn");
		return "hello world!";
	}
	
	@Autowired
	private UserService userServiceImpl;

	@RequestMapping("/get")
	@ResponseBody
	public UserDTO get(){
		try {
			UserDTO user = this.userServiceImpl.getUser("admin");
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public int save(){
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setPassword("000000");
			userDTO.setUserName("test");
			int  result = this.userServiceImpl.saveUser(userDTO);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public int delete(){
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserName("test");
			return this.userServiceImpl.deleteUser(userDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public int update(){
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserName("test");
			userDTO.setPassword("999999");
			return this.userServiceImpl.updateUser(userDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
