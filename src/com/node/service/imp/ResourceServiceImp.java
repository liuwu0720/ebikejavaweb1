/**
 * 文件名：ResourceServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月4日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IResourceDao;
import com.node.model.TResource;
import com.node.service.IResourceService;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月4日 下午4:51:03
 */
@Service
public class ResourceServiceImp implements IResourceService {

	@Autowired
	IResourceDao iResourceDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IResourceService#loadAll()
	 */
	@Override
	public List<TResource> loadAll() {
		// TODO Auto-generated method stub
		return iResourceDao.loadAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IResourceService#getJsonTree(java.util.List)
	 */
	@Override
	public String getJsonTree(List<TResource> list) {
		if (CollectionUtils.isNotEmpty(list)) {

			JSONArray arr = new JSONArray();
			for (TResource r : list) {
				JSONObject obj = new JSONObject();
				obj.element("id", r.getId());
				obj.element("pId", r.getiParent());
				obj.element("name", r.getVcResourceName());
				obj.element("vurl", r.getVcUrl());

				/*
				 * if ( StringUtils.isNotBlank( r.getVcIcon() ) ) { obj.element(
				 * "iconSkin" , r.getVcIcon() ); }
				 */
				obj.element("ntype", r.getnType());// 添加 资源类型
				obj.element("click", "false");
				obj.element("leaf", r.getnLeaf());// 添加 资源类型
				arr.add(obj);
			}

			return arr.toString();
		}

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IResourceService#get(int)
	 */
	@Override
	public TResource get(int parseInt) {
		// TODO Auto-generated method stub
		return iResourceDao.get(parseInt);
	}

}
