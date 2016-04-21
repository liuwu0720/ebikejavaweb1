/**
 * 文件名：DataServiceImp.java
 * 版本信息：Version 1.0
 * 日期：2016年4月17日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.node.dao.IDdcApprovalUserDao;
import com.node.dao.IDdcDaxxbDao;
import com.node.dao.IDdcDaxxbLogDao;
import com.node.dao.IDdcFlowDao;
import com.node.dao.IDdcFlowLogDao;
import com.node.dao.IDdcHyxhBasbDao;
import com.node.dao.IDdcHyxhBasbLogDao;
import com.node.dao.IDdcHyxhBaseDao;
import com.node.dao.IDdcHyxhSsdwDao;
import com.node.dao.IDdcHyxhSsdwclsbDao;
import com.node.dao.IFileRecordDao;
import com.node.model.DdcApproveUser;
import com.node.model.DdcDaxxb;
import com.node.model.DdcDaxxbLog;
import com.node.model.DdcFlow;
import com.node.model.DdcFlowLog;
import com.node.model.DdcHyxhBasb;
import com.node.model.DdcHyxhBasbLog;
import com.node.model.DdcHyxhBase;
import com.node.model.DdcHyxhSsdw;
import com.node.model.DdcHyxhSsdwclsb;
import com.node.model.FileRecord;
import com.node.service.IDataService;
import com.node.util.SystemConstants;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月17日 下午7:14:01
 */
@Service
public class DataServiceImp implements IDataService {
	@Autowired
	IDdcDaxxbDao iDdcDaxxbDao;

	@Autowired
	IDdcApprovalUserDao iDdcApprovalUserDao;

	@Autowired
	IDdcFlowDao iDdcFlowDao;

	@Autowired
	IFileRecordDao iFileRecordDao;

	@Autowired
	IDdcDaxxbLogDao iDdcDaxxbLogDao;

	@Autowired
	IDdcFlowLogDao iDdcFlowLogDao;

	@Autowired
	IDdcHyxhBasbDao iDdcHyxhBasbDao;
	@Autowired
	IDdcHyxhBasbLogDao iDdcHyxhBasbLogDao;
	@Autowired
	IDdcHyxhBaseDao iDdcHyxhBaseDao;

	@Autowired
	IDdcHyxhSsdwDao iDdcHyxhSsdwDao;
	@Autowired
	IDdcHyxhSsdwclsbDao iDdcHyxhSsdwclsbDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDataService#readExcel(java.io.InputStream)
	 */
	@Override
	public String updateReadExcel(InputStream inputStream) {
		String message = "success";
		try {
			Workbook wb = WorkbookFactory.create(inputStream);
			Sheet sheet = wb.getSheetAt(0);// 档案表--外网的数据只会更新不会新增
			updateDaxxb(sheet);
			Sheet approveSheet = wb.getSheetAt(1);// 审批信息表-外网的数据只新增
			saveApproveUser(approveSheet);
			Sheet flowSheet = wb.getSheetAt(2);// flow--外网的数据只会新增
			saveFlow(flowSheet);
			Sheet hyxhSbSheet = wb.getSheetAt(3);// ddc_hyxh_basb--外网的数据只会新增
			saveDdcHyxhBasb(hyxhSbSheet);
			Sheet hyxhBaseSheet = wb.getSheetAt(4);// ddc_hyxh_base:外网只会更新信息，不会新增
			updateHyxhBase(hyxhBaseSheet);
			/**
			 * ddc_hyxh_ssdw:外网新增或修改数据 新增时验证usercode唯一性 修改时有可能是先新增再修改的，要判断
			 */
			Sheet ssdwSheet = wb.getSheetAt(5);
			updateSaveSsdwSheet(ssdwSheet);

			Sheet ssdwClsbSheet = wb.getSheetAt(6);// DDC_HYXH_SSDWCLSB 外网只新增数据
			saveSsdwClsb(ssdwClsbSheet);

		} catch (EncryptedDocumentException | InvalidFormatException
				| IOException e) {
			e.printStackTrace();
			message = e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
		}

		return message;
	}

	/**
	 * 方法描述：DDC_HYXH_SSDWCLSB 外网只新增数据
	 * 
	 * @param ssdwClsbSheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月18日 下午6:39:52
	 * @throws ParseException
	 */
	private void saveSsdwClsb(Sheet ssdwClsbSheet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 3; i <= ssdwClsbSheet.getLastRowNum(); i++) {
			Row row = ssdwClsbSheet.getRow(i);
			if (row != null) {
				int j = 0;
				DdcHyxhSsdwclsb ddcHyxhSsdwclsb = new DdcHyxhSsdwclsb();
				ddcHyxhSsdwclsb.setLsh(row.getCell(j) + "");
				ddcHyxhSsdwclsb.setHyxhzh(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSsdwId(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setCphm(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setPpxh(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setCysy(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setDjh(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setJtzz(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setJsrxm1(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setXb1(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSfzmhm1(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setLxdh1(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setJsrxm2(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setXb2(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSfzmhm2(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setLxdh2(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setXsqy(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setBz(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSqr(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSqrq(sdf.parse(row.getCell(j += 1) + ""));
				ddcHyxhSsdwclsb.setSlzl(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSlyj(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSlbz(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSlr(row.getCell(j += 1) + "");
				String slrqString = row.getCell(j += 1) + "";
				if (StringUtils.isNotBlank(slrqString)) {
					ddcHyxhSsdwclsb.setSlrq(sdf.parse(slrqString));
				} else {
					ddcHyxhSsdwclsb.setSlrq(null);
				}
				ddcHyxhSsdwclsb.setSlbm(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSynFlag(row.getCell(j += 1) + "_W");
				ddcHyxhSsdwclsb.setSqip(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setVcUser1Img(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setVcUser2Img(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setVcEbikeImg(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setTbyy(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setSlIndex(Integer.parseInt(row.getCell(j += 1)
						+ ""));
				ddcHyxhSsdwclsb.setVcUser1CardImg1(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setVcUser1CardImg2(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setVcUser2CardImg1(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setVcUser2CardImg2(row.getCell(j += 1) + "");
				ddcHyxhSsdwclsb.setnEnable(Integer.parseInt(row.getCell(j += 1)
						+ ""));
				ddcHyxhSsdwclsb.setVcEbikeInvoiceImg(row.getCell(j += 1) + "");
				List<DdcHyxhSsdwclsb> ddcHyxhSsdwclsbs = iDdcHyxhSsdwclsbDao
						.findByProperty("lsh", ddcHyxhSsdwclsb.getLsh());
				if (CollectionUtils.isEmpty(ddcHyxhSsdwclsbs)) {
					try {
						iDdcHyxhSsdwclsbDao.save(ddcHyxhSsdwclsb);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 方法描述：ddc_hyxh_ssdw: 外网新增或修改数据 新增时验证usercode唯一性 修改时有可能是先新增再修改的，要判断
	 * 
	 * @param ssdwSheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月18日 下午6:25:07
	 * @throws ParseException
	 */
	private void updateSaveSsdwSheet(Sheet ssdwSheet) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for (int i = 3; i <= ssdwSheet.getLastRowNum(); i++) {
				Row row = ssdwSheet.getRow(i);
				if (row != null) {
					int j = 0;
					DdcHyxhSsdw ddcHyxhSsdw = new DdcHyxhSsdw();
					ddcHyxhSsdw.setId(Long.parseLong(row.getCell(j) + ""));
					ddcHyxhSsdw.setHyxhzh(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setDwmc(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setZzjgdmzh(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setZsdz(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setLxr(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setLxdh(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setBz(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setSqr(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setSqrq(sdf.parse(row.getCell(j += 1) + ""));
					ddcHyxhSsdw.setZt(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setShr(row.getCell(j += 1) + "");
					String shrqString = row.getCell(j += 1) + "";
					if (StringUtils.isNotBlank(shrqString)) {
						ddcHyxhSsdw.setShrq(sdf.parse(shrqString));
					} else {
						ddcHyxhSsdw.setShrq(null);
					}
					ddcHyxhSsdw.setShbm(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setSynFlag(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setDwpe(Integer.parseInt(row.getCell(j += 1)
							+ ""));
					ddcHyxhSsdw.setVcPicPath(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setUserCode(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setPassWord(row.getCell(j += 1) + "");
					ddcHyxhSsdw.setShFlag(Integer.parseInt(row.getCell(j += 1)
							+ ""));
					ddcHyxhSsdw.setTotalPe(Integer.parseInt(row.getCell(j += 1)
							+ ""));
					if (ddcHyxhSsdw.getSynFlag().equalsIgnoreCase(
							SystemConstants.SYSFLAG_ADD)) {
						// 新增
						List<DdcHyxhSsdw> ddcHyxhSsdws = iDdcHyxhSsdwDao
								.findByProperty("userCode",
										ddcHyxhSsdw.getUserCode());
						if (CollectionUtils.isEmpty(ddcHyxhSsdws)) {
							iDdcHyxhSsdwDao.save(ddcHyxhSsdw);
						}
					} else if (ddcHyxhSsdw.getSynFlag().equalsIgnoreCase(
							SystemConstants.SYSFLAG_UPDATE)) {
						DdcHyxhSsdw newDdcHyxhSsdw = iDdcHyxhSsdwDao
								.get(ddcHyxhSsdw.getId());
						if (newDdcHyxhSsdw == null) {
							try {
								ddcHyxhSsdw.setSynFlag(ddcHyxhSsdw.getSynFlag()
										+ "_W");
								iDdcHyxhSsdwDao.save(ddcHyxhSsdw);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							ddcHyxhSsdw.setSynFlag(ddcHyxhSsdw.getSynFlag()
									+ "_W");
							iDdcHyxhSsdwDao.updateCleanBefore(ddcHyxhSsdw);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 方法描述：
	 * 
	 * @param hyxhBaseSheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月18日 下午6:14:42
	 * @throws ParseException
	 */
	private void updateHyxhBase(Sheet hyxhBaseSheet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 3; i <= hyxhBaseSheet.getLastRowNum(); i++) {
			Row row = hyxhBaseSheet.getRow(i);
			if (row != null) {
				int j = 0;
				DdcHyxhBase ddcHyxhBase = new DdcHyxhBase();
				ddcHyxhBase.setId(Long.parseLong(row.getCell(j) + ""));
				ddcHyxhBase.setHyxhzh(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhmm(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhmc(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhdz(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhfzr(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhfzrdh(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhsjzpe(Integer.parseInt(row.getCell(j += 1)
						+ ""));
				ddcHyxhBase.setBz(row.getCell(j += 1) + "");
				ddcHyxhBase.setCjr(row.getCell(j += 1) + "");
				ddcHyxhBase.setCjrq(sdf.parse(row.getCell(j += 1) + ""));
				ddcHyxhBase.setCjbm(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhlb(row.getCell(j += 1) + "");
				ddcHyxhBase.setSynFlag(row.getCell(j += 1) + "_W");
				String totalString = row.getCell(j += 1) + "";
				if (StringUtils.isNotBlank(totalString)) {
					ddcHyxhBase.setTotalPe(Integer.parseInt(totalString));
				} else {
					ddcHyxhBase.setTotalPe(null);
				}

				iDdcHyxhBaseDao.update(ddcHyxhBase);
			}
		}

	}

	/**
	 * 方法描述：
	 * 
	 * @param hyxhSbSheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月18日 下午3:57:38
	 * @throws ParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("static-access")
	private void saveDdcHyxhBasb(Sheet hyxhSbSheet) throws ParseException,
			IllegalAccessException, InvocationTargetException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 3; i <= hyxhSbSheet.getLastRowNum(); i++) {
			Row row = hyxhSbSheet.getRow(i);
			if (row != null) {
				int j = 0;
				DdcHyxhBasb ddcHyxhBasb = new DdcHyxhBasb();
				ddcHyxhBasb.setLsh(row.getCell(j) + "");
				ddcHyxhBasb.setHyxhzh(row.getCell(j += 1) + "");
				ddcHyxhBasb.setHyxhbcsjpe(Integer.parseInt(row.getCell(j += 1)
						+ ""));
				ddcHyxhBasb.setBz(row.getCell(j += 1) + "");
				ddcHyxhBasb.setSqr(row.getCell(j += 1) + "");
				ddcHyxhBasb.setSqrq(sdf.parse(row.getCell(j += 1) + ""));

				ddcHyxhBasb.setBjjg(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBjbz(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBzjr(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBjbm(row.getCell(j += 1) + "");
				String bjrq = row.getCell(j += 1) + "";
				if (StringUtils.isNotBlank(bjrq)) {
					ddcHyxhBasb.setBjrq(sdf.parse(bjrq));
				} else {
					ddcHyxhBasb.setBjrq(null);
				}

				ddcHyxhBasb.setSynFlag(row.getCell(j += 1) + "_W");
				ddcHyxhBasb.setTranDate(new Date());
				ddcHyxhBasb.setSlIndex(Integer.parseInt(row.getCell(j += 1)
						+ ""));
				ddcHyxhBasb.setHyxhmc(row.getCell(j += 1) + "");
				List<DdcHyxhBasb> ddcHyxhBasbs = iDdcHyxhBasbDao
						.findByProperty("lsh", ddcHyxhBasb.getLsh());

				BeanUtils beanUtils = new BeanUtils();
				DdcHyxhBasbLog ddcHyxhBasbLog = new DdcHyxhBasbLog();
				beanUtils.copyProperties(ddcHyxhBasbLog, ddcHyxhBasb);
				if (CollectionUtils.isEmpty(ddcHyxhBasbs)) {
					try {
						iDdcHyxhBasbDao.save(ddcHyxhBasb);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// iDdcHyxhBasbLogDao.save(ddcHyxhBasbLog);
			}
		}

	}

	/**
	 * 方法描述：
	 * 
	 * @param flowSheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月17日 下午10:34:47
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("static-access")
	private void saveFlow(Sheet flowSheet) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for (int i = 3; i <= flowSheet.getLastRowNum(); i++) {
				Row row = flowSheet.getRow(i);
				if (row != null) {
					int j = 0;
					DdcFlow ddcFlow = new DdcFlow();
					ddcFlow.setLsh(row.getCell(j) + "");
					ddcFlow.setYwlx(row.getCell(j += 1) + "");
					ddcFlow.setYwyy(row.getCell(j += 1) + "");
					ddcFlow.setHyxhzh(row.getCell(j += 1) + "");
					ddcFlow.setDabh(row.getCell(j += 1) + "");
					ddcFlow.setCphm(row.getCell(j += 1) + "");
					ddcFlow.setPpxh(row.getCell(j += 1) + "");
					ddcFlow.setCysy(row.getCell(j += 1) + "");
					ddcFlow.setDjh(row.getCell(j += 1) + "");
					ddcFlow.setJtzz(row.getCell(j += 1) + "");
					ddcFlow.setJsrxm1(row.getCell(j += 1) + "");
					ddcFlow.setXb1(row.getCell(j += 1) + "");
					ddcFlow.setSfzmhm1(row.getCell(j += 1) + "");
					ddcFlow.setLxdh1(row.getCell(j += 1) + "");
					ddcFlow.setJsrxm2(row.getCell(j += 1) + "");
					ddcFlow.setXb2(row.getCell(j += 1) + "");
					ddcFlow.setSfzmhm2(row.getCell(j += 1) + "");
					ddcFlow.setLxdh2(row.getCell(j += 1) + "");
					ddcFlow.setXsqy(row.getCell(j += 1) + "");
					ddcFlow.setBz(row.getCell(j += 1) + "");
					ddcFlow.setSlzl(row.getCell(j += 1) + "");
					ddcFlow.setSlyj(row.getCell(j += 1) + "");
					ddcFlow.setSlbz(row.getCell(j += 1) + "");
					ddcFlow.setSlr(row.getCell(j += 1) + "");
					ddcFlow.setSlrq(sdf.parse(row.getCell(j += 1) + ""));
					ddcFlow.setSlbm(row.getCell(j += 1) + "");
					ddcFlow.setGdyj(row.getCell(j += 1) + "");
					ddcFlow.setTbyy(row.getCell(j += 1) + "");
					ddcFlow.setGdbz(row.getCell(j += 1) + "");
					ddcFlow.setGdr(row.getCell(j += 1) + "");
					String gdrq = row.getCell(j += 1) + "";
					if (StringUtils.isNotBlank(gdrq)) {
						ddcFlow.setGdrq(sdf.parse(gdrq));
					} else {
						ddcFlow.setGdrq(null);
					}
					ddcFlow.setGdbm(row.getCell(j += 1) + "");
					ddcFlow.setSynFlag(row.getCell(j += 1) + "_W");
					ddcFlow.setYclb(row.getCell(j += 1) + "");
					ddcFlow.setVcUser1Img(row.getCell(j += 1) + "");
					ddcFlow.setVcUser2Img(row.getCell(j += 1) + "");
					ddcFlow.setVcEbikeImg(row.getCell(j += 1) + "");
					ddcFlow.setVcUser1CardImg1(row.getCell(j += 1) + "");
					ddcFlow.setVcUser1CardImg2(row.getCell(j += 1) + "");
					ddcFlow.setVcUser2CardImg1(row.getCell(j += 1) + "");
					ddcFlow.setVcUser2CardImg2(row.getCell(j += 1) + "");
					ddcFlow.setVcEbikeInvoiceImg(row.getCell(j += 1) + "");
					ddcFlow.setSsdwId(row.getCell(j += 1) + "");
					ddcFlow.setVcTableName(row.getCell(j += 1) + "");
					ddcFlow.setiTableId(Long.parseLong(row.getCell(j += 1) + ""));
					ddcFlow.setTranDate(new Date());
					// 根据流水号查询是否存在，存在则是重复导入
					List<DdcFlow> ddcFlows = iDdcFlowDao.findByProperty("lsh",
							ddcFlow.getLsh());
					DdcFlowLog ddcFlowLog = new DdcFlowLog();
					BeanUtils beanUtils = new BeanUtils();
					beanUtils.copyProperties(ddcFlowLog, ddcFlow);
					if (CollectionUtils.isEmpty(ddcFlows)) {
						ddcFlow.setTranDate(new Date());
						iDdcFlowDao.save(ddcFlow);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 方法描述：审批信息表
	 * 
	 * @param approveSheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月17日 下午8:38:15
	 * @throws ParseException
	 */
	private void saveApproveUser(Sheet approveSheet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 3; i <= approveSheet.getLastRowNum(); i++) {
			Row row = approveSheet.getRow(i);
			if (row != null) {
				int j = 0;
				DdcApproveUser ddcApproveUser = new DdcApproveUser();
				ddcApproveUser.setUserName(row.getCell(j) + "");
				ddcApproveUser.setUserOrgname(row.getCell(j += 1) + "");
				ddcApproveUser.setUserRoleName(row.getCell(j += 1) + "");
				ddcApproveUser.setApproveIndex(Integer.parseInt(row
						.getCell(j += 1) + ""));
				ddcApproveUser.setApproveNote(row.getCell(j += 1) + "");
				ddcApproveUser.setApproveTable(row.getCell(j += 1) + "");
				ddcApproveUser.setApproveTableid(Long.parseLong(row
						.getCell(j += 1) + ""));

				ddcApproveUser.setApproveTime(sdf.parse(row.getCell(j += 1)
						+ ""));
				ddcApproveUser.setApproveState(Integer.parseInt(row
						.getCell(j += 1) + ""));
				ddcApproveUser.setLsh(row.getCell(j += 1) + "");
				ddcApproveUser.setSysFlag(row.getCell(j += 1) + "_W");
				ddcApproveUser.setApproveNo(row.getCell(j += 1) + "");
				List<DdcApproveUser> ddcApproveUsers = iDdcApprovalUserDao
						.findByProperty("approveNo",
								ddcApproveUser.getApproveNo());
				if (CollectionUtils.isEmpty(ddcApproveUsers)) {
					ddcApproveUser.setTranDate(new Date());
					try {
						iDdcApprovalUserDao.save(ddcApproveUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

	/**
	 * 方法描述：更新档案表
	 * 
	 * @param sheet
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月17日 下午8:36:17
	 * @throws ParseException
	 */
	private void updateDaxxb(Sheet sheet) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			for (int i = 3; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					int j = 0;
					DdcDaxxb daxxb = new DdcDaxxb();
					daxxb.setId(Long.parseLong(row.getCell(j) + ""));
					daxxb.setDabh(row.getCell(j += 1) + "");
					daxxb.setYwlx(row.getCell(j += 1) + "");
					daxxb.setYwyy(row.getCell(j += 1) + "");
					daxxb.setHyxhzh(row.getCell(j += 1) + "");
					daxxb.setSsdwId(row.getCell(j += 1) + "");
					daxxb.setCphm(row.getCell(j += 1) + "");
					daxxb.setPpxh(row.getCell(j += 1) + "");
					daxxb.setCysy(row.getCell(j += 1) + "");
					daxxb.setDjh(row.getCell(j += 1) + "");
					daxxb.setJtzz(row.getCell(j += 1) + "");
					daxxb.setJsrxm1(row.getCell(j += 1) + "");
					daxxb.setXb1(row.getCell(j += 1) + "");
					daxxb.setSfzmhm1(row.getCell(j += 1) + "");
					daxxb.setLxdh1(row.getCell(j += 1) + "");
					daxxb.setJsrxm2(row.getCell(j += 1) + "");
					daxxb.setXb2(row.getCell(j += 1) + "");
					daxxb.setSfzmhm2(row.getCell(j += 1) + "");
					daxxb.setLxdh2(row.getCell(j += 1) + "");
					daxxb.setXsqy(row.getCell(j += 1) + "");
					daxxb.setBz(row.getCell(j += 1) + "");
					daxxb.setZt(row.getCell(j += 1) + "");
					daxxb.setSyrq(sdf.parse(row.getCell(j += 1) + ""));
					daxxb.setSlzl(row.getCell(j += 1) + "");
					daxxb.setSlyj(row.getCell(j += 1) + "");
					daxxb.setSlbz(row.getCell(j += 1) + "");
					daxxb.setSlr(row.getCell(j += 1) + "");
					String slrq = row.getCell(j += 1) + "";
					if (StringUtils.isNotBlank(slrq)) {
						daxxb.setSlrq(sdf.parse(slrq));
					}
					daxxb.setSlbm(row.getCell(j += 1) + "");
					daxxb.setGdyj(row.getCell(j += 1) + "");
					daxxb.setTbyy(row.getCell(j += 1) + "");
					daxxb.setGdbz(row.getCell(j += 1) + "");
					daxxb.setGdr(row.getCell(j += 1) + "");
					String gdrq = row.getCell(j += 1) + "";
					if (StringUtils.isNotBlank(gdrq)) {
						daxxb.setGdrq(sdf.parse(gdrq));
					}

					daxxb.setGdbm(row.getCell(j += 1) + "");
					daxxb.setSynFlag(row.getCell(j += 1) + "_W");
					daxxb.setVcUser1Img(row.getCell(j += 1) + "");
					daxxb.setVcUser2Img(row.getCell(j += 1) + "");
					daxxb.setVcEbikeImg(row.getCell(j += 1) + "");
					daxxb.setVcUser1CardImg1(row.getCell(j += 1) + "");
					daxxb.setVcUser1CardImg2(row.getCell(j += 1) + "");
					daxxb.setVcUser2CardImg1(row.getCell(j += 1) + "");
					daxxb.setVcUser2CardImg2(row.getCell(j += 1) + "");
					daxxb.setVcEbikeInvoiceImg(row.getCell(j += 1) + "");
					daxxb.setTranDate(new Date());
					iDdcDaxxbDao.update(daxxb);
					// saveDaxxbLog(daxxb);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 方法描述：
	 * 
	 * @param daxxb
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年4月18日 下午5:00:42
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("static-access")
	private void saveDaxxbLog(DdcDaxxb daxxb) throws IllegalAccessException,
			InvocationTargetException {
		DdcDaxxbLog dest = new DdcDaxxbLog();
		BeanUtils copyBeanUtils = new BeanUtils();
		copyBeanUtils.copyProperties(dest, daxxb);
		iDdcDaxxbLogDao.save(dest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDataService#saveFileRecord(com.node.model.FileRecord)
	 */
	@Override
	public void saveFileRecord(FileRecord fileRecord) {
		// TODO Auto-generated method stub
		iFileRecordDao.save(fileRecord);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDataService#createDaxxbExcel(jxl.write.WritableCellFormat
	 * , jxl.write.WritableCellFormat, jxl.write.WritableSheet)
	 */
	@Override
	public void createDaxxbExcel(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws) throws Exception {
		Label label = new Label(0, 0, "DDCDAXXB", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j0 = 0;
		ws.addCell(new Label(j0, 2, "ID", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "DABH", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "YWLX", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "YWYY", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "HYXHZH", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SSDWID", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "CPHM", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "PPXH", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "CYSY", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "DJH", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "JTZZ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "JSRXM1", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "XB1", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SFZMHM1", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "LXDH1", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "JSRXM2", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "XB2", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SFZMHM2", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "LXDH2", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "XSQY", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "BZ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "ZT", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SYRQ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SLZL", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SLYJ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SLBZ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SLR", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SLRQ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SLBM", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "GDYJ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "TBYY", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "GDBZ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "GDR", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "GDRQ", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "GDBM", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_USER1IMG", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_USER2IMG", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_EBIKE_IMG", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_USER1_CARDIMG1", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_USER1_CARDIMG2", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_USER2_CARDIMG1", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_USER2_CARDIMG2", wcfFC2));
		ws.addCell(new Label(j0 += 1, 2, "VC_EBIKE_INVOICE", wcfFC2));
		List<DdcDaxxb> allDaxxbs = new ArrayList<>();

		List<DdcDaxxb> daxxbs = iDdcDaxxbDao.findByProperty("synFlag", "UP");
		List<DdcDaxxb> daxxbs2 = iDdcDaxxbDao.findByProperty("synFlag", "ADD");
		allDaxxbs.addAll(daxxbs);
		allDaxxbs.addAll(daxxbs2);
		int i = 3;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (DdcDaxxb daxxb : allDaxxbs) {
			int j = 0;
			ws.addCell(new Label(j, i, String.valueOf(daxxb.getId())));
			ws.addCell(new Label(j += 1, i, daxxb.getDabh()));
			ws.addCell(new Label(j += 1, i, daxxb.getYwlx()));
			ws.addCell(new Label(j += 1, i, daxxb.getYwyy()));
			ws.addCell(new Label(j += 1, i, daxxb.getHyxhzh()));
			ws.addCell(new Label(j += 1, i, daxxb.getSsdwId()));
			ws.addCell(new Label(j += 1, i, daxxb.getCphm()));
			ws.addCell(new Label(j += 1, i, daxxb.getPpxh()));
			ws.addCell(new Label(j += 1, i, daxxb.getCysy()));
			ws.addCell(new Label(j += 1, i, daxxb.getDjh()));
			ws.addCell(new Label(j += 1, i, daxxb.getJtzz()));
			ws.addCell(new Label(j += 1, i, daxxb.getJsrxm1()));
			ws.addCell(new Label(j += 1, i, daxxb.getXb1()));
			ws.addCell(new Label(j += 1, i, daxxb.getSfzmhm1()));
			ws.addCell(new Label(j += 1, i, daxxb.getLxdh1()));
			ws.addCell(new Label(j += 1, i, daxxb.getJsrxm2()));
			ws.addCell(new Label(j += 1, i, daxxb.getXb2()));
			ws.addCell(new Label(j += 1, i, daxxb.getSfzmhm2()));
			ws.addCell(new Label(j += 1, i, daxxb.getLxdh2()));
			ws.addCell(new Label(j += 1, i, daxxb.getXsqy()));
			ws.addCell(new Label(j += 1, i, daxxb.getBz()));
			ws.addCell(new Label(j += 1, i, daxxb.getZt()));
			ws.addCell(new Label(j += 1, i, sdf.format(daxxb.getSyrq())));
			ws.addCell(new Label(j += 1, i, daxxb.getSlzl()));
			ws.addCell(new Label(j += 1, i, daxxb.getSlyj()));
			ws.addCell(new Label(j += 1, i, daxxb.getSlbz()));
			ws.addCell(new Label(j += 1, i, daxxb.getSlr()));
			if (daxxb.getSlrq() != null) {
				ws.addCell(new Label(j += 1, i, sdf.format(daxxb.getSlrq())));
			} else {
				ws.addCell(new Label(j += 1, i, ""));
			}

			ws.addCell(new Label(j += 1, i, daxxb.getSlbm()));
			ws.addCell(new Label(j += 1, i, daxxb.getGdyj()));
			ws.addCell(new Label(j += 1, i, daxxb.getTbyy()));
			ws.addCell(new Label(j += 1, i, daxxb.getGdbz()));
			ws.addCell(new Label(j += 1, i, daxxb.getGdr()));
			if (daxxb.getGdrq() != null) {
				ws.addCell(new Label(j += 1, i, sdf.format(daxxb.getGdrq())));
			} else {
				ws.addCell(new Label(j += 1, i, ""));
			}

			ws.addCell(new Label(j += 1, i, daxxb.getGdbm()));
			ws.addCell(new Label(j += 1, i, daxxb.getSynFlag()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcUser1Img()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcUser2Img()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcEbikeImg()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcUser1CardImg1()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcUser1CardImg2()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcUser2CardImg1()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcUser2CardImg2()));
			ws.addCell(new Label(j += 1, i, daxxb.getVcEbikeInvoiceImg()));
			i++;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDataService#createApproveUsers(jxl.write.WritableCellFormat
	 * , jxl.write.WritableCellFormat, jxl.write.WritableSheet)
	 */
	@Override
	public void createApproveUsers(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws)
			throws RowsExceededException, WriteException {
		Label label = new Label(0, 0, "审批报表", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j1 = 0;
		ws.addCell(new Label(j1, 2, "USER_NAME", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "USER_ORGNAME", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "USER_ROLE_NAME", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_INDEX", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_NOTE", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_TABLE", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_TABLEID", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_TIME", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_STATE", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "LSH", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j1 += 1, 2, "APPROVE_NO", wcfFC2));
		List<DdcApproveUser> ddcApproveUsers = iDdcApprovalUserDao
				.findByProperty("sysFlag", "ADD");
		int i = 3;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (DdcApproveUser ddcApproveUser : ddcApproveUsers) {
			int j = 0;
			ws.addCell(new Label(j, i, ddcApproveUser.getUserName()));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getUserOrgname()));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getUserRoleName()));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getApproveIndex()
					+ ""));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getApproveNote()));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getApproveTable()));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getApproveTableid()
					+ ""));
			ws.addCell(new Label(j += 1, i, sdf.format(ddcApproveUser
					.getApproveTime())));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getApproveState()
					+ ""));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getLsh()));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getSysFlag() + ""));
			ws.addCell(new Label(j += 1, i, ddcApproveUser.getApproveNo() + ""));
			i++;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDataService#createDdcflows(jxl.write.WritableCellFormat
	 * , jxl.write.WritableCellFormat, jxl.write.WritableSheet)
	 */
	@Override
	public void createDdcflows(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws)
			throws RowsExceededException, WriteException {
		Label label = new Label(0, 0, "DDCFLOW", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j = 0;
		ws.addCell(new Label(j, 2, "LSH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "YWLX", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "YWYY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHZH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "DABH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CPHM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "PPXH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CYSY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "DJH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "JTZZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "JSRXM1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "XB1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SFZMHM1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "LXDH1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "JSRXM2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "XB2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SFZMHM2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "LXDH2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "XSQY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLZL", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLYJ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLBZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLBM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "GDYJ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "TBYY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "GDBZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "GDR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "GDRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "GDBM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "YCLB", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER1IMG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER2IMG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_EBIKE_IMG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER1_CARDIMG1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER1_CARDIMG2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER2_CARDIMG1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER2_CARDIMG2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_EBIKE_INVOICE", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SSDWID", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "TABLENAME", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "TABLEID", wcfFC2));
		List<DdcFlow> ddcFlows = iDdcFlowDao.findByProperty("synFlag", "ADD");
		int i = 3;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (DdcFlow ddcFlow : ddcFlows) {
			int j1 = 0;
			ws.addCell(new Label(j1, i, ddcFlow.getLsh()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getYwlx()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getYwyy()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getHyxhzh()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getDabh()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getCphm()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getPpxh()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getCysy()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getDjh()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getJtzz()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getJsrxm1()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getXb1()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSfzmhm1()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getLxdh1()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getJsrxm2()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getXb2()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSfzmhm2()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getLxdh2()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getXsqy()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getBz()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSlzl()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSlyj()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSlbz()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSlr()));
			ws.addCell(new Label(j1 += 1, i, sdf.format(ddcFlow.getSlrq())));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSlbm()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getGdyj()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getTbyy()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getGdbz()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getGdr()));
			if (ddcFlow.getGdrq() != null) {
				ws.addCell(new Label(j1 += 1, i, sdf.format(ddcFlow.getGdrq())));
			} else {
				ws.addCell(new Label(j1 += 1, i, ""));
			}
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getGdbm()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSynFlag()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getYclb()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcUser1Img()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcUser2Img()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcEbikeImg()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcUser1CardImg1()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcUser1CardImg2()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcUser2CardImg1()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcUser2CardImg2()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcEbikeInvoiceImg()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getSsdwId()));
			ws.addCell(new Label(j1 += 1, i, ddcFlow.getVcTableName()));
			if (ddcFlow.getiTableId() != null) {
				ws.addCell(new Label(j1 += 1, i, ddcFlow.getiTableId() + ""));
			} else {
				ws.addCell(new Label(j1 += 1, i, ""));
			}

			i++;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDataService#createDdcHyxhBasb(jxl.write.WritableCellFormat
	 * , jxl.write.WritableCellFormat, jxl.write.WritableSheet)
	 */
	@Override
	public void createDdcHyxhBasb(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws)
			throws RowsExceededException, WriteException {
		Label label = new Label(0, 0, "ddc_hyxh_basb", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j = 0;
		ws.addCell(new Label(j, 2, "LSH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHZH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHSQPE", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQRQ", wcfFC2));

		ws.addCell(new Label(j += 1, 2, "BJJG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BJBZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BZJR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BJBM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BJRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SL_INDEX", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHMC", wcfFC2));
		List<DdcHyxhBasb> ddcHyxhBasbs = iDdcHyxhBasbDao.findByProperty(
				"synFlag", SystemConstants.SYSFLAG_UPDATE);
		int i = 3;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (DdcHyxhBasb ddcHyxhBasb : ddcHyxhBasbs) {
			int j1 = 0;
			ws.addCell(new Label(j1, i, ddcHyxhBasb.getLsh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getHyxhzh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getHyxhsqpe() + ""));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getBz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getSqr()));
			ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhBasb.getSqrq())));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getBjjg()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getBjbz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getBzjr()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getBjbm()));
			if (ddcHyxhBasb.getBjrq() != null) {
				ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhBasb
						.getBjrq())));
			} else {
				ws.addCell(new Label(j1 += 1, i, ""));
			}

			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getSynFlag()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getSlIndex() + ""));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhBasb.getHyxhmc()));
			i++;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IDataService#createDdcHyxhBase(jxl.write.WritableCellFormat
	 * , jxl.write.WritableCellFormat, jxl.write.WritableSheet)
	 */
	@Override
	public void createDdcHyxhBase(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws)
			throws RowsExceededException, WriteException {
		Label label = new Label(0, 0, "DDC_HYXH_BASE", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j = 0;
		ws.addCell(new Label(j, 2, "ID", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHZH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHMM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHMC", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHDZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHFZR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHFZRDH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHSJZPE", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CJR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CJRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CJBM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHLB", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "TOTALPE", wcfFC2));
		List<DdcHyxhBase> ddcHyxhBases = iDdcHyxhBaseDao.findByProperty(
				"synFlag", SystemConstants.SYSFLAG_ADD);
		List<DdcHyxhBase> ddcHyxhBases2 = iDdcHyxhBaseDao.findByProperty(
				"synFlag", SystemConstants.SYSFLAG_UPDATE);
		List<DdcHyxhBase> allDdcHyxhBases = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(ddcHyxhBases)) {
			allDdcHyxhBases.addAll(ddcHyxhBases);
		}
		if (CollectionUtils.isNotEmpty(ddcHyxhBases2)) {
			allDdcHyxhBases.addAll(ddcHyxhBases2);
		}

		int i = 3;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (CollectionUtils.isNotEmpty(allDdcHyxhBases)) {
			for (DdcHyxhBase ddcHyxhBase : allDdcHyxhBases) {
				int j1 = 0;
				ws.addCell(new Label(j1, i, String.valueOf(ddcHyxhBase.getId())));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhzh()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhmm()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhmc()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhdz()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhfzr()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhfzrdh()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhsjzpe()
						+ ""));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getBz()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getCjr()));
				if (ddcHyxhBase.getCjrq() != null) {
					ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhBase
							.getCjrq())));
				} else {
					ws.addCell(new Label(j1 += 1, i, null));
				}

				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getCjbm()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getHyxhlb()));
				ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getSynFlag()));
				if (ddcHyxhBase.getTotalPe() != null) {
					ws.addCell(new Label(j1 += 1, i, ddcHyxhBase.getTotalPe()
							+ ""));
				} else {
					ws.addCell(new Label(j1 += 1, i, ""));
				}

				i++;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.node.service.IEbikeService#createDdcHyxhSsdw(jxl.write.WritableCellFormat
	 * , jxl.write.WritableCellFormat, jxl.write.WritableSheet)
	 */
	@Override
	public void createDdcHyxhSsdw(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws)
			throws RowsExceededException, WriteException {
		List<DdcHyxhSsdw> ddcHyxhSsdws = iDdcHyxhSsdwDao.findByProperty(
				"synFlag", SystemConstants.SYSFLAG_UPDATE);
		Label label = new Label(0, 0, "ddc_hyxh_ssdw", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j = 0;
		ws.addCell(new Label(j, 2, "ID", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHZH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "DWMC", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "ZZJGDMZH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "ZSDZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "LXR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "LXDH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "ZT", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SHR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SHRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SHBM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "DWPE", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_PICPATH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "USERCODE", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "PASSWORD", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SHFLAG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "TOTALPE", wcfFC2));
		int i = 3;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (DdcHyxhSsdw ddcHyxhSsdw : ddcHyxhSsdws) {
			int j1 = 0;
			ws.addCell(new Label(j1, i, String.valueOf(ddcHyxhSsdw.getId())));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getHyxhzh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getDwmc()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getZzjgdmzh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getZsdz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getLxr()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getLxdh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getBz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getSqr()));
			ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhSsdw.getSqrq())));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getZt()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getShr()));
			if (ddcHyxhSsdw.getShrq() != null) {
				ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhSsdw
						.getShrq())));
			} else {
				ws.addCell(new Label(j1 += 1, i, null));
			}

			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getShbm()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getSynFlag()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getDwpe() + ""));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getVcPicPath()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getUserCode()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getPassWord()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getShFlag() + ""));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdw.getTotalPe() + ""));
			i++;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.node.service.IDataService#createDdcHyxhSsdwClSb(jxl.write.
	 * WritableCellFormat, jxl.write.WritableCellFormat,
	 * jxl.write.WritableSheet)
	 */
	@Override
	public void createDdcHyxhSsdwClSb(WritableCellFormat wcfFC,
			WritableCellFormat wcfFC2, WritableSheet ws)
			throws RowsExceededException, WriteException {
		List<DdcHyxhSsdwclsb> ddcHyxhSsdwclsbs = iDdcHyxhSsdwclsbDao
				.findByProperty("synFlag", SystemConstants.SYSFLAG_UPDATE);
		Label label = new Label(0, 0, "DDC_HYXH_SSDWCLSB", wcfFC);
		ws.mergeCells(0, 0, 6, 0);
		ws.addCell(label);
		int j = 0;
		ws.addCell(new Label(j, 2, "LSH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "HYXHZH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SSDWID", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CPHM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "PPXH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "CYSY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "DJH", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "JTZZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "JSRXM1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "XB1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SFZMHM1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "LXDH1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "JSRXM2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "XB2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SFZMHM2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "LXDH2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "XSQY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "BZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLZL", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLYJ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLBZ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLR", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLRQ", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SLBM", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SYN_FLAG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SQIP", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER1IMG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER2IMG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_EBIKE_IMG", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "TBYY", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "SL_INDEX", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER1_CARDIMG1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER1_CARDIMG2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER2_CARDIMG1", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_USER2_CARDIMG2", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "ENABLE", wcfFC2));
		ws.addCell(new Label(j += 1, 2, "VC_EBIKE_INVOICE", wcfFC2));
		int i = 3;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (DdcHyxhSsdwclsb ddcHyxhSsdwclsb : ddcHyxhSsdwclsbs) {
			int j1 = 0;
			ws.addCell(new Label(j1, i, ddcHyxhSsdwclsb.getLsh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getHyxhzh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSsdwId()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getCphm()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getPpxh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getCysy()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getDjh()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getJtzz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getJsrxm1()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getXb1()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSfzmhm1()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getLxdh1()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getJsrxm2()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getXb2()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSfzmhm2()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getLxdh2()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getXsqy()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getBz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSqr()));
			if (ddcHyxhSsdwclsb.getSqrq() != null) {
				ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhSsdwclsb
						.getSqrq())));
			} else {
				ws.addCell(new Label(j1 += 1, i, null));
			}

			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSlzl()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSlyj()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSlbz()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSlr()));
			if (ddcHyxhSsdwclsb.getSlrq() != null) {
				ws.addCell(new Label(j1 += 1, i, sdf.format(ddcHyxhSsdwclsb
						.getSlrq())));
			} else {
				ws.addCell(new Label(j1 += 1, i, null));
			}

			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSlbm()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSynFlag()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSqip()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getVcUser1Img()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getVcUser2Img()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getVcEbikeImg()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getTbyy()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getSlIndex() + ""));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb
					.getVcUser1CardImg1()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb
					.getVcUser1CardImg2()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb
					.getVcUser2CardImg1()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb
					.getVcUser2CardImg2()));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb.getnEnable() + ""));
			ws.addCell(new Label(j1 += 1, i, ddcHyxhSsdwclsb
					.getVcEbikeInvoiceImg()));
			i++;
		}

	}
}