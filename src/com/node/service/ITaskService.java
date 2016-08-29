/**
  * 文件名：ITaskService.java
  * 版本信息：Version 1.0
  * 日期：2016年6月13日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.node.model.DdcDriver;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月13日 下午9:04:48 
 */
public interface ITaskService {

	
	/**
	  * 方法描述： 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月13日 下午9:05:52
	 * @param maxIndex 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	  */
	void updateDdcDriverImg(int maxIndex) ;

	
	/**
	  * 方法描述：
	  * @param sql 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月25日 下午5:57:58
	  */
	void updateBySql(String sql);


	
	/**
	  * 方法描述：
	  * @param driver 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年7月11日 下午5:48:17
	  */
	void updateDdcDriverImgByDriver(DdcDriver driver);


	
	/**
	  * 方法描述： 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年7月13日 上午9:35:22
	  */
	void updateDriverImgBlob();


	
	/**
	  * 方法描述：
	  * @param sql
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年7月19日 上午11:01:55
	  */
	int updateBySql2(String sql);


	

}
