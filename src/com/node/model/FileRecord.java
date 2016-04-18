/**
 * 文件名：FileRecord.java
 * 版本信息：Version 1.0
 * 日期：2016年4月14日
 * Copyright 结点科技 Corporation 2016 
 * 版权所有
 */
package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 类描述：文件记录表
 * 
 * @version: 1.0
 * @author: liuwu
 * @version: 2016年4月14日 下午4:41:34
 */
@Entity
@Table(name = "FILERECORD")
public class FileRecord implements java.io.Serializable {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 6014968187060044491L;
	private Long id;
	private String fileName;
	private String filePath;
	private Date dateTime;
	private Integer flag;// 0-导入 1-导出

	@Id
	@SequenceGenerator(name = "FILERECORD", sequenceName = "SEQ_FILERECORD", allocationSize = 1)
	@GeneratedValue(strategy = SEQUENCE, generator = "FILERECORD")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            : set the property id.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FILENAME", length = 50)
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            : set the property fileName.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATETIIME", length = 7)
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * @param dateTime
	 *            : set the property dateTime.
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "FLAG")
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            : set the property flag.
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "FILEPATH", length = 100)
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            : set the property filePath.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
