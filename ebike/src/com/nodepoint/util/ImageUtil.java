/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-30 下午4:14:08 
 * @version V1.0 
 */
package com.nodepoint.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import common.Logger;

/** 
 * @Package com.clt.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-30 下午4:14:08 
 * @version V1.0 
 */
public class ImageUtil
{	
	/**
	 * 
	 * @param infile
	 *            输入文件
	 * @param outfile
	 *            输出文件
	 * @param srcFormat
	 *            源格式
	 * @param destFormat
	 *            输出格式
	 * @return
	 * @throws Exception
	 */
	public static boolean convertFormat( InputStream infile ,
	        OutputStream outfile , String srcFormat , String destFormat ,
	        int width , int height ) throws Exception
	{
		boolean flag = false;
		BufferedImage src = ImageIO.read( infile );
		if ( height > 0 && width > 0 )
		{// compress the origin image if width and height are non-zero
			height = src.getHeight() > height ? height : src.getHeight();
			width = src.getWidth() > width ? width : src.getWidth();
			Image image = src.getScaledInstance( width , height ,
			        Image.SCALE_DEFAULT );// 这个是用来进行图片大小调整的
			
			BufferedImage tag = new BufferedImage( width , height ,
			        BufferedImage.TYPE_INT_RGB );
			
			Graphics g = tag.getGraphics();
			// 可在下面对图片进行绘制和更改
			g.drawImage( image , 0 , 0 , null ); // 绘制缩小后的图
			
			g.dispose();
			tag.flush();
			flag = ImageIO.write( tag , destFormat , outfile );// 输出到经过缩放的文件流
		}
		else
		{
			flag = ImageIO.write( src , destFormat , outfile );// 输出原分辨率的图片
		}
		Logger.getLogger( ImageUtil.class ).info(
		        "图片转换成功: 从[" + srcFormat + "]到[" + destFormat + "]" );
		return flag;
	}
	
	/**
	 * 
	 * @Description: TODO(将图片转成PNG格式)
	 * @param session
	 * @param file
	 * @param filepath
	 * @return
	 * @throws Exception
	 *             String 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-24 下午5:32:47
	 */
	@SuppressWarnings( "unused" )
	public static String storeFile( HttpSession session , MultipartFile file ,
	        String filepath ) throws Exception
	{
		String fileType = file.getContentType().split( "/" )[1];
		String path = session.getServletContext().getRealPath( "/" ) + filepath;
		// + SystemConstants.USER_STATUS_IMG_PATH;
		String separator = File.separator;
		String uuid = UUID.randomUUID().toString();
		FileOutputStream fos = null;
		String fileName = null;
		try
		{
			InputStream fis = file.getInputStream();
			// 转换文件为png格式，并保存在同名目录下
			File files = new File( path );
			// 判断文件夹是否存在,如果不存在则创建文件夹
			if ( ! files.exists() )
			{
				files.mkdir();
			}
			if ( file.getContentType().split( "/" )[0].equals( "image" ) )
			{
				if ( path.endsWith( separator ) )
					fileName = path + uuid + ".png";
				else
					fileName = path + separator + uuid + ".png";
				fos = new FileOutputStream( fileName );
				ImageUtil.convertFormat( fis , fos , fileType , "png" , 0 , 0 );
				fos.flush();
				fos.close();
			}
		}
		catch ( Exception ex )
		{
			System.out.println( "文件取出失败，错误信息: " + ex.getMessage() );
			if ( fos != null )
				fos.close();
			throw ex;
		}
		return uuid + ".png";
	}
}
