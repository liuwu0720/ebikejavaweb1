package com.nodepoint.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nodepoint.systemmanger.model.TUser;
import com.nodepoint.systemmanger.service.IUserService;
import com.nodepoint.security.bean.Assembler;

/*
 * 自定义登录验证
 */
public class MyUsernamePasswordAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter
{
	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "account";
	public static final String PASSWORD = "password";
	@Autowired
	private IUserService userService;
	@Autowired
	private Assembler assembler;
	
	public Authentication attemptAuthentication( HttpServletRequest request ,
	        HttpServletResponse response ) throws AuthenticationException
	{
		if ( ! request.getMethod().equals( "POST" ) )
		{
			throw new AuthenticationServiceException(
			        "Authentication method not supported: " + request.getMethod() );
		}
		// 检测验证码
		// checkValidateCode( request );
		
		String username = obtainUsername( request );
		String password = obtainPassword( request );
		String pw = password;
		if ( StringUtils.isNotEmpty( password ) )
		{
			org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
			pw = t.encodePassword( password , username );
		}
		// 验证用户账号与密码是否对应
		username = username.trim();
		TUser u = userService.getByAccount( username );
		// Users users = this.usersDao.findByName( username );
		
		if ( u == null || ! u.getVcPassword().equals( pw ) )
		{
			/*
			          在我们配置的simpleUrlAuthenticationFailureHandler处理登录失败的处理类在这么一段
			    这样我们可以在登录失败后，向用户提供相应的信息。
				if (forwardToDestination) {
			        request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
			    } else {
			        HttpSession session = request.getSession(false);

			        if (session != null || allowSessionCreation) {
			            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
			        }
			    }
			 */
			throw new AuthenticationServiceException( "用户名或者密码错误！" );
		}
		HttpSession session = request.getSession();
		session.setAttribute( "user" , u );
		User users = assembler.buildUserFromUserEntity( u );
		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
		        username , password );
		
		// 允许子类设置详细属性
		setDetails( request , authRequest );
		// /this.getAuthenticationManager();
		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		try
		{
			this.getAuthenticationManager().authenticate( authRequest );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return this.getAuthenticationManager().authenticate( authRequest );
		
	}
	
	protected void checkValidateCode( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		
		String sessionValidateCode = obtainSessionValidateCode( session );
		// 让上一次的验证码失效
		session.setAttribute( VALIDATE_CODE , null );
		String validateCodeParameter = obtainValidateCodeParameter( request );
		if ( StringUtils.isEmpty( validateCodeParameter )
		        || ! sessionValidateCode.equalsIgnoreCase( validateCodeParameter ) )
		{
			throw new AuthenticationServiceException( "验证码错误！" );
		}
	}
	
	private String obtainValidateCodeParameter( HttpServletRequest request )
	{
		Object obj = request.getParameter( VALIDATE_CODE );
		return null == obj ? "" : obj.toString();
	}
	
	protected String obtainSessionValidateCode( HttpSession session )
	{
		Object obj = session.getAttribute( VALIDATE_CODE );
		return null == obj ? "" : obj.toString();
	}
	
	@Override
	protected String obtainUsername( HttpServletRequest request )
	{
		Object obj = request.getParameter( USERNAME );
		return null == obj ? "" : obj.toString();
	}
	
	@Override
	protected String obtainPassword( HttpServletRequest request )
	{
		Object obj = request.getParameter( PASSWORD );
		return null == obj ? "" : obj.toString();
	}
	
}
