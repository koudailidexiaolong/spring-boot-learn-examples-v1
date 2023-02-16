package com.julong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot 启动文件
 * @author julong
 * @date 2020年1月16日 下午5:25:52
 * @desc SpringBootServletInitializer 如果使用war包 部署 需要引入 并且将此启动类作为源文件映射进去
 */
@SpringBootApplication
@ComponentScan(basePackages="com.julong")//扫描的包
public class SpringBootExceptionApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootExceptionApplication.class, args);
	}

}
