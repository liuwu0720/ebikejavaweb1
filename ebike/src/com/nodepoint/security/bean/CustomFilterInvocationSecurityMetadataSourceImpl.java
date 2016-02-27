package com.nodepoint.security.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


/**
 * @Package com.clt.security.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月14日 下午8:01:25
 * @version V1.0
 */
public class CustomFilterInvocationSecurityMetadataSourceImpl implements
        FilterInvocationSecurityMetadataSource
{

	
		/* (non-Javadoc)
		 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
		 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	
		/* (non-Javadoc)
		 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
		 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object arg0)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
		/* (non-Javadoc)
		 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
		 */
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}
	
}
