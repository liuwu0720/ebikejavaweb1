/**
 * 文件名：EbikeServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月24日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcApprovalUserDao;
import com.node.dao.IDdcDaxxbDao;
import com.node.dao.IDdcFlowDao;
import com.node.dao.IDdcHyxhBasbDao;
import com.node.dao.IDdcHyxhBaseDao;
import com.node.dao.IDdcHyxhSsdwclsbDao;
import com.node.dao.IDdcSjzdDao;
import com.node.model.DdcApproveUser;
import com.node.model.DdcDaxxb;
import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBasb;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.DdcSjzd;
import com.node.service.IEbikeService;
import com.node.util.HqlHelper;
import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月24日 下午2:01:29
 */
@Service
public class EbikeServiceImp implements IEbikeService {
	@Autowired
	IDdcDaxxbDao iDdcDaxxbDao;

	@Autowired
	IDdcSjzdDao iDdcSjzdDao;

	@Autowired
	IDdcFlowDao iDdcFlowDao;

	@Autowired
	IDdcHyxhBasbDao iDdcHyxhBasbDao;

	@Autowired
	IDdcHyxhBaseDao iDdcHyxhBaseDao;

	@Autowired
	IDdcApprovalUserDao iDdcApprovalUserDao;

	@Autowired
	IDdcHyxhSsdwclsbDao iDdcHyxhSsdwclsbDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#queryBySpringSql(java.lang.String,
	 * com.node.util.Page)
	 */
	@Override
	public Map<String, Object> queryBySpringSql(String sql, Page p) {
		// TODO Auto-generated method stub
		return iDdcDaxxbDao.getSpringSQL(sql, p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getById(long)
	 */
	@Override
	public DdcDaxxb getById(long sbId) {
		// TODO Auto-generated method stub
		return iDdcDaxxbDao.get(sbId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#findByProPerties(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String findByProPerties(String string, String cysy) {
		List<DdcSjzd> ddcSjzds = iDdcSjzdDao.findByPropertys(new String[] {
				"dmz", "dmlb" }, new Object[] { cysy, string });
		if (ddcSjzds != null && ddcSjzds.size() > 0) {
			return ddcSjzds.get(0).getDmms1();
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getAllSjzdByDmlb(java.lang.String)
	 */
	@Override
	public List<DdcSjzd> getAllSjzdByDmlb(String dmlb) {
		// TODO Auto-generated method stub
		return iDdcSjzdDao.findByProperty("dmlb", dmlb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#updateDdcDaxxb(com.node.model.DdcDaxxb)
	 */
	@Override
	public void updateDdcDaxxb(DdcDaxxb ddcDaxxb) {
		// TODO Auto-generated method stub
		iDdcDaxxbDao.updateCleanBefore(ddcDaxxb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDdcDaxxbById(java.lang.Long)
	 */
	@Override
	public DdcDaxxb getDdcDaxxbById(Long id) {
		// TODO Auto-generated method stub
		return iDdcDaxxbDao.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getFlowById(long)
	 */
	@Override
	public DdcFlow getFlowById(long flowId) {
		// TODO Auto-generated method stub
		return iDdcFlowDao.get(flowId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#queryDdcHyxhBasbByHql(com.node.util.HqlHelper
	 * )
	 */
	@Override
	public Map<String, Object> queryDdcHyxhBasbByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDdcHyxhBasbDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDdcHyxhBasbById(long)
	 */
	@Override
	public DdcHyxhBasb getDdcHyxhBasbById(long dId) {
		// TODO Auto-generated method stub
		return iDdcHyxhBasbDao.get(dId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#findApproveUsersByProperties(java.lang
	 * .String, java.lang.Long)
	 */
	@Override
	public List<DdcApproveUser> findApproveUsersByProperties(
			String approveTableName, Long id) {
		String[] propertyNames = { "approveTable", "approveTableid" };
		Object[] values = { approveTableName, id };
		List<DdcApproveUser> ddcApproveUsers = iDdcApprovalUserDao
				.findByPropertysOrderBy(propertyNames, values, "id", "asc");
		if (ddcApproveUsers != null && ddcApproveUsers.size() > 0) {
			return ddcApproveUsers;
		} else {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#updateDdcHyxhBasb(com.node.model.DdcHyxhBasb
	 * )
	 */
	@Override
	public void updateDdcHyxhBasb(DdcHyxhBasb ddcHyxhBasb) {
		// TODO Auto-generated method stub
		iDdcHyxhBasbDao.updateCleanBefore(ddcHyxhBasb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#saveDdcApproveUser(com.node.model.
	 * DdcApproveUser)
	 */
	@Override
	public void saveDdcApproveUser(DdcApproveUser approveUser) {
		// TODO Auto-generated method stub
		iDdcApprovalUserDao.save(approveUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDdcHyxhSsdwclsbById(long)
	 */
	@Override
	public DdcHyxhSsdwclsb getDdcHyxhSsdwclsbById(long dId) {
		// TODO Auto-generated method stub
		return iDdcHyxhSsdwclsbDao.get(dId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getHyxhByCode(java.lang.String)
	 */
	@Override
	public DdcHyxhBase getHyxhByCode(String hyxhzh) {
		List<DdcHyxhBase> ddcHyxhBases = iDdcHyxhBaseDao.findByProperty(
				"hyxhzh", hyxhzh);
		if (ddcHyxhBases != null && ddcHyxhBases.size() > 0) {
			return ddcHyxhBases.get(0);
		} else {
			return null;
		}

	}
}
