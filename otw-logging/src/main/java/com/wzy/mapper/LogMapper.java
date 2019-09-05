package com.wzy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wzy.domain.Log;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-28
 */
public interface LogMapper extends BaseMapper<Log> {

    Long findIp(@Param("date1") String date1, @Param("date2") String date2);

    String findExceptionById(Long id);

}
