/*
 * 创建日期 2004-2-26
 *
 * 时间运算函数
 * 
 */
package com.winnerbook.base.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 * 
 *         更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class TimeUtil {

    public static String getMillTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss SSS");
        String sj_str = dateFormat.format(new Date());

        return sj_str;
    }

    /*
     * 获取当前时间
     */
    public static String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String sj_str = dateFormat.format(new Date());

        return sj_str;
    }

    /*
     * 获取当前年份
     */
    public static int getYear() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy");
        String sj_int = dateformat.format(new Date());

        return Integer.parseInt(sj_int);

    }

    /*
     * 获取当前年份
     */
    public static String getYearMonth() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM");
        String sj_int = dateformat.format(new Date());

        return sj_int;

    }

    /**
     * 获取当前日期的 DAY段数据
     * 
     * @return
     */
    public static int getToDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取针对当前时间的 指定月偏移的某一天
     * 
     * @param month
     * @param day
     * @return
     */
    public static String getDate(int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1 + month;
        year = month > 12 ? year + 1 : year;
        month = month > 12 ? month - 12 : month;
        return year + "-" + (month < 10 ? "0" + month : month) + "-" + day;

    }

    /*
     * 获取当前日期
     */
    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sj_str = dateFormat.format(new Date());

        return sj_str;
    }

    /*
     * 格式化
     */
    public static String formatNewsDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM"
                + "'<b>'dd'</b>'");
        if (null == dateStr || dateStr.trim().length() < 1) {
            return dateFormat.format(new Date());
        }

        Date date = parseDate(dateStr);
        String sj_str = dateFormat.format(date);

        return sj_str;
    }

    /*
     * 格式化时间
     */
    public static String formatTime(String timeStr) {
        if (null == timeStr) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = parseTime(timeStr);
        String sj_str = dateFormat.format(date);

        return sj_str;
    }

    public static String formatOnlyTime(String timeStr) {
        if (null == timeStr) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = parseTime(timeStr);
        String sj_str = dateFormat.format(date);

        return sj_str;
    }

    /*
     * 格式化日期
     */
    public static String formatDate(String dateStr) {
        if (null == dateStr || dateStr.trim().length() < 1) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parseDate(dateStr);
        String sj_str = dateFormat.format(date);

        return sj_str;
    }

    /*
     * 解析日期字符串
     */
    public static Date parseDate(String dateStr) {
        if (null == dateStr) {
            return null;
        }
        Date date = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }

        return date;
    }

    /*
     * 解析时间字符串
     */
    public static Date parseTime(String timeStr) {
        if (null == timeStr) {
            return null;
        }

        Date date = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        try {
            date = dateFormat.parse(timeStr);
        } catch (ParseException e) {
        }

        return date;
    }

    /*
     * 时间偏移运算
     */
    public static String getTime(int skipDay) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(cal.getTime());
    }

    /*
     * 时间偏移运算
     */
    public static String getTime2(int skipDay) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(cal.getTime());
    }

    public static String getTime3(int skipDay) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(cal.getTime());
    }

    /*
     * 某一时间的偏移运算
     */
    public static String getTime(String timeStr, int skipDay) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(parseTime(timeStr));

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(cal.getTime());
    }

    /**
     * 获取月的偏移量
     * 
     * @param timeStr
     * @param skipMonth
     * @return
     */
    public static String getMonthTime(String timeStr, int skipMonth) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(parseTime(timeStr));

        cal.add(GregorianCalendar.MONTH, skipMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(cal.getTime());
    }

    public static String getMonthDate(String dateStr, int skipMonth) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr));

        cal.add(GregorianCalendar.MONTH, skipMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(cal.getTime());
    }

    public static String getRedDate(String dateStr) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr));

        int month = cal.get(Calendar.MONTH) + 1;// 获取月份
        int day = cal.get(Calendar.DAY_OF_MONTH);// 获取日
        if (2 == month && (18 <= day && day <= 26)) {
            int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            cal.set(Calendar.DAY_OF_MONTH, maxDay);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(cal.getTime());
    }

    /*
     * 日期偏移运算(增、减几日）
     */
    public static String getDate(int skipDay) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(cal.getTime());
    }

    /*
     * 日期偏移运算(增、减几日）
     */
    public static String getDate(String dateStr, int skipDay) {
        if (null == dateStr) {
            return null;
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr));

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateFormat.format(cal.getTime());
    }

    /*
     * 时间偏移运算(增、减几日、几小时、几分）
     */
    public static String getTime(int skipDay, int skipHour, int skipMinute) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        cal.add(GregorianCalendar.HOUR_OF_DAY, skipHour);
        cal.add(GregorianCalendar.MINUTE, skipMinute);

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(cal.getTime());
    }

    /*
     * 某一时间的偏移运算(增、减几日、几小时、几分）
     */
    public static String getTime(String timeStr, int skipDay, int skipHour,
            int skipMinute) {
        if (null == timeStr) {
            return null;
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(parseTime(timeStr));

        cal.add(GregorianCalendar.DAY_OF_MONTH, skipDay);
        cal.add(GregorianCalendar.HOUR_OF_DAY, skipHour);
        cal.add(GregorianCalendar.MINUTE, skipMinute);
        cal.get(GregorianCalendar.DAY_OF_WEEK_IN_MONTH);

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(cal.getTime());
    }

    /**
     * 计算月份偏移 protoDate 格式为 yyyy-MM-dd HH:mm:ss 日期 monthOffset int 偏移量
     * 未作2月特殊处理，需在外部进行运算前判断。
     */
    public static String getOffsetMonthDate(String protoDate, int monthOffset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(parseTime(protoDate));
        // cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - monthOffset);
        // //这种写法是错误的，这种偏移以30天为标准
        cal.add(Calendar.MONTH, monthOffset); // 正确写法
        // System.out.println(cal.get(Calendar.MONTH));

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(cal.getTime());

    }

    public static String getImgPath(String str) {
        Calendar c = Calendar.getInstance();
        String path = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1)
                + "/";
        if (!Tools.isEmpty(str))
            path += str + "/";

        return path;

    }

    /*
     * 计算日期相差几天
     */
    public static long subtraction(Date minuend, Date subtrahend) {

        long daterange = minuend.getTime() - subtrahend.getTime();
        long time = 1000 * 3600 * 24;

        return (daterange % time == 0) ? (daterange / time)
                : (daterange / time + 1);
    }

    public static long getM(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public static long getTime(String time) {
        long r = 0;
        if (!Tools.isEmpty(time)) {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date d1 = sd.parse(time, pos);
            r = d1.getTime();
        }
        return r;
    }

    /**
     * 验证时间偏移日期后是否超过指定时间
     * 
     * @param begtime
     * @param pov
     * @param compareTime
     * @return
     */
    public static boolean validTime(String begtime, int pov, String compareTime) {
        Date date = TimeUtil.parseDate(TimeUtil.getDate(begtime, pov));
        if (date == null)
            return false;

        if (!Tools.isEmpty(compareTime)) {
            Date compareDate = TimeUtil.parseDate(compareTime);
            if (date.before(compareDate)) {
                return false;
            }
        } else {
            if (date.before(new Date())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算时间便宜 返回单位为S 主要用于短信发送倒计时时间
     * 
     * @param time
     * @return
     */
    public static long getTimeStamp(String time) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date d1 = sd.parse(time, pos);

        // 用现在距离1970年的时间间隔new
        // Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔
        long result = new Date().getTime() - d1.getTime();// 得出的时间间隔是毫秒
        return result / 1000;
    }

    public static String getInterval(String createtime) { // 传入的时间格式必须类似于2012-8-21
        // 17:53:20这样的格式
        String interval = null;

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date d1 = sd.parse(createtime, pos);

        // 用现在距离1970年的时间间隔new
        // Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔
        long time = new Date().getTime() - d1.getTime();// 得出的时间间隔是毫秒

        if (time / 1000 < 10 && time / 1000 >= 0) {
            // 如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒
            interval = "刚刚";

        } else if (time / 3600000 < 24 && time / 3600000 > 0) {
            // 如果时间间隔小于24小时则显示多少小时前
            int h = (int) (time / 3600000);// 得出的时间间隔的单位是小时
            interval = h + "小时前";

        } else if (time / 60000 < 60 && time / 60000 > 0) {
            // 如果时间间隔小于60分钟则显示多少分钟前
            int m = (int) ((time % 3600000) / 60000);// 得出的时间间隔的单位是分钟
            interval = m + "分钟前";

        } else if (time / 1000 < 60 && time / 1000 > 0) {
            // 如果时间间隔小于60秒则显示多少秒前
            int se = (int) ((time % 60000) / 1000);
            interval = se + "秒前";

        } else {
            // 大于24小时，则显示正常的时间，但是不显示秒
            interval = "一天前";
        }
        return interval;
    }

    /**
     * liyanlin 2014-11-14 17:43
     * 
     * @param 传入指定时间
     * 
     * @return 指定日期+1个月+1天之后的时间
     */
    public static String getCaclDate(String createtime) { // 传入的时间格式必须类似于2012-8-21
        // 17:53:20这样的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date dt = sdf.parse(createtime, pos);

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(dt);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, 1);// (在刚才的结果上)再加10天

        return sdf.format(calendar.getTime());
    }

    // 当前时间与
    public static long subDay(String endTime) {
        Date minuend;
        long time = 1000 * 3600 * 24;
        long daterange = 0l;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            minuend = sdf.parse(endTime);
            daterange = minuend.getTime() - new Date().getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (daterange % time == 0) ? (daterange / time)
                : (daterange / time + 1);
    }

    // 增加返回天、小时、分、秒
    public static String subDay2(String endTime) {
        long different = StringToDate(endTime).getTime()
                - StringToDate(getTime()).getTime();
        String result = "";
        SimpleDateFormat sd = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;
        long elapsedSeconds = different / secondsInMilli;
        if (elapsedDays <= 0 && elapsedHours <= 0 && elapsedMinutes <= 0
                && elapsedSeconds <= 0) {
            return "0";
        } else {
            result = elapsedDays + "天" + elapsedHours + "小时" + elapsedMinutes
                    + "分钟" + elapsedSeconds + "秒";
            System.out.printf("%d days, %d hours, %d minutes, %d seconds%n",
                    elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        }
        // 获得两个时间的毫秒时间差异
        return result;
    }

    // 字符串转为时间
    public static Date StringToDate(String dateStr) {
        return StringToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    // 字符串转为时间
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前月首天或者末天
     * @param value 0当月末天 1当月首天
     */
    public static String getLastDay(int value) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        if (value == 1) {
            calendar.setTime(new Date());
            calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
        } else if (value == 0) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, value);
        }
        return sdf.format(calendar.getTime());
    }

    public static void main(String[] args) {

        System.out.println(TimeUtil.subDay2("2015-01-09 13:46:52"));

        // SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        // Date date = parseTime("2012-11-26 12:34:56");
        // String sj_str = dateFormat.format(date);

        // System.out.println(sj_str);
        // System.out.println("reply".equals("RePly"));
        //
        // System.out.println("当前日期：" + getDate());
        // System.out.println("当前时间：" + getTime());
        //
        // System.out.println("两天后：" + getTime(2));
        // System.out.println("十四天前：" + getMonthTime("2014-3-15 8:10:30", -1));
        //
        // System.out.println("四天后：" + getDate(4));
        // System.out.println("十五天前：" + getDate(-15));
        //
        // System.out.println("一分钟前：" + getTime(0, 0, -1));
        // System.out.println("一分钟后：" + getTime(0, 0, 1));
        //
        // System.out.println("昨天的一小时一分钟前：" + getTime(-1, -1, -1));
        //
        // Date cTime = parseTime(getTime());
        // Date bTime = parseTime(getTime(0, 0, -1));
        // Date lTime = parseTime(getTime(0, 0, 1));
        //
        // System.out.println(cTime.compareTo(bTime));
        // System.out.println(cTime.compareTo(lTime));
        // System.out.println(cTime.compareTo(cTime));
        //
        // Date btime = TimeUtil.parseTime(TimeUtil.getTime(0, 0, -15));
        // Date ltime = TimeUtil.parseTime("2004-3-24 8:10:30");
        //
        // if (btime.compareTo(ltime) > 0) {
        // System.out.println("间隔大于15分");
        // } else {
        // System.out.println("间隔小于15分");
        // }
        //
        // System.out.println("格式化2001-04-09 14:39:00.000  "
        // + formatTime("2001-04-09 14:39:00.000"));
        // System.out.println("格式化2001-04-09 14:2:3.000  "
        // + formatTime("2001-04-09 14:2:3.000"));
        // System.out.println("格式化2004-04-01 00:00:00.000  "
        // + formatDate("2004-04-01 00:00:00.000"));
        // System.out.println("格式化2004-04-01 10:03:00.000  "
        // + formatDate("2004-04-01 10:03:00.000"));
        // System.out.println("格式化null  " + formatDate(null));
        //
        // System.out.println(subtraction(parseDate("2004-04-01"),
        // parseDate("2004-04-02")));
        // System.out.println(subtraction(parseDate("2004-6-1 22:22:00"),
        // parseDate("2004-5-31 23:22:00")));
        // System.out.println(subtraction(parseDate("2004-6-1 23:22:00"),
        // parseDate("2004-5-31 22:22:00")));
        // System.out.println(getDate("2004-4-1", 1));
        // System.out.println(getDate("2004-4-1", -2));
        // System.out.println(getTime(getDate() + " 01:10:10", 1));
        //
        // System.out.println("my test");
        // System.out.println(getM(new Date()));
        // System.out.println(getM(parseTime("2004-4-1 10:10:10")));
        // System.out.println(getM(parseTime("2003-4-1 10:10:10")));
        // System.out.println(getM(parseTime("2004-5-1 10:10:10")));
        // System.out.println(getM(parseTime("2004-7-1 10:10:10")));
        //
        // System.out.println(getTime().replaceAll("-", "").replace(" ", "")
        // .replace(":", ""));
        //
        // System.out.println(getDate(7, 15));
        // System.out.println(getInterval("2014-3-19 22:22:00"));
        // System.out.println(getTimeStamp("2014-8-15 22:22:00"));
        // System.out.println(subtraction(parseDate("2014-08-16 00:00:00"),
        // parseDate("2014-8-16")));

        // System.out.println(getMonthTime("2014-8-15 22:22:00", 12));
        // System.out.println(subDay2("2015-1-5 12:00:25"));

        System.out.println(getTime2(1));
        System.out.println(getTime3(1));

    }
}
