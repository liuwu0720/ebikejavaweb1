/**
 * 文件名：DdcHmdDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月15日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IDdcHmdDao;
import com.node.model.DdcHmd;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月15日 下午2:28:30
 */
@Repository
public class DdcHmdDaoImp extends GenericHibernateDao<DdcHmd, Long> implements
		IDdcHmdDao {

}
