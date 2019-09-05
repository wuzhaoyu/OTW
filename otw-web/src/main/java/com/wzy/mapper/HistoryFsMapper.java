package com.wzy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wzy.domain.HistoryFs;
import com.wzy.domain.HistoryFsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-25
 */
public interface HistoryFsMapper extends BaseMapper<HistoryFs> {

    List<Map<String,Object>> queryGroupByCode();

    HistoryFsDto queryGroupByMinAndMax(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("code") String code);

    List<HistoryFs> queryGroupByOther(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("code") String code);

}
