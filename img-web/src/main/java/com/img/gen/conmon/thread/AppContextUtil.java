package com.img.gen.conmon.thread;

import org.springframework.context.ApplicationContext;

/**
 * Spring ApplicationContext 获取类
 *
 */
public class AppContextUtil {
	private static transient ApplicationContext context;

	public static void setContext(ApplicationContext con) {
		if (context == null) {
			context = con;
		}
	}

	public static Object getBean(String beanId) {
		return context.getBean(beanId);
	}
}
