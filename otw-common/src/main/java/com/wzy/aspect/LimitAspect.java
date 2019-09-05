package com.wzy.aspect;

import com.google.common.collect.ImmutableList;
import com.wzy.aop.Limit;
import com.wzy.utils.RequestHolder;
import com.wzy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 类功能说明: 实现
 * 类修改者	创建日期2019/9/4
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
@Component
@Slf4j
@Aspect
public class LimitAspect {

    @Autowired
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.wzy.aop.Limit)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest httpServletRequest = RequestHolder.getHttpServletRequest();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Limit limit = method.getAnnotation(Limit.class);
        LimitType limitType = limit.limitType();
        String name = limit.name();
        String key = limit.key();
        if(StringUtils.isEmpty(key)){
            switch (limitType){
                case IP:
                    key = StringUtils.getIP(httpServletRequest);
                    break;
                default:
                    key = method.getName();
            }
        }

        ImmutableList keys = ImmutableList.of(StringUtils.join(limit.prefix(),'_',key,"_",httpServletRequest.getRequestURI().replaceAll("/","-")));

        String luaScript = buildLuaScript();

        DefaultRedisScript<Number> numberDefaultRedisScript = new DefaultRedisScript<>(luaScript, Number.class);
        Number count = (Number) redisTemplate.execute(numberDefaultRedisScript, keys, limit.count(), limit.period());

        if(null != count && count.intValue() <= limit.count()){
            log.info("第{}次访问key为 {}，描述为 [{}] 的接口",count,keys,name);
            return joinPoint.proceed();
        } else {
            throw new RuntimeException("访问次数受限制");
        }
    }

    /**
     * 限流脚本
     */
    private String buildLuaScript() {

        return "local c" +
                "\nc = redis.call('get',KEYS[1])" +
                "\nif c and tonumber(c) > tonumber(ARGV[1]) then" +
                "\nreturn c;" +
                "\nend" +
                "\nc = redis.call('incr',KEYS[1])" +
                "\nif tonumber(c) == 1 then" +
                "\nredis.call('expire',KEYS[1],ARGV[2])" +
                "\nend" +
                "\nreturn c;";

    }
}
