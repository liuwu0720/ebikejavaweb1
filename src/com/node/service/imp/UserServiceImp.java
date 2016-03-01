/**
  * 文件名：UserServiceImp.java
  * 版本信息：Version 1.0
  * 日期：2016年3月1日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IUserDao;
import com.node.model.TUser;
import com.node.service.IUserService;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月1日 上午12:18:31 
 */
@Service
public class UserServiceImp implements IUserService{

	@Autowired
	IUserDao iUserDao;
	
	@Override
	public TUser findById(int id) {
		// TODO Auto-generated method stub
		return iUserDao.get(id);
	}

}
