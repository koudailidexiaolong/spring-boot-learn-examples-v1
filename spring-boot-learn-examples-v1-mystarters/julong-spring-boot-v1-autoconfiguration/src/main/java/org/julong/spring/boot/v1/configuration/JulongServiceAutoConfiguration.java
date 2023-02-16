package org.julong.spring.boot.v1.configuration;

import org.julong.spring.boot.v1.service.JulongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 * @author julong
 * @date 2023年2月16日 上午9:41:11
 * @desc 
 */
@Configuration
@ConditionalOnWebApplication //web 环境生效
@EnableConfigurationProperties(JulongProperties.class)
public class JulongServiceAutoConfiguration {

	@Autowired
	private JulongProperties julongProperties;
	
	@Bean
	public JulongService julongService(){
		JulongService service = new JulongService();
		service.setJulongProperties(julongProperties);
		return service;
	}
}
