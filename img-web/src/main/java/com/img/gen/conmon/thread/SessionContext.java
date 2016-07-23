package com.img.gen.conmon.thread;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

/**
 * 在filter 中，将session 传入到 线程变量中，代码中所有session的操作，通过线程变量操作 
 *
 */
public final class SessionContext {
	
	private static ThreadLocal<HttpSession> context = new ThreadLocal<HttpSession>();

	public static void init(HttpSession httpSession) {
		context.set(httpSession);
	}

	public static final Object get(String key) {
		if (context.get() == null) {
			return null;
		}
		return context.get().getAttribute(key);
	}

	public final static String getString(String key) {
		Object o = get(key);
		if(o==null){
			return null;
		}
		return String.valueOf(o);
	}
	

	public final static Long getLong(String key) {
		Object o = get(key);
		if(o==null){
			return null;
		}
		return Long.valueOf(String.valueOf(o));
	}

	public final static Integer getInteger(String key) {
		Object o = get(key);
		if(o==null){
			return null;
		}
		return Integer.valueOf(String.valueOf(o));
	}

	public final static void set(String key, Object value) {
		if (context.get() == null) {
			return;
		}
		context.get().setAttribute(key, value);
	}

	@SuppressWarnings("unchecked")
	public final static Enumeration<String> getAttributeNames() {
		if (context.get() == null) {
			return null;
		}
		return context.get().getAttributeNames();
	}

	public final static void remove(String key) {
		if (context.get() == null) {
			return;
		}
		context.get().removeAttribute(key);
	}

	public final static void destroy() {
		context.remove();
	}
}
