/**
 * 文件名：RoleResourceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IRoleResourceDao;
import com.node.model.TRoleResource;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 下午1:49:35
 */
@Repository
public class RoleResourceImp extends
		GenericHibernateDao<TRoleResource, Integer> implements IRoleResourceDao {

}
