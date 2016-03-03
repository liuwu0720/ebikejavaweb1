package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 资源表 TResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RESOURCE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TResource implements java.io.Serializable, Comparable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -991538113738481102L;
	private Integer id;
	private String vcResourceName;
	private Integer nType;
	private String vcUrl;
	private Integer nEnable;
	private Integer nRoot;
	private Integer nLeaf;
	private String vcDesc;
	private Integer iParent;
	private Integer nSort;
	private String vcIcon;

	// Constructors

	/** default constructor */
	public TResource() {
	}

	// Property accessors
	@SequenceGenerator(name = "RESOURCE", sequenceName = "S_RESOURCE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "RESOURCE")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "VC_RESOURCE_NAME", length = 40)
	public String getVcResourceName() {
		return vcResourceName;
	}

	/**
	 * @param vcResourceName
	 *            : set the property vcResourceName.
	 */
	public void setVcResourceName(String vcResourceName) {
		this.vcResourceName = vcResourceName;
	}

	@Column(name = "N_TYPE", precision = 22, scale = 0)
	public Integer getnType() {
		return nType;
	}

	/**
	 * @param nType
	 *            : set the property nType.
	 */
	public void setnType(Integer nType) {
		this.nType = nType;
	}

	@Column(name = "VC_URL", length = 100)
	public String getVcUrl() {
		return vcUrl;
	}

	/**
	 * @param vcUrl
	 *            : set the property vcUrl.
	 */
	public void setVcUrl(String vcUrl) {
		this.vcUrl = vcUrl;
	}

	@Column(name = "N_ENABLE", precision = 22, scale = 0)
	public Integer getnEnable() {
		return nEnable;
	}

	/**
	 * @param nEnable
	 *            : set the property nEnable.
	 */
	public void setnEnable(Integer nEnable) {
		this.nEnable = nEnable;
	}

	@Column(name = "N_ROOT", precision = 22, scale = 0)
	public Integer getnRoot() {
		return nRoot;
	}

	/**
	 * @param nRoot
	 *            : set the property nRoot.
	 */
	public void setnRoot(Integer nRoot) {
		this.nRoot = nRoot;
	}

	@Column(name = "N_LEAF", precision = 22, scale = 0)
	public Integer getnLeaf() {
		return nLeaf;
	}

	/**
	 * @param nLeaf
	 *            : set the property nLeaf.
	 */
	public void setnLeaf(Integer nLeaf) {
		this.nLeaf = nLeaf;
	}

	@Column(name = "VC_DESC", length = 100)
	public String getVcDesc() {
		return vcDesc;
	}

	/**
	 * @param vcDesc
	 *            : set the property vcDesc.
	 */
	public void setVcDesc(String vcDesc) {
		this.vcDesc = vcDesc;
	}

	@Column(name = "I_PARENT", precision = 22, scale = 0)
	public Integer getiParent() {
		return iParent;
	}

	/**
	 * @param iParent
	 *            : set the property iParent.
	 */
	public void setiParent(Integer iParent) {
		this.iParent = iParent;
	}

	@Column(name = "N_SORT", precision = 22, scale = 0)
	public Integer getnSort() {
		return nSort;
	}

	/**
	 * @param nSort
	 *            : set the property nSort.
	 */
	public void setnSort(Integer nSort) {
		this.nSort = nSort;
	}

	@Column(name = "VC_ICON")
	public String getVcIcon() {
		return vcIcon;
	}

	/**
	 * @param vcIcon
	 *            : set the property vcIcon.
	 */
	public void setVcIcon(String vcIcon) {
		this.vcIcon = vcIcon;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		if (this.getId() > ((TResource) o).getId()) {
			return 1;
		} else {
			return -1;
		}
	}

}