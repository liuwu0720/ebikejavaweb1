package com.nodepoint.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @Package com.clt.security.filter
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月14日 下午8:42:24
 * @version V1.0
 */
public class CustomFilterSecurityInterceptorImpl extends AbstractSecurityInterceptor
        implements Filter
{
	
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	public void setAccessDecisionManager( AccessDecisionManager accessDecisionManager )
	{
		super.setAccessDecisionManager( accessDecisionManager );
	}
	
	public void setAuthenticationManager( AuthenticationManager newManager )
	{
		super.setAuthenticationManager( newManager );
	}
	
	public void doFilter( ServletRequest request , ServletResponse response ,
	        FilterChain chain ) throws IOException , ServletException
	{
		FilterInvocation fi = new FilterInvocation( request , response , chain );
		infoke( fi );
		
	}
	
	/**
	 * 
	 * @param fi
	 * @throws ServletException
	 * @throws IOException
	 */
	private void infoke( FilterInvocation fi ) throws IOException , ServletException
	{
		InterceptorStatusToken token = super.beforeInvocation( fi );
		
		try
		{
			fi.getChain().doFilter( fi.getRequest() , fi.getResponse() );
		}
		finally
		{
			super.afterInvocation( token , null );
		}
	}
	
	public void init( FilterConfig arg0 ) throws ServletException
	{	
		
	}
	
	public Class< ? > getSecureObjectClass()
	{
		return FilterInvocation.class;
	}
	
	public SecurityMetadataSource obtainSecurityMetadataSource()
	{
		return this.securityMetadataSource;
	}
	
	public void destroy()
	{	
		
	}
	
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource()
	{
		return securityMetadataSource;
	}
	
	public void setSecurityMetadataSource(
	        FilterInvocationSecurityMetadataSource securityMetadataSource )
	{
		this.securityMetadataSource = securityMetadataSource;
	}
	
}
