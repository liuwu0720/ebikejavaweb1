/**
 * 文件名：IResourceService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月4日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.TResource;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月4日 下午4:50:38
 */
public interface IResourceService {

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月4日 下午4:55:18
	 */
	List<TResource> loadAll();

	/**
	 * 方法描述：
	 * 
	 * @param list
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月4日 下午4:56:23
	 */
	String getJsonTree(List<TResource> list);

	/**
	 * 方法描述：
	 * 
	 * @param parseInt
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月4日 下午7:54:12
	 */
	TResource get(int parseInt);

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午1:43:15
	 */
	Map<String, Object> queryAllResource(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午2:36:34
	 */
	List<TResource> getAllParentResource();

	/**
	 * 方法描述：
	 * 
	 * @param tResource
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午2:52:53
	 */
	void saveOrUpdate(TResource tResource);

	/**
	 * 方法描述：
	 * 
	 * @param vcParent
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午5:49:07
	 */
	int getPidByResourceName(String vcParent);

	/**
	 * 方法描述：
	 * 
	 * @param vcResourceName
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午5:54:54
	 */
	String findIsExisteName(String vcResourceName);

	/**
	 * 方法描述：
	 * 
	 * @param tResource
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午6:00:11
	 */
	void save(TResource tResource);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午6:04:16
	 */
	TResource getById(Integer id);

	/**
	 * 方法描述：
	 * 
	 * @param tResource
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月5日 下午6:07:20
	 */
	void update(TResource tResource);

}
