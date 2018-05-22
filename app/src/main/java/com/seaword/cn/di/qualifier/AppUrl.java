package com.seaword.cn.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by zl on 2018/5/22.
 * App注解  Qualifier  -- 限定词
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AppUrl {
}
