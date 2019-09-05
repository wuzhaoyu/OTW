package com.wzy.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/2/27
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
@Configuration
public class DefinErrorConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/404"));
        tomcatServletWebServerFactory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404"));
        return  tomcatServletWebServerFactory;
    }
}
