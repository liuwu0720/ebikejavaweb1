package com.nodepoint.systemmanager.dao.imp;

import org.springframework.stereotype.Repository;

import com.nodepoint.basedao.imp.GenericHibernateDao;
import com.nodepoint.systemmanager.dao.IRoleDao;
import com.nodepoint.systemmanger.model.TRole;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:12:38
 * @version V1.0
 */
@Repository
public class RoleDaoImp extends GenericHibernateDao< TRole , Integer > implements
        IRoleDao
{	
	
}
