package com.wzy.controller.system;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.wzy.common.ResultCode;
import com.wzy.common.ResultMsg;
import com.wzy.domain.SysContent;
import com.wzy.service.SysContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-03-06
 */
@RestController
@RequestMapping("api/sysResources")
public class SysContentController extends AbstractController {


    private static final Logger log = LoggerFactory.getLogger(SysContentController.class);
    @Autowired
    private SysContentService sysContentService;

    @PostMapping("/queryCondition")
    @ResponseBody
    public ResultMsg queryCondition(@RequestBody JSONObject param) {
        try {
            int current = param.getInteger("current");
            int size = param.getInteger("size");
            Map<String, Object> map = new HashMap<>();
            Page<SysContent> page = new Page<SysContent>(current, size);
            SysContent condition = new SysContent();
            Page<SysContent> list  = sysContentService.queryCondition(page,condition);
            return ResultMsg.success(list);
        } catch (Exception e) {
            return ResultMsg.create(400, e.toString());
        }
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ResultMsg save(@RequestBody JSONObject param){
        if(Objects.isNull(param)){
            return ResultMsg.create(ResultCode.ERROR_SAVE_FAILED,ResultCode.ERROR_SAVE_FAILED_MSG);
        }
        try {
            SysContent obj = param.getObject("resource",SysContent.class);
            sysContentService.save(obj);
        }catch (Exception e){
            return ResultMsg.create(ResultCode.ERROR_SAVE_FAILED,ResultCode.ERROR_SAVE_FAILED_MSG);
        }

        return ResultMsg.success();
    }

}

