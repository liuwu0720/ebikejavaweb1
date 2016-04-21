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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IJtMenuDao;
import com.node.dao.IJtRoleDao;
import com.node.dao.IJtRoleMenuDao;
import com.node.dao.IJtUserDao;
import com.node.model.JtMenu;
import com.node.model.JtRole;
import com.node.model.JtRoleMenu;
import com.node.model.JtUser;
import com.node.object.JtViewDept;
import com.node.service.IJtUserService;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.SystemConstants;

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

	@Autowired
	IJtRoleDao iJtRoleDao;

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
			if (jtMenu.getnEnable().equals(SystemConstants.ENABLE)) {
				jtMenus.add(jtMenu);
			}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getBySpringSql(java.lang.String,
	 * com.node.util.Page)
	 */
	@Override
	public Map<String, Object> getBySpringSql(String sql, Page p) {
		// TODO Auto-generated method stub
		return iJtUserDao.getSpringSQL(sql, p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IJtUserService#getRoleNameByRoleCode(java.lang.String)
	 */
	@Override
	public String getRoleNameByRoleCode(String userRole) {
		if (StringUtils.isBlank(userRole)) {
			return null;
		} else {
			String[] roleIds = userRole.split(",");
			String roleName = "";
			for (String id : roleIds) {
				JtRole jtRole = iJtRoleDao.get(Integer.parseInt(id));
				if (StringUtils.isNotBlank(jtRole.getRoleName())) {
					roleName += jtRole.getRoleName() + "   ";
				}

			}
			return roleName;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#deleteJtUserById(int)
	 */
	@Override
	public void deleteJtUserById(int jtuserId) {
		// TODO Auto-generated method stub
		iJtUserDao.deleteByKey(jtuserId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getJtUserById(int)
	 */
	@Override
	public JtUser getJtUserById(int jtuserId) {
		// TODO Auto-generated method stub
		return iJtUserDao.get(jtuserId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#updateJtUser(com.node.model.JtUser)
	 */
	@Override
	public void updateJtUser(JtUser jtUser) {
		// TODO Auto-generated method stub
		iJtUserDao.updateCleanBefore(jtUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#save(com.node.model.JtUser)
	 */
	@Override
	public void save(JtUser jtUser) {
		// TODO Auto-generated method stub
		iJtUserDao.save(jtUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getAllRoles()
	 */
	@Override
	public List<JtRole> getAllRoles() {
		// TODO Auto-generated method stub
		return iJtRoleDao.findByProperty("roleState", "1");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getAllMenus()
	 */
	@Override
	public List<JtMenu> getAllMenus() {
		// TODO Auto-generated method stub
		return iJtMenuDao.findByProperty("nEnable", SystemConstants.ENABLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IJtUserService#getAllRolesByHql(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> getAllRolesByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iJtRoleDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#deleteRoleById(int)
	 */
	@Override
	public void deleteRoleById(int delId) {
		// TODO Auto-generated method stub
		iJtRoleDao.deleteByKey(delId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#saveJtRole(com.node.model.JtRole)
	 */
	@Override
	public void saveJtRole(JtRole jtRole) {
		// TODO Auto-generated method stub
		iJtRoleDao.save(jtRole);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IJtUserService#updateJtUserRole(com.node.model.JtRole)
	 */
	@Override
	public void updateJtUserRole(JtRole jtRole) {
		// TODO Auto-generated method stub
		iJtRoleDao.update(jtRole);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getAllMenusByRole(int)
	 */
	@Override
	public List<JtMenu> getAllMenusByRole(int roleId) {
		List<JtRoleMenu> jtRoleMenus = iJtRoleMenuDao.findByProperty("roleid",
				roleId);
		List<JtMenu> jtMenus = new ArrayList<JtMenu>();
		for (JtRoleMenu jtRoleMenu : jtRoleMenus) {
			int menuId = jtRoleMenu.getMenuid();
			JtMenu jtMenu = iJtMenuDao.get(menuId);
			if (jtMenu.getnEnable().equals(SystemConstants.ENABLE)) {
				jtMenus.add(jtMenu);
			}

		}
		return jtMenus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getJtRoleById(int)
	 */
	@Override
	public JtRole getJtRoleById(int roleId) {
		// TODO Auto-generated method stub
		return iJtRoleDao.get(roleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#deleteMenusByRoleId(int)
	 */
	@Override
	public void deleteMenusByRoleId(int roId) {
		// TODO Auto-generated method stub
		iJtRoleMenuDao.deleteByProperty("roleid", roId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IJtUserService#saveJtRoleMenu(com.node.model.JtRoleMenu)
	 */
	@Override
	public void saveJtRoleMenu(JtRoleMenu jtRoleMenu) {
		// TODO Auto-generated method stub
		iJtRoleMenuDao.save(jtRoleMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IJtUserService#getJtUserByUserCode(java.lang.String)
	 */
	@Override
	public JtUser getJtUserByUserCode(String slr) {
		List<JtUser> jtUsers = iJtUserDao.findByProperty("userCode", slr);
		if (jtUsers != null && jtUsers.size() > 0) {
			return jtUsers.get(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getJtDeptByOrg(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JtViewDept getJtDeptByOrg(String userOrg) {
		String sql = "select * from oa_dept_view where ORG_ID = '" + userOrg
				+ "'";
		Map<String, Object> map = iJtUserDao.getSpringSQL(sql, null);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map
				.get("rows");
		if (list != null && list.size() > 0) {
			Map<String, Object> deptMap = list.get(0);
			JtViewDept jtViewDept = new JtViewDept();
			jtViewDept.setId(Integer.parseInt(deptMap.get("ID").toString()));
			jtViewDept.setFlag(deptMap.get("FLAG").toString());
			jtViewDept.setJb(deptMap.get("JB").toString());
			jtViewDept.setOrgId(deptMap.get("ORG_ID").toString());
			jtViewDept.setOrgName(deptMap.get("ORG_NAME").toString());
			jtViewDept.setUpOrg(deptMap.get("UP_ORG").toString());
			return jtViewDept;
		} else {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getAllApproveRoles()
	 */
	@Override
	public List<JtRole> getAllApproveRoles() {
		// TODO Auto-generated method stub
		String[] propertyNames = { "roleType", "roleState" };
		Object[] values = { "1", "1" };
		return iJtRoleDao.findByPropertys(propertyNames, values);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IJtUserService#getApproveRolesByUser(com.node.model.
	 * JtUser)
	 */
	@Override
	public List<JtRole> getApproveRolesByUser(JtUser jtUser) {
		List<JtRole> jtRoles = new ArrayList<>();
		if (StringUtils.isNotBlank(jtUser.getUserRole())) {
			String[] roleIds = jtUser.getUserRole().split(",");
			for (int i = 0; i < roleIds.length; i++) {
				JtRole jtRole = iJtRoleDao.get(Integer.parseInt(roleIds[i]));
				if (jtRole.getRoleType().equals("1")) {
					jtRoles.add(jtRole);
				}

			}
		}

		return jtRoles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IJtUserService#getDeptNameByUser(java.lang.String)
	 */
	@Override
	public String getDeptNameByUser(String orgId) {
		String sql = "select ORG_NAME from  OA_DEPT_VIEW where ORG_ID = "
				+ orgId;
		Object object = iJtUserDao.getDateBySQL(sql);
		return object.toString();
	}
}
