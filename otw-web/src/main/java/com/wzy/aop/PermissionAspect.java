package com.wzy.aop;

import com.wzy.common.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/2/23
 * 修改说明
 * <p>Title: PermissionAspect.java</p>
 *
 * @author Wzy
 * @version V1.0
 **/
@Aspect
@Component
public class PermissionAspect {

    private static final Logger log = LoggerFactory.getLogger(PermissionAspect.class);
    @Value("#{'${permission.ignore}'.split(',')}")
    private List<String> ingoreParam;

    @Autowired
    private RedisUtils redisUtils;

    public PermissionAspect(){}

    @Pointcut("execution(* com.wzy.controller..*.*(..)) && @annotation(com.wzy.aop.Permission)")
    public void addAdvice(){}

    @Around("addAdvice()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {

        Permission annotation =((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(Permission.class);
        String[] perm = annotation.value();
        boolean contains = false;
        String[] var1 = perm;
        int var2 = perm.length;

        for (int var3 = 0;var3 < var2; ++var3 ){
            String p = var1[var3];
            contains = this.ingoreParam.contains(p);
            if(contains){
                break;
            }
        }
        // todo 用户权限校验 数据获取sys_resource表
         boolean b = this.validPermission(perm);
        // 改接口为忽略权限校验接口，则不需要通过用权限校验
        if(!contains){
            return contains ? Result.create(ResultCode.ERROR_VALI_PERMISSION,ResultCode.ERROR_VALI_PERMISSION_MSG+perm) : pjp.proceed();
        }
//        return !contains && !b? Result.create(ResultCode.ERROR_VALI_PERMISSION,ResultCode.ERROR_VALI_PERMISSION_MSG+perm) : pjp.proceed();
         return !contains? Result.create(ResultCode.ERROR_VALI_PERMISSION,ResultCode.ERROR_VALI_PERMISSION_MSG+perm) : pjp.proceed();

    }

   private boolean validPermission(String[] perm){
       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       String token = TokenUtil.getToken(request);
       if (StringUtils.isEmpty(token)) {
            return false;
       }
       Object o = redisUtils.get(token);

       //校验token是否失效,前端展示页面未涉用户权限校验
       if(Objects.isNull(o)){
           return false;
       }
       /*if (perm != null && perm.length > 0) {
           JSONObject user = (JSONObject) JSON.toJSON(o);
           JSONArray res = user.getJSONArray("res");
           long count = res.stream().filter((i) -> {
               return Arrays.asList(perm).contains(((JSONObject)i).getString("key"));
           }).count();
           if (count < 1L) {
               log.debug("Permission===============================================\n" + JSON.toJSONString(perm));
               return false;
           }
       }*/
       //todo 用户分配的权限key
       request.setAttribute(SystemConst.CACHED_USER_INFO,o);
       return true;

   }
}
