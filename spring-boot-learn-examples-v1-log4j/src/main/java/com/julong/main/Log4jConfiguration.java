package com.julong.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 引入log4j日志模块
 * @author julong
 * @date 2020年2月12日 下午9:54:00
 * @desc 
 */
@Configuration
@ConfigurationProperties("classpath:log4j.properties")//引入 log4j 配置文件
public class Log4jConfiguration {

}
