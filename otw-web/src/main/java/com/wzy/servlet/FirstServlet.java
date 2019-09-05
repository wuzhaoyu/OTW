package com.wzy.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类功能说明:  使用spingboot 注解webservlet进行配置servlet的映射规则
 * //在SystemApplication 启动时@ServletComponentScan  会扫描webservlet注解
 * 类修改者	创建日期2019/2/13
 * 修改说明
 * <p>Title: FirstServlet.java</p>
 *
 * @author Wuzhaoyu
 * @version V1.0
 **/
@WebServlet(name = "FirstServlet",urlPatterns = "/first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            super.doGet(req,resp);
            System.out.println("servlet配置成功");
    }

}
