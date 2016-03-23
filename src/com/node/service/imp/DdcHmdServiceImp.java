/**
 * 文件名：DdcHmdServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcHmdDao;
import com.node.model.DdcHmd;
import com.node.service.IDdcHmdService;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 上午11:35:19
 */
@Service
public class DdcHmdServiceImp implements IDdcHmdService {

	@Autowired
	IDdcHmdDao iDdcHmdDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDdcHmdService#queryByHql(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> queryByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDdcHmdDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDdcHmdService#deleteHmdById(int)
	 */
	@Override
	public void deleteHmdById(long blackId) {
		// TODO Auto-generated method stub
		iDdcHmdDao.deleteByKey(blackId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDdcHmdService#save(com.node.model.DdcHmd)
	 */
	@Override
	public void save(DdcHmd ddcHmd) {
		// TODO Auto-generated method stub
		iDdcHmdDao.save(ddcHmd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDdcHmdService#update(com.node.model.DdcHmd)
	 */
	@Override
	public void update(DdcHmd ddcHmd) {
		// TODO Auto-generated method stub
		iDdcHmdDao.updateCleanBefore(ddcHmd);
	}

}
