/**
 * 文件名：UserServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IResourceDao;
import com.node.dao.IRoleDao;
import com.node.dao.IRoleResourceDao;
import com.node.dao.IUserDao;
import com.node.dao.IUserRoleDao;
import com.node.model.TResource;
import com.node.model.TRole;
import com.node.model.TRoleResource;
import com.node.model.TUser;
import com.node.model.TUserRole;
import com.node.service.IUserService;
import com.node.util.HqlHelper;
import com.node.util.SystemConstants;

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

	@Autowired
	IResourceDao iResourceDao;

	@Autowired
	IRoleResourceDao iRoleResourceDao;

	@Autowired
	IUserRoleDao iUserRoleDao;

	@Autowired
	IRoleDao iRoleDao;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#getAllRoleByUserId(java.lang.Integer)
	 */
	@Override
	public List<TRole> getAllRoleByUserId(Integer id) {
		List<TUserRole> urList = iUserRoleDao.findByPropertys(new String[] {
				"iUser", "nEnable" }, new Object[] { id, 0 });
		List<TRole> roleList = new ArrayList<TRole>();
		if (CollectionUtils.isNotEmpty(urList)) {
			Iterator<TUserRole> urIt = urList.iterator();
			while (urIt.hasNext()) {
				roleList.add(iRoleDao.get(urIt.next().getiRole()));
			}
		}
		return roleList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#getByRoleid(java.lang.Integer)
	 */
	@Override
	public List<TResource> getByRoleid(Integer id) {
		List<TRoleResource> rResources = iRoleResourceDao.findByProperty(
				"iRoleId", id);
		List<TResource> resources = new ArrayList<TResource>();
		if (CollectionUtils.isNotEmpty(rResources)) {
			for (TRoleResource rr : rResources) {
				resources.add(iResourceDao.get(rr.getiResourceId()));
			}
			if (CollectionUtils.isNotEmpty(resources))
				return resources;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IUserService#getByIAchiveId(java.lang.Integer)
	 */
	@Override
	public TUser getByIAchiveId(Integer id) {
		List<TUser> tUsers = iUserDao.findByPropertys(new String[] {
				"iArchiveType", "iArchive", "nEnable" }, new Object[] {
				SystemConstants.IARCHIVETYPE_TRAFFICE, id, 0 });
		if (CollectionUtils.isNotEmpty(tUsers)) {
			return tUsers.get(0);
		}
		return null;
	}

}
