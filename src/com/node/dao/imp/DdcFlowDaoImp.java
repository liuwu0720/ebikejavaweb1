/**
 * 文件名：DdcFlowDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月17日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IDdcFlowDao;
import com.node.model.DdcFlow;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月17日 下午10:07:44
 */
@Repository
public class DdcFlowDaoImp extends GenericHibernateDao<DdcFlow, Long> implements
		IDdcFlowDao {

}
