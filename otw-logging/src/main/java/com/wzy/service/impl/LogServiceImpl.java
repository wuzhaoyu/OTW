package com.wzy.service.impl;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wzy.domain.Log;
import com.wzy.mapper.LogMapper;
import com.wzy.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Autowired
    private LogMapper logMapper;

    private final String LOGINPATH = "login";

    @Override
    public void save(String username, String ip, ProceedingJoinPoint joinPoint, Log log) {
        MethodSignature signature =(MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.wzy.aop.log.Log annotation = method.getAnnotation(com.wzy.aop.log.Log.class);

        //描述
        if(annotation !=null){
            log.setDescription(annotation.value());
        }

        // 方法路径
        String methodName =joinPoint.getTarget().getClass().getName()+ "."+ signature.getName()+ "()";

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();

        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }
        // 获取IP地址
        log.setRequestIp(ip);
        if(LOGINPATH.equals(signature.getName())){
            try {
                JSONObject jsonObject = new JSONObject(argValues[0].toString());
                username = jsonObject.get("username").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setCreateTime(new Date());
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params + " }");
        logMapper.insert(log);
    }

    @Override
    public Object findByErrDetail(Long id) {
        return Dict.create().set("exception",logMapper.findExceptionById(id));
    }
}
