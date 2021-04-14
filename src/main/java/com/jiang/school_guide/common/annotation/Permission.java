package com.jiang.school_guide.common.annotation;


import com.jiang.school_guide.common.domain.Const;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String[] roles() default {Const.ADMIN, Const.USER};
}
