package com.wzy.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.wzy.domain.BizArticle;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-07-04
 */
public interface BizArticleService extends IService<BizArticle> {
    /**
     * 分页查询
     * @param page
     * @param bizArticle
     * @return
     */
    Page<BizArticle> queryCondition(Page page, BizArticle bizArticle);

    /**
     * 条件查询
     * @param bizArticle
     * @param bizArticle
     * @return
     */
    List<BizArticle> queryCondition(BizArticle bizArticle);

    /**
     * 近六个月的文章统计
     * @return
     */
    Map<String,Object> queryRecentSixMonthTotal();

    /**
     * 按照文章分组
     * @param
     * @return
     */
    List<Map<String,Object>> queryAritcleGroupByType();

    /**
     * 查询详情
     * @param bizArticle
     * @return
     */
    BizArticle  querySingleBizArtcle(BizArticle bizArticle);
    /**
     * 更新浏览量
     * @param id
     * @return
     */
    int updateViews(Long id);
}
