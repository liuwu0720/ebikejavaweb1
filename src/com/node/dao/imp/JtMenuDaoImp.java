/**
 * 文件名：JtMenuDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IJtMenuDao;
import com.node.model.JtMenu;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 下午1:05:35
 */
@Repository
public class JtMenuDaoImp extends GenericHibernateDao<JtMenu, Integer>
		implements IJtMenuDao {

}
