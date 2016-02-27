/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午2:59:22
 * @version V1.0
 */
package com.nodepoint.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @Package com.clt.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午2:59:22
 * @version V1.0
 */
public class ServiceUtil
{
	
	/**
	 * @Description: 手动触发 RuntimeException，回滚事务 void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月12日 下午3:05:39
	 */
	public static void rollback()
	{
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
	
	/**
	 * @Description: 判断当前页是否存在 不存在设置为1
	 * @param request
	 * @return Page 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:27:42
	 */
	public static Page getcurrPage( HttpServletRequest request )
	{
		Page p = new Page();
		String page = request.getParameter( "page" );
		String rows = request.getParameter( "rows" );
		String sort = request.getParameter( "sort" );
		if ( StringUtils.isNotBlank( sort ) )
		{
			String order = request.getParameter( "order" );
			p.setSort( sort );
			p.setOrder( order );
		}
		
		if ( ! StringUtils.isNotBlank( page ) )
			page = "1";
		if ( ! StringUtils.isNotBlank( rows ) )
			rows = "10";
		// 设置当前页
		int intPage = page == null || Integer.parseInt( page ) <= 0 ? 1 : Integer
		        .parseInt( page );
		// 设置每页显示的数量
		int intPageSize = rows == null || Integer.parseInt( rows ) <= 0 ? 10 : Integer
		        .parseInt( rows );
		
		p.setCurrPage( intPage );
		p.setRecordCountperPage( intPageSize );
		/*p.setCurrPage( ( request.getParameter( "page" ) == null )
		        || ( request.getParameter( "page" ) == "" ) ? 1 : Integer
		        .parseInt( request.getParameter( "page" ) ) );*/
		
		return p;
	}
	
	/**
	 * 
	 * @Description: 返回BasePath路径 树形的Icon 图片属性加上该路径
	 * @param request
	 * @return String
	 * @author chenbin
	 * @create_date 2014-7-30 下午3:15:00
	 */
	public static String getBasePath( HttpServletRequest request )
	{
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
		        + request.getServerPort() + path + "/";
		return basePath;
	}
	
	/**
	 * 转化为弧度(rad)
	 * */
	private static double rad( double d )
	{
		return d * Math.PI / 180.0;
	}
	
	
	
	/**
	 * 根据绑定信息对象获取验证错误信息，并封装为map对象返回
	 * 
	 * @param error
	 *            spring 验证结果绑定信息对象
	 * @return map验证错误信息
	 * @author hjx86
	 * @see public Map< String , Object > saveCapatity(
	 * @Valid @ModelAttribute TSpareCapacity capacity , BindingResult error ,
	 *        HttpServletRequest request )
	 */
	public static Map< String , Object > getErrorVerification( BindingResult error )
	{
		List< ObjectError > errors = error.getAllErrors();
		List< String > errList = new ArrayList< String >();
		for ( ObjectError objectError : errors )
		{
			System.out.println( objectError.getDefaultMessage() );
			errList.add( objectError.getDefaultMessage() );
		}
		Map< String , Object > errMap = new HashMap< String , Object >();
		errMap.put( "isSuccess" , false );
		errMap.put( "message" , errList );
		
		return errMap;
	}
}
