/**
 * 文件名：DictionaryServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcSjzdDao;
import com.node.model.DdcSjzd;
import com.node.service.IDictionaryService;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午1:37:58
 */
@Service
public class DictionaryServiceImp implements IDictionaryService {

	@Autowired
	IDdcSjzdDao iDdcSjzdDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDictionaryService#queryByHql(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> queryByHql(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDdcSjzdDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDictionaryService#deleteById(int)
	 */
	@Override
	public void deleteById(long dictionId) {
		// TODO Auto-generated method stub
		iDdcSjzdDao.deleteByKey(dictionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDictionaryService#save(com.node.model.DdcSjzd)
	 */
	@Override
	public void save(DdcSjzd ddcSjzd) {
		// TODO Auto-generated method stub
		iDdcSjzdDao.save(ddcSjzd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDictionaryService#update(com.node.model.DdcSjzd)
	 */
	@Override
	public void update(DdcSjzd ddcSjzd) {
		// TODO Auto-generated method stub
		iDdcSjzdDao.updateCleanBefore(ddcSjzd);
	}

}
