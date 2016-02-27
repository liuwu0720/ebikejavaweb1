package com.nodepoint.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nodepoint.systemmanager.dao.IUserDao;
import com.nodepoint.systemmanger.model.TUser;
import com.nodepoint.systemmanger.service.IUserService;



/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: 用户管理服务
 * @author hjx
 * @date 2014年7月12日 下午1:59:30
 * @version V1.0
 */
@Service
public class UserServiceImp implements IUserService
{
	
	@Autowired
	private IUserDao userDao;
	
	
	
	/**
	 * @Description: 通过用户账户获得用户
	 * @param account
	 *            用户账户
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午1:59:30
	 */
	public TUser getByAccount( String account )
	{
		List< TUser > list = userDao.findByProperty( "vcAccount" , account );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 );
		}
		return null;
	}
	
	
}
