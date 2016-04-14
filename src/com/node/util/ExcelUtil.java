package com.node.util;

import java.io.Console;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 数据导出到Excel
 * @author GuoZhiLong
 * @date 2014年12月25日 上午10:50:53
 * 
 */
public class ExcelUtil {
	/**
	  * 方法描述： 
	  * @return 
	  * @version: 1.0
	  * @author: danielzou
	  * @version: 2016年4月11日 下午4:03:17
	 * @param keysArr 
	  */
	public static HSSFWorkbook getWorkBook(String[] headRowArr, JSONArray jsonArray, String[] keysArr) {
		HSSFWorkbook wb = new HSSFWorkbook();                  // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("电动车档案信息表");         // 第三步，在sheet中添加表头第0行 
        HSSFRow row = sheet.createRow((int) 0);             // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFCell cell = row.createCell((int) 0);  
        for(int i=0;i<headRowArr.length;i++) {
        	cell.setCellValue(headRowArr[i]);  
            cell.setCellStyle(style);  
            cell = row.createCell((int) i + 1);  
        }
       
        for (int i = 0; i < jsonArray.size(); i++)  
        {  
        	JSONObject currentRow = (JSONObject) jsonArray.get(i);
            row = sheet.createRow((int) i + 1);                  // 第四步，创建单元格，并设置值
            for(int j=0;j<keysArr.length;j++){
    			System.out.println(keysArr[i]);
    			row.createCell((int) j).setCellValue((String) currentRow.get(keysArr[j]));
    		}
        } 
        adjustColumnSize(sheet,6);
		return wb;
	}
	
	/*自动调整列宽*/
	private static void adjustColumnSize(HSSFSheet sheet,
			int colNum) {
		for (int i = 0; i < colNum + 1; i++) {
			sheet.autoSizeColumn((int) i); //调整宽度
		}
	}
}