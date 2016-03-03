/**
 * 文件名：ITrafficService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.TrafficUser;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 下午3:57:34
 */
public interface ITrafficService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午3:58:52
	 */
	Map<String, Object> queryTrafficeUsers(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param vcAccount
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午4:45:48
	 */
	List<TrafficUser> findByVcAccount(String vcAccount);

	/**
	 * 方法描述：
	 * 
	 * @param trafficUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午4:55:51
	 */
	void saveTrafficUser(TrafficUser trafficUser);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午4:59:37
	 */
	TrafficUser getById(Integer id);

	/**
	 * 方法描述：
	 * 
	 * @param trafficUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 下午5:18:38
	 */
	void update(TrafficUser trafficUser);

}
