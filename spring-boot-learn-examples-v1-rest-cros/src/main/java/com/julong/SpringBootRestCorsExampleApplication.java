package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author julong
 * @date 2020年2月17日 下午10:34:23
 * @desc 
 *  * 	跨源资源共享(CORS)是一种安全概念，用于限制Web浏览器中实现的资源。 它可以防止JavaScript代码产生或消耗针对不同来源的请求。
	例如，Web应用程序在8080端口上运行，并且使用JavaScript尝试从9090端口使用RESTful Web服务。在这种情况下，在Web浏览器上将面临跨源资源共享安全问题。
	处理此问题需要两个要求 -
	RESTful Web服务应该支持跨源资源共享。RESTful Web服务应用程序应允许从8080端口访问API
	
	报错 同源禁止访问 了 目前测试没有通过  
 */
@SpringBootApplication
public class SpringBootRestCorsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestCorsExampleApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//	   return new WebMvcConfigurerAdapter() {
//	      @Override
//	      public void addCorsMappings(CorsRegistry registry) {
//	         registry.addMapping("/api/*")
//			         .allowedOrigins("*")
//			         .allowedHeaders("*")
//			         .allowedMethods("*");
//	      }    
//	   };
//	}

//	//注入bean 模板类
//	@Bean
//	public RestTemplate getRestTemplate(){
//		return new RestTemplate();
//	}

}
