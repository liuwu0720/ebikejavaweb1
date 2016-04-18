/**
 * 文件名：IdataService.java
 * 版本信息：Version 1.0
 * 日期：2016年4月17日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.io.InputStream;

import com.node.model.FileRecord;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月17日 下午7:13:44
 */
public interface IDataService {

	/**
	 * 方法描述：
	 * 
	 * @param inputStream
	 * @return
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月17日 下午7:25:32
	 */
	String updateReadExcel(InputStream inputStream);

	/**
	 * 方法描述：
	 * 
	 * @param fileRecord
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月18日 下午4:18:41
	 */
	void saveFileRecord(FileRecord fileRecord);

}
