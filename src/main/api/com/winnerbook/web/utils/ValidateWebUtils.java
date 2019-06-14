package com.winnerbook.web.utils;

import org.apache.commons.lang.StringUtils;

public class ValidateWebUtils {
	
	public static String defaultBus(String busId){
		if(!StringUtils.isNotBlank(busId) || "0".equals(busId)){
			busId = ConstantWebUtils.busIdDefulat;
		}
		return busId;
	}

}
