/**
 * 文件名：IEbikeService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月24日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.model.DdcApproveUser;
import com.node.model.DdcDaxxb;
import com.node.model.DdcDriver;
import com.node.model.DdcFlow;
import com.node.model.DdcHyxhBasb;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.DdcSjzd;
import com.node.util.HqlHelper;
import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月24日 下午2:01:12
 */
public interface IEbikeService {

	/**
	 * 方法描述：
	 * 
	 * @param sql
	 * @param p
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:02:14
	 */
	Map<String, Object> queryBySpringSql(String sql, Page p);

	/**
	 * 方法描述：
	 * 
	 * @param sbId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:19:56
	 */
	DdcDaxxb getById(long sbId);

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @param cysy
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午2:22:26
	 */
	String findByProPerties(String string, String cysy);

	/**
	 * 方法描述：
	 * 
	 * @param dmlb
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午4:21:09
	 */
	List<DdcSjzd> getAllSjzdByDmlb(String dmlb);

	/**
	 * 方法描述：
	 * 
	 * @param ddcDaxxb
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午5:50:40
	 */
	void updateDdcDaxxb(DdcDaxxb ddcDaxxb);

	/**
	 * 方法描述：
	 * 
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月24日 下午6:10:13
	 */
	DdcDaxxb getDdcDaxxbById(Long id);

	/**
	 * 方法描述：
	 * 
	 * @param flowId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 下午6:45:01
	 */
	DdcFlow getFlowById(long flowId);

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午3:52:26
	 */
	Map<String, Object> queryDdcHyxhBasbByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午5:09:51
	 */
	DdcHyxhBasb getDdcHyxhBasbById(long dId);

	/**
	 * 方法描述：
	 * 
	 * @param approveTableName
	 * @param id
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午5:16:30
	 */
	List<DdcApproveUser> findApproveUsersByProperties(String approveTableName,
			Long id);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBasb
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午7:17:21
	 */
	void updateDdcHyxhBasb(DdcHyxhBasb ddcHyxhBasb);

	/**
	 * 方法描述：
	 * 
	 * @param approveUser
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月28日 下午7:19:17
	 */
	void saveDdcApproveUser(DdcApproveUser approveUser);

	/**
	 * 方法描述：
	 * 
	 * @param dId
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午6:30:20
	 */
	DdcHyxhSsdwclsb getDdcHyxhSsdwclsbById(long dId);

	/**
	 * 方法描述：
	 * 
	 * @param hyxhzh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月29日 下午6:48:53
	 */
	DdcHyxhBase getHyxhByCode(String hyxhzh);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhSsdwclsb
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 上午11:11:36
	 */
	void updateDdcHyxhSsdwclsb(DdcHyxhSsdwclsb ddcHyxhSsdwclsb);

	/**
	 * 方法描述：
	 * 
	 * @param ddcFlow
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 上午11:12:19
	 */
	void saveDdcFlow(DdcFlow ddcFlow);

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 上午11:26:19
	 */
	List<DdcSjzd> getSjzdByDmlb(String string);

	/**
	 * 方法描述：
	 * 
	 * @param daxxb
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午2:19:37
	 */
	void saveDaxxb(DdcDaxxb daxxb);

	/**
	 * 方法描述：
	 * 
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午2:21:10
	 */
	String getDabhByProcess();

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午2:29:14
	 */
	String getCphmByProcess(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param tbyy
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午4:13:40
	 */
	List<DdcSjzd> getDbyyList(String tbyy, String type);

	/**
	 * 方法描述：
	 * 
	 * @param slzl
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午6:59:28
	 */
	List<DdcSjzd> getSelectSlzl(String slzl);

	/**
	 * 方法描述：
	 * 
	 * @param sql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月30日 下午7:38:39
	 */
	Object getDateBySQL(String sql);

	/**
	 * 方法描述：
	 * 
	 * @param lsh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月5日 下午1:34:39
	 */
	List<DdcApproveUser> findApproveUsersByLsh(String lsh);

	/**
	 * 方法描述：
	 * 
	 * @param ddcDriver
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月16日 下午7:14:45
	 */
	void saveDdcDriver(DdcDriver ddcDriver);

	/**
	 * 方法描述：
	 * 
	 * @param hql
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月17日 下午6:40:01
	 */
	Map<String, Object> queryFileRecordByHql(HqlHelper hql);

	/**
	 * 方法描述：
	 * 
	 * @param dabh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月22日 上午10:31:49
	 */
	DdcDaxxb getDdcDaxxbByDabh(String dabh);

	/**
	 * 方法描述：
	 * 
	 * @param ddcFlow
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月22日 上午10:39:11
	 */
	void updateDdcFlow(DdcFlow ddcFlow);

	/**
	 * 方法描述：
	 * 
	 * @param ddcHyxhBase
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月22日 上午10:54:17
	 */
	void updateDdcHyxhBase(DdcHyxhBase ddcHyxhBase);

	/**
	 * 方法描述：
	 * 
	 * @param lsh
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年5月10日 下午3:27:19
	 */
	DdcHyxhSsdwclsb getDdcHyxhSsdwclsbByLsh(String lsh);

}
