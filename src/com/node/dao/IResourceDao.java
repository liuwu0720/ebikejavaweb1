/**
 * 文件名：IResourceDao.java
 * 版本信息：Version 1.0
 * 日期：2016年3月3日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao;

import java.util.List;

import com.node.model.TResource;
import com.node.model.TRoleResource;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月3日 上午9:15:16
 */
public interface IResourceDao extends GenericDao<TResource, Integer> {

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月3日 上午10:07:11
	 */
	List<TRoleResource> findByProperty(String string, Integer id);

}
