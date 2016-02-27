package com.nodepoint.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * @Package com.clt.security.bean
 * @Description: Aes加密
 * @author hjx
 * @date 2015年4月16日 下午3:35:28
 * @version V1.0
 */
public class JdkAesUtil
{
	static String src = "unlcn";
	
	public static void aes()
	{
		
		try
		{
			KeyGenerator keyGenerator = KeyGenerator.getInstance( "AES" );
			keyGenerator.init( 128 );
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyByte = secretKey.getEncoded();
			
			System.out.println( "key =" + Base64.encodeBase64String( keyByte ) );
			
			Key key = new SecretKeySpec( keyByte , "AES" );
			
			Cipher cipher = Cipher.getInstance( "AES/ECB/PKCS5Padding" );
			cipher.init( Cipher.ENCRYPT_MODE , key );
			byte[] result = cipher.doFinal( src.getBytes() );
			
			System.out.println( "加密后：" + Base64.encodeBase64String( result ) );
			
			cipher.init( Cipher.DECRYPT_MODE , key );
			byte[] deresult = cipher.doFinal( result );
			System.out.println( "解密：" + new String( deresult ) );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public static void main( String[] args )
	{
		String md5 = MD5( "http://192.168.1.74:8080/wlptbackend/seckillAction/getPersonalProduct?a80759de-4aa4-402a-b051-0535ff30344c&1429688929328" );
		
		System.out.println( md5 );
		// org.springframework.security.authentication.encoding.Md5PasswordEncoder
		// t = new Md5PasswordEncoder();
		// String tt = t.encodePassword( "123456" , "admin" );
		// System.out.println( tt );
		// aes();
		/*String content = "123456789";
		System.out.println( "加密前：" + content );
		
		String key = "unlcn";
		System.out.println( "加密密钥和解密密钥：" + key );
		
		try
		{
			String encrypt = aesEncrypt( content , key );
			System.out.println( "加密后：" + encrypt );
			
			String decrypt = aesDecrypt( "9A4561ACE187B820C4D309207A372ADB" , key );
			System.out.println( "解密后：" + decrypt );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}*/
		
	}
	
	/**
	 * 将byte[]转为各种进制的字符串
	 * 
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary( byte[] bytes , int radix )
	{
		return new BigInteger( 1 , bytes ).toString( radix );// 这里的1代表正数
	}
	
	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode( byte[] bytes )
	{
		// return new BASE64Encoder().encode( bytes );
		return null;
	}
	
	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode( String base64Code ) throws Exception
	{
		/*return StringUtils.isEmpty( base64Code ) ? null : new BASE64Decoder()
		        .decodeBuffer( base64Code );*/
		
		return null;
	}
	
	/**
	 * 获取byte[]的md5值
	 * 
	 * @param bytes
	 *            byte[]
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5( byte[] bytes ) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance( "MD5" );
		md.update( bytes );
		
		return md.digest();
	}
	
	/**
	 * 获取字符串md5值
	 * 
	 * @param msg
	 * @return md5
	 * @throws Exception
	 */
	public static byte[] md5( String msg ) throws Exception
	{
		return StringUtils.isEmpty( msg ) ? null : md5( msg.getBytes() );
	}
	
	/**
	 * 结合base64实现md5加密
	 * 
	 * @param msg
	 *            待加密字符串
	 * @return 获取md5后转为base64
	 * @throws Exception
	 */
	public static String md5Encrypt( String msg ) throws Exception
	{
		return StringUtils.isEmpty( msg ) ? null : base64Encode( md5( msg ) );
	}
	
	/**
	 * AES加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes( String content , String encryptKey )
	        throws Exception
	{
		KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
		kgen.init( 128 , new SecureRandom( encryptKey.getBytes() ) );
		
		Cipher cipher = Cipher.getInstance( "AES" );
		cipher.init( Cipher.ENCRYPT_MODE , new SecretKeySpec( kgen.generateKey()
		        .getEncoded() , "AES" ) );
		
		return cipher.doFinal( content.getBytes( "utf-8" ) );
	}
	
	/**
	 * AES加密为base 64 code
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static String aesEncrypt( String content , String encryptKey )
	        throws Exception
	{
		return base64Encode( aesEncryptToBytes( content , encryptKey ) );
	}
	
	/**
	 * AES解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes( byte[] encryptBytes , String decryptKey )
	        throws Exception
	{
		KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
		kgen.init( 128 , new SecureRandom( decryptKey.getBytes() ) );
		
		Cipher cipher = Cipher.getInstance( "AES" );
		cipher.init( Cipher.DECRYPT_MODE , new SecretKeySpec( kgen.generateKey()
		        .getEncoded() , "AES" ) );
		byte[] decryptBytes = cipher.doFinal( encryptBytes );
		
		return new String( decryptBytes );
	}
	
	/**
	 * 将base 64 code AES解密
	 * 
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt( String encryptStr , String decryptKey )
	        throws Exception
	{
		return StringUtils.isEmpty( encryptStr ) ? null : aesDecryptByBytes(
		        base64Decode( encryptStr ) , decryptKey );
	}
	
	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt( String content , String password )
	{
		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
			kgen.init( 128 , new SecureRandom( password.getBytes() ) );
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec( enCodeFormat , "AES" );
			Cipher cipher = Cipher.getInstance( "AES" );// 创建密码器
			byte[] byteContent = content.getBytes( "utf-8" );
			cipher.init( Cipher.ENCRYPT_MODE , key );// 初始化
			byte[] result = cipher.doFinal( byteContent );
			return result; // 加密
		}
		catch ( NoSuchAlgorithmException e )
		{
			e.printStackTrace();
		}
		catch ( NoSuchPaddingException e )
		{
			e.printStackTrace();
		}
		catch ( InvalidKeyException e )
		{
			e.printStackTrace();
		}
		catch ( UnsupportedEncodingException e )
		{
			e.printStackTrace();
		}
		catch ( IllegalBlockSizeException e )
		{
			e.printStackTrace();
		}
		catch ( BadPaddingException e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt( byte[] content , String password )
	{
		try
		{
			KeyGenerator kgen = KeyGenerator.getInstance( "AES" );
			kgen.init( 128 , new SecureRandom( password.getBytes() ) );
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec( enCodeFormat , "AES" );
			Cipher cipher = Cipher.getInstance( "AES" );// 创建密码器
			cipher.init( Cipher.DECRYPT_MODE , key );// 初始化
			byte[] result = cipher.doFinal( content );
			return result; // 加密
		}
		catch ( NoSuchAlgorithmException e )
		{
			e.printStackTrace();
		}
		catch ( NoSuchPaddingException e )
		{
			e.printStackTrace();
		}
		catch ( InvalidKeyException e )
		{
			e.printStackTrace();
		}
		catch ( IllegalBlockSizeException e )
		{
			e.printStackTrace();
		}
		catch ( BadPaddingException e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String MD5( String sourceStr )
	{
		String result = "";
		try
		{
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			md.update( sourceStr.getBytes() );
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer( "" );
			for ( int offset = 0 ; offset < b.length ; offset++ )
			{
				i = b[offset];
				if ( i < 0 )
					i += 256;
				if ( i < 16 )
					buf.append( "0" );
				buf.append( Integer.toHexString( i ) );
			}
			result = buf.toString();
			System.out.println( "MD5(" + sourceStr + ",32) = " + result );
			System.out.println( "MD5(" + sourceStr + ",16) = "
			        + buf.toString().substring( 8 , 24 ) );
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( e );
		}
		return result;
	}
}
