package com.nodepoint.basedao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import com.nodepoint.util.HqlHelper;
import com.nodepoint.util.Page;

public interface GenericDao< T extends Serializable , PK extends Serializable >
{
	// -------------------- 基本检索、增加、修改、删除操作 --------------------
	
	/**
	 * @Description: 根据主键获取实体。如果没有相应的实体，返回 null。
	 * @param id
	 * @return T 返回值描述
	 * @throws
	 */
	public T get( PK id );
	
	/**
	 * @Description: 根据主键获取实体并加锁。如果没有相应的实体，返回 null。
	 * @param id
	 * @param lock
	 * @return T 返回值描述
	 * @throws
	 */
	public T getWithLock( PK id , LockMode lock );
	
	/**
	 * @Description: 根据主键获取实体。如果没有相应的实体，抛出异常。
	 * @param id
	 * @return T 返回值描述
	 * @throws
	 */
	public T load( PK id );
	
	/**
	 * @Description: 根据主键获取实体并加锁。如果没有相应的实体，抛出异常。
	 * @param id
	 * @param lock
	 * @return T 返回值描述
	 * @throws
	 */
	public T loadWithLock( PK id , LockMode lock );
	
	/**
	 * @Description: 获取全部实体。
	 * @return List<T> 返回值描述
	 * @throws
	 */
	public List< T > loadAll();
	
	/**
	 * @Description: 查找有效的数据，并按id升序排列
	 * @return
	 * @author hjx
	 * @create_date 2014年7月17日 上午11:18:46
	 */
	public List< T > loadAllByEnable();
	
	/**
	 * @Description: 更新实体
	 * @param entity
	 *            void 返回值描述
	 * @throws
	 */
	public void update( T entity );
	
	/**
	 * 
	 * @Description: TODO(防止同一session两个对象时Hibernate报错))
	 * @param entity
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 下午6:00:19
	 */
	public void updateCleanBefore( T entity );

	/**
	 * @Description: 更新实体并加锁
	 * @param entity
	 * @param lock
	 *            void 返回值描述
	 * @throws
	 */
	public void updateWithLock( T entity , LockMode lock );
	
	/**
	 * @Description: 存储实体到数据库
	 * @param entity
	 *            void 返回值描述
	 * @throws
	 */
	public void save( T entity );
	
	// saveWithLock()
	
	/**
	 * @Description: 增加或更新实体
	 * @param entity
	 *            void 返回值描述
	 * @throws
	 */
	public void saveOrUpdate( T entity );
	
	/**
	 * @Description: 增加或更新集合中的全部实体
	 * @param entities
	 *            void 返回值描述
	 * @throws
	 */
	public void saveOrUpdateAll( Collection< T > entities );
	
	/**
	 * @Description: 删除指定的实体
	 * @param entity
	 *            void 返回值描述
	 * @throws
	 */
	public void delete( T entity );
	
	/**
	 * @Description: 加锁并删除指定的实体
	 * @param entity
	 * @param lock
	 *            void 返回值描述
	 * @throws
	 */
	public void deleteWithLock( T entity , LockMode lock );
	
	/**
	 * @Description: 根据主键删除指定实体
	 * @param id
	 *            void 返回值描述
	 * @throws
	 */
	public void deleteByKey( PK id );
	
	/**
	 * @Description: 根据主键加锁并删除指定的实体
	 * @param id
	 * @param lock
	 *            void 返回值描述
	 * @throws
	 */
	public void deleteByKeyWithLock( PK id , LockMode lock );
	
	/**
	 * @Description: 删除集合中的全部实体
	 * @param entities
	 *            void 返回值描述
	 * @throws
	 */
	public void deleteAll( Collection< T > entities );
	
	/**
	 * @Description: 查找所有，并分页
	 * @param page
	 *            要返回的页数
	 * @param pageSize
	 *            没有记录数
	 * @return
	 */
	public abstract List< T > findAll( Page page );
	
	/**
	 * @Description: 查找所有，并分页
	 * @param page
	 *            要返回的页数
	 * @param pageSize
	 *            没有记录数
	 * @return
	 */
	public abstract List< T > findAllByEnable( Page page );
	
	public List< T > findAll();
	
	/**
	 * 与findByProperty相似，当properyName == value 时把相应的记录删除
	 */
	public abstract void deleteByProperty( String propertyName , Object value );
	
	public abstract List< T > findByExample( T example );
	
	/**
	 * @Description: 通过属性查找
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性的值
	 * @return
	 */
	public abstract List< T > findByProperty( String propertyName , Object value );
	
	/**
	 * @Description: 通过多个属性查找
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @return
	 */
	public abstract List< T > findByPropertys( String[] propertyNames , Object[] values );
	
	/**
	 * @Description: 通过多个属性查找，并分页， 属性名称数组和属性值数组的序列要对应
	 * 
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            属性值数组
	 * @param page
	 *            页码
	 * @param pageSize
	 *            每页内容条数
	 * @return
	 */
	public List< T > findByPropertys( String[] propertyNames , Object[] values , Page page );
	
	/**
	 * @Description: 通过属性查找，并分页， 属性名称数组和属性值数组的序列要对应
	 * 
	 * @param propertyNames
	 *            属性名称
	 * @param values
	 *            属性值
	 * @param page
	 *            页码
	 * @param pageSize
	 *            每页内容条数
	 * @return
	 */
	public List< T > findByProperty( String propertyName , Object value , Page page );
	
	/**
	 * @Description: 统计所有记录的总数
	 * @return 总数
	 */
	public int countAll();
	
	/**
	 * @Description: 统计数据库中当propertyName=value时的记录总数
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public int countByProperty( String propertyName , Object value );
	
	/**
	 * 统计数据库中当多个propertyName=value时的记录总数
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public int countByPropertys( String[] propertyNames , Object[] values );
	
	/**
	 * @Description: 查找并通过某一属性排序
	 * @param property
	 *            排序依据的顺序
	 * @param isSequence
	 *            是否顺序排序
	 */
	public List< T > findAndOrderByProperty( Page page );
	
	/**
	 * @Description: 查找并通过某一属性排序
	 * @param property
	 *            排序依据的顺序
	 * @param isSequence
	 *            是否顺序排序
	 */
	public List< T > findAllAndOrderByProperty( String propertyName , boolean isSequence );
	
	// -------------------- HSQL
	// ----------------------------------------------
	
	/**
	 * @Description: 使用HSQL语句直接增加、更新、删除实体
	 * @param queryString
	 * @return int 返回值描述
	 * @throws
	 */
	public int bulkUpdate( String queryString );
	
	/**
	 * @Description: 使用带参数的HSQL语句增加、更新、删除实体
	 * @param queryString
	 * @param values
	 * @return int 返回值描述
	 * @throws
	 */
	public int bulkUpdate( String queryString , Object[] values );
	
	/**
	 * @Description: 使用HSQL语句检索数据
	 * @param queryString
	 * @return List 返回值描述
	 * @throws
	 */
	public List< T > find( String queryString );
	
	/**
	 * @Description: 使用带参数的HSQL语句检索数据
	 * @param queryString
	 * @param values
	 * @return List 返回值描述
	 * @throws
	 */
	public List< T > find( String queryString , Object[] values );
	
	/**
	 * @Description: 使用带命名的参数的HSQL语句检索数据
	 * @param queryString
	 * @param paramNames
	 * @param values
	 * @return List 返回值描述
	 * @throws
	 */
	public List findByNamedParam( String queryString , String[] paramNames ,
	        Object[] values );
	
	/**
	 * @Description: 使用命名的HSQL语句检索数据
	 * @param queryName
	 * @return List 返回值描述
	 * @throws
	 */
	public List findByNamedQuery( String queryName );
	
	/**
	 * @Description: 使用带参数的命名HSQL语句检索数据
	 * @param queryName
	 * @param values
	 * @return List 返回值描述
	 * @throws
	 */
	public List findByNamedQuery( String queryName , Object[] values );
	
	/**
	 * @Description: 使用带命名参数的命名HSQL语句检索数据
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @return List 返回值描述
	 * @throws
	 */
	public List findByNamedQueryAndNamedParam( String queryName , String[] paramNames ,
	        Object[] values );
	
	/**
	 * @Description: 使用HSQL语句检索数据，返回 Iterator
	 * @param queryString
	 * @return Iterator 返回值描述
	 * @throws
	 */
	public Iterator iterate( String queryString );
	
	/**
	 * @Description: 使用带参数HSQL语句检索数据，返回 Iterator
	 * @param queryString
	 * @param values
	 * @return Iterator 返回值描述
	 * @throws
	 */
	public Iterator iterate( String queryString , Object[] values );
	
	/**
	 * @Description: 关闭检索返回的 Iterator
	 * @param it
	 *            void 返回值描述
	 * @throws
	 */
	public void closeIterator( Iterator it );
	
	// -------------------------------- Criteria
	// ------------------------------
	
	/**
	 * @Description: 创建与会话无关的检索标准对象
	 * @return DetachedCriteria 返回值描述
	 * @throws
	 */
	public DetachedCriteria createDetachedCriteria();
	
	/**
	 * @Description: 创建与会话绑定的检索标准对象
	 * @return Criteria 返回值描述
	 * @throws
	 */
	public Criteria createCriteria();
	
	/**
	 * @Description: 使用指定的检索标准检索数据
	 * @param criteria
	 * @return List 返回值描述
	 * @throws
	 */
	public List findByCriteria( DetachedCriteria criteria );
	
	/**
	 * @Description: 使用指定的检索标准检索数据，返回部分记录
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return List 返回值描述
	 * @throws
	 */
	public List findByCriteria( DetachedCriteria criteria , int firstResult ,
	        int maxResults );
	
	/**
	 * @Description: 使用指定的实体及属性检索（满足除主键外属性＝实体值）数据
	 * @param entity
	 * @param propertyNames
	 * @return List<T> 返回值描述
	 * @throws
	 */
	public List< T > findEqualByEntity( T entity , String[] propertyNames );
	
	/**
	 * @Description: 使用指定的实体及属性(非主键)检索（满足属性 like 串实体值）数据
	 * @param entity
	 * @param propertyNames
	 * @return List<T> 返回值描述
	 * @throws
	 */
	public List< T > findLikeByEntity( T entity , String[] propertyNames );
	
	/**
	 * @Description: 使用指定的检索标准检索数据，返回指定范围的记录
	 * @param criteria
	 * @return Integer 返回值描述
	 * @throws
	 */
	public Integer getRowCount( DetachedCriteria criteria );
	
	/**
	 * @Description: 使用指定的检索标准检索数据，返回指定统计值
	 * @param criteria
	 * @param propertyName
	 * @param StatName
	 * @return Object 返回值描述
	 * @throws
	 */
	public Object getStatValue( DetachedCriteria criteria , String propertyName ,
	        String StatName );
	
	// -------------------------------- Others
	// --------------------------------
	
	/**
	 * @Description: 加锁指定的实体
	 * @param entity
	 * @param lockMode
	 *            void 返回值描述
	 * @throws
	 */
	public void lock( T entity , LockMode lockMode );
	
	/**
	 * @Description: 强制初始化指定的实体
	 * @param proxy
	 *            void 返回值描述
	 * @throws
	 */
	public void initialize( Object proxy );
	
	/**
	 * @Description: 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新） void 返回值描述
	 * @throws
	 */
	public void flush();
	
	/**
	 * 
	 * @Description: 根据hql返回查询的记录数 count
	 * @param hql
	 * @return int 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午6:48:59
	 */
	public int getCountHQL( String hql , Object[] values );
	
	/**
	 * 
	 * @Description: 根据sql返回查询的记录数 count
	 * @param sql
	 * @return int 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午6:48:59
	 */
	public int getCountSQL( String sql );
	
	/**
	 * 
	 * @Description: 根据所传的SQL语句 查出数据 并分页
	 * @param sql
	 * @param page
	 * @return List<String[]> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午6:42:29
	 */
	public List< String[] > getDateBySQL( String sql , Page page );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * 
	 * @Description: 传sql,分页对象 语句分装 easy ui 所需要的Map
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-5 下午6:34:08
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	public Object getDateBySQL( String sql );
	
	public List< T > findByPropertysOrderBy( String[] propertyNames ,
	        Object[] values , String orderByParam );
	

}
