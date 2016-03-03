/**
 * 文件名：TrafficServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.ITrafficUserDao;
import com.node.model.TrafficUser;
import com.node.service.ITrafficService;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 下午3:58:09
 */
@Service
public class TrafficServiceImp implements ITrafficService {

	@Autowired
	ITrafficUserDao iTrafficUserDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.ITrafficService#queryTrafficeUsers(com.node.util.HqlHelper
	 * )
	 */
	@Override
	public Map<String, Object> queryTrafficeUsers(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iTrafficUserDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ITrafficService#findByVcAccount(java.lang.String)
	 */
	@Override
	public List<TrafficUser> findByVcAccount(String vcAccount) {
		List<TrafficUser> trafficUsers = iTrafficUserDao.findByPropertys(
				new String[] { "vcAccount", "nEnable" }, new Object[] {
						vcAccount, 0 });
		if (CollectionUtils.isNotEmpty(trafficUsers)) {
			return trafficUsers;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.ITrafficService#saveTrafficUser(com.node.model.TrafficUser
	 * )
	 */
	@Override
	public void saveTrafficUser(TrafficUser trafficUser) {
		// TODO Auto-generated method stub
		iTrafficUserDao.save(trafficUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ITrafficService#getById(java.lang.Integer)
	 */
	@Override
	public TrafficUser getById(Integer id) {
		// TODO Auto-generated method stub
		return iTrafficUserDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ITrafficService#update(com.node.model.TrafficUser)
	 */
	@Override
	public void update(TrafficUser trafficUser) {
		// TODO Auto-generated method stub
		iTrafficUserDao.updateCleanBefore(trafficUser);
	}

}
