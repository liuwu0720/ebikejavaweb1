/**
 * 文件名：IEbikeService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月24日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.DdcDaxxb;
import com.node.model.DdcSjzd;
import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月24日 下午2:01:12
 */
public interface IEbikeService {

	/**
	 * 方法描述：
	 * 
	 * @param sql
	 * @param p
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:02:14
	 */
	Map<String, Object> queryBySpringSql(String sql, Page p);

	/**
	 * 方法描述：
	 * 
	 * @param sbId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:19:56
	 */
	DdcDaxxb getById(long sbId);

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @param cysy
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:22:26
	 */
	String findByProPerties(String string, String cysy);

	/**
	 * 方法描述：
	 * 
	 * @param dmlb
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午4:21:09
	 */
	List<DdcSjzd> getAllSjzdByDmlb(String dmlb);

	/**
	 * 方法描述：
	 * 
	 * @param ddcDaxxb
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午5:50:40
	 */
	void updateDdcDaxxb(DdcDaxxb ddcDaxxb);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午6:10:13
	 */
	DdcDaxxb getDdcDaxxbById(Long id);

}
