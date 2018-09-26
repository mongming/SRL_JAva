/**
 * 
 */
package com.srl.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @Description:
* @author: zwl
* @date: 2018年8月19日 下午4:02:08
*/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableId {

	String value() default "";
	
	String desc() default "";
}
