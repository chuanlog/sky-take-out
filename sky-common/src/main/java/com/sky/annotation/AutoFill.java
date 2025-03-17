package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 用于标识方法需要自动填充的注解
 */
@Target({java.lang.annotation.ElementType.METHOD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
