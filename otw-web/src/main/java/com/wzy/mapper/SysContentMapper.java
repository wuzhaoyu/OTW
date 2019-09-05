package com.wzy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wzy.domain.SysContent;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-03-06
 */
public interface SysContentMapper extends BaseMapper<SysContent> {

    /**
     * 分页查询
     * @param page
     * @param sysResources
     * @return
     */
    List<SysContent> queryCondition(Pagination page, SysContent sysResources);

    /**
     * 分页查询
     * @param sysResources
     * @param sysResources
     * @return
     */
    List<SysContent> queryCondition(SysContent sysResources);

}
