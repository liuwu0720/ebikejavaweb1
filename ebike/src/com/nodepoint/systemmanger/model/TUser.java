package com.nodepoint.systemmanger.model;

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
 * 
	 * 类描述：
 	 * @version: 1.0
	 * @author: liuwu
	 * @version: 2016年2月27日 下午3:16:39
 */
@Entity
@Table( name = "T_USER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TUser implements Serializable
{
	
	private static final long serialVersionUID = 1055427704110192984L;
	// Fields	
	private Integer id;
	private Integer iArchiveType;// 档案类型id (1:内部；2：分供方；3：司机；4：金融公司；5：4S店)
	private Integer iArchive;// 档案id
	private String vcUsername;// 用户名
	private String vcAccount;// 帐号
	private String vcPassword;// 密码
	private Integer nEnable;// 是否有效（0有效，1无效)
	private Date dtAddtime;// 添加时间

	
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

	/**
	 * @return iArchiveType : return the property iArchiveType.
	 */
	@Column( name = "I_ARCHIVE_TYPE" , precision = 22 , scale = 0 )
	public Integer getiArchiveType() {
		return iArchiveType;
	}

	/**
	 * @param iArchiveType : set the property iArchiveType.
	 */
	public void setiArchiveType(Integer iArchiveType) {
		this.iArchiveType = iArchiveType;
	}

	/**
	 * @return nEnable : return the property nEnable.
	 */
	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 )
	public Integer getnEnable() {
		return nEnable;
	}

	/**
	 * @param nEnable : set the property nEnable.
	 */
	public void setnEnable(Integer nEnable) {
		this.nEnable = nEnable;
	}
	
	
}