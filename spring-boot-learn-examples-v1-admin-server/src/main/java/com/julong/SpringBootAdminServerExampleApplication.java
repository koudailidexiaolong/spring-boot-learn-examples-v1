package com.julong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;

import de.codecentric.boot.admin.config.EnableAdminServer;


/**
 * Spring Boot执行器(Actuator)提供安全端点，用于监视和管理Spring Boot应用程序。 默认情况下，所有执行器端点都是安全的
 * @author julong
 * @date 2020年3月11日 下午8:23:13
 * @desc 
 * 使用Spring Boot Actuator Endpoint监控应用程序有点困难。 因为，如果有’n’个应用程序，每个应用程序都有单独的执行器端点，从而使监控变得困难。 
 * Spring Boot Admin Server是一个用于管理和监控Microservice应用程序的程序。
 */
@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdminServerExampleApplication.class, args);
	}

	@Bean
	public Config hazelcastConfig() {
		return new Config().setProperty("hazelcast.jmx", "true")
				.addMapConfig(new MapConfig("spring-boot-admin-application-store").setBackupCount(1)
						.setEvictionPolicy(EvictionPolicy.NONE))
				.addListConfig(new ListConfig("spring-boot-admin-event-store").setBackupCount(1)
						.setMaxSize(1000));
	}
}
