package com.sysco.house.common.utils;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j
public class ObjectUtil {

	public static Object transfer(Object from, Object target ) {
		if (from == null) {
			return null;
		}

		try {
			Class targetClass=target.getClass();
			Field[] targetFields = targetClass.getDeclaredFields();// 目标对象类型的属性数组
			if(!(targetClass.getSuperclass().isAssignableFrom(Object.class))){
				List<Field> fieldList= Arrays.asList(targetFields);
				List<Field> tmp=new ArrayList<Field>(fieldList);
				tmp.addAll(Arrays.asList(targetClass.getSuperclass().getDeclaredFields()));
				targetFields=tmp.toArray(targetFields) ;
			}
			Method fromMethod = null;
			Method targetMethod = null;
			Object data;// 属性值
			String methodFiled = null;
			for (Field targetField : targetFields) {

				fromMethod = null;
				methodFiled = getBig(targetField.getName());

				try {
					fromMethod = from.getClass().getDeclaredMethod("get" // 根据
							// 目标属性获取来源对象get属性的方法
							+ methodFiled);
				} catch (Exception e) {

					try {
						fromMethod = from.getClass().getSuperclass().getDeclaredMethod("get" // 根据
								// 目标属性获取来源对象get属性的方法
								+ methodFiled);
					} catch (Exception ex) {
						//logger.info("转换类型获取方法异常" + targetField.getName(),ex);

						Class<?>[] interfaces= from.getClass().getInterfaces();

						for(Class<?> interfaceClass: interfaces){
							try {
								fromMethod =interfaceClass.getDeclaredMethod("get" // 根据
										// 目标属性获取来源对象get属性的方法
										+ methodFiled);
								if(fromMethod!=null) {
									break;
								}
							} catch (Exception em) {

							}
						}

					}
				}

				if (fromMethod == null) {
					continue;
				}
				data = fromMethod.invoke(from);// 调用来源对象的get方法获取值
				try {
					targetMethod = null;
					targetMethod = targetClass.getDeclaredMethod("set" + methodFiled, data.getClass());

				} catch (Exception e) {
					try {
						targetMethod = null;
						targetMethod = targetClass.getSuperclass().getDeclaredMethod("set" + methodFiled, data.getClass());

					} catch (Exception exc) {
						//logger.info("转换类型存放方法异常" + targetField.getName(), exc);
					}				}
				if (targetMethod != null) {
					targetMethod.invoke(target, data);// 目标对象调用set方法
				}
			}

			return target;
		} catch (Exception e) {
			log.error("转换类型异常", e);
			return null;
		}
	}

	public static String getBig(String filedName) {
		return filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
	}

}
