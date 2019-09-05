package com.wzy;

import com.wzy.filter.SecondFilter;
import com.wzy.listen.SecondListener;
import com.wzy.servlet.SecondServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/**
 * 类功能说明:springboot启动类
 * @SpringBootApplication 启动类标识 扫描同级包及以下及包
 * @ServletComponentScan springboot启动时会扫描webservlet注解
 * 类修改者	创建日期2019/2/12
 * 修改说明
 * <p>Title: app.java</p>
 *
 * @author Wuzhaoyu
 * @version V1.0
 **/
@ServletComponentScan
@SpringBootApplication
@EnableCaching
@MapperScan("com.wzy.mapper")
public class SystemApplication {
    /**
     * 系统版本号
     */
    public static final String ROOTPAth = "F:\\googledownload\\SpringBoot01\\src\\main\\webapp";

    public static void main(String[] args){
        //禁用热部署方法
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(SystemApplication.class,args);
    }

    /**
     * 使用bean配置servlet配置对象
     * @return
     */
    @Bean
    public ServletRegistrationBean getServletRegistrationBean(){
        ServletRegistrationBean<SecondServlet> secondServletServletRegistrationBean = new ServletRegistrationBean<>(new SecondServlet());
        secondServletServletRegistrationBean.addUrlMappings("/second");
        return secondServletServletRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SecondFilter());
        registrationBean.addUrlPatterns("/second");
        return registrationBean;
    }

    /**
     * 注册listener不需要@ServletComponentScan
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean getServletContextListener(){
        ServletListenerRegistrationBean servletContextListener = new ServletListenerRegistrationBean(new SecondListener());
        return  servletContextListener;
    }
    /**
     * 配置上传文件大小的配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        //  单个数据大小
        multipartConfigFactory.setMaxFileSize("20480000");
        /// 总上传数据大小
        multipartConfigFactory.setMaxRequestSize("20480000");
        return  multipartConfigFactory.createMultipartConfig();
    }

}
