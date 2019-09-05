package com.wzy.aop;

import com.wzy.aspect.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类功能说明: 接口访问限制
 * 类修改者	创建日期2019/9/4
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    //资源名称，用于描述接口
    String name() default "";

    //资源 key
    String key() default "";

    //前缀
    String prefix() default "";

    //时间 单位秒
    int period();

    //访问次数
    int count();

    //限制类型
    LimitType limitType() default LimitType.IP;
}
