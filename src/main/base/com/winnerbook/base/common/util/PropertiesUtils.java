package com.winnerbook.base.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
 	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	private static Properties config = null;

	static {
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("common.properties");
		config = new Properties();
		try {
			config.load(in);
			in.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

	// 根据key读取value
	public static String readValue(String key) {
		// Properties props = new Properties();
		try {
			String value = config.getProperty(key);
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
}
