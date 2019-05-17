package com.winnerbook.base.common.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	/**
	 *获取当前IP地址
	 *@param request
	 *@return String
	 */
	public static String getCurrIpAddress(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if(ipAddress.indexOf(",") != -1){
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }
}
