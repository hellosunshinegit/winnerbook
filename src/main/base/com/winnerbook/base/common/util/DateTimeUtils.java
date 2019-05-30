package com.winnerbook.base.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-21
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeUtils {
    private static String datePattern = "yyyy-MM-dd";;

    private static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * @return the datePattern
     */
    public static String getDatePattern() {
        return datePattern;
    }

    /**
     * @param datePattern the datePattern to set
     */
    public static void setDatePattern(String datePattern) {
        DateTimeUtils.datePattern = datePattern;
    }

    /**
     * @return the dateTimePattern
     */
    public static String getDateTimePattern() {
        return dateTimePattern;
    }

    /**
     * @param dateTimePattern the dateTimePattern to set
     */
    public static void setDateTimePattern(String dateTimePattern) {
        DateTimeUtils.dateTimePattern = dateTimePattern;
    }

    /**
     * get the current time of the system
     *
     * @return Timestamp
     */
    public static Timestamp getNowTimestamp() {
        java.util.Date d = new java.util.Date();
        Timestamp numTime = new Timestamp(d.getTime());
        return numTime;
    }

    /**
     * get the current time of the system , return java.util.Date class
     *
     * @return java.util.Date
     */
    public static java.util.Date getNowDate() {
        java.util.Date d = new java.util.Date();
        return d;// new java.sql.Date(d.getTime());
    }

    /**
     * format a Timestamp with a format string, if Timestamp is null then return
     * null
     *
     * @param date
     *            Timestamp
     * @param pattern
     *            a format string
     * @return String
     */
    public static String TimestampToString(Timestamp date, String pattern) {
        String strTemp = null;
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            strTemp = formatter.format(date);
        }
        return strTemp;
    }

    /**
     * format a Timestamp with a format string as String, if Timestamp is null
     * then return the default string
     *
     * @param date
     *            Timestamp
     * @param pattern
     *            a format string
     * @param strDefault
     *            a default string
     * @return String
     */
    public static String TimestampToString(Timestamp date, String pattern,
                                           String strDefault) {
        String strTemp = strDefault;
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            strTemp = formatter.format(date);
        }
        return strTemp;
    }

    /**
     * format a Date with a formated string as String , if Date is null then
     * return null
     *
     * @param date
     *            java.util.Date
     * @param pattern
     *            a formated string
     * @return String
     */
    public static String DateToString(java.util.Date date, String pattern) {
        String strTemp = null;
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            strTemp = formatter.format(date);
        }
        return strTemp;
    }

    /**
     * format a java.util.Date with a format string as String, if java.util.Date
     * is null then return the default string
     *
     * @param date
     *            java.util.Date
     * @param pattern
     *            a formated string
     * @param strDefault
     *            a default string
     * @return
     */
    public static String DateToString(java.util.Date date, String pattern,
                                      String strDefault) {
        String strTemp = strDefault;
        if (date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            strTemp = formatter.format(date);
        }
        return strTemp;
    }

    /**
     * change a String to Timestamp with a formated String
     *
     * @param strDate
     *            String
     * @param pattern
     *            a formated string
     * @return Timestamp
     */
    public static Timestamp StringToTimestamp(String strDate, String pattern) {
        if (strDate != null && !strDate.equals("")) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                java.util.Date d = formatter.parse(strDate);
                Timestamp numTime = new Timestamp(d.getTime());
                return numTime;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * change a String to java.sql.date with a formated String
     *
     * @param strDate
     *            String
     * @param format
     *            a formated string
     * @return java.util.Date
     */
    public static java.util.Date StringToDate(String strDate, String format) {
        if (strDate != null && !strDate.equals("")) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                java.util.Date d = formatter.parse(strDate);
                return d;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * validate whether a String meet to the formater 'MM/dd/YYYY' if yes return
     * true, else return false
     *
     * @param strExp
     *            String
     * @return boolean
     */
    public static boolean isDate(String strExp) {
        if (strExp.length() != 10)
            return false;

        try {
            Calendar cal = new GregorianCalendar();
            cal.setLenient(false);
            cal.set(Integer.parseInt(strExp.substring(6, 10)), Integer
                    .parseInt(strExp.substring(3, 5)) - 1, Integer
                    .parseInt(strExp.substring(0, 2)));
            java.util.Date ud = cal.getTime();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * validate whether the three integer coulde be consised of a appropriate
     * date if yes return true, else return false
     *
     * @param intYear
     *            int
     * @param intMonth
     *            int
     * @param intDay
     *            int
     * @return boolean
     */
    public static boolean isDate(int intYear, int intMonth, int intDay) {
        try {
            Calendar cal = new GregorianCalendar();
            cal.setLenient(false);
            cal.set(intYear, intMonth, intDay);
            java.util.Date ud = cal.getTime();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * get a Date according to the given data (year, month, day, hour, minute)
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return Date
     */
    public static final Date getDate(int year, int month, int day, int hour,
                                     int minute) {
        Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
                day, hour, minute);
        return cal.getTime();
    }

    /**
     * get a Date according to the given data (year, month, day)
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static final Date getDate(int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, intToCalendarMonth(month),
                day);
        return cal.getTime();
    }

    /**
     * chage a Date to Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp getTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * chage a Calendar to Timestamp
     *
     * @param date
     * @return
     */
    public static Timestamp getTimestamp(Calendar date) {
        return new Timestamp(date.getTime().getTime());
    }

    /**
     * change a String to Timestamp with a formated String
     *
     * @param date
     *            String
     * @param pattern
     *            a formated string
     * @return Timestamp
     */
    public static Timestamp getTimestamp(String date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            Timestamp timestamp = new Timestamp(formatter.parse(date).getTime());
            return timestamp;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * add several days to a given date
     *
     * @param target
     * @param days
     * @return Date
     */
    public static final Date addDays(Date target, int days) {
        long msPerDay = 0x5265c00L;
        long msTarget = target.getTime();
        long msSum = msTarget + msPerDay * (long) days;
        Date result = new Date();
        result.setTime(msSum);
        return result;
    }

    /**
     * @param source
     *            a date
     * @param field
     *            a time type, the constant of Calendar should be use eg.
     *            Calendar.YEAR,Calendar.MONTH,Calendar.WEEK
     * @param amount
     *            a interval, could be a positive or negtive integer
     * @return a date
     */
    public static final Date addCalendar2(final Date source, int field,
                                          int amount) {

        Calendar greCal = new GregorianCalendar();
        if (source != null) {
            greCal.setTime(source);
        }
        greCal.add(field, amount);
        return greCal.getTime();
    }

    /**
     * get the interal of two dates
     *
     * @param first
     * @param second
     * @return the interal of two dates
     */
    public static int dayDiff(Date first, Date second) {
        long msPerDay = 0x5265c00L;
        long diff = first.getTime() / msPerDay - second.getTime() / msPerDay;
        Long convertLong = new Long(diff);
        return convertLong.intValue();
    }

    /**
     * get the year part of a date
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(1);
    }

    /**
     * get the month part of a date
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int calendarMonth = cal.get(2);
        return calendarMonthToInt(calendarMonth);
    }

    /**
     * get the day part of a date
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(5);
    }

    /**
     * get the hour part of a date
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(11);
    }

    /**
     * get the minute part of a date
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(12);
    }

    /**
     * get the days of a month whitch the date is in
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * chage the calendarMonth to intager
     *
     * @param calendarMonth
     * @return
     */
    private static int calendarMonthToInt(int calendarMonth) {
        if (calendarMonth == 0)
            return 1;
        if (calendarMonth == 1)
            return 2;
        if (calendarMonth == 2)
            return 3;
        if (calendarMonth == 3)
            return 4;
        if (calendarMonth == 4)
            return 5;
        if (calendarMonth == 5)
            return 6;
        if (calendarMonth == 6)
            return 7;
        if (calendarMonth == 7)
            return 8;
        if (calendarMonth == 8)
            return 9;
        if (calendarMonth == 9)
            return 10;
        if (calendarMonth == 10)
            return 11;
        return calendarMonth != 11 ? 1 : 12;
    }

    /**
     * format a Date with a formated string as String
     *
     * @param date
     *            Date
     * @param pattern
     *            a formated string
     * @return String
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * chage the intager to calendarMonth
     *
     * @param month
     * @return
     */
    private static int intToCalendarMonth(int month) {
        if (month == 1)
            return 0;
        if (month == 2)
            return 1;
        if (month == 3)
            return 2;
        if (month == 4)
            return 3;
        if (month == 5)
            return 4;
        if (month == 6)
            return 5;
        if (month == 7)
            return 6;
        if (month == 8)
            return 7;
        if (month == 9)
            return 8;
        if (month == 10)
            return 9;
        if (month == 11)
            return 10;
        return month != 12 ? 0 : 11;
    }


    /**
     * 
     *
     * @param date
     * @return Date
     */
    public static java.sql.Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 
     *
     * @param days
     * @return Date
     */

    public static java.util.Date addDay(int days){
        java.util.Date   date=new   Date();//当前时间
        Calendar   calendar= new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,days);
        date=calendar.getTime();
        return date;
    }
    
    //获取两个时间差  分钟
    public static Long getDiffMinute(Date beforeDate){
    	if(null==beforeDate){
    		return 0l;
    	}
    	Date dataNow = new Date();
		Long times = dataNow.getTime() - beforeDate.getTime();
		Long minute = times/1000/60;//分钟
		return minute;
    }
    
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    //日期数字转成汉字
    public static String transformDateChinese(String date){
		String chineseDate = "";
 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date da;
		try {
			da = sdf.parse(date);
			System.out.println(da);
			String datestr = sdf.format(da);
			System.out.println(datestr);
			String[] strs = datestr.split("-");
			// 年
			for (int i = 0; i < strs[0].length(); i++) {
				chineseDate += formatDigit(strs[0].charAt(i));
			}
			// 月
			char c1 = strs[1].charAt(0);
			char c2 = strs[1].charAt(1);
			String newmonth = "";
			if (c1 == '0') {
				newmonth = String.valueOf(formatDigit(c2));
			} else if (c1 == '1' && c2 == '0') {
				newmonth = "十";
			} else if (c1 == '1' && c2 != '0') {
				newmonth = "十" + formatDigit(c2);
			}
			chineseDate = chineseDate + "年" + newmonth + "月";
			// 日
			char d1 = strs[2].charAt(0);
			char d2 = strs[2].charAt(1);
			String newday = "";
			if (d1 == '0') {//单位数天
				newday = String.valueOf(formatDigit(d2));
			} else if (d1 != '1' && d2 == '0') {//几十
				newday = String.valueOf(formatDigit(d1)) + "十";
			} else if (d1 != '1' && d2 != '0') {//几十几
				newday = formatDigit(d1) + "十" + formatDigit(d2);
			} else if (d1 == '1' && d2 != '0') {//十几
				newday = "十" + formatDigit(d2);
			} else {//10
				newday = "十";
			}
			chineseDate = chineseDate + newday + "日";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chineseDate;
	}
 
	public static char formatDigit(char sign) {
		if (sign == '0')
			sign = '零';
		if (sign == '1')
			sign = '一';
		if (sign == '2')
			sign = '二';
		if (sign == '3')
			sign = '三';
		if (sign == '4')
			sign = '四';
		if (sign == '5')
			sign = '五';
		if (sign == '6')
			sign = '六';
		if (sign == '7')
			sign = '七';
		if (sign == '8')
			sign = '八';
		if (sign == '9')
			sign = '九';
		return sign;
	}
    

    public static void main(String []arg){
        java.util.Date   date=new   Date();//当前时间
        System.out.println("old date="+date);
        Calendar   calendar   =   new   GregorianCalendar();

        calendar.setTime(date);

        calendar.add(calendar.DATE,1);
        date=calendar.getTime();
        System.out.println("new date="+date);
    }
}
