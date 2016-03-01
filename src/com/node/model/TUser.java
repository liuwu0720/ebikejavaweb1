package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
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
 * 用户表 TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_USER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TUser implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1055427704110192984L;
	// Fields
	
	private Integer id;
	private Integer IArchiveType;// 档案类型id (1:内部；2：分供方；3：司机；4：金融公司；5：4S店)
	private String vcImgpath;;// 图像路径
	private Integer iArchive;// 档案id
	private String vcUsername;// 用户名
	private String vcAccount;// 帐号
	private String vcPassword;// 密码
	private Integer NEnable;// 是否有效（0有效，1无效)
	private Date dtAddtime;// 添加时间
	private Integer NIntegral;// 积分
	private Integer NApplyResource;// 是否申请权限(0:是申请，1：没申请 2是否审批)
	private String vcApplyNote;// 权限备注
	private String vcDeviceTokens;// 用户设备唯一编号
	private String vcPhone;
	private String vcEmail;
	private Integer NStatus;// 用户当前状态
	
	// Constructors
	
	/** default constructor */
	public TUser()
	{}
	
	/** minimal constructor */
	public TUser( String vcAccount )
	{
		this.vcAccount = vcAccount;
	}
	
	// Property accessors
	@Id
	@SequenceGenerator( name = "USERID" , sequenceName = "S_T_USER" , allocationSize = 1 )
	@GeneratedValue( strategy = SEQUENCE , generator = "USERID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_ARCHIVE_TYPE" , precision = 22 , scale = 0 )
	public Integer getIArchiveType()
	{
		return this.IArchiveType;
	}
	
	public void setIArchiveType( Integer TArchiveType )
	{
		this.IArchiveType = TArchiveType;
	}
	
	@Column( name = "VC_USERNAME" , length = 20 )
	public String getVcUsername()
	{
		return this.vcUsername;
	}
	
	public void setVcUsername( String vcUsername )
	{
		this.vcUsername = vcUsername;
	}
	
	@Column( name = "VC_ACCOUNT" , nullable = false , length = 32 )
	public String getVcAccount()
	{
		return this.vcAccount;
	}
	
	public void setVcAccount( String vcAccount )
	{
		this.vcAccount = vcAccount;
	}
	
	@Column( name = "VC_PASSWORD" , length = 32 )
	public String getVcPassword()
	{
		return this.vcPassword;
	}
	
	public void setVcPassword( String vcPassword )
	{
		this.vcPassword = vcPassword;
	}
	
	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_ADDTIME" , length = 7 )
	public Date getDtAddtime()
	{
		return this.dtAddtime;
	}
	
	public void setDtAddtime( Date dtAddtime )
	{
		this.dtAddtime = dtAddtime;
	}
	
	@Column( name = "I_ARCHIVE" , precision = 22 , scale = 0 )
	public Integer getiArchive()
	{
		return iArchive;
	}
	
	public void setiArchive( Integer iArchive )
	{
		this.iArchive = iArchive;
	}
	
	@Column( name = "N_INTEGRAL" )
	public Integer getNIntegral()
	{
		return NIntegral;
	}
	
	public void setNIntegral( Integer nIntegral )
	{
		NIntegral = nIntegral;
	}
	
	@Column( name = "N_APPLY_RESOURCE" )
	public Integer getNApplyResource()
	{
		return NApplyResource;
	}
	
	public void setNApplyResource( Integer nApplyResource )
	{
		NApplyResource = nApplyResource;
	}
	
	@Column( name = "VC_APPLY_NOTE" )
	public String getVcApplyNote()
	{
		return vcApplyNote;
	}
	
	public void setVcApplyNote( String vcApplyNote )
	{
		this.vcApplyNote = vcApplyNote;
	}
	
	@Column( name = "VC_DEVICETOKEN" )
	public String getVcDeviceTokens()
	{
		return vcDeviceTokens;
	}
	
	public void setVcDeviceTokens( String vcDeviceTokens )
	{
		this.vcDeviceTokens = vcDeviceTokens;
	}
	
	/**
	 * @return the vcPhone
	 */
	@Column( name = "VC_PHONE" )
	public String getVcPhone()
	{
		return vcPhone;
	}
	
	/**
	 * @param vcPhone
	 *            the vcPhone to set
	 */
	public void setVcPhone( String vcPhone )
	{
		this.vcPhone = vcPhone;
	}
	
	/**
	 * @return the vcEmail
	 */
	@Column( name = "VC_EMAIL" )
	public String getVcEmail()
	{
		return vcEmail;
	}
	
	/**
	 * @param vcEmail
	 *            the vcEmail to set
	 */
	public void setVcEmail( String vcEmail )
	{
		this.vcEmail = vcEmail;
	}
	
	@Column( name = "N_STATUS" )
	public Integer getNStatus()
	{
		return NStatus;
	}
	
	public void setNStatus( Integer nStatus )
	{
		NStatus = nStatus;
	}
	
	@Column( name = "VC_IMGPATH" , length = 100 )
	public String getVcImgpath()
	{
		return vcImgpath;
	}
	
	public void setVcImgpath( String vcImgpath )
	{
		this.vcImgpath = vcImgpath;
	}
	
}