/**
 * 文件名：IDeptServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月7日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDeptDao;
import com.node.model.TDept;
import com.node.service.IDeptService;
import com.node.util.HqlHelper;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月7日 下午4:13:51
 */
@Service
public class IDeptServiceImp implements IDeptService {

	@Autowired
	IDeptDao iDeptDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDeptService#queryAllDepts(com.node.util.HqlHelper)
	 */
	@Override
	public Map<String, Object> queryAllDepts(HqlHelper hql) {
		// TODO Auto-generated method stub
		return iDeptDao.findAllByHqlHelp(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDeptService#getVcDeptNameById(int)
	 */
	@Override
	public String getVcDeptNameById(int pid) {
		if (pid == 0) {
			return "深圳市交通警察支队";
		} else {
			TDept tDept = iDeptDao.get(pid);
			return tDept.getVcDept();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDeptService#saveOrUpdate(com.node.model.TDept)
	 */
	@Override
	public void saveOrUpdate(TDept tDept) {
		// TODO Auto-generated method stub
		iDeptDao.saveOrUpdate(tDept);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDeptService#findAll()
	 */
	@Override
	public List<TDept> findAll() {
		// TODO Auto-generated method stub
		return iDeptDao.findAll();
	}

}
