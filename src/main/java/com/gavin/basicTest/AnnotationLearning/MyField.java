package com.gavin.basicTest.AnnotationLearning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 第一个参数是运行时也可用,第二个限定用在属性字段上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyField {
    String description() default "";
    int length() default 0;
}
