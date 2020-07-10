package com.mp.blog.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface H5OpenV1Api {
    String desc() default "H5专用开放接口 1.0";
}
