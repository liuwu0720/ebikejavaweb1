/**
 * 文件名：DateUtils.java
 * 版本信息：Version 1.0
 * 日期：2016年5月11日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年5月11日 下午3:32:22
 */
public class DateStrUtil {

	/**
	 * 方法描述：将DATE转为string yyyy-MM-dd HH:mm：ss
	 * 
	 * @param date
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年5月11日 下午3:37:27
	 */
	public static String toString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 
	 * 方法描述：
	 * 
	 * @param timeString
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年5月11日 下午3:40:22
	 * @throws ParseException
	 */
	public static Date toDate(String timeString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(timeString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Calendar nowTime = Calendar.getInstance();   
		   Date nowDate = (Date) nowTime.getTime(); //得到当前时间  
		   Calendar afterTime = Calendar.getInstance();   
		   afterTime.add(Calendar.DAY_OF_MONTH, 30);       //当前分钟+5  
		   Date afterDate = (Date) afterTime.getTime();   
		   System.out.println("今天时间"+nowDate);  
		   System.out.println("修改后的 时间"+afterDate);  
		   Date beginTime =afterTime(new Date(),Calendar.MINUTE,15);
		   System.out.println(beginTime);
	}

	
	/**
	  * 方法描述：
	  * @param date
	  * @param second
	  * @param i
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年5月25日 上午9:26:13
	  */
	public static Date afterTime(Date date, int second, int i) {
		Calendar afterTime = Calendar.getInstance();
		afterTime.add(second, i);
		Date afDate = afterTime.getTime();
		return afDate;
	}
}
