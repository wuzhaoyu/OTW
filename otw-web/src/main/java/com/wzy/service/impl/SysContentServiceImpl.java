package com.wzy.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wzy.domain.SysContent;
import com.wzy.mapper.SysContentMapper;
import com.wzy.service.SysContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-03-06
 */
@Service
public class SysContentServiceImpl extends ServiceImpl<SysContentMapper, SysContent> implements SysContentService {

    @Autowired
    private SysContentMapper sysContentMapper;
    @Override
    public Page<SysContent> queryCondition(Page<SysContent> page, SysContent sysResources) {
        page.setRecords(sysContentMapper.queryCondition(page,sysResources));
        return page;
    }

    @Override
    public List<SysContent> queryCondition(SysContent sysResources) {
        return sysContentMapper.queryCondition(sysResources);
    }

    @Override
    public void save(SysContent sysResources) {
        if(Objects.isNull(sysResources)){
            return;
        }
        sysResources.setCreatedBy(1);
        if(Objects.nonNull(sysResources.getTid())){
            sysResources.setUpdatedAt(new Date());
            sysContentMapper.updateById(sysResources);
        }else{
            sysResources.setCreatedAt(new Date());
            sysContentMapper.insert(sysResources);
        }
    }
}
