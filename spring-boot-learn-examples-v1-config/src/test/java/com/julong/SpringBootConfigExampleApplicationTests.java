package com.julong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.julong.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootConfigExampleApplicationTests {

	@Autowired
	User user;
	@Test
	public void contextLoads() {
		
		System.out.println(user);
	}

}
