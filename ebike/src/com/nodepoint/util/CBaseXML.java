/*
 * CBaseXML.java Created on 2006年3月26日, 下午4:13
 */

package com.nodepoint.util;

/**
 * 
 * @author xgh
 */

import java.io.ByteArrayInputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class CBaseXML
{
	private Document doc = null; // 文档对象
	private Element root = null;// 根结点
	
	/** Creates a new instance of CBaseXML */
	public CBaseXML()
	{	
		
	}
	
	private void initMember()
	{
		root = new Element( "root" );
		doc = new Document( root );
	}
	
	private void initMember( int iTotal )
	{
		root = new Element( "root" );
		root.setAttribute( "iTotal" , iTotal + "" );
		doc = new Document( root );
	}
	
	/**
	 * 此函数只执行RS转换为XML字符串的操作。
	 * 
	 * @param RS
	 *            ResultSet类型。
	 * @throws Exception
	 *             抛出一个Exception异常。
	 * @return java.lang.String类型。返回转换的XML结果。
	 * @since V1.0.0
	 * @creator xiegh 2004年11月1日
	 */
	
	public String transRstoXML( java.sql.ResultSet rs )
	{
		initMember();
		String result = "";
		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			// 将记录集放入XML中
			Element rows = new Element( "datas" );
			// rows.setAttribute("status","1"); 不能加这个属性，否则数据不能绑定
			root.addContent( "\n  " );
			root.addContent( rows );
			root.addContent( "\n  " );
			String strDate;
			while ( rs.next() )
			{
				Element row = new Element( "data" );
				
				for ( int loop = 1 ; loop <= numberOfColumns ; loop++ )
				{
					String colName = "";
					
					int dataType = 0;
					colName = rsmd.getColumnName( loop ).toUpperCase();
					if ( colName.equals( "DCTOTAL" ) )
					{
						
						int ddd = 0;
					}
					dataType = rsmd.getColumnType( loop );
					switch ( dataType )
						{
							case java.sql.Types.BLOB :
								byte[] blobValue = rs.getBlob( loop ) == null ? null : rs
								        .getBlob( loop ).getBytes( 1 ,
								                ( int ) rs.getBlob( loop ).length() );
								if ( blobValue == null )
								{
									Element blobElement = new Element( colName );
									row.addContent( blobElement );
								}
								else
								{
									Element blobElement = new Element( colName );
									blobElement.setText( blobValue.toString() );
									row.addContent( blobElement );
								}
								break;
							case java.sql.Types.VARCHAR :
								String varcharValue = rs.getString( loop ) == null ? null
								        : rs.getString( loop );
								
								if ( varcharValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , varcharValue );
								}
								break;
							case java.sql.Types.DATE :
								
								java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
								        "yyyy-MM-dd" );
								java.text.SimpleDateFormat formatter1 = new java.text.SimpleDateFormat(
								        "HH:mm" );
								java.sql.Date dateValue = rs.getDate( loop );
								java.sql.Time timeValue1 = rs.getTime( loop );
								
								if ( dateValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									strDate = formatter.format( dateValue );
									String strTime = formatter1.format( timeValue1 );
									
									if ( Integer.parseInt( strTime.substring( 0 , 2 ) ) > 0
									        || Integer.parseInt( strTime
									                .substring( 3 , 5 ) ) > 0 )
									{
										strDate = strDate + " " + strTime;
									}
									row.setAttribute( colName , strDate );
								}
								break;
							case java.sql.Types.TIMESTAMP :
								java.sql.Timestamp timestampValue = rs
								        .getTimestamp( loop );
								if ( timestampValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , timestampValue.toString() );
								}
								break;
							case java.sql.Types.INTEGER :
								
							case java.sql.Types.NUMERIC :
							case java.sql.Types.DECIMAL :
								// 如果小数位不为0，则做浮点数处理
								double decimailValue = rs.getDouble( loop );
								Float tmp_float = new Float( decimailValue );
								if ( tmp_float.equals( null ) )
								{
									row.setAttribute( colName , "0" );
								}
								else
								{
									// String temp = rs.getString(loop);
									String temp = String.valueOf( decimailValue );
									String[] aValue = temp.split( "\\." , 0 );
									if ( aValue.length == 1 )
									{// 无小数点
										row.setAttribute( colName , temp );
										break;
									}
									if ( Float.parseFloat( aValue[1] ) == 0 )
									{// 无小数点
										row.setAttribute( colName , aValue[0] );
									}
									else
									{
										
										String strTemp = aValue[1] + "00";
										if ( aValue[1].length() > 2 )
										{
											row.setAttribute( colName , aValue[0] + "."
											        + strTemp.substring( 0 , 2 ) );
										}
										else
											row.setAttribute( colName , temp );
										
									}
									
								}
								break;
							
							default :
								String defaultValue = rs.getString( loop ) == null ? null
								        : rs.getString( loop );
								if ( defaultValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , defaultValue );
								}
								break;
						}
					
				}
				// System.out.print(iCount);
				rows.addContent( "\n  " );
				rows.addContent( row );
				rows.addContent( "\n  " );
			}
			// rows.setAttribute("status","0");
			// rs.close();
			result = getXML();
		}
		catch ( SQLException se )
		{
			System.out.println( se.getMessage() );
			result = "";
		}
		return result;
	}
	
	public String transRstoXML( java.sql.ResultSet rs , int iTotal )
	{
		initMember( iTotal );
		String result = "";
		try
		{
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			// 将记录集放入XML中
			Element rows = new Element( "datas" );
			// rows.setAttribute("status","1"); 不能加这个属性，否则数据不能绑定
			root.addContent( "\n  " );
			root.addContent( rows );
			root.addContent( "\n  " );
			String strDate = "";
			while ( rs.next() )
			{
				Element row = new Element( "data" );
				
				for ( int loop = 1 ; loop <= numberOfColumns ; loop++ )
				{
					String colName = "";
					int dataType = 0;
					colName = rsmd.getColumnName( loop );
					dataType = rsmd.getColumnType( loop );
					switch ( dataType )
						{
							case java.sql.Types.BLOB :
								byte[] blobValue = rs.getBlob( loop ) == null ? null : rs
								        .getBlob( loop ).getBytes( 1 ,
								                ( int ) rs.getBlob( loop ).length() );
								if ( blobValue == null )
								{
									Element blobElement = new Element( colName );
									row.addContent( blobElement );
								}
								else
								{
									Element blobElement = new Element( colName );
									blobElement.setText( blobValue.toString() );
									row.addContent( blobElement );
								}
								break;
							case java.sql.Types.VARCHAR :
								String varcharValue = rs.getString( loop ) == null ? null
								        : rs.getString( loop );
								if ( varcharValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , varcharValue );
								}
								break;
							case java.sql.Types.DATE :
								java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
								        "yyyy-MM-dd" );
								java.text.SimpleDateFormat formatter1 = new java.text.SimpleDateFormat(
								        "HH:mm" );
								java.sql.Date dateValue = rs.getDate( loop );
								java.sql.Time timeValue1 = rs.getTime( loop );
								
								if ( dateValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									strDate = formatter.format( dateValue );
									String strTime = formatter1.format( timeValue1 );
									
									if ( Integer.parseInt( strTime.substring( 0 , 2 ) ) > 0
									        || Integer.parseInt( strTime
									                .substring( 3 , 5 ) ) > 0 )
									{
										strDate = strDate + " " + strTime;
									}
									row.setAttribute( colName , strDate );
								}
								break;
							case java.sql.Types.TIME :
								java.sql.Time timeValue = rs.getTime( loop );
								if ( timeValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , timeValue.toString() );
								}
								break;
							case java.sql.Types.TIMESTAMP :
								java.sql.Timestamp timestampValue = rs
								        .getTimestamp( loop );
								if ( timestampValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , timestampValue.toString() );
								}
								break;
							case java.sql.Types.INTEGER :
							case java.sql.Types.NUMERIC :
							case java.sql.Types.DECIMAL :
								// 如果小数位不为0，则做浮点数处理
								double decimailValue = rs.getDouble( loop );
								Float tmp_float = new Float( decimailValue );
								if ( tmp_float.equals( null ) )
								{
									row.setAttribute( colName , "0" );
								}
								else
								{
									// String temp = rs.getString(loop);
									String temp = String.valueOf( decimailValue );
									String[] aValue = temp.split( "\\." , 0 );
									if ( aValue.length == 1 )
									{// 无小数点
										row.setAttribute( colName , temp );
										break;
									}
									if ( Float.parseFloat( aValue[1] ) == 0 )
									{// 无小数点
										row.setAttribute( colName , aValue[0] );
									}
									else
									{
										
										String strTemp = aValue[1] + "00";
										if ( aValue[1].length() > 2 )
										{
											row.setAttribute( colName , aValue[0] + "."
											        + strTemp.substring( 0 , 2 ) );
										}
										else
											row.setAttribute( colName , temp );
										
									}
									
								}
								break;
							
							default :
								String defaultValue = rs.getString( loop ) == null ? null
								        : rs.getString( loop );
								if ( defaultValue == null )
								{
									row.setAttribute( colName , "" );
								}
								else
								{
									row.setAttribute( colName , defaultValue );
								}
								break;
						}
					
				}
				rows.addContent( "\n  " );
				rows.addContent( row );
				rows.addContent( "\n  " );
			}
			// rows.setAttribute("status","0");
			result = getXML();
		}
		catch ( SQLException se )
		{
			System.out.println( se.getMessage() );
			result = "";
		}
		return result;
	}
	
	/**
	 * 用于获得XML文档。
	 * 
	 * @throws Exception
	 *             抛出一个Exception异常。
	 * @return String类型。
	 * @since V1.0.0
	 * @creator xiegh 2004年10月27日
	 */
	private String getXML()
	{
		String result = "";
		if ( doc == null )
		{
			return result;
		}
		else
		{
			// java.util.Properties sysProp = new
			// java.util.Properties(System.getProperties());
			// String sysFileEncoding =
			// sysProp.getProperty("file.encoding");//得到操作系统的编码
			// org.jdom.output.Format myFormat =
			// org.jdom.output.Format.getPrettyFormat();
			// myFormat.setEncoding(sysFileEncoding);//设置编码格式
			Format myFormat = Format.getCompactFormat();
			myFormat.setEncoding( "UTF-8" );
			XMLOutputter outputter = new XMLOutputter( myFormat );
			result = outputter.outputString( doc );
			return result;
		}
	}
	
	public String getReturnCodeXML( String strReturnFlag )
	{
		String result = getReturnCodeXML( strReturnFlag , "" );
		return result;
	}
	
	/**
	 * 用于获得XML文档。
	 * 
	 * @throws Exception
	 *             抛出一个Exception异常。
	 * @return String类型。
	 * @since V1.0.0
	 * @creator xiegh 2004年10月27日
	 */
	public String getReturnCodeXML( String strReturnFlag , String strDesc )
	{
		initMember();
		String result = "";
		Element row = new Element( "datas" );
		row.setAttribute( "status" , strReturnFlag );
		row.setAttribute( "description" , strDesc );
		root.addContent( "\n  " );
		root.addContent( row );
		root.addContent( "\n  " );
		result = getXML();
		return result;
	}
	
	// 取得客户端发来的数据包
	public Document ReadPackage( HttpServletRequest request )
	{
		Document _doc = null; // 文档对象
		byte mStream[] = null;
		int totalRead = 0;
		int readBytes = 0;
		int totalBytes = 0;
		try
		{
			totalBytes = request.getContentLength();
			mStream = new byte[totalBytes];
			while ( totalRead < totalBytes )
			{
				readBytes = request.getInputStream().read( mStream , totalRead ,
				        totalBytes - totalRead );
				totalRead += readBytes;
				continue;
			}
			SAXBuilder builder = new SAXBuilder(); // 建立SAX工厂
			_doc = builder.build( new ByteArrayInputStream( mStream ) ); // 生XML文档树
		}
		catch ( Exception e )
		{
			System.out.println( e.toString() );
		}
		return _doc;
	}
	
}
