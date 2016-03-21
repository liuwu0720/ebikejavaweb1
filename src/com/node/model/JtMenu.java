package com.node.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * JtMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "JT_MENU")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class JtMenu implements java.io.Serializable, Comparable<Object> {

	/* serialVersionUID: serialVersionUID */
	private static final long serialVersionUID = 2250376083483822709L;
	// Fields

	private Integer id;
	private String vcMenu;
	private String vcUrl;
	private Integer nEnable;
	private Integer nSort;
	private String vcIcon;
	private Integer iParent;
	private String vcParent;
	private String vcDesc;
	private List<JtMenu> subJtMenus = new ArrayList<JtMenu>();

	// Constructors

	/** default constructor */
	public JtMenu() {
	}

	/** minimal constructor */
	public JtMenu(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public JtMenu(Integer id, String vcMenu, String vcUrl, Integer nEnable,
			Integer nSort, String vcIcon, Integer iParent, String vcParent) {
		this.id = id;
		this.vcMenu = vcMenu;
		this.vcUrl = vcUrl;
		this.nEnable = nEnable;
		this.nSort = nSort;
		this.vcIcon = vcIcon;
		this.iParent = iParent;
		this.vcParent = vcParent;
	}

	@SequenceGenerator(name = "JT_MENU", sequenceName = "SEQ_JT_MENU", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "JT_MENU")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "VC_MENU", length = 20)
	public String getVcMenu() {
		return this.vcMenu;
	}

	public void setVcMenu(String vcMenu) {
		this.vcMenu = vcMenu;
	}

	@Column(name = "VC_URL", length = 50)
	public String getVcUrl() {
		return this.vcUrl;
	}

	public void setVcUrl(String vcUrl) {
		this.vcUrl = vcUrl;
	}

	@Column(name = "N_ENABLE", precision = 0)
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

	@Column(name = "N_SORT", precision = 0)
	public Integer getnSort() {
		return this.nSort;
	}

	public void setnSort(Integer nSort) {
		this.nSort = nSort;
	}

	@Column(name = "VC_ICON", length = 20)
	public String getVcIcon() {
		return this.vcIcon;
	}

	public void setVcIcon(String vcIcon) {
		this.vcIcon = vcIcon;
	}

	@Column(name = "I_PARENT", precision = 0)
	public Integer getiParent() {
		return this.iParent;
	}

	public void setiParent(Integer iParent) {
		this.iParent = iParent;
	}

	@Column(name = "VC_PARENT", length = 50)
	public String getVcParent() {
		return this.vcParent;
	}

	public void setVcParent(String vcParent) {
		this.vcParent = vcParent;
	}

	@Transient
	public List<JtMenu> getSubJtMenus() {
		return subJtMenus;
	}

	/**
	 * @param subJtMenus
	 *            : set the property subJtMenus.
	 */
	public void setSubJtMenus(List<JtMenu> subJtMenus) {
		this.subJtMenus = subJtMenus;
	}

	@Column(name = "VC_DESC", length = 200)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		if (this.getnSort() > ((JtMenu) o).getnSort()) {
			return 1;
		} else {
			return -1;
		}
	}

}