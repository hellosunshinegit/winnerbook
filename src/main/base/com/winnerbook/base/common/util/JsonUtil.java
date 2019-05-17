package com.winnerbook.base.common.util;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/** 
 * 
* <p>Title: Json工具类</p>
* <p>Description: </p>
* <p>Company: minxin</p> 
* @author    xiabz
* @date       Oct 28, 2013
 */
public class JsonUtil{

	/**
	 * 
	 * 将java对象转换成json字符串
	 * 
	 * @param javaObj
	 * 
	 * @return
	 */

	public static String getJsonString4JavaPOJO(Object javaObj) {

		JSONObject json;

		json = JSONObject.fromObject(javaObj);

		return json.toString();

	}
	
	
	
	/**
	 * 
	 * 将java对象转换成json字符串，并设定日期格式
	 * 
	 * @param javaObj
	 * 
	 * @param dataFormat
	 * 
	 * @return
	 */

	public static String getJsonArrayString4JavaPOJO(Object javaObj,
			String dataFormat) {

		JSONArray json;

		JsonConfig jsonConfig = configJson("yyyy-MM-dd HH:mm:ss");

		json = JSONArray.fromObject(javaObj, jsonConfig);

		return json.toString();

	}
	
	/**
	 * 
	 * JSON 时间解析器具
	 * 
	 * @param datePattern
	 * 
	 * @return
	 */

	public static JsonConfig configJson(String datePattern) {

		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setExcludes(new String[] { "" });

		jsonConfig.setIgnoreDefaultExcludes(false);

		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		jsonConfig.registerJsonValueProcessor(Date.class,
				new DateJsonValueProcessor(datePattern));

		return jsonConfig;

	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 从一个JSON 对象字符格式中得到一个java对象
	 * </p>
	 * 
	 * @author Robin
	 * @param jsonString
	 * @param pojoCalss
	 * @return
	 */
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		Object pojo = null;
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			pojo = JSONObject.toBean(jsonObject, pojoCalss);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo;
	}

}
