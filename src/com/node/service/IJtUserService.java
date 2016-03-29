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
import com.node.model.JtRole;
import com.node.model.JtRoleMenu;
import com.node.model.JtUser;
import com.node.object.JtViewDept;
import com.node.util.HqlHelper;
import com.node.util.Page;

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

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @param p
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 上午8:59:36
	 */
	Map<String, Object> getBySpringSql(String sql, Page p);

	/**
	 * 方法描述：
	 * 
	 * @param userRole
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 上午10:42:33
	 */
	String getRoleNameByRoleCode(String userRole);

	/**
	 * 方法描述：
	 * 
	 * @param jtuserId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午1:41:53
	 */
	void deleteJtUserById(int jtuserId);

	/**
	 * 方法描述：
	 * 
	 * @param jtuserId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午1:50:52
	 */
	JtUser getJtUserById(int jtuserId);

	/**
	 * 方法描述：
	 * 
	 * @param jtUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午1:52:21
	 */
	void updateJtUser(JtUser jtUser);

	/**
	 * 方法描述：
	 * 
	 * @param jtUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午2:29:24
	 */
	void save(JtUser jtUser);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午3:06:48
	 */
	List<JtRole> getAllRoles();

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午3:15:51
	 */
	List<JtMenu> getAllMenus();

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午7:37:40
	 */
	Map<String, Object> getAllRolesByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param delId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午8:22:23
	 */
	void deleteRoleById(int delId);

	/**
	 * 方法描述：
	 * 
	 * @param jtRole
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午8:41:00
	 */
	void saveJtRole(JtRole jtRole);

	/**
	 * 方法描述：
	 * 
	 * @param jtRole
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月22日 下午8:41:39
	 */
	void updateJtUserRole(JtRole jtRole);

	/**
	 * 方法描述：
	 * 
	 * @param roleId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午9:40:46
	 */
	List<JtMenu> getAllMenusByRole(int roleId);

	/**
	 * 方法描述：
	 * 
	 * @param roleId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午9:48:07
	 */
	JtRole getJtRoleById(int roleId);

	/**
	 * 方法描述：
	 * 
	 * @param roId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午10:34:41
	 */
	void deleteMenusByRoleId(int roId);

	/**
	 * 方法描述：
	 * 
	 * @param jtRoleMenu
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 上午10:39:40
	 */
	void saveJtRoleMenu(JtRoleMenu jtRoleMenu);

	/**
	 * 方法描述：
	 * 
	 * @param slr
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午3:48:26
	 */
	JtUser getJtUserByUserCode(String slr);

	/**
	 * 方法描述：
	 * 
	 * @param userOrg
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午3:56:01
	 */
	JtViewDept getJtDeptByOrg(String userOrg);

	/**
	 * 方法描述：获取所有的审批角色
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午7:26:28
	 */
	List<JtRole> getAllApproveRoles();

	/**
	 * 方法描述：获取该用户的审批角色，可能为多重审批角色
	 * 
	 * @param jtUser
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午7:56:51
	 */
	List<JtRole> getApproveRolesByUser(JtUser jtUser);

	/**
	 * 方法描述：
	 * 
	 * @param userOrg
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 上午10:58:04
	 */
	String getDeptNameByUser(String userOrg);

}
