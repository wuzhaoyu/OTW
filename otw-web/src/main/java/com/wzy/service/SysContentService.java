package com.wzy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wzy.domain.SysContent;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-03-06
 */
public interface SysContentService extends IService<SysContent> {

    /**
     * 分页查询
     * @param page
     * @param sysResources
     * @return
     */
    Page<SysContent> queryCondition(Page<SysContent> page, SysContent sysResources);

    /**
     * 分页查询
     * @param sysResources
     * @param sysResources
     * @return
     */
    List<SysContent> queryCondition(SysContent sysResources);

    /**
     * 保存
     * @param sysResources
     */
    void save(SysContent sysResources);

}
