package com.wzy.config;

import org.springframework.context.annotation.Configuration;

/**
 * 通过SimpleMappingExceptionResolver做全局异常处理
 *
 *
 */
@Configuration
public class SimpleGlobalException {
	
	/**
	 * 该方法必须要有返回值。返回值类型必须是：SimpleMappingExceptionResolver
	 * 该方式无法传递异常信息
	 */
	/*@Bean
	public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver(){
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		
		Properties mappings = new Properties();
		
		*//**
		 * 参数一：异常的类型，注意必须是异常类型的全名
		 * 参数二：视图名称
		 *//*
		mappings.put("java.lang.ArithmeticException", "index");
		mappings.put("java.lang.NullPointerException","index");
		
		//设置异常与视图映射信息的
		resolver.setExceptionMappings(mappings);
		
		return resolver;
	}*/
	
}
