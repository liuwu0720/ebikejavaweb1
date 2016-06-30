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
	 * @throws FileNotFoundException 
	 * @throws IOException 
	  */
	void updateDdcDriverImg() ;

	
	/**
	  * 方法描述：
	  * @param sql 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年6月25日 下午5:57:58
	  */
	void updateBySql(String sql);

}
