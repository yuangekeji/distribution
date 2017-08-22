package com.distribution.common.intercept;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * <p>注解方式配置登录拦截</p>
 * <p>在方法或类上加入该注解可以在访问时忽略登录拦截</p>
 * <p>在类上加入该注解，整个类都将被忽略</p>
 */
public @interface IgnoreLoginCheck {
}
