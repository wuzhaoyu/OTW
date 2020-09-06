package com.wzy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

/**
 * 类功能说明: LocalDateTime 适应方法
 * 类修改者	创建日期2019/9/5
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class LocalDateTimeTest {

    public static void main(String[] args) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusDays(7);

        System.out.println("开始时间：" + startTime.format(df) + "，结束时间：" + endTime.format(df));

        LocalDateTime dateTest = LocalDateTime.parse("2018-02-30 12:12:12", df);
        System.out.println("时间自动转化：" + dateTest.toString());

        int daysNum = Period.between(startTime.toLocalDate(), endTime.toLocalDate()).getDays();
        int monthNum = Period.between(startTime.toLocalDate(), endTime.toLocalDate()).getMonths();
        System.out.println("相差天数：" + daysNum);
        System.out.println("相差月数：" + monthNum);

        System.out.println("当前时间向前推6天：" + LocalDateTime.now().minusDays(6));
        System.out.println("当前时间向前推6小时：" + LocalDateTime.now().minusHours(6));


        //一周从周日开始
        WeekFields weekFields = WeekFields.of(DayOfWeek.SUNDAY, 7);
        LocalDate today = LocalDate.now();
        System.out.println("今天是今年第" + today.get(weekFields.weekOfYear()) + "周");

        // 日期的工具类
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }
}
