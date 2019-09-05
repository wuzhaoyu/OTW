package com.wzy.config;

import org.springframework.context.annotation.Configuration;

/**
 * 类功能说明: 定时任务
 * 类修改者	创建日期2019/3/1
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
@Configuration
public class QuartzConfig {
//
//    /**
//     * 创建job对象
//     */
//    @Bean
//    public JobDetailFactoryBean jobDetailFactoryBean(){
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(QuartzDemo.class);
//        return factoryBean;
//    }
//    /**
//     * 简单创建Trigger
//     */
//    /*@Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean factory){
//        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
//        simpleTriggerFactoryBean.setJobDetail(factory.getObject());
//        //参数为毫秒数
//        simpleTriggerFactoryBean.setRepeatInterval(2000);
//        //重复次数
//        simpleTriggerFactoryBean.setRepeatCount(5);
//        return simpleTriggerFactoryBean;
//    }*/
//    @Bean
//    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean factory){
//        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
//        factoryBean.setJobDetail(factory.getObject());
//        factoryBean.setCronExpression("0/2 * * * * ?");
//        return factoryBean;
//    }
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean bean,MyAdapterQuartAble myAdapterQuartAble){
//        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//        factoryBean.setJobFactory(myAdapterQuartAble);
//        factoryBean.setTriggers(bean.getObject());
//        return factoryBean;
//    }

}
