package com.julong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 启动类
 * @author julong
 * @date 2020年2月16日 下午2:41:10
 * @desc 
 */
@MapperScan(basePackages="com.julong.dao") //mybaits 的扫描
@SpringBootApplication
public class SpringBootPaghelperExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPaghelperExampleApplication.class, args);
    }

}
