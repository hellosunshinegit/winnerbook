package com.winnerbook.base.frame.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
/**
 * 前台微信拦截器
 * @author nql
 * @date 2015-2-4
 *
 */
public class LoginFrontInterceptor extends HandlerInterceptorAdapter{
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("\t\tyyyy/MM/dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(LoginFrontInterceptor.class);
	
	@SuppressWarnings("unchecked")
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
		String clientIpAddress = getCurrIpAddress(request);
        logger.info("RequestParameterFilter begin log: " + clientIpAddress+dateFormat.format(new Date()));
        logger.info("RequestServlet: " + request.getRequestURI());
        
		Map<String, String[]> parameterMaps = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMaps.entrySet()) {
            logger.info("Key:" + entry.getKey());
            if (entry.getValue() != null) {
                for (String str : entry.getValue()) {
                    logger.info("Value:"+str);
                }
            }
        }
        //判断 是非需要登录。。。。
        
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
			throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 *获取当前IP地址
	 *@param request
	 *@return String
	 */
	private String getCurrIpAddress(HttpServletRequest request){
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
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("MobileInterceptor: afterCompletion");
		String clientIpAddress = getCurrIpAddress(request);
		ThreadLocalWrapper.unbind();
		logger.info("============RequestParameterFilter end log============: "+clientIpAddress+dateFormat.format(new Date()));
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域访问
		super.afterCompletion(request, response, handler, ex);
	}
}
