/*
 * (C) 2013 NTCO Platform Milipp
 */

/**
 * (C) 2008 Operation platform platform.com
 */
package com.img.gen.conmon;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 */
public class DateUtil {
    //
    public static final String PATTERN_DATE_YEAR = "yyyy";
    public static final String PATTERN_DATE_MONTH = "yyyyMM";
    public static final String PATTERN_DATE_DAY = "yyyyMMdd";
    public static final String PATTERN_DATE_DAY2 = "yyMMdd";

    public static final String PATTERN_DATE_SHORT = "yyyyMMdd";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_TIME_NOSECOND= "yyyy-MM-dd HH:mm";
    public static final String PATTERN_DATE_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_TIME = "HH:mm:ss";
    public static final String PATTERN_TIME_INT = "HHmmss";

    //
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String DEFAULT_DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss";
    public static final String HH_MM_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String YYYYMMDD_FORMAT = "yyyyMMdd";
    public static final String YYYYMMDDHHMM_FORMAT = "yyyyMMddHHmm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String MM_DD_FORMAT = "MM月dd日";

    //
    public static final String DATE_TYPE_DAY = "DAY";
    public static final String DATE_TYPE_MONTH = "MONTH";
    public static final String DATE_TYPE_YEAR = "YEAR";
    public static final String DATE_TYPE_WEEK = "WEEK";
    public static final String DATE_TYPE_HOUR = "HOUR";
    public static final String DATE_TYPE_MINUTE = "MINUTE";
    public static final String DATE_TYPE_SECOND = "SECOND";

    public static final long MSEC_OF_MIN = 60000;

    public static final int PHP_TIME_ADD_CONSTANTS = 8*60*60;

    public static String formatDateToString(Date d, String formatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);

        return dateFormat.format(d);
    }

    public static Date formatStringToDate(String dateString, String formatPattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);

        return dateFormat.parse(dateString);
    }

    public static Date adjustDate(Date d, int calendarType, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        cal.add(calendarType, amount);

        return cal.getTime();
    }

    public static Date ignoreTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date getCutMinuteDate(Date d, int cutInterval) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);

        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        int minute = cal.get(Calendar.MINUTE);
        cal.set(Calendar.MINUTE, (minute / cutInterval) * cutInterval);

        return cal.getTime();
    }

    public static List<Date> getYearList(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();

        fromCal.setTime(from);
        fromCal.set(Calendar.MONTH, 0);
        fromCal.set(Calendar.DAY_OF_MONTH, 1);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MILLISECOND, 0);

        toCal.setTime(to);
        toCal.set(Calendar.MONTH, 0);
        toCal.set(Calendar.DAY_OF_MONTH, 1);
        toCal.set(Calendar.HOUR_OF_DAY, 0);
        toCal.set(Calendar.MINUTE, 0);
        toCal.set(Calendar.SECOND, 0);
        toCal.set(Calendar.MILLISECOND, 0);

        List<Date> returnList = new ArrayList<Date>();
        while (!toCal.before(fromCal)) {
            returnList.add(fromCal.getTime());
            fromCal.add(Calendar.MONTH, 1);
        }

        return returnList;
    }

    public static String getQuarterName(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);
        String quarter = "A";

        if (month < 3) {
            quarter = "A";
        } else if (month < 6) {
            quarter = "B";
        } else if (month < 9) {
            quarter = "C";
        } else {
            quarter = "D";
        }

        return quarter;
    }

    public static List<Date> getQuarterList(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();

        fromCal.setTime(from);
        fromCal.set(Calendar.DAY_OF_MONTH, 1);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MILLISECOND, 0);

        toCal.setTime(to);
        toCal.set(Calendar.DAY_OF_MONTH, 1);
        toCal.set(Calendar.HOUR_OF_DAY, 0);
        toCal.set(Calendar.MINUTE, 0);
        toCal.set(Calendar.SECOND, 0);
        toCal.set(Calendar.MILLISECOND, 0);

        List<Date> returnList = new ArrayList<Date>();
        while (!toCal.before(fromCal)) {
            returnList.add(fromCal.getTime());
            fromCal.add(Calendar.MONTH, 3);
            int month = fromCal.get(Calendar.MONTH);
            int firstMonthOfFromCal;

            if (month < 3) {
                firstMonthOfFromCal = 0;
            } else if (month < 6) {
                firstMonthOfFromCal = 3;
            } else if (month < 9) {
                firstMonthOfFromCal = 6;
            } else {
                firstMonthOfFromCal = 9;
            }
            fromCal.set(Calendar.MONTH, firstMonthOfFromCal);
        }

        return returnList;
    }

    public static int getWeekOfDate(Date d) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(d);

        return returnCal.get(Calendar.WEEK_OF_YEAR);
    }

    public static List<Date> getMonthList(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();

        fromCal.setTime(from);
        fromCal.set(Calendar.DAY_OF_MONTH, 1);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MILLISECOND, 0);

        toCal.setTime(to);
        toCal.set(Calendar.DAY_OF_MONTH, 1);
        toCal.set(Calendar.HOUR_OF_DAY, 0);
        toCal.set(Calendar.MINUTE, 0);
        toCal.set(Calendar.SECOND, 0);
        toCal.set(Calendar.MILLISECOND, 0);

        List<Date> returnList = new ArrayList<Date>();
        while (!toCal.before(fromCal)) {
            returnList.add(fromCal.getTime());
            fromCal.add(Calendar.MONTH, 1);
        }

        return returnList;
    }

    public static List<Date> getDayList(Date from, Date to) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();

        fromCal.setTime(from);
        fromCal.set(Calendar.HOUR_OF_DAY, 0);
        fromCal.set(Calendar.MINUTE, 0);
        fromCal.set(Calendar.SECOND, 0);
        fromCal.set(Calendar.MILLISECOND, 0);

        toCal.setTime(to);
        toCal.set(Calendar.HOUR_OF_DAY, 0);
        toCal.set(Calendar.MINUTE, 0);
        toCal.set(Calendar.SECOND, 0);
        toCal.set(Calendar.MILLISECOND, 0);

        List<Date> returnList = new ArrayList<Date>();
        while (!toCal.before(fromCal)) {
            returnList.add(fromCal.getTime());
            fromCal.add(Calendar.DAY_OF_YEAR, 1);
        }

        return returnList;
    }

    //
    public static Date convertToSameDayTime(final Date destDate, final Date dameDate) {
        Calendar returnCal = Calendar.getInstance();

        Calendar today = Calendar.getInstance();
        today.setTime(dameDate);

        returnCal.setTime(destDate);

        returnCal.set(Calendar.YEAR, today.get(Calendar.YEAR));
        returnCal.set(Calendar.MONTH, today.get(Calendar.MONTH));
        returnCal.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));

        return returnCal.getTime();
    }

    public static Date getYesterday(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);
        returnCal.add(Calendar.DATE, -1);

        return returnCal.getTime();
    }

    public static Date getLastWeek(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);
        returnCal.add(Calendar.WEEK_OF_MONTH, -1);

        return returnCal.getTime();
    }

    public static Date getLastSunday(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);
        returnCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        return returnCal.getTime();
    }

    public static Date getThisWeek(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);
        returnCal.add(Calendar.WEEK_OF_MONTH, 0);

        return returnCal.getTime();
    }

    public static Date getLastMonth(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);
        returnCal.set(Calendar.DATE, 1);// 设为当前月的1号
        returnCal.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号

        return returnCal.getTime();
    }

    public static Date getFirstDayOfMonth(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);

        returnCal.set(Calendar.DATE, 1);// 设为当前月的1号
        return returnCal.getTime();
    }

    public static Date getLastDayOfMonth(final Date nowDate) {
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(nowDate);

        returnCal.add(Calendar.MONTH, 1);// 变为下月的1号
        returnCal.set(Calendar.DATE, 1);// 设为下月的1号
        returnCal.add(Calendar.DATE, -1);
        return returnCal.getTime();
    }

    // check the day if the first day of the month.
    public static boolean isFirstDayOfMonth(Date d) {
        Calendar day = Calendar.getInstance();
        day.setTime(d);

        return day.get(Calendar.DAY_OF_MONTH) == 1;
    }

    // check the day if the first day of the year.
    public static boolean isFirstDayOfYear(Date d) {
        Calendar day = Calendar.getInstance();
        day.setTime(d);

        return day.get(Calendar.DAY_OF_MONTH) == 1 && day.get(Calendar.MONTH) == 1;
    }

    public static long ignoreSec(long t) {
        long min = t / MSEC_OF_MIN;
        long left = t % MSEC_OF_MIN;

        if ((left * 2) >= MSEC_OF_MIN) {
            min++;
        }

        return min * MSEC_OF_MIN;
    }


    /**
     * 将Date转换为字符串
     *
     * @param date          Date            要转换的日期
     * @param dateFormatStr String 要转换的日期类型
     * @return String              返加String类型的日期
     */
    public static String DateToString(Date date, String dateFormatStr) {
        if (date == null) {
            return "";
        } else {
            if (dateFormatStr == null || "".equals(dateFormatStr)) {
                dateFormatStr = DEFAULT_DATE_FORMAT;
            }
            SimpleDateFormat simpleDteFormat = new SimpleDateFormat(dateFormatStr);
            return simpleDteFormat.format(date);
        }

    }

    /**
     * 返回当前日期时间的字符串
     *
     * @param dateFormatStr String  要转换的日期类型
     * @return String               返加String类型的日期
     */
    public static String getCurrentDateTime(String dateFormatStr) {
        if (dateFormatStr == null || "".equals(dateFormatStr)) {
            dateFormatStr = DEFAULT_DATE_FORMAT2;
        }
        Date date = new Date();
        SimpleDateFormat simpleDteFormat = new SimpleDateFormat(dateFormatStr);
        return simpleDteFormat.format(date);
    }

    /**
     * 返回当前日期的字符串
     *
     * @param dateFormatStr String  日期格式
     * @return String               返回当前日期的字符串
     */
    public static String getCurrentDate(String dateFormatStr) {
        if (dateFormatStr == null || "".equals(dateFormatStr)) {
            dateFormatStr = DATE_FORMAT;
        }
        Date date = new Date();
        SimpleDateFormat simpleDteFormat = new SimpleDateFormat(dateFormatStr);
        return simpleDteFormat.format(date);
    }

    /**
     * 将字符串转换为Date
     *
     * @param strDate       String        被转换的String类型的日期
     * @param strDateFormat String  Date格式
     * @return Date                 返加Date类型的日期
     */
    public static Date StringTodate(String strDate, String strDateFormat) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        } else {
            if ("".equals(strDateFormat) || strDateFormat == null) {
                strDateFormat = DEFAULT_DATE_FORMAT2;
            }
            Date rDate;
            SimpleDateFormat format = new SimpleDateFormat(strDateFormat);
            try {
                rDate = format.parse(strDate);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return rDate;
        }
    }

    /**
     * 将string转换成指定类型的Timestamp
     *
     * @param str  String   被转换的String类型的日期
     * @param type String  日期格式
     * @return Timestamp   返加Timestamp类型的日期
     */
    public static Timestamp cString2Timestamp(String str, String type) {
        if (type == null || type.equals("")) {
            type = DEFAULT_DATE_FORMAT2;
        }
        if (str.length() <= 10) {
            str = str + " 00:00:00";
        }
        SimpleDateFormat df = new SimpleDateFormat(type);
        try {
            return new Timestamp(df.parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Timestamp getTimeStamp() {
        return cString2Timestamp(getCurrentDateTime(null), null);
    }

    /**
     * 将Timestamp转换成指定类型的string
     *
     * @param ts   Timestamp    被转换的Timestamp类型的日期
     * @param type String     日期格式
     * @return String         返加String类型的日期
     */
    public static String cTimestamp2String(Timestamp ts, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date(ts.getTime()));
    }

    /**
     * 将Timestamp转换成date
     *
     * @param ts Timestamp   被转换的Timestamp类型的日期
     * @return Date          返加Date类型的日期
     */
    public static Date cTimestamp2Date(Timestamp ts) {
        String strDate = cTimestamp2String(ts, DEFAULT_DATE_FORMAT2);
        return StringTodate(strDate, DEFAULT_DATE_FORMAT2);
    }

    /**
     * 日期相加函数
     *
     * @param sorDate  String   被加的日期,必须为String类型
     * @param value    int        天数,可为负数
     * @param dateType String  日期格式
     * @return String          相加后的日期,必须为String类型
     */
    public static String dateAdd(String sorDate, int value, String dateType) {
        if (dateType == null || value == 0 || sorDate == null) {
            return sorDate;
        }
        Date date = DateUtil.StringTodate(sorDate, DATE_FORMAT);
        Date getDate = DateUtil.dateAdd(date, value, dateType);
        return DateUtil.DateToString(getDate, DATE_FORMAT);
    }

    /**
     * 日期相加函数
     *
     * @param sorDate  Date     被加的日期,必须为Date类型
     * @param value    int        天数,可为负数
     * @param dateType String  日期格式
     * @return Date            相加后的日期
     */
    public static Date dateAdd(Date sorDate, int value, String dateType) {
        if (dateType == null || value == 0 || sorDate == null) {
            return sorDate;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sorDate);
        if (dateType.equalsIgnoreCase(DATE_TYPE_SECOND)) {
            calendar.add(Calendar.SECOND, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_MINUTE)) {
            calendar.add(Calendar.MINUTE, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_HOUR)) {
            calendar.add(Calendar.HOUR, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_WEEK)) {
            calendar.add(Calendar.WEDNESDAY, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_DAY)) {
            calendar.add(Calendar.DATE, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_MONTH)) {
            calendar.add(Calendar.MONTH, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_YEAR)) {
            calendar.add(Calendar.YEAR, value);
        }
        return calendar.getTime();
    }
    
    /**
     * 日期相加函数
     *
     * @param sorDate  Date     被加的日期,必须为Date类型
     * @param dateFormat String 日期格式
     * @param value    int        数量,可为负数
     * @param dateType String  日期类型(年、月、星期、日、时、分、秒)
     * @return Date            相加后的日期
     */
    public static String dateAdd(String sorDate, String dateFormat,int value, String dateType) {
        if (dateType == null || value == 0 || sorDate == null) {
            return sorDate;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(StringTodate(sorDate,dateFormat));
        if (dateType.equalsIgnoreCase(DATE_TYPE_SECOND)) {
            calendar.add(Calendar.SECOND, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_MINUTE)) {
            calendar.add(Calendar.MINUTE, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_HOUR)) {
            calendar.add(Calendar.HOUR, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_WEEK)) {
            calendar.add(Calendar.WEDNESDAY, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_DAY)) {
            calendar.add(Calendar.DATE, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_MONTH)) {
            calendar.add(Calendar.MONTH, value);
        } else if (dateType.equalsIgnoreCase(DATE_TYPE_YEAR)) {
            calendar.add(Calendar.YEAR, value);
        }
        return DateToString(calendar.getTime(),dateFormat);
    }

    //年份是否相同
    public static boolean isSameYear(Date from, Date to) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        int fromy = cal.get(Calendar.YEAR);
        cal.setTime(to);
        int toy = cal.get(Calendar.YEAR);
        return fromy == toy;
    }

    /**
     * 判断日期是否是今天
     *
     * @param from
     * @return
     */
    public static boolean isToday(Date from) {
        Date to = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        long fromd = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(to);
        long tod = cal.get(Calendar.DAY_OF_YEAR);
        return isSameYear(from, to) && fromd == tod;
    }

    /**
     * 判断日期是否是昨天
     *
     * @param from
     * @return
     */
    public static boolean isYesterDay(Date from) {
        Date to = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        int fromd = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(to);
        int tod = cal.get(Calendar.DAY_OF_YEAR);
        return isSameYear(from, to) && ((fromd + 1) == tod);
    }

    /**
     * 将时间转换成 昨天 12:30 /  今天 12:40 / 10分钟前 //两个小时前
     *
     * @param date
     * @return
     */
    public static String parseDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            return "";
        }
        calendar.setTime(date);

        if (isToday(date)) {//是否今天 DEFAULT_HOUR_MINITE_FORMAT
            return "今天 " + DateToString(date, DateUtil.HH_MM_FORMAT);
        }

        if (isYesterDay(date)) {//是否昨天
            return "昨天 " + DateToString(date, DateUtil.HH_MM_FORMAT);
        }

        if (isSameYear(date, new Date())) {//是否今年
            return DateToString(date, DateUtil.MM_DD_FORMAT);
        }

        return DateToString(date, DateUtil.DATE_FORMAT);
    }
    
    public static String cSecond2HHmmss(long seconds){
        String timeStr = "";

        long hour = seconds / 3600;
        long minute = seconds % 3600 / 60;
        long second = seconds % 60;

        if (hour > 0) {
            if (hour < 10) {
                timeStr = "0" + hour + ":";
            } else {
                timeStr = hour + ":";
            }
        }

        if (minute < 10) {
            timeStr = timeStr + "0" + minute + ":";
        } else {
            timeStr = timeStr + minute + ":";
        }

        if (second < 10) {
            timeStr = timeStr + "0" + second;
        } else {
            timeStr = timeStr + second;
        }

        return timeStr;
    }
    /**
     * 将时间转换成 几秒前 /  几分钟之前 / 几小时前 // 1天前、3天前//1年前
     */
    public static String parseDhms(Date from) {
        Date to = new Date();//当前日期

        int severalDay = Integer.parseInt(DateToString(to, PATTERN_DATE_DAY))-Integer.parseInt(DateToString(from,PATTERN_DATE_DAY));

        if(severalDay==0){
            String dateFrom = DateToString(from, PATTERN_TIME_INT);
            int hhFrom = Integer.parseInt(dateFrom.substring(0, 2));
            int mmFrom = Integer.parseInt(dateFrom.substring(2,4));
            int ssFrom = Integer.parseInt(dateFrom.substring(4,6));

            String dateTo = DateToString(to, PATTERN_TIME_INT);
            int hhTo = Integer.parseInt(dateTo.substring(0, 2));
            int mmTo = Integer.parseInt(dateTo.substring(2,4));
            int ssTo = Integer.parseInt(dateTo.substring(4,6));

            if(hhTo>hhFrom){
                return (hhTo- hhFrom)+"小时前";
            }else if(mmTo>mmFrom){
                return (mmTo- mmFrom)+"分钟前";
            }else {
                System.out.println(dateFrom+":"+dateTo);
                return (ssTo- ssFrom)+"秒前";
            }

        }else if(severalDay>0&&severalDay<=3){
            return severalDay+"天前";
        }else {

            return parseDate(from);
        }

    }
   //得到相差天数
    public static long getDiffDay(String startTime, String endTime, String format) {
//按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long diff;
        try {
//获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff/nd;//计算差多少天

            return day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDiffHour(String startTime, String endTime, String format) {
//按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long diff;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff/nd;//计算差多少天
            long hour = diff%nd/nh;//计算差多少小时
            return hour;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    //返回相差小时数（包括天）
    public static long getAllDiffHour(String startTime, String endTime, String format) {
    	//按照传入的格式生成一个simpledateformate对象
    	        SimpleDateFormat sd = new SimpleDateFormat(format);
    	        long nd = 1000*24*60*60;//一天的毫秒数
    	        long nh = 1000*60*60;//一小时的毫秒数
    	        long diff;
    	        try {
    	            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
    	            long day = diff/nd;//计算差多少天
    	            long hour = diff%nd/nh;//计算差多少小时
    	            return day*24+hour;
    	        } catch (ParseException e) {
    	            e.printStackTrace();
    	        }
    	        return 0;
    	    }

    public static long getDiffMm(String startTime, String endTime, String format) {
//按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long diff;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long day = diff/nd;//计算差多少天
            long hour = diff%nd/nh;//计算差多少小时
            long min = diff%nd%nh/nm;//计算差多少分钟
            return min;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getDiffSs(String startTime, String endTime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000*24*60*60;//一天的毫秒数
        long nh = 1000*60*60;//一小时的毫秒数
        long nm = 1000*60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数
        long diff;
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            long sec = diff%nd%nh%nm/ns;//计算差多少秒
            return sec;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * Description:处理shop库中表存放的时间戳
     * @param phpTimeSeconds
     * @param dateFormat  要转换的时间格式：如：yyyy-MM-dd
     * @return   dateFormat格式转换的日期时间字符串
     */
    public static String handlePHPTimeSeconds(long phpTimeSeconds,String dateFormat) {
        String returnValue = "";
        if(phpTimeSeconds>=0) {
            long javaTimeMillis = Long.parseLong(phpTimeSeconds + "000");
            returnValue = cTimestamp2String(new Timestamp(javaTimeMillis),dateFormat);
        }
        return returnValue;
    }
    
   /**
    * 根据dateFormat格式的字符串返回代表的秒
    * @author xuchao
    * 2013-10-10下午07:35:27
    */
    public static int getDateSeconds(String date,String dateFormat){
    	int seconds=0;
    	Date d=StringTodate(date,dateFormat);
    	if(null!=d){
    		String times=String.valueOf(d.getTime());
    		times=times.substring(0, times.length()-3);
    		seconds=Integer.parseInt(times);
    	}
    	return seconds;
    }

    /**
     * 根据dateFormat格式的字符串返回代表的秒
     * @author hui
     * 2013-10-10下午07:35:27
     */
    public static int getPHPDateSeconds(Date date){
        int seconds=0;
        if(null==date){
            date = new Date();
        }
        String times=String.valueOf(date.getTime());
        times=times.substring(0, times.length()-3);
        seconds=Integer.parseInt(times) - PHP_TIME_ADD_CONSTANTS;
        return seconds;
    }
    public static void main(String[] args){
        System.out.println(parseDhms(StringTodate("2012-01-25 15:44:02", PATTERN_DATE_TIME)));
    }

}
