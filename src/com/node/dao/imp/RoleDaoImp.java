package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IRoleDao;
import com.node.model.TRole;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:12:38
 * @version V1.0
 */
@Repository
public class RoleDaoImp extends GenericHibernateDao<TRole, Integer> implements
		IRoleDao {

}
