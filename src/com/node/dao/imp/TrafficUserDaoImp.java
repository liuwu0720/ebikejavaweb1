/**
 * 文件名：TrafficUserDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.ITrafficUserDao;
import com.node.model.TrafficUser;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 下午4:03:46
 */
@Repository
public class TrafficUserDaoImp extends
		GenericHibernateDao<TrafficUser, Integer> implements ITrafficUserDao {

}
