/**
 * 文件名：SystemServiceImp.java
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
import com.node.model.JtMenu;
import com.node.object.JtViewDept;
import com.node.service.ISystemService;
import com.node.util.HqlHelper;
import com.node.util.SystemConstants;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月21日 下午2:42:09
 */
@Service
public class SystemServiceImp implements ISystemService {
	@Autowired
	IJtMenuDao iJtMenuDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ISystemService#queryByHql(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> queryByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iJtMenuDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ISystemService#getParentMenu()
	 */
	@Override
	public List<JtMenu> getParentMenu() {
		// TODO Auto-generated method stub
		return iJtMenuDao.findByProperty("iParent",
				SystemConstants.ROOT_PARENTID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ISystemService#save(com.node.model.JtMenu)
	 */
	@Override
	public void save(JtMenu jtMenu) {
		// TODO Auto-generated method stub
		iJtMenuDao.save(jtMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ISystemService#update(com.node.model.JtMenu)
	 */
	@Override
	public void update(JtMenu jtMenu) {
		// TODO Auto-generated method stub
		iJtMenuDao.updateCleanBefore(jtMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ISystemService#getMenuById(java.lang.Integer)
	 */
	@Override
	public JtMenu getMenuById(Integer getiParent) {
		// TODO Auto-generated method stub
		return iJtMenuDao.get(getiParent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.ISystemService#getDepts()
	 */
	@Override
	public List<JtViewDept> getDepts(String orgId, String orgName, String upOrg) {
		StringBuffer sql = new StringBuffer(
				"select id,org_id,up_org,org_name,jb,flag from oa_dept_view where 1=1");
		if (orgId != null && !orgId.equals("")) {
			sql.append(" and ORG_ID = '" + orgId + "'");
		}
		if (orgName != null && !orgName.equals("")) {
			sql.append(" and ORG_NAME like '%" + orgName + "%'");
		}
		if (upOrg != null && !upOrg.equals("")) {
			sql.append(" and UP_ORG = '" + upOrg + "'");
		}
		sql.append(" order by  up_org asc");
		List<JtViewDept> jtViewDepts = new ArrayList<>();
		Map<String, Object> resultMap = iJtMenuDao.getSpringSQL(sql.toString(),
				null);
		List<Map<String, Object>> objList = (List<Map<String, Object>>) resultMap
				.get("rows");

		for (int i = 0; i < objList.size(); i++) {
			JtViewDept jtViewDept = new JtViewDept();
			jtViewDept
					.setId(objList.get(i).get("ID") == null ? SystemConstants.ROOT_PARENTID
							: Integer.parseInt(objList.get(i).get("ID")
									.toString()));
			jtViewDept.setOrgId(objList.get(i).get("ORG_ID").toString());
			jtViewDept.setFlag(objList.get(i).get("FLAG").toString());
			jtViewDept.setUpOrg(objList.get(i).get("UP_ORG").toString());
			jtViewDept.setOrgName(objList.get(i).get("ORG_NAME").toString());
			jtViewDept.setJb(objList.get(i).get("JB").toString());
			jtViewDepts.add(jtViewDept);
		}

		return jtViewDepts;
	}
}
