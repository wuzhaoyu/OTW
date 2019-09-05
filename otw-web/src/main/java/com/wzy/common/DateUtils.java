package com.wzy.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public static int getCurrentDate() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	public static int getCurrentHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public static int getCurrentSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	public static int getCurrentMilliSecond() {
		return Calendar.getInstance().get(Calendar.MILLISECOND);
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 函数功能说明 根据日历的规则，为给定的日历字段添加或减去指定的时间量。 (day为正数 增加 负数 减少) xurun Sep 30, 2014
	 * 修改者名字 修改日期 修改内容
	 * 
	 * @param
	 * @param d
	 * @param
	 * @param format
	 * @param
	 * @param days
	 * @param
	 * @return
	 * @return java.util.Date
	 * @throws
	 */
	public static Date returnAddDate(Date d, String format, int days) {
		Date result = null;
		String ft = null;
		if (format == null) {
			ft = "yyyy-MM-dd HH:mm:ss";
		} else {
			ft = format;
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(ft);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, days); // 加n天
		try {
			result = bartDateFormat.parse(bartDateFormat.format(calendar
					.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 函数功能说明 获取月的第一天 xurun Oct 26, 2014 修改者名字 修改日期 修改内容
	 * 
	 * @param
	 * @param date
	 * @param
	 * @return
	 * @return Date
	 * @throws
	 */
	public static Date getMonthFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 函数功能说明 .获取月的最后一天 xurun Oct 26, 2014 修改者名字 修改日期 修改内容
	 * 
	 * @param
	 * @param date
	 * @param
	 * @return
	 * @return Date
	 * @throws
	 */
	public static Date getMonthLastDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// 先设置为下个月第一天
		calendar.add(Calendar.MONTH, 1);// 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1);// 再减一天即为上个月最后一天
		// 设置时分秒
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 函数功能说明 月份减 xurun Oct 26, 2014 修改者名字 修改日期 修改内容
	 * 
	 * @param
	 * @param date
	 * @param
	 * @param months
	 * @param
	 * @return
	 * @return Date
	 * @throws
	 */
	public static Date getBeforeMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - months);
		return calendar.getTime();
	}

	/**
	 * 函数功能说明 月份加 xurun Oct 26, 2014 修改者名字 修改日期 修改内容
	 * 
	 * @param
	 * @param date
	 * @param
	 * @param months
	 * @param
	 * @return
	 * @return Date
	 * @throws
	 */
	public static Date getAfterMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		return calendar.getTime();
	}

	/**
	 * 函数功能说明 根据日期和代帐周期得到下个日期 xurun Oct 26, 2014 修改者名字 修改日期 修改内容
	 * 
	 * @param
	 * @param date
	 * @param
	 * @param dzzqmc
	 * @param
	 * @return
	 * @return Date
	 * @throws
	 */
	public static Date getNextDateByDzzq(Date date, String dzzqmc) {
		Date nextDate = new Date();
		if ("年付".equals(dzzqmc)) {
			nextDate = getAfterMonth(date, 12);
		} else if ("半年付".equals(dzzqmc)) {
			nextDate = getAfterMonth(date, 6);
		} else if ("季付".equals(dzzqmc)) {
			nextDate = getAfterMonth(date, 3);
		} else if ("月付".equals(dzzqmc)) {
			nextDate = getAfterMonth(date, 1);
		}
		return returnAddDate(nextDate, "yyyy-MM-dd", -1);
	}

	public static int calMonths(Date start, Date end) {
		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(start);
		c2.setTime(end);

		int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12
				+ c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		return month == 0 ? 1 : month;
	}

	public static void main(String[] args) throws ParseException {
		Date date1 = getNextDateByDzzq(new Date(), "年付");
		Date date2 = getNextDateByDzzq(new Date(), "半年付");
		Date date3 = getNextDateByDzzq(new Date(), "季付");
		Date date4 = getNextDateByDzzq(new Date(), "月付");
		Date date5 = getNextDateByDzzq(date1, "半年付");
		// System.out.println(TimeUtil.toStrDateFromUtilDateByFormat(date1,
		// "yyyy-MM-dd"));
		// System.out.println(TimeUtil.toStrDateFromUtilDateByFormat(date2,
		// "yyyy-MM-dd"));
		// System.out.println(TimeUtil.toStrDateFromUtilDateByFormat(date3,
		// "yyyy-MM-dd"));
		// System.out.println(TimeUtil.toStrDateFromUtilDateByFormat(date4,
		// "yyyy-MM-dd"));

		 System.out.println(calMonths(new Date(),date1));
		 System.out.println(calMonths(new Date(), date2));
		 System.out.println(calMonths(new Date(),date3));
		 System.out.println(calMonths(new Date(),date4));
		 System.out.println(calMonths(new Date(),date5));
		System.out.println(calMonths(new Date(), new Date()));
	}
	
	/** 
     *  
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度 
     *  
     * @param date 
     * @return 
     */  
    public static int getSeason(Date date) {  
  
        int season = 0;  
  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int month = c.get(Calendar.MONTH);  
        switch (month) {  
        case Calendar.JANUARY:  
        case Calendar.FEBRUARY:  
        case Calendar.MARCH:  
            season = 1;  
            break;  
        case Calendar.APRIL:  
        case Calendar.MAY:  
        case Calendar.JUNE:  
            season = 2;  
            break;  
        case Calendar.JULY:  
        case Calendar.AUGUST:  
        case Calendar.SEPTEMBER:  
            season = 3;  
            break;  
        case Calendar.OCTOBER:  
        case Calendar.NOVEMBER:  
        case Calendar.DECEMBER:  
            season = 4;  
            break;  
        default:  
            break;  
        }  
        return season;  
    }  
}
