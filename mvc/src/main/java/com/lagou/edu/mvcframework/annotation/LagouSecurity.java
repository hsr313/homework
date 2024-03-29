package com.lagou.edu.mvcframework.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LagouSecurity {
    String[] value() default {};
}
