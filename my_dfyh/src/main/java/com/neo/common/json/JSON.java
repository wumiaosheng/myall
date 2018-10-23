package com.neo.common.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 让方法支持多重@JSON 注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONS.class)
// 让方法支持多重@JSON 注解
public @interface JSON {
	Class<?>[] type();

	String[] include() default "";

	String[] filter() default "";
}
