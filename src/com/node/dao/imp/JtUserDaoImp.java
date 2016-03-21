/**
 * 文件名：JtUserDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IJtUserDao;
import com.node.model.JtUser;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 上午10:40:05
 */
@Repository
public class JtUserDaoImp extends GenericHibernateDao<JtUser, Integer>
		implements IJtUserDao {

}
