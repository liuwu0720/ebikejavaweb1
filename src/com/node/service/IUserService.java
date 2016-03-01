/**
  * 文件名：IUserService.java
  * 版本信息：Version 1.0
  * 日期：2016年3月1日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.service;

import com.node.model.TUser;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:17:55 
 */
public interface IUserService {

	
	/**
	  * 方法描述：
	  * @param i
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年3月1日 上午12:22:22
	  */
	TUser findById(int i);

}
