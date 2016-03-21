/**
 * 文件名：JtRoleDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IJtRoleDao;
import com.node.model.JtRole;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 上午10:42:22
 */
@Repository
public class JtRoleDaoImp extends GenericHibernateDao<JtRole, Integer>
		implements IJtRoleDao {

}
