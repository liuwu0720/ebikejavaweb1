/**
 * 文件名：DdcApprovalUserDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月28日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IDdcApprovalUserDao;
import com.node.model.DdcApproveUser;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月28日 下午5:20:51
 */
@Repository
public class DdcApprovalUserDaoImp extends
		GenericHibernateDao<DdcApproveUser, Long> implements
		IDdcApprovalUserDao {

}
