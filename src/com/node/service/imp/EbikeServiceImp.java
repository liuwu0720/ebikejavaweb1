/**
 * 文件名：EbikeServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月24日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcApprovalUserDao;
import com.node.dao.IDdcDaxxbDao;
import com.node.dao.IDdcDriverDao;
import com.node.dao.IDdcDriverDaxxDao;
import com.node.dao.IDdcFlowDao;
import com.node.dao.IDdcHyxhBasbDao;
import com.node.dao.IDdcHyxhBaseDao;
import com.node.dao.IDdcHyxhSsdwDao;
import com.node.dao.IDdcHyxhSsdwclsbDao;
import com.node.dao.IDdcSjzdDao;
import com.node.dao.IFileRecordDao;
import com.node.model.DdcApproveUser;
import com.node.model.DdcDaxxb;
import com.node.model.DdcDriver;
import com.node.model.DdcDriverDaxx;
import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBasb;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.DdcSjzd;
import com.node.service.IEbikeService;
import com.node.util.HqlHelper;
import com.node.util.Page;
import com.node.util.SystemConstants;

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

	@Autowired
	IDdcDriverDao iDdcDriverDao;

	@Autowired
	IFileRecordDao iFileRecordDao;

	@Autowired
	IDdcHyxhSsdwDao iDdcHyxhSsdwDao;
	
	@Autowired
	IDdcDriverDaxxDao iDdcDriverDaxxDao;

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
				.findByPropertysOrderBy(propertyNames, values, "approveTime",
						"asc");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#updateDdcHyxhSsdwclsb(com.node.model.
	 * DdcHyxhSsdwclsb)
	 */
	@Override
	public void updateDdcHyxhSsdwclsb(DdcHyxhSsdwclsb ddcHyxhSsdwclsb) {
		// TODO Auto-generated method stub
		iDdcHyxhSsdwclsbDao.update(ddcHyxhSsdwclsb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#saveDdcFlow(com.node.model.DdcFlow)
	 */
	@Override
	public void saveDdcFlow(DdcFlow ddcFlow) {
		// TODO Auto-generated method stub
		iDdcFlowDao.save(ddcFlow);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getSjzdByDmlb(java.lang.String)
	 */
	@Override
	public List<DdcSjzd> getSjzdByDmlb(String string) {
		// TODO Auto-generated method stub
		return iDdcSjzdDao.findByProperty("dmlb", string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#saveDaxxb(com.node.model.DdcDaxxb)
	 */
	@Override
	public void saveDaxxb(DdcDaxxb daxxb) {
		// TODO Auto-generated method stub
		iDdcDaxxbDao.save(daxxb);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDabhByProcess()
	 */
	@Override
	public String getDabhByProcess() {
		String sql = "select max(t.dabh) from DDC_DAXXB t";
		Object object = iDdcDaxxbDao.getDateBySQL(sql);
		String dabh = "";
		if (object == null) {
			dabh = "440300000000";
		} else {
			dabh = object.toString();
			long daNo = Long.parseLong(dabh) + 1;
			dabh = daNo + "";
		}
		return dabh;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#getCphmByProcess(com.node.model.DdcHyxhBase
	 * )
	 */
	@Override
	public String getCphmByProcess(DdcHyxhBase ddcHyxhBase) {
		String sql = "select  max(t.cphm)   from DDC_DAXXB t  where t.cphm like '%"
				+ ddcHyxhBase.getHyxhlb() + "%'  ";
		Object object = iDdcDaxxbDao.getDateBySQL(sql);
		String cphm = "";
		if (object == null) {
			cphm = ddcHyxhBase.getHyxhlb() + "00001";
		} else {
			cphm = object.toString().replace(ddcHyxhBase.getHyxhlb(), "1");
			int daNo = Integer.parseInt(cphm) + 1;
			cphm = (daNo + "").substring(1);
			cphm = ddcHyxhBase.getHyxhlb() + ""+cphm;
		}
		return cphm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDbyyList(java.lang.String)
	 */
	@Override
	public List<DdcSjzd> getDbyyList(String tbyy, String type) {
		if (StringUtils.isNotBlank(tbyy)) {
			String[] tbyyStrings = tbyy.split(",");
			List<DdcSjzd> allDdcSjzds = new ArrayList<>();
			for (String dmz : tbyyStrings) {
				List<DdcSjzd> ddcSjzds = iDdcSjzdDao.findByPropertys(
						new String[] { "dmz", "dmlb" }, new Object[] { dmz,
								type });
				if (ddcSjzds != null && ddcSjzds.size() > 0) {
					allDdcSjzds.addAll(ddcSjzds);
				}

			}

			return allDdcSjzds;
		} else {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getSelectSlzl(java.lang.String)
	 */
	@Override
	public List<DdcSjzd> getSelectSlzl(String slzl) {
		if (StringUtils.isNotBlank(slzl)) {
			String[] tbyyStrings = slzl.split(",");
			List<DdcSjzd> allDdcSjzds = new ArrayList<>();
			for (String dmz : tbyyStrings) {
				List<DdcSjzd> ddcSjzds = iDdcSjzdDao.findByPropertys(
						new String[] { "dmz", "dmlb" }, new Object[] { dmz,
								"BASQZL" });
				if (ddcSjzds != null && ddcSjzds.size() > 0) {
					allDdcSjzds.addAll(ddcSjzds);
				}

			}

			return allDdcSjzds;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDateBySQL(java.lang.String)
	 */
	@Override
	public Object getDateBySQL(String sql) {
		// TODO Auto-generated method stub
		return iDdcFlowDao.getDateBySQL(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#findApproveUsersByLsh(java.lang.String)
	 */
	@Override
	public List<DdcApproveUser> findApproveUsersByLsh(String lsh) {
		List<DdcApproveUser> ddcApproveUsers = iDdcApprovalUserDao
				.findByPropertyOrderBy("lsh", lsh, "approveTime");
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
	 * com.node.service.IEbikeService#saveDdcDriver(com.node.model.DdcDriver)
	 */
	@Override
	public void saveDdcDriver(DdcDriver ddcDriver) {
		// TODO Auto-generated method stub
		iDdcDriverDao.save(ddcDriver);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#queryFileRecordByHql(com.node.util.HqlHelper
	 * )
	 */
	@Override
	public Map<String, Object> queryFileRecordByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iFileRecordDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#getDdcDaxxbByDabh(java.lang.String)
	 */
	@Override
	public DdcDaxxb getDdcDaxxbByDabh(String dabh) {
		List<DdcDaxxb> ddcDaxxbs = iDdcDaxxbDao.findByProperty("dabh", dabh);
		if (CollectionUtils.isNotEmpty(ddcDaxxbs)) {
			return ddcDaxxbs.get(0);
		} else {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IEbikeService#updateDdcFlow(com.node.model.DdcFlow)
	 */
	@Override
	public void updateDdcFlow(DdcFlow ddcFlow) {
		// TODO Auto-generated method stub
		iDdcFlowDao.updateCleanBefore(ddcFlow);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#updateDdcHyxhBase(com.node.model.DdcHyxhBase
	 * )
	 */
	@Override
	public void updateDdcHyxhBase(DdcHyxhBase ddcHyxhBase) {
		// TODO Auto-generated method stub
		iDdcHyxhBaseDao.updateCleanBefore(ddcHyxhBase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#getDdcHyxhSsdwclsbByLsh(java.lang.String)
	 */
	@Override
	public DdcHyxhSsdwclsb getDdcHyxhSsdwclsbByLsh(String lsh) {
		List<DdcHyxhSsdwclsb> ddcHyxhSsdwclsbs = iDdcHyxhSsdwclsbDao
				.findByProperty("lsh", lsh);
		if (CollectionUtils.isNotEmpty(ddcHyxhSsdwclsbs)) {
			return ddcHyxhSsdwclsbs.get(0);
		} else {
			return null;
		}

	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IEbikeService#saveDdcDriverDaxx(com.node.model.DdcDaxxb)
		 */
	@Override
	public void saveDdcDriverDaxx(DdcDaxxb daxxb) {
		String[] propertyNames = { "sfzhm", "ssdwId" };
		Object[] values = { daxxb.getSfzmhm1(), Integer.parseInt(daxxb.getSsdwId()) };
	   List<DdcDriver> ddcDrivers = iDdcDriverDao.findByPropertys(propertyNames, values);
	   if(CollectionUtils.isNotEmpty(ddcDrivers)){
		   DdcDriver ddcDriver1 = ddcDrivers.get(0);
		   DdcDriverDaxx ddcDriverDaxx = new DdcDriverDaxx();
		   ddcDriverDaxx.setDaId(daxxb.getId());
		   ddcDriverDaxx.setDriverId(ddcDriver1.getId());
		   ddcDriverDaxx.setSysFlag(SystemConstants.SYSFLAG_ADD);
		   iDdcDriverDaxxDao.save(ddcDriverDaxx);
	   }
	   if(StringUtils.isNotBlank(daxxb.getSfzmhm2())){
			String[] propertyNames2 = { "sfzhm", "ssdwId" };
			Object[] values2 = { daxxb.getSfzmhm2(), Integer.parseInt(daxxb.getSsdwId()) };
		   List<DdcDriver> ddcDrivers2 = iDdcDriverDao.findByPropertys(propertyNames2, values2);
		   if(CollectionUtils.isNotEmpty(ddcDrivers2)){
			   DdcDriver ddcDriver2 = ddcDrivers2.get(0);
			   DdcDriverDaxx ddcDriverDaxx = new DdcDriverDaxx();
			   ddcDriverDaxx.setDaId(daxxb.getId());
			   ddcDriverDaxx.setDriverId(ddcDriver2.getId());
			   ddcDriverDaxx.setSysFlag(SystemConstants.SYSFLAG_ADD);
			   iDdcDriverDaxxDao.save(ddcDriverDaxx);
		   }
	   }
	 
		
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IEbikeService#getDriverInfoByProperties(java.lang.String, java.lang.String)
		 */
	@Override
	public List<DdcDriver> getDriverInfoByProperties(String sfzhm, String lxdh) {
		if(StringUtils.isNotBlank(sfzhm)){
			List<DdcDriver> ddcDrivers = iDdcDriverDao.findByProperty("sfzhm", sfzhm);
			return ddcDrivers;
		}
		if(StringUtils.isNotBlank(lxdh)){
			List<DdcDriver> ddcDrivers = iDdcDriverDao.findByProperty("lxdh", lxdh);
			return ddcDrivers;
		}
		return null;
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IEbikeService#findDdcFlowsByDabh(java.lang.String)
		 */
	@Override
	public List<DdcFlow> findDdcFlowsByDabh(String dabh) {
		List<DdcFlow> ddcFlows = iDdcFlowDao.findByProperty("dabh", dabh);
		return ddcFlows;
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IEbikeService#saveNewDaxxb(com.node.model.DdcHyxhSsdwclsb, com.node.model.DdcFlow, com.node.model.DdcDaxxb, com.node.model.DdcApproveUser)
		 */
	@Override
	public void saveNewDaxxb(DdcHyxhSsdwclsb ddcHyxhSsdwclsb, DdcFlow ddcFlow,
			DdcDaxxb daxxb, DdcApproveUser approveUser) {
		// TODO Auto-generated method stub
		iDdcHyxhSsdwclsbDao.update(ddcHyxhSsdwclsb);
		iDdcFlowDao.save(ddcFlow);
		iDdcDaxxbDao.save(daxxb);
		saveDdcDriverDaxx(daxxb);
		iDdcApprovalUserDao.save(approveUser);
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IEbikeService#updateDdcDaxxbDisable(com.node.model.DdcHyxhSsdwclsb, com.node.model.DdcApproveUser, com.node.model.DdcFlow)
		 */
	@Override
	public void updateDdcDaxxbDisable(DdcHyxhSsdwclsb ddcHyxhSsdwclsb,
			DdcApproveUser approveUser, DdcFlow ddcFlow) {
		iDdcHyxhSsdwclsbDao.update(ddcHyxhSsdwclsb);
		iDdcApprovalUserDao.save(approveUser);
		iDdcFlowDao.save(ddcFlow);
	}

}
