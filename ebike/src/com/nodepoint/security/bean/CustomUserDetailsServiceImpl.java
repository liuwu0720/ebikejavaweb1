package com.nodepoint.security.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nodepoint.systemmanger.model.TUser;
import com.nodepoint.systemmanger.service.IUserService;

/**
 * @Package com.clt.security.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月14日 下午8:19:26
 * @version V1.0
 */

public class CustomUserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private IUserService userService;
	@Autowired
	private Assembler assembler;
	
	public UserDetails loadUserByUsername( String loginUserName )
	        throws UsernameNotFoundException
	{
		
		TUser user = userService.getByAccount( loginUserName );
		
		return assembler.buildUserFromUserEntity( user );
	}
	
}
