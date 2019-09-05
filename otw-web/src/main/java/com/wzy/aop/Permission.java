package com.wzy.aop;

import java.lang.annotation.*;

/**
 * 类功能说明: 接口权限校验
 * 类修改者	创建日期2019/2/23
 * 修改说明
 * <p>Title: Permission.java</p>
 *
 * @author Wzy
 * @version V1.0
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permission {
     String[] value() default {} ;
}
