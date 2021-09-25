package com.julong.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * springboot 启动文件
 * @author julong
 * @date 2020年1月16日 下午5:25:52
 * @desc SpringBootServletInitializer 如果使用war包 部署 需要引入 并且将此启动类作为源文件映射进去
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages="com.julong")//扫描的包
public class SpringBootApplicationStarter extends SpringBootServletInitializer{

	

	/* (non-Javadoc) 需要扩展类SpringBootServletInitializer以支持WAR文件部署
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringBootApplicationStarter.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SpringBootApplicationStarter.class, args);
	}

}
