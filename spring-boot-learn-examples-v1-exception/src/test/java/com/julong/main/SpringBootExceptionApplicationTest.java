package com.julong.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

public class SpringBootExceptionApplicationTest {

	/**
	 * @param args
	 * @author julong
	 * @date 2020年2月14日 上午10:03:13
	 * @desc
	 */
	public static void main(String[] args) {
//		Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld1");
//		logger.debug("Hello world.");

		Logger logger = LoggerFactory.getLogger("chapters.introduction.HelloWorld2");
		logger.debug("Hello world.");
		logger.info("Hello world.");
		logger.error("Hello world.");
		logger.trace("Hello world.");
		logger.warn("Hello world.");

		// print internal state
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(lc);
	}
}
