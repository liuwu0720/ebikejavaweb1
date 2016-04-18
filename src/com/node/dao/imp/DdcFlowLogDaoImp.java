/**
 * 文件名：DdcFlowLogDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年4月18日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IDdcFlowLogDao;
import com.node.model.DdcFlowLog;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月18日 下午5:31:12
 */
@Repository
public class DdcFlowLogDaoImp extends GenericHibernateDao<DdcFlowLog, Long>
		implements IDdcFlowLogDao {

}
