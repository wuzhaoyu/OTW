package com.wzy.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/2/14
 * 修改说明
 * <p>Title: FirstListener.java</p>
 *
 * @author Wuzhaoyu
 * @version V1.0
 **/
@WebListener
public class FirstListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("初始化listener");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
