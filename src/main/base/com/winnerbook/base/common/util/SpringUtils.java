package com.winnerbook.base.common.util;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.MethodInvoker;

public class SpringUtils implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}

	public static Object getBean(String name) {
	    return applicationContext.getBean(name);
	}
	
	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
   }
	
	/**
	 * 运行Bean中指定名称的方法并返回其返回值
	 * 
	 * @param object
	 *            object对象
	 * @param methodName
	 *            方法名
	 * @param arguments
	 *            参数值
	 * @return 运行bean中指定名称的方法的返回值
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 */
	 public static Object invokeBeanMethod(String beanName, String methodName,
	    Object[] arguments) {
		try {
			    // 初始化
		    MethodInvoker methodInvoker = new MethodInvoker();
		  
			methodInvoker.setTargetObject(SpringUtils.getBean(beanName));
			methodInvoker.setTargetMethod(methodName);
		
			// 设置参数
			if (arguments != null && arguments.length > 0) {
				methodInvoker.setArguments(arguments);
			}
		
			// 准备方法
			methodInvoker.prepare();
		
			// 执行方法
		    return methodInvoker.invoke();
		  } catch (ClassNotFoundException e) {
			  throw new RuntimeException(e);
		  } catch (NoSuchMethodException e) {
			  throw new RuntimeException(e);
		  } catch (InvocationTargetException e) {
			  throw new RuntimeException(e);
		  } catch (IllegalAccessException e) {
			  throw new RuntimeException(e);
		  }
		}

}
