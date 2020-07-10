package com.mp.blog.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenV1Api {

    String desc() default "开放接口 1.0";
}
