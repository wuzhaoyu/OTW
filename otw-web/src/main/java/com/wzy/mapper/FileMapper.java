package com.wzy.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wzy.domain.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author zzw
 * @since 2018-08-01
 */
public interface FileMapper extends BaseMapper<File> {

    /**
     * 查询已删除文件日期大于三个月的文件
     * @return
     */
    List<File> queryDeletedFile(@Param("days") Integer days);
}
