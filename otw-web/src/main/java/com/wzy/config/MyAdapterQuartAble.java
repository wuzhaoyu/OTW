package com.wzy.config;

import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 类功能说明: 创建的定时任务中注入service，
 * 在AdaptableJobFactory中使用的是java的反射，不在IOC容器中，所以不能够依赖注入
 * 通过继承该方法，并在Object返回之前放入AutowireCapableBeanFactory 进行手动注入
 * 类修改者	创建日期2019/3/1
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
@Component("myAdapterQuartAble")
public class MyAdapterQuartAble extends AdaptableJobFactory {
//
//    @Autowired
//    private AutowireCapableBeanFactory autowireCapableBeanFactory;
//    @Override
//    protected Object createJobInstance(TriggerFiredBundle bundle) throws exception {
//        Object jobInstance = super.createJobInstance(bundle);
//        autowireCapableBeanFactory.autowireBean(jobInstance);
//        return jobInstance;
//    }
}
