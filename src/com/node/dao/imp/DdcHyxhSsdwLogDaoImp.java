/**
 * 文件名：DdcHyxhSsdwLogDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月12日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IDcHyxhSsdwLogDao;
import com.node.model.DdcHyxhSsdwLog;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月12日 下午6:08:58
 */
@Repository
public class DdcHyxhSsdwLogDaoImp extends
		GenericHibernateDao<DdcHyxhSsdwLog, Long> implements IDcHyxhSsdwLogDao {

}
