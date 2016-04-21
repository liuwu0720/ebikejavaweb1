/**
 * 文件名：IdataService.java
 * 版本信息：Version 1.0
 * 日期：2016年4月17日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service;

import java.io.InputStream;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午1:58:38
	 * @throws Exception
	 */
	void createDaxxbExcel(WritableCellFormat wcfFC, WritableCellFormat wcfFC2,
			WritableSheet ws) throws Exception;

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws2
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午2:12:12
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	void createApproveUsers(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws2)
			throws RowsExceededException, WriteException;

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws3
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午2:14:21
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	void createDdcflows(WritableCellFormat wcfFC, WritableCellFormat wcfFC2,
			WritableSheet ws3) throws RowsExceededException, WriteException;

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws4
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午2:22:43
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	void createDdcHyxhBasb(WritableCellFormat wcfFC, WritableCellFormat wcfFC2,
			WritableSheet ws4) throws RowsExceededException, WriteException;

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws5
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午2:28:44
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	void createDdcHyxhBase(WritableCellFormat wcfFC, WritableCellFormat wcfFC2,
			WritableSheet ws5) throws RowsExceededException, WriteException;

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws6
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午3:01:53
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	void createDdcHyxhSsdw(WritableCellFormat wcfFC, WritableCellFormat wcfFC2,
			WritableSheet ws6) throws RowsExceededException, WriteException;

	/**
	 * 方法描述：
	 * 
	 * @param wcfFC
	 * @param wcfFC2
	 * @param ws7
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月21日 下午3:03:12
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	void createDdcHyxhSsdwClSb(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws7)
			throws RowsExceededException, WriteException;

}
