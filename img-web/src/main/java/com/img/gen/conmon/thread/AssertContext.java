package com.img.gen.conmon.thread;

/**
 * 每次请求中会在filter 中，将用户的登录信息放入线程变量中，可以直接获取到
 */
public class AssertContext {

	private static ThreadLocal<User> allContext = new ThreadLocal<User>();

	public static void init(User user) {
		allContext.set(user);
	}

	public static User get() {
		return allContext.get();
	}

	public static Long getUserId() {
		User userObject = get();
		if (userObject != null) {
			return userObject.getUserId();
		}
		return null;
	}

	public static String userName() {
		User userObject = get();
		if (userObject != null) {
			return userObject.getUserName();
		}
		return null;
	}

	public final static void destroy() {
		allContext.remove();
	}

}
