package com.nodepoint.security.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.nodepoint.systemmanager.dao.IRoleDao;
import com.nodepoint.systemmanager.dao.IUserRoleDao;
import com.nodepoint.systemmanger.model.TUser;
import com.nodepoint.systemmanger.model.TUserRole;


/**
 * @Package com.clt.security.bean
 * @Description:
 * @author hjx
 * @date 2014年7月15日 上午11:15:55
 * @version V1.0
 */
@Service
public class Assembler
{
	@Autowired
	private IUserRoleDao urDao;
	@Autowired
	private IRoleDao roleDao;
	
	public User buildUserFromUserEntity( TUser userEntity )
	{
		
		String username = userEntity.getVcAccount();
		String password = userEntity.getVcPassword();
		boolean enabled = true;// userEntity.isActive();
		boolean accountNonExpired = true;// userEntity.isActive();
		boolean credentialsNonExpired = true;// userEntity.isActive();
		boolean accountNonLocked = true;// userEntity.isActive();
		
		Collection< GrantedAuthority > authorities = new HashSet< GrantedAuthority >();
		authorities = obtionGrantedAuthorities( userEntity );
		
		User user = new User( username , password , enabled , accountNonExpired ,
		        credentialsNonExpired , accountNonLocked , authorities );
		return user;
	}

	
	/**
	  * 方法描述：
	  * @param userEntity
	  * @return 
	  * @version: 1.0
	  * @author: liuwu
	  * @version: 2016年2月27日 下午3:49:39
	  */
	@SuppressWarnings( "deprecation" )
	private Set< GrantedAuthority > obtionGrantedAuthorities( TUser user )
	{
		Set< GrantedAuthority > authSet = new HashSet< GrantedAuthority >();
		List< TUserRole > uroles = urDao.findByPropertys( new String[] { "iUser" ,
		        "iEnable" } , new Object[] { user.getId() , 0 } );
		for ( TUserRole ur : uroles )
		{
			
			authSet.add( new GrantedAuthorityImpl( roleDao.get( ur.getiRole() )
			        .getVcRoleName() ) );
		}
		return authSet;
	}
	

}
