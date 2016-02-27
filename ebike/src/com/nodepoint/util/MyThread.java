/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-17 下午3:55:53 
 * @version V1.0 
 */
package com.nodepoint.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/** 
 * @Package com.clt.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-17 下午3:55:53 
 * @version V1.0 
 */
public class MyThread extends Thread
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author liuwu
	 * @create_date 2015-6-17 下午3:56:05
	 */
	public void run()
	{
		for ( int i = 0 ; i < 100 ; i++ )
		{
			if ( ( i ) % 10 == 0 )
			{
				System.out.println( "-------" + i );
			}
			System.out.print( i );
			try
			{
				Thread.sleep( 1000 );
				System.out.print( "    线程睡眠1毫秒！\n" );
			}
			catch ( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main( String[] args )
    {

		try
		{
			String strDate = "2015-08-08 14:32:33";
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			Date date = sdf.parse( strDate );
			System.out.println( DateUtils.truncate( new Date() ,
			        Calendar.DAY_OF_MONTH ).compareTo( date ) );
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	
}
