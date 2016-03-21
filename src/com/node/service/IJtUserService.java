/**
 * 文件名：IJtUserService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.JtMenu;
import com.node.model.JtUser;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 上午10:53:37
 */
public interface IJtUserService {

	/**
	 * 方法描述：
	 * 
	 * @param cuser
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 上午10:55:17
	 */
	JtUser findByAccount(String cuser);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午12:52:40
	 * @param userRole
	 */
	List<JtMenu> getByRole(String userRole);

	/**
	 * 方法描述：
	 * 
	 * @param parseInt
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午1:20:35
	 */
	JtMenu getByMenuId(int parseInt);

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午5:19:33
	 */
	Map<String, Object> queryByHql(HqlHelper hql);

}
