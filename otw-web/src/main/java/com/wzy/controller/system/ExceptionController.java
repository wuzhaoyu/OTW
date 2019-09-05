package com.wzy.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/2/27
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
@Controller
@RequestMapping("/error")
public class ExceptionController {

    @RequestMapping("/show2")
    public String showInfo2() {
        int a = 10 / 0;
        return "index";
    }

    @RequestMapping("/500")
    public String exception() {
        return "thyemleaf/error";
    }

    @RequestMapping("/404")
    public String notFound() {

        return "/404";
    }
}
