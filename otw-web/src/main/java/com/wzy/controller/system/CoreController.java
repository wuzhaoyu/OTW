package com.wzy.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类功能说明: 核心接口
 * 类修改者	创建日期2019/7/28
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
@Controller
@Slf4j
public class CoreController {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${defind.exitkey}")
    private String exitkey;

    /**
     * 关闭服务
     * @param key
     * @return
     */
    @RequestMapping("exit")
    public String  exit(String key){
        String result = "";
        if(exitkey.equals(key)){
            SpringApplication.exit(applicationContext);
            log.info("服务关闭成功......");
            result = "服务关闭成功";
        }else{
            log.info(key+"校验失败......");
            result = "校验失败";
        }
       return result;
    }
}
