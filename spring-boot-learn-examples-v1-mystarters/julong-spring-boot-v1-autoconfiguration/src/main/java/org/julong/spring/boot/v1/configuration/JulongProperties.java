package org.julong.spring.boot.v1.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置属性类
 * @author julong
 * @date 2023年2月16日 上午9:37:20
 * @desc 
 */
@ConfigurationProperties(prefix="julong")
public class JulongProperties {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
