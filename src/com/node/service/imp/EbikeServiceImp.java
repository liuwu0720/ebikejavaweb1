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

import com.node.dao.IDdcDaxxbDao;
import com.node.dao.IDdcSjzdDao;
import com.node.model.DdcDaxxb;
import com.node.model.DdcSjzd;
import com.node.service.IEbikeService;
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

}
