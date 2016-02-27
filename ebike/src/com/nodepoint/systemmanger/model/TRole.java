package com.nodepoint.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 角色表 TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ROLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TRole implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 7698657067625537210L;
	private Integer id;
	private String vcRoleName;
	private Integer nEnable;
	private String vcDesc;
	private Integer IArchiveType;
	private Integer iLeader;
	
	// Constructors
	
	/** default constructor */
	public TRole()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "ROLEID" , sequenceName = "S_T_ROLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "ROLEID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_ROLE_NAME" , length = 20 )
	public String getVcRoleName()
	{
		return this.vcRoleName;
	}
	
	public void setVcRoleName( String vcRoleName )
	{
		this.vcRoleName = vcRoleName;
	}
	
	
	
	@Column( name = "VC_DESC" , length = 50 )
	public String getVcDesc()
	{
		return this.vcDesc;
	}
	
	public void setVcDesc( String vcDesc )
	{
		this.vcDesc = vcDesc;
	}
	
	
	@Column( name = "I_ARCHIVE_TYPE" , precision = 22 , scale = 0 )
	public Integer getIArchiveType()
	{
		return IArchiveType;
	}
	
	public void setIArchiveType( Integer iArchiveType )
	{
		IArchiveType = iArchiveType;
	}
	
	@Column( name = "N_LEADER" , precision = 22 , scale = 0 )
	public Integer getiLeader()
	{
		return iLeader;
	}
	
	public void setiLeader( Integer iLeader )
	{
		this.iLeader = iLeader;
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