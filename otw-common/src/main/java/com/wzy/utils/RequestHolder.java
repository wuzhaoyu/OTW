package com.wzy.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wzy
 * @Description 获取 HttpServletRequest
 * @Date:   2019/9/2 20:46
 **/
public class RequestHolder {

   public static HttpServletRequest getHttpServletRequest(){

       return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
   }
}
