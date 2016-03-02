/**
 * 文件名：UserServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IUserDao;
import com.node.model.TUser;
import com.node.service.IUserService;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:18:31
 */
@Service
public class UserServiceImp implements IUserService {

	@Autowired
	IUserDao iUserDao;

	@Override
	public TUser findById(int id) {
		// TODO Auto-generated method stub
		return iUserDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#findByAccount(java.lang.String)
	 */
	@Override
	public TUser findByAccount(String cuser) {
		List<TUser> list = iUserDao.findByProperty("vcAccount", cuser);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#queryAllUsers()
	 */
	@Override
	public Map<String, Object> queryAllUsers(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iUserDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#getById(int)
	 */
	@Override
	public TUser getById(int userId) {
		// TODO Auto-generated method stub
		return iUserDao.get(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#saveOrUpdate(com.node.model.TUser)
	 */
	@Override
	public void saveOrUpdate(TUser tUser) {
		// TODO Auto-generated method stub
		iUserDao.saveOrUpdate(tUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#saveUser(com.node.model.TUser)
	 */
	@Override
	public void saveUser(TUser tUser) {
		// TODO Auto-generated method stub
		iUserDao.save(tUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#updateUser(com.node.model.TUser)
	 */
	@Override
	public void updateUser(TUser tUser) {
		// TODO Auto-generated method stub
		iUserDao.update(tUser);
	}

}
