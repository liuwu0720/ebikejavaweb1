/**
 * 文件名：IDeptService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.TDept;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午4:13:27
 */
public interface IDeptService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午4:15:22
	 */
	Map<String, Object> queryAllDepts(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param pid
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午4:41:58
	 */
	String getVcDeptNameById(int pid);

	/**
	 * 方法描述：
	 * 
	 * @param tDept
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午4:46:17
	 */
	void saveOrUpdate(TDept tDept);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月7日 下午5:05:46
	 */
	List<TDept> findAll();

}
