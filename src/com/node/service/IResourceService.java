/**
 * 文件名：IResourceService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月4日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;

import com.node.model.TResource;

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

}
