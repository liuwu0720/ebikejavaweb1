/**
 * 文件名：FileRecordDaoImp.java
 * 版本信息：Version 1.0
 * 日期：2016年4月14日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.dao.imp;

import org.springframework.stereotype.Repository;

import com.node.dao.IFileRecordDao;
import com.node.model.FileRecord;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月14日 下午4:52:47
 */
@Repository
public class FileRecordDaoImp extends GenericHibernateDao<FileRecord, Integer>
		implements IFileRecordDao {

}
