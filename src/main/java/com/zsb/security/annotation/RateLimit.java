package com.zsb.security.annotation;

import java.lang.annotation.*;

/**
 * @author shangbangZheng
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface RateLimit {

    double  limitNum() default 20;
}
