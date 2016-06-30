/**
 * 文件名：IDdcDriverDao.java
 * 版本信息：Version 1.0
 * 日期：2016年4月16日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao;

import java.util.List;

import com.node.model.DdcDriver;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月16日 下午7:15:15
 */
public interface IDdcDriverDao extends GenericDao<DdcDriver, Long> {

	
	/**
	  * 方法描述：
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月14日 上午10:17:20
	  */
	List<DdcDriver> findXjDriver();

	
	/**
	  * 方法描述：
	  * @param sfzmhm1
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月22日 下午2:42:26
	  */
	List<DdcDriver> findAllBySfzhm(String sfzmhm1);


	
	/**
	  * 方法描述：
	  * @param sql 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月25日 下午5:58:27
	  */
	void updateBySql(String sql);

}
