package com.wzy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.wzy.domain.BizArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-07-04
 */
public interface BizArticleMapper extends BaseMapper<BizArticle> {
    /**
     * 分页查询
     * @param page
     * @param bizArticle
     * @return
     */
    List<BizArticle> queryCondition(Pagination page, BizArticle bizArticle);


    /**
     * @Author com.wzy
     * @Description 获取总数
     **/
    Long queryTotalByCondition(BizArticle bizArticle);

    /**
     * 条件查询
     * @param bizArticle
     * @return
     */
    List<BizArticle> queryCondition(BizArticle bizArticle);

    /**
     * 条件查询
     * @param bizArticle
     * @return
     */
    BizArticle querySingleBizArtcle(BizArticle bizArticle);

    /**
     * 条件查询
     * @param bizArticle
     * @return
     */
    BizArticle querySingleBizArtcleIgnoreContent(BizArticle bizArticle);

    /**
     * 近六个月的文章统计
     * @return
     */
    Long queryRecentSixMonthTotal(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 按照文章分组
     * @param
     * @return
     */
    List<Map<String,Object>> queryAritcleGroupByType();

    /**
     * 更新浏览量
     * @param id
     * @return
     */
    int updateViews(@Param("id") Long id);
}
