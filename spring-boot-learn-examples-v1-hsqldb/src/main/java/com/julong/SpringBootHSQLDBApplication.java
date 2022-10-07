package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 内存数据库的使用
 * @author julong
 * @date 2020年4月28日 下午3:02:50
 * @desc 
 */
@Configuration
@EnableAutoConfiguration
//spring boot 扫描包 有时候 配置前缀 会
@ComponentScan(basePackages={"com.julong"})
public class SpringBootHSQLDBApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootHSQLDBApplication.class, args);
	}

}
