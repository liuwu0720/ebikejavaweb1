package com.nodepoint.security.bean;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Package com.clt.security.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月14日 下午8:39:35
 * @version V1.0
 */
public class CustomAccessDecisionManagerImpl implements AccessDecisionManager
{
	
	/**
	 * 思路:如果该页面不需要权限访问,则直接结束 authentication:用户的权限 configAttributes:访问该资源所需要的权限
	 */
	public void decide( Authentication authentication , Object object ,
	        Collection< ConfigAttribute > configAttributes )
	        throws AccessDeniedException , InsufficientAuthenticationException
	{
		// 针对手机访问特殊处理
		if ( object.toString().contains( "visit=phone" ) )
		{
			return;
		}
		if ( null == configAttributes )
		{
			return;
		}
		
		// 访问该uri所需要的角色列表
		Iterator< ConfigAttribute > cons = configAttributes.iterator();
		
		while ( cons.hasNext() )
		{
			ConfigAttribute ca = cons.next();
			String needRole = ( ( SecurityConfig ) ca ).getAttribute();// 访问该资源所需要的权限
			for ( GrantedAuthority gra : authentication.getAuthorities() )
			{// gra:该用户拥有的权限
				if ( needRole.trim().equals( gra.getAuthority().trim() ) )
				{
					// 放行
					return;
				}
			}
		}
		// 该用户没有权限访问该资源
		throw new AccessDeniedException( "Access Denied" );
	}
	
	public boolean supports( ConfigAttribute arg0 )
	{
		return true;
	}
	
	public boolean supports( Class< ? > arg0 )
	{
		return true;
	}
	
}
