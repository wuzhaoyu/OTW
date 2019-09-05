package com.wzy.controller.portal;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.wzy.aop.Limit;
import com.wzy.aop.log.Log;
import com.wzy.common.ResultMsg;
import com.wzy.common.exception.BussinessException;
import com.wzy.controller.system.AbstractController;
import com.wzy.domain.BizArticle;
import com.wzy.service.BizArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-07-04
 */
@Controller
@RequestMapping("/bizArticle")
public class BizArticleController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(BizArticleController.class);

    @Autowired
    private BizArticleService bizArticleService;

    /**
     * 文章列表
     * @param modal
     * @return
     */
    @PostMapping("/grid")
    public ModelAndView grid(ModelAndView modal){
        try{
            int current =1;
            int size = 6;
            Map<String, Object> map = new HashMap<>();
            BizArticle bizArticle = new BizArticle();
            Page<BizArticle> page = new Page<BizArticle>(current, size);
            Page<BizArticle> sysResources = bizArticleService.queryCondition(page,bizArticle);
            modal.addObject("resources",sysResources.getRecords());
            modal.setViewName("portal/grid");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modal;
    }
    @PostMapping("/queryCondition")
    @ResponseBody
    public ResultMsg queryCondition(@RequestBody JSONObject param) {
        try {
            int current = param.getInteger("current");
            int size = param.getInteger("size");
            BizArticle bizArticle = new BizArticle();
            if(param.containsKey("date")){
                bizArticle = createCondition(bizArticle ,param.getString("date"));
            }
            Page<BizArticle> page = new Page<>(current, size);
            Page<BizArticle> sysResources = bizArticleService.queryCondition(page,bizArticle);
            return ResultMsg.success(sysResources);
        } catch (Exception e) {
            return ResultMsg.create(400, e.toString());
        }
    }

    @PostMapping("/blogList")
    @Log("查询文章")
    public String blogPage(@RequestBody JSONObject param, Model model){
        try{
            int current = param.getInteger("current");
            int size = param.getInteger("size");
            BizArticle bizArticle = new BizArticle();
            if(param.containsKey("date")){
                if(!StringUtils.isEmpty(param.getString("date"))){
                    bizArticle = createCondition(bizArticle ,param.getString("date"));
                }
            }
            if(param.containsKey("typeId")){
                if(!StringUtils.isEmpty(param.getString("typeId"))){
                    bizArticle = bizArticle.setTypeId(Long.parseLong(param.getString("typeId")));
                }
                }
            Page<BizArticle> page = new Page<>(current, size);
            Page<BizArticle> sysResources = bizArticleService.queryCondition(page,bizArticle);
            model.addAttribute("resources",sysResources);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "portal/blog::article_type";
    }
    /**
     * 查询文章详情
     * @param tid
     * @return
     */
    @RequestMapping("/singlePost/{tid}")
    @Log("查询文章详情")
    @Limit(name = "测试",key = "singlePost",prefix = "biz",count = 5,period = 30)
    public ModelAndView singlePost(@PathVariable Long tid){
        ModelAndView modal = new ModelAndView();
        try{
            BizArticle bizArticle = new BizArticle();
            bizArticle.setId(tid);
            BizArticle bizArticles = bizArticleService.querySingleBizArtcle(bizArticle);
            modal.addObject("detail",bizArticles);
            modal.setViewName("portal/single-post");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return modal;
    }
    /**
     * 查询文章详情
     * @return
     */
    @RequestMapping("/queryRecentSixMonthTotal")
    @ResponseBody
    public ResultMsg queryRecentSixMonthTotal() {
        try {
            Map<String, Object> bizArticles = bizArticleService.queryRecentSixMonthTotal();
            return ResultMsg.success(bizArticles);
        } catch (Exception e) {
            log.error(e.getMessage());
            BussinessException e1 = (BussinessException) e;
            return ResultMsg.create(e1.getErrorCode(),e1.getMessage());
        }
    }

    @GetMapping("/queryRecentSixMonthCondition")
    public String queryRecentSixMonthCondition(@RequestParam(required = false)String date,Model model) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(format.parse(date));
                calendar.add(Calendar.MONTH,1);
                System.out.println(format.format(calendar.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String startDate = date;
            String endDate = format.format(calendar.getTime());
            BizArticle bizArticle = new BizArticle();
            bizArticle.setStartDate(startDate).setEndDate(endDate);
            Page<BizArticle> page = new Page<>(1, 4);
            Page<BizArticle> sysResources =  bizArticleService.queryCondition( page,bizArticle);
            model.addAttribute("resources",sysResources);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "portal/blog::article_type";
    }

    /**
     * 最近更新
     * @param model
     * @return
     */
    @PostMapping("/recentPosts")
    public String recentPosts(Model model) {
        try{
            Page<BizArticle> page = new Page<>(1, 4);
            Page<BizArticle> sysResources =  bizArticleService.queryCondition( page,new BizArticle() );
            model.addAttribute("recentPosts",sysResources);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "portal/blog::article_recentPosts";
    }
    /**
     * 分类文章
     * @param model
     * @return
     */
    @PostMapping("/queryAritcleGroupByType")
    public String queryAritcleGroupByType(Model model) {
        try{
            List<Map<String,Object>> groupAritcles =  bizArticleService.queryAritcleGroupByType();
            model.addAttribute("groupAritcles",groupAritcles);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "portal/blog::article_groupAritcle";
    }

    public static BizArticle createCondition(BizArticle bizArticle,String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(date));
            calendar.add(Calendar.MONTH,1);
            System.out.println(format.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String startDate = date;
        String endDate = format.format(calendar.getTime());
        bizArticle.setStartDate(startDate).setEndDate(endDate);
        return bizArticle;
    }

}

