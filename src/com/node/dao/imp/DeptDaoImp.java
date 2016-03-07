/**
 * 文件名：DeptDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IDeptDao;
import com.node.model.TDept;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午4:16:49
 */
@Repository
public class DeptDaoImp extends GenericHibernateDao<TDept, Integer> implements
		IDeptDao {

}
