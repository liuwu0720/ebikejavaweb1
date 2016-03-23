/**
 * 文件名：IInDustryService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.Map;

import com.node.model.DdcHyxhBase;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午6:20:25
 */
public interface IInDustryService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:23:58
	 */
	Map<String, Object> queryByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:42:19
	 */
	void deleteById(long dId);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:47:03
	 */
	void save(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:47:22
	 */
	void update(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午7:09:12
	 */
	DdcHyxhBase getDdcHyxhBase(long dId);

}
