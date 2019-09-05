package com.wzy.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 类功能说明:
 * 类修改者	创建日期2019/3/1
 * 修改说明
 *
 * @author com.wzy
 * @version V1.0
 **/
public class QuartzMain {

    public static void main(String[] args) throws SchedulerException {
        //创建job对象，你要做什么事
        JobDetail job = JobBuilder.newJob(QuartzDemo.class).build();
        /**
         * 简单trigger触发时间
         *
         */
        //创建Trigger对象 在什么时间做
//        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();
        //创建Schedule,在什么时间做什么事
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job,trigger);

        //启动
        scheduler.start();
    }

}
