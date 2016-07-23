package com.img.gen.conmon.thread;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * http请求的上下文信息，在当前线程内可以获取req请求头中所有的信息
 * 在 CommonTransformFilter 中设置的值
 * 
 */
public class RequestContext {
	
	
	private static ThreadLocal<Map<String, String>> context = new ThreadLocal<Map<String,String>>();

	public static void init(Map<String, String> map){
		if(context.get()==null){
			context.set(map);
		}else{
			context.get().putAll(map);
		}
	}
	
	public static String get(String key){
		if(null!=context.get()){
			return context.get().get(key);
		}
		return null;
	}
	
	public static Set<String> getKeys(){
		if(null!=context.get()){
			return context.get().keySet();
		}
		return null;
	}
	
	public static Map<String, String> getHeadMap(){
		if(null!=context.get()){
			return context.get();
		}
		return null;
	}
	
	private final static String LANGUAGE = "language";
	private final static String COUNTRY = "country";
	private final static String VARIANT = "variant";

	public static void setLocale(Locale locale) {
		Map<String, String> localeMap = new HashMap<String, String>();
		localeMap.put(LANGUAGE, locale.getLanguage());
		localeMap.put(COUNTRY, locale.getCountry());
		localeMap.put(VARIANT, locale.getVariant());
		init(localeMap);
	}
	
	public static Locale getLocale() {
		if(null!=context.get()){
			Locale Locale = new Locale(get(LANGUAGE), get(COUNTRY), get(VARIANT));
			return Locale;
		}
		return null;
	}
	
	public final static void destroy(){
		context.remove();
	}
	
}
