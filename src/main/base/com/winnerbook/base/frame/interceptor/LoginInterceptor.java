package com.winnerbook.base.frame.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.util.ConstantUtils;
import com.winnerbook.base.frame.content.ThreadLocalWrapper;
import com.winnerbook.base.frame.content.UserContext;
/**
 * 后台维护系统拦截器
 * @date 2016-2-15
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("\t\tyyyy/MM/dd HH:mm:ss");
	private static final Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String clientIpAddress = getCurrIpAddress(request);
		logger.info("============RequestParameterFilter start log============: "+clientIpAddress+dateFormat.format(new Date()));
		HttpSession session = request.getSession();
		String contextPath = (String) session.getAttribute("path");
		if(!StringUtils.isNotBlank(contextPath)){
			contextPath = request.getContextPath();
			session.setAttribute("path", contextPath);
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
			logger.info("====basePath============"+basePath);
			session.setAttribute("basePath", basePath);
			/*StringBuilder builder = new StringBuilder();
	        builder.append(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort());
	        builder.append(GlobalConfigure.IMAGE_SERVER_HTTP_PATH).toString();
	        session.setAttribute("logoHttpPath", builder.toString());*/
		}
		
		ThreadLocalWrapper.put(GlobalConfigure.HTTP_PATH, contextPath);
        ThreadLocalWrapper.put(GlobalConfigure.HTTP_BASE_PATH, session.getAttribute("basePath"));
        ThreadLocalWrapper.put(GlobalConfigure.HTTP_LOGO_PATH, session.getAttribute("logoHttpPath"));
		
		Map<String, String[]> parameterMaps = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMaps.entrySet()) {
            logger.info("Key:" + entry.getKey());
            if (entry.getValue() != null) {
                for (String str : entry.getValue()) {
                    logger.info("Value:"+str);
                }
            }
        }
		
		String servletPath = request.getServletPath();
		int firstQuestionMarkIndex = servletPath.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			servletPath = servletPath.substring(0, firstQuestionMarkIndex);
		}
		
		if(servletPath.endsWith("/login.html") || servletPath.endsWith("/dologin.html") 
				|| servletPath.endsWith("/logout.html")){
			return true;
		}
		
		UserContext userContext = (UserContext) request.getSession().getAttribute(ThreadLocalWrapper.USER_CONTEXT_KEY);
		logger.info("获取usercontext是否为空："+userContext);
		if(userContext == null){
			response.sendRedirect(contextPath+"/user/login.html");
			return false;
		}
		userContext.setBasePath((String)session.getAttribute("basePath"));
		userContext.setFileServerPath((String)session.getAttribute("logoHttpPath"));
		ThreadLocalWrapper.bind(userContext);
		return super.preHandle(request, response, handler);
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
		super.afterCompletion(request, response, handler, ex);
	}

}
