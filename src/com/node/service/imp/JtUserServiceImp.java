/**
 * 文件名：JtUserServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月21日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IJtMenuDao;
import com.node.dao.IJtRoleMenuDao;
import com.node.dao.IJtUserDao;
import com.node.model.JtMenu;
import com.node.model.JtRoleMenu;
import com.node.model.JtUser;
import com.node.service.IJtUserService;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 上午10:53:55
 */
@Service
public class JtUserServiceImp implements IJtUserService {
	@Autowired
	IJtUserDao iJtUserDao;

	@Autowired
	IJtRoleMenuDao iJtRoleMenuDao;

	@Autowired
	IJtMenuDao iJtMenuDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#findByAccount(java.lang.String)
	 */
	@Override
	public JtUser findByAccount(String cuser) {
		List<JtUser> jtUsers = iJtUserDao.findByProperty("userCode", cuser);
		if (jtUsers != null && jtUsers.size() > 0) {
			return jtUsers.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<JtMenu> getByRole(String userRole) {
		String[] rolesArray = userRole.split(",");
		List<JtRoleMenu> jtRoleMenusList = new ArrayList<JtRoleMenu>();
		for (String role : rolesArray) {
			List<JtRoleMenu> jtRoleMenus = iJtRoleMenuDao.findByProperty(
					"roleid", Integer.parseInt(role));
			jtRoleMenusList.addAll(jtRoleMenus);
		}
		List<JtMenu> jtMenus = new ArrayList<JtMenu>();
		for (JtRoleMenu jtRoleMenu : jtRoleMenusList) {
			JtMenu jtMenu = iJtMenuDao.get(jtRoleMenu.getMenuid());
			jtMenus.add(jtMenu);
		}
		if (jtMenus != null && jtMenus.size() > 0) {
			return jtMenus;
		} else {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getByMenuId(int)
	 */
	@Override
	public JtMenu getByMenuId(int parseInt) {
		// TODO Auto-generated method stub
		return iJtMenuDao.get(parseInt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#queryByHql(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> queryByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iJtUserDao.findAllByHqlHelp(hql);
	}
}
