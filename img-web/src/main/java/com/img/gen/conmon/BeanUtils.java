package com.img.gen.conmon;


import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * Created by zx.dev on 2016/1/11.
 */
public class BeanUtils {

//    public static Map<String,BeanCopier> beanCopierMap = new HashMap<String,BeanCopier>();

//    public static void copyProperties(Object source, Object target){
//        String beanKey =  generateKey(source.getClass(), target.getClass());
//        BeanCopier copier =  null;
//        if(!beanCopierMap.containsKey(beanKey)){
//            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
//            beanCopierMap.put(beanKey, copier);
//        }else{
//            copier = beanCopierMap.get(beanKey);
//        }
//        copier.copy(source, target, null);
//    }
//    private static String generateKey(Class<?> class1,Class<?>class2){
//        return class1.toString() + class2.toString();
//    }

    public static void copyProperties(Object source, Object target) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    public static void copyProperties(Object source, Object target, Converter converter) {
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), true);
        copier.copy(source, target, converter);
    }

    /**
     * 批量拷贝集合的方法
     * @param sourceList
     * @param targetList
     * @param clasz
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static <T, V> ArrayList<V> copyPropertieses(List<T> sourceList, ArrayList<V> targetList, Class<?> clasz) throws Exception{
    	if (sourceList != null && sourceList.size() >= 1) {
	    	for (T t : sourceList) {
	    			V instance = (V)clasz.newInstance();
					copyProperties(t, instance);
					targetList.add(instance);
	    	}
	    	return targetList;
    	}
    	return targetList;
    }
}
