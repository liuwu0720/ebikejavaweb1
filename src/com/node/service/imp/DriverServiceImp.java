/**
  * 文件名：DriverServiceImp.java
  * 版本信息：Version 1.0
  * 日期：2016年6月21日
  * Copyright 结点科技 Corporation 2016 
  * 版权所有
  */
package com.node.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcDriverDao;
import com.node.dao.IDdcHyxhBaseDao;
import com.node.dao.IDdcHyxhSsdwDao;
import com.node.model.DdcDriver;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.service.IDriverService;
import com.node.util.HqlHelper;
import com.node.util.Page;


/**
 * 类描述：
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月21日 下午6:47:33 
 */
@Service
public class DriverServiceImp implements IDriverService{

	@Autowired
	IDdcDriverDao iDdcDriverDao;
	@Autowired
	IDdcHyxhBaseDao iDdcHyxhBaseDao;
	@Autowired
	IDdcHyxhSsdwDao iDdcHyxhSsdwDao;
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#queryByHql(com.node.util.HqlHelper)
		 */
	@Override
	public Map<String, Object> queryByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDdcDriverDao.findAllByHqlHelp(hql);
	}
	
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#queryBySpringsql(java.lang.String)
		 */
	@Override
	public Map<String, Object> queryBySpringsql(String sql, Page page) {
		// TODO Auto-generated method stub
		return iDdcDriverDao.getSpringSQL(sql, page);
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#getDriverById(long)
		 */
	@Override
	public DdcDriver getDriverById(long driverId) {
		// TODO Auto-generated method stub
		return iDdcDriverDao.get(driverId);
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#getHyxhNameByHyxhzh(java.lang.String)
		 */
	@Override
	public String getHyxhNameByHyxhzh(String hyxhzh) {
		List<DdcHyxhBase> ddcHyxhBases = iDdcHyxhBaseDao.findByProperty("hyxhzh", hyxhzh);
		if(CollectionUtils.isNotEmpty(ddcHyxhBases)){
			return ddcHyxhBases.get(0).getHyxhmc();
		}else {
			return null;
		}
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#getDwmcById(java.lang.Integer)
		 */
	@Override
	public String getDwmcById(Integer ssdwId) {
		DdcHyxhSsdw ddcHyxhSsdw = iDdcHyxhSsdwDao.get(Long.parseLong(ssdwId+""));
		return ddcHyxhSsdw.getDwmc();
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#updateDdcDriver(com.node.model.DdcDriver)
		 */
	@Override
	public void updateDdcDriver(DdcDriver ddcDriver) {
		// TODO Auto-generated method stub
		iDdcDriverDao.update(ddcDriver);
	}

	
		/* (non-Javadoc)
		 * @see com.node.service.IDriverService#deleteById(long)
		 */
	@Override
	public void deleteById(long driverId) {
		// TODO Auto-generated method stub
		iDdcDriverDao.deleteByKey(driverId);
	}

}
