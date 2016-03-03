/**
 * 文件名：ResoureDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.node.dao.IResourceDao;
import com.node.model.TResource;
import com.node.model.TRoleResource;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 上午9:15:54
 */
@Repository
public class ResoureDaoImp extends GenericHibernateDao<TResource, Integer>
		implements IResourceDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.dao.IResourceDao#findByProperty(java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public List<TRoleResource> findByProperty(String string, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
