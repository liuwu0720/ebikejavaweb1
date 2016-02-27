/**
  * 文件名：IUserService.java
  * 版本信息：Version 1.0
  * 日期：2016年2月27日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.nodepoint.systemmanger.service;

import com.nodepoint.systemmanger.model.TUser;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年2月27日 下午3:10:55 
 */
public interface IUserService {

	
	/**
	  * 方法描述：
	  * @param loginUserName
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年2月27日 下午3:25:45
	  */
	TUser getByAccount(String loginUserName);



	

	

	
	

}
