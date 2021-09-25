package com.julong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot 启动文件 
 * 实现多个 配置文件 dev prod test 等多个配置文件的使用过程的方式
 * @author julong
 * @date 2020年1月16日 下午5:25:52
 * @desc 
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.julong") //需要扫描的包
public class SpringBootApplicationStarter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootApplicationStarter.class, args);
	}

}
