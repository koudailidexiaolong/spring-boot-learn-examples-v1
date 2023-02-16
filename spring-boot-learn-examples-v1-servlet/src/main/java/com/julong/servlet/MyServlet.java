package com.julong.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义servlet 
 * @author julong
 * @date 2023年2月15日 上午10:04:37
 * @desc 
 */
public class MyServlet extends HttpServlet{

	/**
	 * @author julong
	 * @date 2023年2月15日 上午10:04:32
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final Logger logger =LoggerFactory.getLogger(MyServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("进去doGet");
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("进去doPost");
		resp.getWriter().print("myServlet");
	}

	
}
