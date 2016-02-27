package com.nodepoint.systemmanager.dao;

import java.util.List;

import com.nodepoint.basedao.GenericDao;
import com.nodepoint.systemmanger.model.TUserRole;


/**
 * @Package com.clt.systemmanger.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:03:32
 * @version V1.0
 */
public interface IUserRoleDao extends GenericDao< TUserRole , Integer >
{

	
	/**
	  * 方法描述：
	  * @param strings
	  * @param objects
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年2月27日 下午3:58:44
	  */
	List<TUserRole> findByPropertys(
			String[] strings, Object[] objects);	
	
}
