/**
 * 文件名：ISystemService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.JtMenu;
import com.node.object.JtViewDept;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 下午2:41:25
 */
public interface ISystemService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午2:42:45
	 */
	Map<String, Object> queryByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午2:59:10
	 */
	List<JtMenu> getParentMenu();

	/**
	 * 方法描述：
	 * 
	 * @param jtMenu
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午3:05:27
	 */
	void save(JtMenu jtMenu);

	/**
	 * 方法描述：
	 * 
	 * @param jtMenu
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午3:05:46
	 */
	void update(JtMenu jtMenu);

	/**
	 * 方法描述：
	 * 
	 * @param getiParent
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午3:53:03
	 */
	JtMenu getMenuById(Integer getiParent);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月21日 下午4:50:17
	 */
	List<JtViewDept> getDepts(String orgId, String orgName, String upOrg);

}
