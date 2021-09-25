package com.julong.action;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {


	//定时任务配置
	@Scheduled(cron="0/1 * * * * ? ")
	public void init(){
		System.out.println("进来了");
	}
}
