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
	private static final Logger logger = Logger.getLogger("自动任务处理");
	@Autowired
	ITaskService iTaskService;

	/**
	 * 
	 * 方法描述：下午导数据前改变一次
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
	
	
	@Scheduled(fixedRate=1000 *60*30)
	public void updateXjFlag(){
		String sql = "update  DDC_DRIVER t set t.user_status=0,t.xj_flag=null,t.xj_rq=null,t.xj_msg=null   where t.xj_flag =-99";
		int row = iTaskService.updateBySql2(sql);
		logger.warn("修改了条数："+row);
	}
	
	
	/**
	 * 
	  * 方法描述： 外网重复导入的图片重新写入BLOB，上午一次 下午一次
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年7月13日 上午9:34:39
	 */
	@Scheduled(fixedRate=1000 *60*180)
	public void updateDriverImgBlob() {
		iTaskService.updateDriverImgBlob();
	}
	
	
	@Scheduled(fixedRate=1000 *60*180)
	public void autoTask2() {
		iTaskService.updateDdcDriverImg();
	}


	/**
	 * 
	 * 方法描述：上午导数据前改变一次
	 * 
	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年7月11日 下午7:41:33
	 */
	@Scheduled(cron = "0 52 06 * * *?")
	public void updateDriverState() {
		String sql = "update  DDC_DRIVER t set t.user_status=2  where t.xj_flag is not null and t.xj_flag != -99";
		iTaskService.updateBySql2(sql);

	}


	@Scheduled(fixedRate=1000 *60*60)
	public void updateDriverState4() {
		String sql2 = " update DDC_DRIVER t set t.syn_flag='ADD' where t.xj_rq>(select sysdate - interval '10' day from dual ) and t.syn_flag is not null  and t.xj_flag != -99";
		iTaskService.updateBySql2(sql2);
	}

}
