/**
 * 文件名：IUserService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.TResource;
import com.node.model.TRole;
import com.node.model.TUser;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:17:55
 */
public interface IUserService {

	/**
	 * 方法描述：
	 * 
	 * @param i
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月1日 上午12:22:22
	 */
	TUser findById(int i);

	/**
	 * 方法描述：
	 * 
	 * @param cuser
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 上午8:54:02
	 */
	TUser findByAccount(String cuser);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午4:53:45
	 */
	Map<String, Object> queryAllUsers(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param userId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:18:29
	 */
	TUser getById(int userId);

	/**
	 * 方法描述：
	 * 
	 * @param tUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午6:41:26
	 */
	void saveOrUpdate(TUser tUser);

	/**
	 * 方法描述：
	 * 
	 * @param tUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午7:35:25
	 */
	void saveUser(TUser tUser);

	/**
	 * 方法描述：
	 * 
	 * @param tUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月2日 下午8:12:09
	 */
	void updateUser(TUser tUser);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 上午9:14:16
	 */
	List<TRole> getAllRoleByUserId(Integer id);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 上午10:37:26
	 */
	List<TResource> getByRoleid(Integer id);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午5:07:42
	 */
	TUser getByIAchiveId(Integer id);

}
