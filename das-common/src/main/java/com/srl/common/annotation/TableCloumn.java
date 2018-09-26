/**
 * 
 */
package com.srl.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 用户注解，这个实体对应的表名称
* @Description:
* @author: zwl
* @date: 2018年8月19日 下午3:44:57
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableCloumn {
	
	String value() default "";
	
	String desc() default "";
}
