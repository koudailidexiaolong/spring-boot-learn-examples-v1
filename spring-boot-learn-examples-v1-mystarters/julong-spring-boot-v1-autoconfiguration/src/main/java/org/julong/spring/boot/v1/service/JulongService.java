package org.julong.spring.boot.v1.service;

import org.julong.spring.boot.v1.configuration.JulongProperties;

public class JulongService {

	private JulongProperties julongProperties;
	
	
	public JulongProperties getJulongProperties() {
		return julongProperties;
	}


	public void setJulongProperties(JulongProperties julongProperties) {
		this.julongProperties = julongProperties;
	}


	public String sayHello(String name){
		return "自定义starter"+name+"julongProperties:"+julongProperties.getName();
	}
}
