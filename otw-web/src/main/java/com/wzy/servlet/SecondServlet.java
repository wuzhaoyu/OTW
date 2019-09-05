package com.wzy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能说明:  不是同注解 在springboot的启动类中创建Servletbean创建
 * 类修改者	创建日期2019/2/13
 * 修改说明
 * <p>Title: FirstServlet.java</p>
 *
 * @author Wuzhaoyu
 * @version V1.0
 **/
public class SecondServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req,resp);
            System.out.println("servlet配置成功......");
    }

}
