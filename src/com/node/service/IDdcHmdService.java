/**
 * 文件名：IDdcHmdService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.Map;

import com.node.model.DdcHmd;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 上午11:35:05
 */
public interface IDdcHmdService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:36:05
	 */
	Map<String, Object> queryByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param blackId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:41:09
	 */
	void deleteHmdById(long blackId);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHmd
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:49:11
	 */
	void save(DdcHmd ddcHmd);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHmd
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午11:49:32
	 */
	void update(DdcHmd ddcHmd);

}
