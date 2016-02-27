package com.nodepoint.systemmanager.dao.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nodepoint.basedao.imp.GenericHibernateDao;
import com.nodepoint.systemmanager.dao.IUserDao;
import com.nodepoint.systemmanger.model.TUser;
import com.nodepoint.util.DataGridModel;

@Repository
public class UserDaoImp extends GenericHibernateDao< TUser , Integer > implements
        IUserDao
{
	@SuppressWarnings( "rawtypes" )
	public Map< String , Object > getPageList( DataGridModel dgm , TUser user )
	        throws Exception
	{
		
		Map< String , Object > result = new HashMap< String , Object >( 2 );
		String countQuery = "select count(*) from TUser user ";
		String fullQuery = " from TUser user ";
		// 这里需要用new map()，jquery easyui datagrid有一个小bug，必须把idField单独列出来，要不然不能多选
		String orderString = "";
		
		// 增加条件
		StringBuffer sb = new StringBuffer();
		Map< String , Object > params = new HashMap< String , Object >();
		
		Query queryTotal = getSession().createQuery( countQuery + sb.toString() );
		Query queryList = getSession()
		        .createQuery( fullQuery + sb.toString() + orderString )
		        .setFirstResult( ( dgm.getPage() - 1 ) * dgm.getRows() )
		        .setMaxResults( dgm.getRows() );
		if ( params != null && ! params.isEmpty() )
		{
			
		}
		
		int total = ( ( Long ) queryTotal.uniqueResult() ).intValue(); // 这里必须先转成Long再取intValue，不能转成Integer
		
		List list = queryList.list();
		result.put( "total" , total );
		result.put( "rows" , list );
		
		return result;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-26 下午4:19:11
	 */
	public List< TUser > findAllByType( String type )
	{
		int iArchivetype = Integer.parseInt( type );
		String fullQuery = " from TUser user where user.IArchiveType = ?";
		Query query = getSession().createQuery( fullQuery );
		query.setParameter( 0 , iArchivetype );
		List< TUser > tUsers = query.list();
		return tUsers;
	}
}
