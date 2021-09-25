package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 * @author julong
 * @date 2020年2月17日 下午10:34:23
 * @desc 
 * 
 * 使用 rest 方式  并且使用  rest template 模板类   使用
 */
@SpringBootApplication
public class SpringBootRestTemplateExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestTemplateExampleApplication.class, args);
	}

	//注入bean 模板类
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
