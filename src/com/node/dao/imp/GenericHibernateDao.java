package com.node.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.node.dao.GenericDao;
import com.node.util.HqlHelper;
import com.node.util.Page;


@SuppressWarnings( "unchecked" )
public class GenericHibernateDao< T extends Serializable , PK extends Serializable >
        extends HibernateDaoSupport implements GenericDao< T , PK >
{
	
	/*
	 * @SuppressWarnings( "unused" )
	 * @Resource( name = "sessionFactory" ) private void setMySessionFactory(
	 * SessionFactory sessionFactory ) { // 这个方法名可以随便写，@Resource可以通过name
	 * 或者type来装载的。 super.setSessionFactory( sessionFactory ); }
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 实体类类型(由构造方法自动赋值)
	 */
	private Class< T > entityClass;
	
	/**
	 * 构造方法，根据实例类自动获取实体类类型
	 */
	public GenericHibernateDao()
	{
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if ( t instanceof ParameterizedType )
		{
			Type[] p = ( ( ParameterizedType ) t ).getActualTypeArguments();
			this.entityClass = ( Class< T > ) p[0];
		}
	}
	
	public Class< T > getEntityClass()
	{
		return entityClass;
	}
	
	// -------------------- 基本检索、增加、修改、删除操作 --------------------
	
	/**
	 * 根据主键获取实体。如果没有相应的实体，返回 null。
	 */
	public T get( PK id )
	{
		return ( T ) getHibernateTemplate().get( entityClass , id );
	}
	
	/**
	 * 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
	 */
	public T getWithLock( PK id , LockMode lock )
	{
		T t = ( T ) getHibernateTemplate().get( entityClass , id , lock );
		if ( t != null )
		{
			this.flush(); // 立即刷新，否则锁不会生效。
		}
		return t;
	}
	
	/**
	 * 根据主键获取实体。如果没有相应的实体，抛出异常。
	 */
	public T load( PK id )
	{
		return ( T ) getHibernateTemplate().load( entityClass , id );
	}
	
	// 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
	public T loadWithLock( PK id , LockMode lock )
	{
		T t = ( T ) getHibernateTemplate().load( entityClass , id , lock );
		if ( t != null )
		{
			this.flush(); // 立即刷新，否则锁不会生效。
		}
		return t;
	}
	
	// 获取全部实体。
	public List< T > loadAll()
	{
		return ( List< T > ) getHibernateTemplate().loadAll( entityClass );
	}
	
	// loadAllWithLock() ?
	
	// 更新实体
	public void update( T entity )
	{
		getHibernateTemplate().update( entity );
	}
	
	// 防止同一session两个对象时Hibernate报错)
	public void updateCleanBefore( T entity )
	{	
		this.getSession().merge( entity );

	}

	// 更新实体并加锁
	public void updateWithLock( T entity , LockMode lock )
	{
		getHibernateTemplate().update( entity , lock );
		this.flush(); // 立即刷新，否则锁不会生效。
	}
	
	// 存储实体到数据库
	public void save( T entity )
	{
		getHibernateTemplate().save( entity );
	}
	
	// 增加或更新实体
	public void saveOrUpdate( T entity )
	{
		getHibernateTemplate().saveOrUpdate( entity );
	}
	
	// 增加或更新集合中的全部实体
	public void saveOrUpdateAll( Collection< T > entities )
	{
		getHibernateTemplate().saveOrUpdateAll( entities );
	}
	
	// 删除指定的实体
	public void delete( T entity )
	{
		getHibernateTemplate().delete( entity );
	}
	
	// 加锁并删除指定的实体
	public void deleteWithLock( T entity , LockMode lock )
	{
		getHibernateTemplate().delete( entity , lock );
		this.flush(); // 立即刷新，否则锁不会生效。
	}
	
	// 根据主键删除指定实体
	public void deleteByKey( PK id )
	{
		this.delete( this.load( id ) );
	}
	
	// 根据主键加锁并删除指定的实体
	public void deleteByKeyWithLock( PK id , LockMode lock )
	{
		this.deleteWithLock( this.load( id ) , lock );
	}
	
	// 删除集合中的全部实体
	public void deleteAll( Collection< T > entities )
	{
		getHibernateTemplate().deleteAll( entities );
	}
	
	/**
	 * 通过属性删除
	 */
	public void deleteByProperty( String propertyName , Object value )
	{
		String queryString = "delete from " + getEntityClass().getName()
		        + " as model where model." + propertyName + "= ?";
		Query query = this.getSession().createQuery( queryString );
		query.setParameter( 0 , value );
		query.executeUpdate();
	}
	
	/**
	 * 分页查找所有的记录
	 * 
	 * @param page
	 *            要返回的页数
	 * @param pageSize
	 *            没有记录数
	 * @return
	 */
	public List< T > findAll( Page page )
	{
		String queryString = "from " + getEntityClass().getName();
		Query query = this.getSession().createQuery( queryString );
		if ( page != null )
		{
			query.setFirstResult( page.getFirstResult() );
			query.setMaxResults( page.getRecordCountperPage() );
			if ( page.getSort() != "" && page.getSort() != null )
			{
				queryString += " order by " + page.getSort() + " " + page.getOrder();
			}
		}
		
		return query.list();
	}
	
	/**
	 * 统计所有记录的总数
	 * 
	 * @return 总数
	 */
	public int countAll()
	{
		String queryString = "select count(*) from " + getEntityClass().getName();
		Query query = this.getSession().createQuery( queryString );
		List< T > list = query.list();
		Long result = ( Long ) list.get( 0 );
		return result.intValue();
	}
	
	/**
	 * find By Example
	 * 
	 * @param entity
	 * @return
	 */
	public List< T > findByExample( T entity )
	{
		return this.getHibernateTemplate().findByExample( entity );
	}
	
	public List< T > findAll()
	{
		return this.getHibernateTemplate().find( "from " + getEntityClass().getName() );
	}
	
	/**
	 * 通过属性查找
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @return
	 */
	public List< T > findByProperty( String propertyName , Object value )
	{
		String queryString = "from " + getEntityClass().getName()
		        + " as model where model." + propertyName
		        + "= ? order by model." + propertyName;
		return this.getHibernateTemplate().find( queryString , value );
		
	}
	
	/**
	 * 通过多个属性组合查询
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应于propertyNames的值 return 匹配的结果
	 */
	public List< T > findByPropertys( String[] propertyNames , Object[] values )
	{
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append( "from " + getEntityClass().getName() );
		strBuffer.append( " as model where " );
		for ( int i = 0 ; i < propertyNames.length ; i++ )
		{
			if ( i != 0 )
				strBuffer.append( " and" );
			strBuffer.append( " model." );
			strBuffer.append( propertyNames[i] );
			strBuffer.append( "=" );
			strBuffer.append( "? " );
		}
		String queryString = strBuffer.toString();
		return this.getHibernateTemplate().find( queryString , values );
	}
	
	/**
	 * 通过属性查找并分页
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示条数
	 */
	public List< T > findByProperty( String propertyName , Object value , Page page )
	{
		return this.findByPropertys( new String[] { propertyName } ,
		        new Object[] { value } , page );
	}
	
	/**
	 * 通过多个属性组合查询
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应于propertyNames的值
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示数 return 匹配的结果 return 匹配的结果
	 */
	public List< T > findByPropertys( String[] propertyNames , Object[] values , Page page )
	{
		
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append( "from " + getEntityClass().getName() );
		strBuffer.append( " as model where " );
		for ( int i = 0 ; i < propertyNames.length ; i++ )
		{
			if ( i != 0 )
				strBuffer.append( " and" );
			strBuffer.append( " model." );
			strBuffer.append( propertyNames[i] );
			strBuffer.append( "=" );
			strBuffer.append( "? " );
		}
		String queryString = strBuffer.toString();
		
		int recordCount = getCountHQL( queryString , values );
		page.setRecordCount( recordCount );
		
		Query query = this.getSession().createQuery( queryString );
		for ( int i = 0 ; i < values.length ; i++ )
		{
			query.setParameter( i , values[i] );
		}
		
		query.setFirstResult( page.getFirstResult() );
		query.setMaxResults( page.getRecordCountperPage() );
		
		return query.list();
	}
	
	/**
	 * 通过属性统计数量
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 */
	public int countByProperty( String propertyName , Object value )
	{
		String[] propertyNames = new String[] { propertyName };
		Object[] values = new Object[] { value };
		return this.countByPropertys( propertyNames , values );
	}
	
	/**
	 * 通过多个属性统计数量
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应的属性值数组 return
	 */
	public int countByPropertys( String[] propertyNames , Object[] values )
	{
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append( "select count(*) from " + getEntityClass().getName() );
		strBuffer.append( " as model where " );
		for ( int i = 0 ; i < propertyNames.length ; i++ )
		{
			if ( i != 0 )
				strBuffer.append( " and" );
			strBuffer.append( " model." );
			strBuffer.append( propertyNames[i] );
			strBuffer.append( "=" );
			strBuffer.append( "? " );
		}
		
		String queryString = strBuffer.toString();
		Query query = this.getSession().createQuery( queryString );
		for ( int i = 0 ; i < values.length ; i++ )
		{
			query.setParameter( i , values[i] );
		}
		
		List< T > list = query.list();
		Long result = ( Long ) list.get( 0 );
		return result.intValue();
	}
	
	/**
	 * 查找T并通过某一属性排序
	 * 
	 * @param property
	 *            排序依据的顺序
	 * @param isSequence
	 *            是否顺序排序,false为倒序
	 */
	public List< T > findAndOrderByProperty( Page page )
	{
		String queryString = "from " + getEntityClass().getName()
		        + " as model order by model." + page.getSort();
		if ( page.getOrder() != "" && page.getOrder() != null )
		{
			queryString += " " + page.getOrder();
		}
		
		int recordCount = getCountHQL( queryString , null );
		page.setRecordCount( recordCount );
		
		Query queryObject = getSession().createQuery( queryString );
		queryObject.setFirstResult( page.getFirstResult() );
		queryObject.setMaxResults( page.getRecordCountperPage() );
		return queryObject.list();
		
	}
	
	/**
	 * 查找所有并通过某个属性排序
	 * 
	 * @param propertyName
	 *            排序依据的属性名称
	 * @param isSequence
	 *            是否顺序排列
	 */
	public List< T > findAllAndOrderByProperty( String propertyName , boolean isSequence )
	{
		String queryString = "from " + getEntityClass().getName()
		        + " as model order by model." + propertyName;
		if ( isSequence == false )
		{
			queryString = queryString + " DESC";
		}
		
		Query queryObject = getSession().createQuery( queryString );
		return queryObject.list();
	}
	
	// -------------------- HSQL
	// ----------------------------------------------
	
	// 使用HSQL语句直接增加、更新、删除实体
	public int bulkUpdate( String queryString )
	{
		return getHibernateTemplate().bulkUpdate( queryString );
	}
	
	// 使用带参数的HSQL语句增加、更新、删除实体
	public int bulkUpdate( String queryString , Object[] values )
	{
		return getHibernateTemplate().bulkUpdate( queryString , values );
	}
	
	// 使用HSQL语句检索数据
	/**
	 * @Description: hql
	 * @param queryString
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-2 下午8:48:15
	 */
	public List< T > find( String queryString )
	{
		return getHibernateTemplate().find( queryString );
	}
	
	// 使用带参数的HSQL语句检索数据
	public List< T > find( String queryString , Object[] values )
	{
		return getHibernateTemplate().find( queryString , values );
	}
	
	// 使用带命名的参数的HSQL语句检索数据
	public List findByNamedParam( String queryString , String[] paramNames ,
	        Object[] values )
	{
		return getHibernateTemplate()
		        .findByNamedParam( queryString , paramNames , values );
	}
	
	// 使用命名的HSQL语句检索数据
	public List findByNamedQuery( String queryName )
	{
		return getHibernateTemplate().findByNamedQuery( queryName );
	}
	
	// 使用带参数的命名HSQL语句检索数据
	public List findByNamedQuery( String queryName , Object[] values )
	{
		return getHibernateTemplate().findByNamedQuery( queryName , values );
	}
	
	// 使用带命名参数的命名HSQL语句检索数据
	public List findByNamedQueryAndNamedParam( String queryName , String[] paramNames ,
	        Object[] values )
	{
		return getHibernateTemplate().findByNamedQueryAndNamedParam( queryName ,
		        paramNames , values );
	}
	
	// 使用HSQL语句检索数据，返回 Iterator
	public Iterator iterate( String queryString )
	{
		return getHibernateTemplate().iterate( queryString );
	}
	
	// 使用带参数HSQL语句检索数据，返回 Iterator
	public Iterator iterate( String queryString , Object[] values )
	{
		return getHibernateTemplate().iterate( queryString , values );
	}
	
	// 关闭检索返回的 Iterator
	public void closeIterator( Iterator it )
	{
		getHibernateTemplate().closeIterator( it );
	}
	
	// -------------------------------- Criteria
	// ------------------------------
	
	// 创建与会话无关的检索标准
	public DetachedCriteria createDetachedCriteria()
	{
		return DetachedCriteria.forClass( this.entityClass );
	}
	
	// 创建与会话绑定的检索标准
	public Criteria createCriteria()
	{
		return this.createDetachedCriteria().getExecutableCriteria( this.getSession() );
	}
	
	// 检索满足标准的数据
	public List findByCriteria( DetachedCriteria criteria )
	{
		return getHibernateTemplate().findByCriteria( criteria );
	}
	
	// 检索满足标准的数据，返回指定范围的记录
	public List findByCriteria( DetachedCriteria criteria , int firstResult ,
	        int maxResults )
	{
		return getHibernateTemplate()
		        .findByCriteria( criteria , firstResult , maxResults );
	}
	
	// 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	public List< T > findEqualByEntity( T entity , String[] propertyNames )
	{
		Criteria criteria = this.createCriteria();
		Example exam = Example.create( entity );
		exam.excludeZeroes();
		String[] defPropertys = getSessionFactory().getClassMetadata( entityClass )
		        .getPropertyNames();
		for ( String defProperty : defPropertys )
		{
			int ii = 0;
			for ( ii = 0 ; ii < propertyNames.length ; ++ii )
			{
				if ( defProperty.equals( propertyNames[ii] ) )
				{
					criteria.addOrder( Order.asc( defProperty ) );
					break;
				}
			}
			if ( ii == propertyNames.length )
			{
				exam.excludeProperty( defProperty );
			}
		}
		criteria.add( exam );
		return ( List< T > ) criteria.list();
	}
	
	// 使用指定的实体及属性检索（满足属性 like 串实体值）数据
	public List< T > findLikeByEntity( T entity , String[] propertyNames )
	{
		Criteria criteria = this.createCriteria();
		for ( String property : propertyNames )
		{
			try
			{
				Object value = PropertyUtils.getProperty( entity , property );
				if ( value instanceof String )
				{
					criteria.add( Restrictions.like( property , ( String ) value ,
					        MatchMode.ANYWHERE ) );
					criteria.addOrder( Order.asc( property ) );
				}
				else
				{
					criteria.add( Restrictions.eq( property , value ) );
					criteria.addOrder( Order.asc( property ) );
				}
			}
			catch ( Exception ex )
			{
				// 忽略无效的检索参考数据。
			}
		}
		return ( List< T > ) criteria.list();
	}
	
	// 使用指定的检索标准获取满足标准的记录数
	public Integer getRowCount( DetachedCriteria criteria )
	{
		criteria.setProjection( Projections.rowCount() );
		List list = this.findByCriteria( criteria , 0 , 1 );
		return ( Integer ) list.get( 0 );
	}
	
	// 使用指定的检索标准检索数据，返回指定统计值(max,min,avg,sum)
	public Object getStatValue( DetachedCriteria criteria , String propertyName ,
	        String StatName )
	{
		if ( StatName.toLowerCase().equals( "max" ) )
			criteria.setProjection( Projections.max( propertyName ) );
		else if ( StatName.toLowerCase().equals( "min" ) )
			criteria.setProjection( Projections.min( propertyName ) );
		else if ( StatName.toLowerCase().equals( "avg" ) )
			criteria.setProjection( Projections.avg( propertyName ) );
		else if ( StatName.toLowerCase().equals( "sum" ) )
			criteria.setProjection( Projections.sum( propertyName ) );
		else
			return null;
		List list = this.findByCriteria( criteria , 0 , 1 );
		return list.get( 0 );
	}
	
	// -------------------------------- Others
	// --------------------------------
	
	// 加锁指定的实体
	public void lock( T entity , LockMode lock )
	{
		getHibernateTemplate().lock( entity , lock );
	}
	
	// 强制初始化指定的实体
	public void initialize( Object proxy )
	{
		getHibernateTemplate().initialize( proxy );
	}
	
	// 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
	public void flush()
	{
		getHibernateTemplate().flush();
	}
	
	// 根据hql 返回查询count的hql
	public int getCountHQL( String hql , Object[] values )
	{
		String counthql = " select count(*) " + hql;
		Query countquery = this.getSession().createQuery( counthql );
		if ( values != null && values.length > 0 )
		{
			for ( int i = 0 ; i < values.length ; i++ )
			{
				countquery.setParameter( i , values[i] );
			}
		}
		
		int recordCount = Integer.valueOf( countquery.uniqueResult().toString() );
		return recordCount;
	}
	
	// 根据sql 返回查询count的sql
	public int getCountSQL( String sql )
	{
		String countsql = " select count(*) coun from ( " + sql + " )";
		SQLQuery countquery = this.getSession().createSQLQuery( countsql );
		int recordCount = Integer.valueOf( countquery.uniqueResult().toString() );
		return recordCount;
	}
	
	/**
	 * 
	 * @Description: 根据所传的SQL语句 查出数据 并分页
	 * @param sql
	 * @param page
	 * @return List<String[]> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午6:42:29
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		SQLQuery sqlquery = this.getSession().createSQLQuery( sql );
		if ( page != null )
		{
			
			int recordCount = getCountSQL( sql );
			page.setRecordCount( recordCount );
			sqlquery.setFirstResult( page.getFirstResult() );
			sqlquery.setMaxResults( page.getRecordCountperPage() );
		}
		
		return sqlquery.list();
		
	}
	
	/**
	 * @Description: 查找有效的数据，并按id升序排列
	 * @return
	 * @author hjx
	 * @create_date 2014年7月17日 上午11:18:46
	 */
	public List< T > loadAllByEnable()
	{
		String queryString = "from " + getEntityClass().getName()
		        + " where NEnable=0 order by id asc";
		return this.find( queryString );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @return
	 * @author hjx
	 * @create_date 2014年7月17日 上午11:18:46
	 */
	public List< T > findAllByEnable( Page page )
	{
		String queryString = "from " + getEntityClass().getName() + " where NEnable=0 ";
		Query query = this.getSession().createQuery( queryString );
		if ( page != null )
		{
			query.setFirstResult( page.getFirstResult() );
			query.setMaxResults( page.getRecordCountperPage() );
			if ( page.getSort() != "" && page.getSort() != null )
			{
				queryString += " order by " + page.getSort() + " " + page.getOrder();
			}
		}
		
		return query.list();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @return Map< String , Object > 有2个key key=total 总数 key=rows List数据
	 * @author hjx
	 * @create_date 2014年7月17日 上午11:18:46
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		System.out.println( "hql >>" + hql.getHQL() );
		Query query = this.getSession().createQuery( hql.getHQL() );
		// 注册查询参数
		if ( hql.getParams().size() > 0 )
		{
			int i = 0;
			for ( Object param : hql.getParams() )
			{
				query.setParameter( i , param );
				i++ ;
			}
		}
		
		// 分页处理
		if ( hql.getQueryPage() != null )
		{
			Query countQuery = this.getSession().createQuery(
			        "select count(*) "
			                + hql.getHQL().substring(
			                        0 ,
			                        hql.getHQL().indexOf( "order by" ) == - 1 ? hql
			                                .getHQL().length() : hql.getHQL().indexOf(
			                                "order by" ) ) );
			// 注册查询参数
			if ( hql.getParams().size() > 0 )
			{
				int i = 0;
				for ( Object param : hql.getParams() )
				{
					countQuery.setParameter( i , param );
					i++ ;
				}
			}
			int totalCount = Integer.valueOf( countQuery.uniqueResult().toString() );
			if ( totalCount == 0 )
			{
				result.put( "total" , 0 );
				result.put( "rows" , new ArrayList< T >() );
				return result;
			}
			hql.getQueryPage().setRecordCount( totalCount );
			query.setFirstResult( hql.getQueryPage().getFirstResult() );
			query.setMaxResults( hql.getQueryPage().getRecordCountperPage() );
			result.put( "total" , hql.getQueryPage().getRecordCount() );
		}
		
		result.put( "rows" , query.list() );
		
		return result;
	}
	
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		
		int count = getCountSQL( sql );
		if ( page != null )
		{
			page.setRecordCount( count );
		}

		result.put( "total" , count );
		
		sql = getOraclePageSQL( sql , page );
		System.out.println( "base Dao spring " + sql );
		List< Map< String , Object >> arlist = jdbcTemplate.queryForList( sql );
		
		result.put( "rows" , arlist );
		
		String srtr = JSONArray.fromObject( arlist ).toString();
		System.out.println( "srtr" + srtr );
		System.out.println( arlist.size() );
		
		return result;
	}
	
	public String getOraclePageSQL( String queryString , Page page )
	{
		if ( StringUtils.isEmpty( queryString ) )
		{
			return null;
		}
		String itemSource = queryString.toLowerCase();

		String endSql = "";
		if ( page == null )
		{
			endSql = "select * from  (" + queryString + ") ";
		}
		else
		{
			int endIndex = page.getFirstResult() + page.getRecordCountperPage();
			endSql = "select * from (select rOraclePageSQL.*,ROWNUM as currentRow from ("
			        + queryString
			        + ") rOraclePageSQL where rownum <="
			        + endIndex + ") where currentRow>" + page.getFirstResult();
		}

		return endSql;
	}
	
	public Object getDateBySQL( String sql )
	{
		if ( StringUtils.isEmpty( sql ) )
		{
			return null;
		}
		
		SQLQuery query = this.getSession().createSQLQuery( sql );
		
		return query.uniqueResult();
		
	}
	
	/**
	 * 
	 * @Description: TODO(按参数查询并排序)
	 * @param propertyNames
	 * @param values
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-8 下午4:47:42
	 */
	public List< T > findByPropertysOrderBy( String[] propertyNames ,
	        Object[] values , String orderByParam )
	{
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append( "from " + getEntityClass().getName() );
		strBuffer.append( " as model where " );
		for ( int i = 0 ; i < propertyNames.length ; i++ )
		{
			if ( i != 0 )
				strBuffer.append( " and" );
			strBuffer.append( " model." );
			strBuffer.append( propertyNames[i] );
			strBuffer.append( "=" );
			strBuffer.append( "? " );

		}
		strBuffer.append( " order by " );
		strBuffer.append( "model." );
		strBuffer.append( orderByParam );
		strBuffer.append( " desc" );
		String queryString = strBuffer.toString();
		return this.getHibernateTemplate().find( queryString , values );
	}
	
	

}
