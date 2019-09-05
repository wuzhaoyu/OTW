package com.wzy.controller.transmint;

import com.baomidou.mybatisplus.plugins.Page;
import com.wzy.domain.BizArticle;
import com.wzy.domain.SysContent;
import com.wzy.service.BizArticleService;
import com.wzy.service.SysContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能说明: 前端管理
 * 类修改者	创建日期2019/3/5
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
@Controller
@RequestMapping(value={"/portal","/"})
public class PortalController {

    private static final Logger log = LoggerFactory.getLogger(PortalController.class);
    @Autowired
    private BizArticleService bizArticleService;

    @Autowired
    private SysContentService sysContentService;

    @RequestMapping("/")
    public String index(){
        return "portal/grid";
    }
    @RequestMapping("/index")

    public String helloWorld(){
        return "portal/index";
    }

   /* @RequestMapping("/blog")
    public String blogPage(){
        return "portal/blog";
    }*/
    @RequestMapping("/blog")
    public ModelAndView blogPage(){
        ModelAndView modal = new ModelAndView();
        try{
            BizArticle bizArticle = new BizArticle();
            Page<BizArticle> page = new Page<>(1, 4);
            Page<BizArticle> sysResources = bizArticleService.queryCondition(page,bizArticle);
            modal.addObject("resources",sysResources);
            modal.setViewName("portal/blog");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modal;
    }
    @RequestMapping("/footer")
    public String footer(){  return "portal/public/footer"; }

    @RequestMapping("/catalog")
    public String catalog(){  return "portal/public/catalog"; }

    @RequestMapping("/singlePost/{tid}")
    public ModelAndView singlePost(@PathVariable Integer tid){
        ModelAndView modal = new ModelAndView();
        try{
            SysContent sysResources = sysContentService.selectById(tid);
            modal.addObject("detail",sysResources);
            modal.setViewName("portal/single-post");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modal;
    }

    @RequestMapping("/about")
    public String about(){
        return "portal/about";
    }

    @RequestMapping("/grid")
    public ModelAndView grid(ModelAndView modal){
        try{
            int current =1;
            int size = 6;
            Map<String, Object> map = new HashMap<>();
            SysContent resource = new SysContent();
            Page<SysContent> page = new Page<SysContent>(current, size);
            Page<SysContent> sysResources = sysContentService.queryCondition(page,resource);
            modal.addObject("resources",sysResources.getRecords());
            modal.setViewName("portal/grid");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modal;
    }

    @RequestMapping("/search")
    public ModelAndView searchPage(ModelAndView modal){
        try{
            modal.setViewName("portal/search_blog");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modal;
    }
}
