/**
 * 文件名：IUserService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月1日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.Map;

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

}
