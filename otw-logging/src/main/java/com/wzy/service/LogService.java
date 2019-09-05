package com.wzy.service;

import com.baomidou.mybatisplus.service.IService;
import com.wzy.domain.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuzhaoyu
 * @since 2019-08-28
 */
public interface LogService extends IService<Log> {
    /**
     * 新增日志
     * @param joinPoint
     * @param log
     */
    @Async
    void save(String username, String ip, ProceedingJoinPoint joinPoint, Log log);

    /**
     * 查询异常详情
     * @param id
     * @return
     */
    Object findByErrDetail(Long id);
}
