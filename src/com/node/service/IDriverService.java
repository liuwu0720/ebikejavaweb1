/**
  * 文件名：IDriverService.java
  * 版本信息：Version 1.0
  * 日期：2016年6月21日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.service;

import java.util.Map;

import com.node.model.DdcDriver;
import com.node.util.HqlHelper;
import com.node.util.Page;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月21日 下午6:47:17 
 */
public interface IDriverService {

	
	/**
	  * 方法描述：
	  * @param hql
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午6:48:54
	  */
	Map<String, Object> queryByHql(HqlHelper hql);

	
	/**
	  * 方法描述：
	  * @param sql
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午7:12:32
	 * @param page 
	  */
	Map<String, Object> queryBySpringsql(String sql, Page page);


	
	/**
	  * 方法描述：
	  * @param driverId
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午7:13:57
	  */
	DdcDriver getDriverById(long driverId);


	
	/**
	  * 方法描述：
	  * @param hyxhzh
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午7:14:27
	  */
	String getHyxhNameByHyxhzh(String hyxhzh);


	
	/**
	  * 方法描述：
	  * @param ssdwId
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午7:16:08
	  */
	String getDwmcById(Integer ssdwId);


	
	/**
	  * 方法描述：
	  * @param ddcDriver 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月21日 下午8:36:28
	  */
	void updateDdcDriver(DdcDriver ddcDriver);


	
	/**
	  * 方法描述：
	  * @param driverId 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年8月31日 下午2:08:08
	  */
	void deleteById(long driverId);

}
