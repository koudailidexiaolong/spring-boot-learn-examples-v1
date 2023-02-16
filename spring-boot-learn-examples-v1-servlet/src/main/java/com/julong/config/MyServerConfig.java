package com.julong.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.julong.filter.MyFilter;
import com.julong.listener.MyListener;
import com.julong.servlet.MyServlet;

/**
 * 注册三大组件
 * @author julong
 * @date 2023年2月15日 上午10:29:44
 * @desc 
 */
@Configuration
public class MyServerConfig {

	
	/**
	 * 注册servlet
	 * @return
	 * @author julong
	 * @date 2023年2月15日 上午10:29:49
	 * @desc
	 */
	@Bean
	public ServletRegistrationBean mySerlvet(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
		return servletRegistrationBean;
	}
	
	/**
	 * 注册filter
	 * @return
	 * @author julong
	 * @date 2023年2月15日 上午10:39:49
	 * @desc
	 */
	@Bean
	public  FilterRegistrationBean myFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new MyFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/myServlet"));
		return filterRegistrationBean;
	}
	
	
	/**
	 * 注册监听器
	 * @return
	 * @author julong
	 * @date 2023年2月15日 上午10:54:49
	 * @desc
	 */
	@Bean
	public ServletListenerRegistrationBean<MyListener> myListener(){
		ServletListenerRegistrationBean<MyListener> listenerRegistration = new ServletListenerRegistrationBean<MyListener>(new MyListener());
		return listenerRegistration;
	}
}
