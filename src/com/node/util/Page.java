package com.node.util;

/**
 * 分页参数封装类
 */
@SuppressWarnings( "unused" )
public class Page
{
	private int currPage = 1; // 当前页
	private int recordCountperPage = 10; // 每页显示的行数
	private int firstResult = 0; // 开始行
	// private int maxResult; // 最大行
	private int recordCount; // 总条数
	private int pageCount; // 总页数
	private String sort;// 排序的字段
	private String order;// desc,dsc
	
	public Page()
	{}
	
	public int getCurrPage()
	{
		if ( currPage <= 0 )
			currPage = 1;
		return currPage;
	}
	
	public void setCurrPage( int currPage )
	{
		this.currPage = currPage;
	}
	
	public int getRecordCountperPage()
	{
		return recordCountperPage;
	}
	
	public void setRecordCountperPage( int recordCountperPage )
	{
		this.recordCountperPage = recordCountperPage;
	}
	
	public int getRecordCount()
	{
		return recordCount;
	}
	
	public void setRecordCount( int recordCount )
	{
		this.recordCount = recordCount;
	}
	
	public int getFirstResult()
	{
		return ( currPage - 1 ) * recordCountperPage;
	}
	
	public int getPageCount()
	{
		return ( recordCount + ( recordCountperPage - 1 ) ) / recordCountperPage;
	}
	
	public String getSort()
	{
		return sort;
	}
	
	public void setSort( String sort )
	{
		this.sort = sort;
	}
	
	public String getOrder()
	{
		return order;
	}
	
	public void setOrder( String order )
	{
		this.order = order;
	}
	
	public void setPageCount( int pageCount )
	{
		this.pageCount = pageCount;
	}
	
}
