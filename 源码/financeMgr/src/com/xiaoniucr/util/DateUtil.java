package com.xiaoniucr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	public static String formatDate(Date date, String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		//可以让程序严格校验,不合法的日期格式不自动转换
		sdf.setLenient(false);
		if (date != null) {
			result = sdf.format(date);
		}
		return result;
	}

	public static Date formatString(String str, String format) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		//可以让程序严格校验,不合法的日期格式不自动转换
		sdf.setLenient(false);
		return sdf.parse(str);
	}

	/**
	 * 计算两个日期之差
	 * 
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static Long[] getDateBetweenHour(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;// 1天天多少毫秒?
		long nh = 1000 * 60 * 60;// 1个小时多少毫秒?
		long nm = 1000 * 60;// 1分钟多少毫秒
		// long ns = 1000;
		// 获得两个时间的毫秒时间差?
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时?
		long hour = diff % nd / nh;
		// 计算差多少分钟?
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		Long[] res = new Long[3];
		res[0] = day;
		res[1] = hour;
		res[2] = min;
		return res;
	}

	/**
	 * java 获取 获取某年某月 所有日期（yyyy-mm-dd格式字符串）
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public List<String> getMonthFullDay(int year, int month) {
		SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
		List<String> fullDayList = new ArrayList<>(32);
		// 获得当前日期对象
		Calendar cal = Calendar.getInstance();
		cal.clear();// 清除信息
		cal.set(Calendar.YEAR, year);
		// 1月从0开始
		cal.set(Calendar.MONTH, month - 1);
		// 当月1号
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int j = 1; j <= count; j++) {
			fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fullDayList;
	}

	/**
	 * 获取本月第一天是礼拜几
	 */
	public static int getFirstDayInWeek() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);// 将今天设为1号
		int firstDay = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (firstDay == 0) {
			firstDay = 7;
		}
		return firstDay;
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {

		Calendar ca = Calendar.getInstance();
		ca.setTime(date); // someDate 为你要获取的那个月的时间
		ca.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDay = ca.getTime();
		return firstDay;
	}

	/**
	 * 获取本月最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 根据日期判断是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekday(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int firstDay = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (firstDay == 0) {
			firstDay = 7;
		}
//		System.out.println("本月第一天是礼拜："+firstDay);
		return firstDay;
	}

	/**
	 * 获取指定日期的前七天日期
	 * 
	 * @param past
	 * @return
	 */
	public static List<Date> getPastDate(Date date, int past) {
		List<Date> dateList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (int i = past; i >= 1; i--) {
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -i);
			dateList.add(calendar.getTime());
		}
		return dateList;

	}

	/**
	 * 获取指定日期的后七天日期
	 * 
	 * @param past
	 * @return
	 */
	public static List<Date> getFetureDate(Date date, int past) {
		List<Date> dateList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 1; i <= past; i++) {
			calendar.setTime(date);
			calendar.add(Calendar.DATE, +i);
			dateList.add(calendar.getTime());
		}
		return dateList;
	}

	/**
	 * 获取日期当前月全部天数
	 */
	public static List<Date> getMonthFullDay(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		while (cal.get(Calendar.MONTH) == month) {
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	
	/**

     * 查找上一个月
     * @param month
     * @return
     */

    public static String beforeMonth(String month){

        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date thisMonth = sdf.parse(month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(thisMonth);
			cal.add(Calendar.MONTH, -1);
			return sdf.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;

    }
    
    
    /**

     * 查找下一个月
     * @param month
     * @return
     */

    public static String afterMonth(String month){

        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date thisMonth = sdf.parse(month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(thisMonth);
			cal.add(Calendar.MONTH, +1);
			return sdf.format(cal.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;

    }
    
    
    /**

     * 根据年份获取年份下所有月份
     * @param year
     * @return
     */

    public static String[] getYearFullMonth(String year){


    	String[] array = new String[12];
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,Integer.parseInt(year));
		cal.set(Calendar.MONTH, 0);
		for(int i=0;i<12;i++){
			array[i] = sdf.format(cal.getTime());
			cal.add(Calendar.MONTH, 1);	
		}
		return array;
    }
    
    
    
    
    
    /**
     * 比较两个日期大小，date2大于date1返回true
     * @param date1
     * @param date2 
     * @return
     * @throws ParseException
     */
    public static boolean compare(Date date1,Date date2){
        
        if(date1.before(date2))
            return true;
        else
            return false;
    }
    
    
    
	public static void main(String[] args) {
		System.out.println(getYearFullMonth("2021"));
	}
	
	

}
