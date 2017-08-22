package com.distribution.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

public class DateHelper {

	// 年-月-日
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	// 年-月-日
	public static final String YYYYMMDD = "yyyyMMdd";
	// 年-月-日  时:分:秒
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	// 年月日 时分秒
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	// 年月日 时分秒毫秒
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmsssss";
	// 年
	public static final String YYYY = "yyyy";
	// 月
	public static final String MM = "MM";

	public static final String SIEBEL_CPBHEC = "MM/dd/yyyy HH:mm:ss";
	public static final String US_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
	
	
	/**
	 * Example: getSystemDate();
	 * 
	 * return Date
	 * 
	 * @param format
	 * @return
	 */
	public static Date getSystemDate() {

		return new Date();
	}

	/**
	 * Example: getSystemDate("yyyyMMdd");
	 * 
	 * return 20110820
	 * 
	 * @param format
	 * @return
	 */
	public static String getSystemDateString(String format) {

		return formatDate(new Date(), format);
	}
	
	/**
	 * Example: getCurrentTime();
	 * 
	 * return 20110820101010222
	 * 
	 * @param 
	 * @return
	 */
	public static String getCurrentTime() {

		return getSystemDateString(YYYYMMDDHHMMSSSSS);
	}
	
	/**
	 * Example: formatDate(date, "yyyyMMdd");
	 * 
	 * date = 2011/01/02, return 20110102
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {

		final SimpleDateFormat sdf = new SimpleDateFormat(format);

		if (date == null)
			return null;

		return sdf.format(date);
	}

	/**
	 * Example: format("2011/01/10", "yyyy/MM/dd", "yyyy.MM.dd");
	 * 
	 * return 2011.01.10
	 * 
	 * @param dateString
	 * @param srcFormat
	 * @param targetFormat
	 * @return
	 */
	public static String formatDate(String dateString, String srcFormat, String targetFormat) {

		final SimpleDateFormat sdf = new SimpleDateFormat(srcFormat);

		Date date = null;
		try {
			if (StringUtils.isNotBlank(dateString))
				date = sdf.parse(dateString);
		}
		catch (ParseException e) {}

		return formatDate(date, targetFormat);
	}
	
	public static Date getDateFromStr(String dateString, String srcFormat) {

		final SimpleDateFormat sdf = new SimpleDateFormat(srcFormat);

		Date date = null;
		try {
			if (StringUtils.isNotBlank(dateString))
				date = sdf.parse(dateString);
		}
		catch (ParseException e) {}

		return date;
	}
	
	public static boolean isBetweenDate(String beginDateString,String endDateString,String middleDateString,String srcFormat) {
		Date begin = getDateFromStr(beginDateString, srcFormat);
		Date end = getDateFromStr(endDateString, srcFormat);
		Date middle = getDateFromStr(middleDateString, srcFormat);
		long btime = begin.getTime();
		long etime = end.getTime();
		long mtime = middle.getTime();
		
		if(mtime>=btime&&mtime<=etime) {
			return true;
		}
		return false;
	};
	/**
	 * Name: 
	 * Description: 通过时间的毫秒数据计算 对应的时:分:秒
	 * @author gaodan
	 * @date 2015年12月1日 下午3:22:27
	 * @param lms
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getTimeFormat(long time){
		StringBuffer dateStr=new StringBuffer("");
		Date date = new Date(time); 
		dateStr.append(date.getHours() ).append(":").append(date.getMinutes()).append(":").append(date.getSeconds());		
		return dateStr.toString();
	}

	/**
	 * Name: 
	 * Description: 
	 * @author gaodan
	 * @date 2015年12月2日 上午8:51:52
	 * @param y 
	 * @param z
	 * @return
	 */
	public static float getTimeCompletePercent(long y, long z) { 
	    String baifenbi = "";// 接受百分比的值 
	    double baiy = y * 1.0; 
	    double baiz = z * 1.0; 
	    double fen = (baiy / baiz); 
	    DecimalFormat df1 = new DecimalFormat("0.00");
	    baifenbi = df1.format(fen); 
	    float percent=Float.parseFloat(baifenbi)*100;
	    return percent>=100?100:percent; 
	  } 
	
	/**
	 * Name: 
	 * Description: 将 0:3:12.56 时间格式转成毫秒
	 * @author gaodan
	 * @date 2015年12月2日 上午8:55:48
	 * @param time
	 * @return
	 */
	public static long getTimeMS(String time){
		String[] timeArray=time.split(":");
		long totalTime=0;
		for(int i=0;i<timeArray.length;i++){
			String str=timeArray[i];
			if(i==0){
				//处理小时转毫秒
				long hour=Long.parseLong(str);
				totalTime+=hour*60*60*1000;
			}else if(i==1){
				//处理分钟转毫秒
				long minute=Long.parseLong(str);
				totalTime+=minute*60*1000;
			}else if(i==2){
				//处理秒转毫秒
				double second=Double.parseDouble(str);
				totalTime+=second*1000;
			}
			
		}
		return totalTime;
	}
	/**
	 * Name: 
	 * Description: 将 0:3:12.56 时间格式转成秒
	 * @author gaodan
	 * @date 2015年12月2日 下午1:34:59
	 * @param time
	 * @return
	 */
	public static long getTimeSecond(String time){
		String[] timeArray=time.split(":");
		long totalTime=0;
		for(int i=0;i<timeArray.length;i++){
			String str=timeArray[i];
			if(i==0){
				//处理小时转毫秒
				long hour=Long.parseLong(str);
				totalTime+=hour*60*60;
			}else if(i==1){
				//处理分钟转毫秒
				long minute=Long.parseLong(str);
				totalTime+=minute*60;
			}else if(i==2){
				//处理秒转毫秒
				double second=Double.parseDouble(str);
				totalTime+=second;
			}
			
		}
		return totalTime;
	}
	/**
	 * Name: 
	 * Description: 将分钟数转换成毫秒数
	 * @author gaodan
	 * @date 2015年12月2日 上午9:08:33
	 * @param minute
	 * @return
	 */
	public static long getMSFromMinute(int minute){
		return minute*60*1000;
		
	}
	/**
	 * Name: 
	 * Description: 计算两个时间相差的毫秒数
	 * @author gaodan
	 * @date 2015年12月3日 上午10:09:07
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static long geTimeDifference(Date beginTime,Date endTime){
		return (endTime.getTime()-beginTime.getTime());
	}
	/**
	 * Name: 
	 * Description: 将毫秒转换成 0000:00:00格式字符串
	 * @author gaodan
	 * @date 2015年12月3日 上午10:13:36
	 * @param time
	 * @return
	 */
	public static String getMSTimeFormat(long ms){
		StringBuffer timeStr=new StringBuffer();
		 int ss = 1000;  
         int mi = ss * 60;  
         int hh = mi * 60;  
         int dd = hh * 24;  
         long day = ms / dd;  
         long hour = (ms - day * dd) / hh;  
         long minute = (ms - day * dd - hour * hh) / mi;  
         long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
         long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
        // String strDay = day < 10 ? "0" + day : "" + day; //天  
         hour+=(day*24);
         String strHour = hour < 10 ? "000" + hour : "00" + hour;//小时  
         String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟  
         String strSecond = second < 10 ? "0" + second : "" + second;//秒  
         String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒  
         strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;  
         timeStr.append(strHour).append(":").append(strMinute).append(":").append(strSecond);
		 return timeStr.toString();
	}
	/**
	 * Name: 
	 * Description: 将字符串"Thu Aug 27 11:16:20 CST 2015" 格式日期转换成Date类型
	 * @author gaodan
	 * @date 2015年12月15日 下午3:08:37
	 * @param dateTime
	 * @return
	 * @throws Exception
	 */
	public static Date parseDateFromUS(String dateTime) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(US_FORMAT, java.util.Locale.US);
		Date date = sdf.parse(dateTime);
		return date;  
	}
//	public static void main(String[] args) {
//		System.out.println(getMSTimeFormat(25*60*60*1000+20*60*1000+5*1000));
//	}
	/**
     * Name: 
     * Description: 判断当前时间是否在指定的时间范围内
     * @author gaodan
     * @date 2015年12月16日 上午11:24:05
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isTimeAvailable(Date startTime,Date endTime){
    	Date current=new Date();
    	if(startTime.getTime()<=current.getTime()&&current.getTime()<=endTime.getTime()){
    		return true;
    	}
    	return false;
    }
    /**
     * Name: 
     * Description: 获取指定日期的第二天
     * @author gaodan
     * @date 2015年12月31日 上午10:06:31
     * @param currentDay
     * @return
     */
    @SuppressWarnings("static-access")
	public static Date getNextDay(Date currentDay,int days){
    	Calendar calendar=new GregorianCalendar(); 
        calendar.setTime(currentDay); 
        calendar.add(calendar.DATE,days);
        Date nextDate=calendar.getTime();   
    	return nextDate;
    }
    public static void main(String[] args) {
		System.out.println(300/1000);
	}
}
