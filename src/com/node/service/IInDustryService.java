/**
 * 文件名：IInDustryService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月23日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.PicPath;
import com.node.util.HqlHelper;
import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月23日 下午6:20:25
 */
public interface IInDustryService {

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:23:58
	 */
	Map<String, Object> queryByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:42:19
	 */
	void deleteById(long dId);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:47:03
	 */
	void save(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午6:47:22
	 */
	void update(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月23日 下午7:09:12
	 */
	DdcHyxhBase getDdcHyxhBase(long dId);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:04:21
	 */
	void deleteCompanyById(long dId);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:11:05
	 */
	List<DdcHyxhBase> getAllDDcHyxhBase();

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhSsdw
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:22:10
	 */
	void save(DdcHyxhSsdw ddcHyxhSsdw);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhSsdw
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 上午11:22:38
	 */
	void update(DdcHyxhSsdw ddcHyxhSsdw);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午12:05:46
	 */
	DdcHyxhSsdw getDdcHyxhSsdwById(long dId);

	/**
	 * 方法描述：
	 * 
	 * @param picImg
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午12:23:08
	 */
	PicPath getImgPathById(Integer picImg);

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午12:53:25
	 */
	Map<String, Object> findDdcHyxhSsdwclsbByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param sql
	 * @param page
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午1:16:43
	 */
	Map<String, Object> getBySpringSql(String sql, Page page);

	/**
	 * 方法描述：
	 * 
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午2:18:28
	 */
	DdcHyxhBase getDdcHyxhBaseByCode(String hyxhzh);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午2:24:06
	 */
	int getDdcHyxhBaseLastPe(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午4:38:18
	 */
	List<DdcHyxhSsdw> getAllDdcHyxhSsdwByHyxh(String hyxhzh);

}
