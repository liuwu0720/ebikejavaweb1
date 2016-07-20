/**
 * 文件名：AutoRunTask.java
 * 版本信息：Version 1.0
 * 日期：2016年6月13日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.node.service.ITaskService;

/**
 * 类描述：
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年6月13日 下午8:50:07
 */
@Component("scheduledTaskManager")
public class AutoRunTask {
	private static final Logger logger = Logger.getLogger("内外网数据同步");
	@Autowired
	ITaskService iTaskService;

	/**
	 * 
	 * 方法描述：当天晚上开始写入BLOB
	 * 
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年6月13日 下午8:53:35
	 */
	@Scheduled(cron = "0 50 16 * * *?")
	public void autoTask() {
		String sql = "update  DDC_DRIVER t set t.user_status=2  where t.xj_flag is not null  and t.xj_flag != -99";
		iTaskService.updateBySql(sql);
	}
	
	@Scheduled(cron = "0 35 18 * * *?")
	public void autoTaskN() {
		String sql = "update  DDC_DRIVER t set t.user_status=0,t.xj_flag=null,t.xj_rq=null,t.xj_msg=null   where t.xj_flag =-99";
		iTaskService.updateBySql(sql);
	}
	
	@Scheduled(fixedRate=1000 *60*30)
	public void updateXjFlag(){
		String sql = "update  DDC_DRIVER t set t.user_status=0,t.xj_flag=null,t.xj_rq=null,t.xj_msg=null   where t.xj_flag =-99";
		int row = iTaskService.updateBySql2(sql);
		logger.warn("修改了条数："+row);
	}
	
	@Scheduled(fixedRate=1000 *60*20)
	public void updateXjFlag2(){
		String sql = "update  DDC_DRIVER t set t.user_status=0,t.xj_flag=null,t.xj_rq=null,t.xj_msg=null   where t.xj_msg ='图片信息不能为空'";
		int row = iTaskService.updateBySql2(sql);
		logger.warn("修改了条数2："+row);
	}
	
	
	/**
	 * 
	  * 方法描述： 外网重复导入的图片重新写入BLOB
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年7月13日 上午9:34:39
	 */
	@Scheduled(cron = "0 30 18 * * *?")
	public void updateDriverImgBlob() {
		iTaskService.updateDriverImgBlob();
	}
	
	@Scheduled(fixedRate=1000 *60*60)
	public void autoTask2() {
		iTaskService.updateDdcDriverImg();
	}

	/**
	 * 
	 * 方法描述：下午3点前修改状态 分步进行
	 * 
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年7月11日 下午7:41:33
	 */
	@Scheduled(cron = "0 52 15 * * *?")
	public void updateDriverState() {
		String sql = "update  DDC_DRIVER t set t.user_status=2  where t.xj_flag is not null and t.xj_flag != -99";
		iTaskService.updateBySql(sql);

	}

	@Scheduled(cron = "0 53 15 * * *?")
	public void updateDriverState2() {
		iTaskService.updateDdcDriverImg();
	}

	@Scheduled(cron = "0 12 16 * * *?")
	public void updateDriverState3() {
		String sql = "update  DDC_DRIVER t set t.user_status=2  where t.xj_flag is not null and t.xj_flag != -99";
		iTaskService.updateBySql(sql);
	}

	@Scheduled(cron = "0 15 16 * * *?")
	public void updateDriverState4() {
		String sql2 = " update DDC_DRIVER t set t.syn_flag='ADD' where t.xj_rq>(select sysdate - interval '10' day from dual ) and t.syn_flag is not null  and t.xj_flag != -99";
		iTaskService.updateBySql(sql2);
	}

}
