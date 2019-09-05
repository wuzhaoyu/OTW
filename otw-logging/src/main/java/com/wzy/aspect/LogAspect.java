package com.wzy.aspect;

import com.wzy.domain.Log;
import com.wzy.service.LogService;
import com.wzy.utils.RequestHolder;
import com.wzy.utils.StringUtils;
import com.wzy.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类功能说明: 注解实现
 * 类修改者	创建日期2019/8/28
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private LogService logService;

    private long currentTime = 0L;
    /**
     * 配置切入点
     */
     @Pointcut("@annotation(com.wzy.aop.log.Log)")
     public void logPointcut() {
         //该方法无方法体，主要为了让同类中其他方法使用此切入点
     }

    /**
     * 配置环绕通知，使用的logPointcut上注册的切入点
     * @param joinPoint
     * @throws Throwable
     */
     @Around("logPointcut()")
     public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
         currentTime = System.currentTimeMillis();
         Object result = joinPoint.proceed();
         Log log = new Log("info",System.currentTimeMillis() - currentTime);
         logService.save("admin", StringUtils.getIP(RequestHolder.getHttpServletRequest()),(ProceedingJoinPoint)joinPoint, log);
          return result;
     }

    /**
     * 配置异常通知
     * @param joinPoint
     * @param e
     */
     @AfterThrowing(pointcut = "logPointcut()",throwing = "e")
     public void logAfterThrowing(JoinPoint joinPoint, Throwable e){
         Log log = new Log("error",System.currentTimeMillis() - currentTime);
         log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
         logService.save("admin", StringUtils.getIP(RequestHolder.getHttpServletRequest()), (ProceedingJoinPoint)joinPoint, log);
     }

}
