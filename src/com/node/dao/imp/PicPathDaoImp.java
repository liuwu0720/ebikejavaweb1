/**
 * 文件名：PicPathDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年3月12日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IPicPathDao;
import com.node.model.PicPath;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年3月12日 下午2:31:46
 */
@Repository
public class PicPathDaoImp extends GenericHibernateDao<PicPath, Integer>
		implements IPicPathDao {

}
