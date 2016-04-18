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
import java.util.Date;
import java.util.List;

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
			Sheet ssdwClsbSheet = wb.getSheetAt(5);// DDC_HYXH_SSDWCLSB 外网只新增数据
			saveSsdwClsb(ssdwClsbSheet);

			/**
			 * ddc_hyxh_ssdw:外网新增或修改数据 新增时验证usercode唯一性 修改时有可能是先新增再修改的，要判断
			 */
			Sheet ssdwSheet = wb.getSheetAt(5);
			updateSaveSsdwSheet(ssdwSheet);

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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i <= ssdwClsbSheet.getLastRowNum(); i++) {
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
					ddcHyxhSsdwclsb
							.setSlrq(sdf.parse(row.getCell(j += 1) + ""));
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
					iDdcHyxhSsdwclsbDao.save(ddcHyxhSsdwclsb);
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
	private void updateSaveSsdwSheet(Sheet ssdwSheet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i <= ssdwSheet.getLastRowNum(); i++) {
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
					ddcHyxhSsdw.setShrq(sdf.parse(row.getCell(j += 1) + ""));
				} else {
					ddcHyxhSsdw.setShrq(null);
				}
				ddcHyxhSsdw.setShbm(row.getCell(j += 1) + "");
				ddcHyxhSsdw.setSynFlag(row.getCell(j += 1) + "_W");
				ddcHyxhSsdw.setDwpe(Integer.parseInt(row.getCell(j += 1) + ""));
				ddcHyxhSsdw.setVcPicPath(row.getCell(j += 1) + "");
				ddcHyxhSsdw.setUserCode(row.getCell(j += 1) + "");
				ddcHyxhSsdw.setPassWord(row.getCell(j += 1) + "");
				ddcHyxhSsdw
						.setShFlag(Integer.parseInt(row.getCell(j += 1) + ""));
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
					if (newDdcHyxhSsdw != null) {
						iDdcHyxhSsdwDao.save(ddcHyxhSsdw);
					} else {
						iDdcHyxhSsdwDao.updateCleanBefore(ddcHyxhSsdw);
					}
				}
			}
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i <= hyxhBaseSheet.getLastRowNum(); i++) {
			Row row = hyxhBaseSheet.getRow(i);
			if (row != null) {
				int j = 0;
				DdcHyxhBase ddcHyxhBase = new DdcHyxhBase();
				ddcHyxhBase.setId(Long.parseLong(row.getCell(j) + ""));
				ddcHyxhBase.setHyxhzh(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhmm(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhmc(row.getCell(j += 1) + "");
				ddcHyxhBase.setHyxhdz(row.getCell(j += 1) + "");
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
				ddcHyxhBase.setTotalPe(Integer.parseInt(row.getCell(j += 1)
						+ ""));
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
				ddcHyxhBasb.setHyxhbcsjpe(Integer.parseInt(row.getCell(j += 1)
						+ ""));
				ddcHyxhBasb.setBjjg(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBjbz(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBjbz(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBzjr(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBjbm(row.getCell(j += 1) + "");
				ddcHyxhBasb.setBjrq(sdf.parse(row.getCell(j += 1) + ""));
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
					iDdcHyxhBasbDao.save(ddcHyxhBasb);
				}
				iDdcHyxhBasbLogDao.save(ddcHyxhBasbLog);
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
	private void saveFlow(Sheet flowSheet) throws ParseException,
			IllegalAccessException, InvocationTargetException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

				iDdcFlowLogDao.save(ddcFlowLog);
			}
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
					iDdcApprovalUserDao.save(ddcApproveUser);
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
	private void updateDaxxb(Sheet sheet) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
				try {
					iDdcDaxxbDao.update(daxxb);
					saveDaxxbLog(daxxb);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

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
}