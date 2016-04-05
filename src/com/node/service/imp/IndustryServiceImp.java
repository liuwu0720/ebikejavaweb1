/**
 * 文件名：IndustryServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcHyxhBaseDao;
import com.node.dao.IDdcHyxhSsdwDao;
import com.node.dao.IDdcHyxhSsdwclsbDao;
import com.node.dao.IPicPathDao;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.PicPath;
import com.node.service.IInDustryService;
import com.node.util.HqlHelper;
import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午6:21:10
 */
@Service
public class IndustryServiceImp implements IInDustryService {
	@Autowired
	IDdcHyxhBaseDao iDdcHyxhBaseDao;

	@Autowired
	IDdcHyxhSsdwDao iDdcHyxhSsdwDao;

	@Autowired
	IPicPathDao iPicPathDao;

	@Autowired
	IDdcHyxhSsdwclsbDao iDdcHyxhSsdwclsbDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IInDustryService#queryByHql(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> queryByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDdcHyxhBaseDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#deleteById(long)
	 */
	@Override
	public void deleteById(long dId) {
		// TODO Auto-generated method stub
		iDdcHyxhBaseDao.deleteByKey(dId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#save(com.node.model.DdcHyxhBase)
	 */
	@Override
	public void save(DdcHyxhBase ddcHyxhBase) {
		// TODO Auto-generated method stub
		iDdcHyxhBaseDao.save(ddcHyxhBase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#update(com.node.model.DdcHyxhBase)
	 */
	@Override
	public void update(DdcHyxhBase ddcHyxhBase) {
		// TODO Auto-generated method stub
		iDdcHyxhBaseDao.updateCleanBefore(ddcHyxhBase);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#getDdcHyxhBase(int)
	 */
	@Override
	public DdcHyxhBase getDdcHyxhBase(long dId) {
		// TODO Auto-generated method stub
		return iDdcHyxhBaseDao.get(dId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#deleteCompanyById(long)
	 */
	@Override
	public void deleteCompanyById(long dId) {
		// TODO Auto-generated method stub
		iDdcHyxhSsdwDao.deleteByKey(dId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#getAllDDcHyxhBase()
	 */
	@Override
	public List<DdcHyxhBase> getAllDDcHyxhBase() {
		// TODO Auto-generated method stub
		return iDdcHyxhBaseDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#save(com.node.model.DdcHyxhSsdw)
	 */
	@Override
	public void save(DdcHyxhSsdw ddcHyxhSsdw) {
		// TODO Auto-generated method stub
		iDdcHyxhSsdwDao.save(ddcHyxhSsdw);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#update(com.node.model.DdcHyxhSsdw)
	 */
	@Override
	public void update(DdcHyxhSsdw ddcHyxhSsdw) {
		// TODO Auto-generated method stub
		iDdcHyxhSsdwDao.updateCleanBefore(ddcHyxhSsdw);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#getDdcHyxhSsdwById(long)
	 */
	@Override
	public DdcHyxhSsdw getDdcHyxhSsdwById(long dId) {
		// TODO Auto-generated method stub
		return iDdcHyxhSsdwDao.get(dId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#getImgPathById(java.lang.Integer)
	 */
	@Override
	public PicPath getImgPathById(Integer picImg) {
		// TODO Auto-generated method stub
		return iPicPathDao.get(picImg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IInDustryService#findDdcHyxhSsdwclsbByHql(com.node.util
	 * .HqlHelper)
	 */
	@Override
	public Map<String, Object> findDdcHyxhSsdwclsbByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDdcHyxhSsdwclsbDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IInDustryService#getBySpringSql(java.lang.String,
	 * com.node.util.Page)
	 */
	@Override
	public Map<String, Object> getBySpringSql(String sql, Page page) {
		// TODO Auto-generated method stub
		return iDdcHyxhSsdwclsbDao.getSpringSQL(sql, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IInDustryService#getDdcHyxhBaseByCode(java.lang.String)
	 */
	@Override
	public DdcHyxhBase getDdcHyxhBaseByCode(String hyxhzh) {
		List<DdcHyxhBase> ddcHyxhBases = iDdcHyxhBaseDao.findByProperty(
				"hyxhzh", hyxhzh);
		return ddcHyxhBases.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IInDustryService#getDdcHyxhBaseLastPe(com.node.model
	 * .DdcHyxhBase)
	 */
	@Override
	public int getDdcHyxhBaseLastPe(DdcHyxhBase ddcHyxhBase) {
		String sql = "select nvl(sum(t.dwpe),0) from  DDC_HYXH_SSDW t where t.hyxhzh='"
				+ ddcHyxhBase.getHyxhzh() + "'";
		Object object = iDdcHyxhBaseDao.getDateBySQL(sql);
		return Integer.parseInt(object.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IInDustryService#getAllDdcHyxhSsdwByHyxh(java.lang.String
	 * )
	 */
	@Override
	public List<DdcHyxhSsdw> getAllDdcHyxhSsdwByHyxh(String hyxhzh) {
		// TODO Auto-generated method stub
		return iDdcHyxhSsdwDao.findByProperty("hyxhzh", hyxhzh);
	}

}
