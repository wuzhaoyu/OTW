package com.wzy.controller.portal;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wzy.common.ResultMsg;
import com.wzy.controller.system.AbstractController;
import com.wzy.domain.SysContent;
import com.wzy.service.SysContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/contentResources")
public class SysContentsController extends AbstractController {


    private static final Logger log = LoggerFactory.getLogger(SysContentsController.class);
    @Autowired
    private SysContentService sysContentService;

    @PostMapping("/queryCondition")
    @ResponseBody
    public ResultMsg userList(@RequestBody JSONObject param) {
        try {
            int current = param.getInteger("current");
            int size = param.getInteger("size");
            Map<String, Object> map = new HashMap<>();
//        SubsidiaryCertificate subsidiaryCertificate = param.getObject ( "data",SubsidiaryCertificate.class );
            Page<SysContent> page = new Page<SysContent>(1, 6);
            List<SysContent> sysResources = sysContentService.selectList(new EntityWrapper<>());
            Page<SysContent> list = page.setRecords(sysResources);
            return ResultMsg.success(list);
//        redisUtils.set("test",users);

        } catch (Exception e) {
            return ResultMsg.create(400, e.toString());
        }
    }

    @PostMapping("/queryDetailByTid")
    @ResponseBody
    public ResultMsg queryDetailByTid(@RequestBody JSONObject param) {
        try {
            int tid = param.getInteger("tid");
            SysContent sysResources = sysContentService.selectById(tid);
            return ResultMsg.success(sysResources);
        } catch (Exception e) {
            return ResultMsg.create(400, e.toString());
        }
    }
}

