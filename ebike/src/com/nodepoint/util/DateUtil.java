package com.nodepoint.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class DateUtil
{
	/**
	 * 按照指定的格式，将日期类型对象转换成字符串，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
	 * 如果传入的日期为null,则返回空值
	 * 
	 * @param date
	 *            日期类型对象
	 * @param format
	 *            需转换的格式
	 * @return 日期格式字符串
	 */
	public static String formatDate( Date date , String format )
	{
		if ( date == null )
		{
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat( format );
		return formater.format( date );
	}
	
	/**
	 * 将日期类型对象转换成yyyy-MM-dd类型字符串 如果传入的日期为null,则返回空值
	 * 
	 * @param date
	 *            日期类型对象
	 * @return 日期格式字符串
	 */
	public static String formatDate( Date date )
	{
		if ( date == null )
		{
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat( "yyyy-MM-dd" );
		return formater.format( date );
	}
	
	/**
	 * 将日期类型对象转换成yyyy-MM-dd HH:mm:ss类型字符串 如果传入的日期为null,则返回空值
	 * 
	 * @param date
	 *            日期类型对象
	 * @return 日期格式字符串
	 */
	public static String formatTime( Date date )
	{
		if ( date == null )
		{
			return "";
		}
		SimpleDateFormat formater = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return formater.format( date );
	}
	
	/**
	 * 按照指定的格式，将字符串解析成日期类型对象，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @param format
	 *            字符串的格式
	 * @return 日期类型对象
	 */
	public static Date parseDate( String dateStr , String format ) throws ParseException
	{
		if ( StringUtils.isEmpty( dateStr ) )
		{
			return null;
		}
		SimpleDateFormat formater = new SimpleDateFormat( format );
		
		return formater.parse( dateStr );
	}
	
	/**
	 * 将字符串（yyyy-MM-dd）解析成日期
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @return 日期类型对象
	 * @throws ParseException
	 */
	public static Date parseDate( String dateStr ) throws ParseException
	{
		return parseDate( dateStr , "yyyy-MM-dd" );
	}
	
	/**
	 * 将字符串（yyyy-MM-dd hh:mm:ss）解析成日期
	 * 
	 * @param dateStr
	 *            日期格式的字符串
	 * @return 日期类型对象
	 * @throws ParseException
	 */
	public static Date parseTime( String dateStr ) throws ParseException
	{
		return parseDate( dateStr , "yyyy-MM-dd hh:mm:ss" );
	}
	
	/**
	 * 获取当前年份
	 * 
	 * @return int
	 */
	public static int getYear()
	{
		return Calendar.getInstance().get( Calendar.YEAR );
	}
	
	/**
	 * 获取当前月份
	 * 
	 * @return int
	 */
	public static int getMonth()
	{
		return Calendar.getInstance().get( Calendar.MONTH ) + 1;
	}
	
	/**
	 * 获取当前是几号
	 */
	public static int getDay()
	{
		return Calendar.getInstance().get( Calendar.DATE );
	}
	
	/**
	 * 获取当前时间的小时数
	 */
	public static int getHour()
	{
		return Calendar.getInstance().get( Calendar.HOUR_OF_DAY );
	}
	
	/**
	 * 获取当前时间的分钟数
	 */
	public static int getMinite()
	{
		return Calendar.getInstance().get( Calendar.MINUTE );
	}
	
	/**
	 * 判断两个日期是否为同一天
	 * 
	 * @param date1
	 *            日期类型对象1
	 * @param date2
	 *            日期类型对象2
	 * @return true or false
	 */
	public static boolean isSameDay( Date date1 , Date date2 )
	{
		if ( date1 == null || date2 == null )
		{
			return false;
		}
		if ( formatDate( date1 ).equals( formatDate( date2 ) ) )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 判断两个日期是否为同一月
	 * 
	 * @param date1
	 *            日期类型对象1
	 * @param date2
	 *            日期类型对象2
	 * @return true or false
	 */
	public static boolean isSameMonth( Date date1 , Date date2 )
	{
		if ( date1 == null || date2 == null )
		{
			return false;
		}
		if ( formatDate( date1 , "yyyy-MM" ).equals( formatDate( date2 , "yyyy-MM" ) ) )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 判断两个日期是否为同一年
	 * 
	 * @param date1
	 *            日期类型对象1
	 * @param date2
	 *            日期类型对象2
	 * @return true or false
	 */
	public static boolean isSameYear( Date date1 , Date date2 )
	{
		if ( date1 == null || date2 == null )
		{
			return false;
		}
		if ( formatDate( date1 , "yyyy" ).equals( formatDate( date2 , "yyyy" ) ) )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 根据指定的格式（例如：yyyy-MM-dd）获取当天的时间字符串
	 * 
	 * @return 日期字符串
	 */
	public static String getDate( String format )
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat( format );
		String strDate = sdf.format( date );
		return strDate;
	}
	
	/**
	 * 根据指定的格式（例如：yyyy-MM-dd）获取昨天的时间字符串
	 * 
	 * @return 日期字符串
	 */
	public static String getYesterday( String format )
	{
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.DAY_OF_MONTH , - 1 );
		SimpleDateFormat sdf = new SimpleDateFormat( format );
		String strDate = sdf.format( cal.getTime() );
		return strDate;
	}
	
	/**
	 * 对指定的日期增加或减少指定的天数
	 * 
	 * @param date
	 *            需要修改的日期对象
	 * @param amount
	 *            需要修改的数量，如果需要增加一天，amount=1,如果减少一天，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addDay( Date date , int amount )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.DAY_OF_MONTH , amount );
		return cal.getTime();
	}
	
	/**
	 * 对指定的日期增加或减少指定的月数
	 * 
	 * @param date
	 *            需要修改的日期对象
	 * @param amount
	 *            需要修改的数量，如果需要增加一个月，amount=1,如果减少一个月，amount=-1;
	 * @return 修改后日期类型对象
	 */
	public static Date addMonth( Date date , int amount )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.MONTH , amount );
		return cal.getTime();
	}
	
	/**
	 * 计算两个日期相隔的小时数
	 * 
	 * @param date1
	 *            日期对象1
	 * @param date2
	 *            日期对象2
	 * @return 两个日期相隔的小时数
	 */
	@SuppressWarnings( "deprecation" )
	public static double getHours( Date date1 , Date date2 )
	{
		if ( date1 == null || date2 == null )
		{
			return 0;
		}
		date2.setSeconds( 0 );
		date1.setSeconds( 0 );
		return ( date2.getTime() - date1.getTime() ) * 1.0 / ( 1000 * 60 * 60 );
	}
	
	/**
	 * 计算两个日期所隔天数(粗略的天数)
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static Integer getDays( Date startDate , Date endDate ) throws ParseException
	{
		if ( startDate == null || endDate == null )
		{
			return 0;
		}
		// 先将时分移都去掉
		Date start = DateUtil.parseDate( DateUtil.formatDate( startDate , "yyyy-MM-dd" ) );
		Date end = DateUtil.parseDate( DateUtil.formatDate( endDate , "yyyy-MM-dd" ) );
		// Long intervalDays = (end.getTime() - start.getTime())
		// / (24 * 3600 * 1000);
		// return intervalDays.intValue();
		int workdays = 0;
		Calendar cal = null;
		while ( start.before( end ) || start.equals( end ) )
		{
			cal = Calendar.getInstance();
			// 设置日期
			cal.setTime( start );
			// 如果日期不等于周六也不等于周日，工作日+1
			// if((cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
			// &&(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)){
			workdays++ ;
			// }
			// 日期加1
			cal.add( Calendar.DAY_OF_MONTH , 1 );
			start = cal.getTime();
		}
		return workdays;
	}
	
	public static Date getMinTime( List< Date > dateList )
	{
		Date min = new Date();
		for ( int i = 0 ; i < dateList.size() - 1 ; i++ )
		{
			min = dateList.get( i );
			if ( min.after( dateList.get( i + 1 ) ) )
			{
				min = dateList.get( i + 1 );
			}
		}
		
		return min;
	}
	
}
