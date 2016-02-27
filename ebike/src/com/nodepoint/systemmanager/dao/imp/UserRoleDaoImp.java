package com.nodepoint.systemmanager.dao.imp;

import org.springframework.stereotype.Repository;

import com.nodepoint.basedao.imp.GenericHibernateDao;
import com.nodepoint.systemmanager.dao.IUserRoleDao;
import com.nodepoint.systemmanger.model.TUserRole;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:11:28
 * @version V1.0
 */
@Repository
public class UserRoleDaoImp extends GenericHibernateDao< TUserRole , Integer > implements
        IUserRoleDao
{	
	
}
