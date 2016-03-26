/**
 * 文件名：IStatisticalService.java
 * 版本信息：Version 1.0
 * 日期：2016年3月25日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.util.List;
import java.util.Map;

import com.node.util.Page;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月25日 上午10:19:41
 */
public interface IStatisticalService {

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @param p
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 上午10:32:43
	 */
	Map<String, Object> findBySpringSql(String string, Page p);

	/**
	 * 方法描述：
	 * 
	 * @param string
	 * @param p
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月25日 下午3:08:59
	 */
	Map<String, Object> findBySpringSqlPage(String string, Page p);

	/**
	 * 方法描述：
	 * 
	 * @param dlist
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年3月26日 下午3:53:14
	 */
	Map<String, Object> findTeam(List<?> dlist);

}
