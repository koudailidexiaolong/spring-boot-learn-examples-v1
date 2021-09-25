package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * 使用Spring Boot Actuator Endpoint监控应用程序有点困难。
 *  因为，如果有’n’个应用程序，每个应用程序都有单独的执行器端点，从而使监控变得困难。 
 *  Spring Boot Admin Server是一个用于管理和监控Microservice应用程序的程序。
 * @author julong
 * @date 2020年3月11日 下午8:28:38
 * @desc 
 */
@SpringBootApplication
public class SpringBootAdminClientExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminClientExampleApplication.class, args);
	}

}
