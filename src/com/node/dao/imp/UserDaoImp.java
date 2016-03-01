/**
  * 文件名：UserDaoImp.java
  * 版本信息：Version 1.0
  * 日期：2016年3月1日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IUserDao;
import com.node.model.TUser;



/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:30:40 
 */
@Repository
public class UserDaoImp extends GenericHibernateDao< TUser , Integer > implements IUserDao{
	
}
