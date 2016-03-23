/**
 * 文件名：IDictionaryService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.Map;

import com.node.model.DdcSjzd;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午1:37:40
 */
public interface IDictionaryService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午1:39:06
	 */
	Map<String, Object> queryByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param dictionId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午2:23:21
	 */
	void deleteById(long dictionId);

	/**
	 * 方法描述：
	 * 
	 * @param ddcSjzd
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午4:46:55
	 */
	void save(DdcSjzd ddcSjzd);

	/**
	 * 方法描述：
	 * 
	 * @param ddcSjzd
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午4:47:18
	 */
	void update(DdcSjzd ddcSjzd);

}
