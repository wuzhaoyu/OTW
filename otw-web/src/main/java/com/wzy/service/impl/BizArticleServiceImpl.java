package com.wzy.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wzy.common.BeanMapUtils;
import com.wzy.common.DateUtils;
import com.wzy.common.RedisUtils;
import com.wzy.domain.BizArticle;
import com.wzy.mapper.BizArticleMapper;
import com.wzy.service.BizArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-07-04
 */
@Service
@Slf4j
public class BizArticleServiceImpl extends ServiceImpl<BizArticleMapper, BizArticle> implements BizArticleService {

    @Autowired
    private BizArticleMapper mapper;

    @Autowired
    private RedisUtils redisUtils;
    @Override
    public Page<BizArticle> queryCondition(Page page, BizArticle bizArticle) {
        try {
            bizArticle.setOffset(page.getOffset()).setSize( page.getLimit());
            List<BizArticle> bizArticles1 = mapper.queryCondition(bizArticle);
            page.setRecords(bizArticles1);
            page.setTotal(mapper.queryTotalByCondition(bizArticle));
        } catch (Exception e) {
            log.error("文章查询失败："+e.getMessage());
            e.printStackTrace();
        }
        return page;
    }


    @Override
    public List<BizArticle> queryCondition(BizArticle bizArticle) {
        return mapper.queryCondition( bizArticle);
    }

    @Override
    public Map<String, Object> queryRecentSixMonthTotal() {

        Map<String,Object> map  = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy年MM月");
        //获取当前年月前一月
        Date beforeDate = null;
        calendar.add(Calendar.MONTH,1);
        //获取当前年月
        Date date = null;
        for (int i=0;i<6;i++){
            //每次月数减一，如果需要当前月份以后的就填1
            beforeDate =  DateUtils.getMonthFirstDate(calendar.getTime());
            calendar.add(Calendar.MONTH,-1);
            date = DateUtils.getMonthFirstDate(calendar.getTime());
            String format1 = format.format(beforeDate);
            String format2 = format.format(date);
            Long aLong = mapper.queryRecentSixMonthTotal(format2,format1);
            map.put(format3.format(date),aLong);
        }
        return BeanMapUtils.sortMapByKey(map);
    }

    @Override
//    @Cacheable(value = "aritcleGroup")
    public List<Map<String, Object>> queryAritcleGroupByType() {
        List<Map<String, Object>> maps = mapper.queryAritcleGroupByType();
        Map<String,Object> map = new HashMap<>();
        BigDecimal total = new BigDecimal(0);
        for(Map<String,Object> var : maps){
            total=  total.add(new BigDecimal(Integer.parseInt(String.valueOf(var.get("count")))));
        }
        //作统计计算，在sql中union 查询较慢
        map.put("name","全部");
        map.put("typeId","");
        map.put("count",total);
        maps.add(0,map);
        return maps;
    }

    @Override
//    @Cacheable(value = "bizArticles", key ="'querySingleBizArtcle:' + #p0.id")
    public BizArticle querySingleBizArtcle(BizArticle bizArticle) {
        BizArticle bizArticle1 = new BizArticle();
        String key = "bizArticle:" + bizArticle.getId();
        //更新浏览量
        this.updateViews(bizArticle.getId());
        if(Objects.nonNull(redisUtils.get(key))){
            String content   =  redisUtils.get(key).toString();
            bizArticle1 = mapper.querySingleBizArtcleIgnoreContent(bizArticle);
            bizArticle1.setContent(content);
        }else{
            bizArticle1 = mapper.querySingleBizArtcle(bizArticle);
            redisUtils.set("bizArticle:"+bizArticle.getId(),bizArticle1.getContent());
        }
        return bizArticle1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateViews(Long id) {
        return mapper.updateViews(id);
    }


}
