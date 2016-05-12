package com.hx.xk.dao;

import java.util.List;
import java.util.Map;

import com.hx.xk.dto.base.Pager;

/**
 * 
 * @author Hasan
 * @date 2014-3-28 下午6:36:15
 * 
 */
public interface IBaseDao {
	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T> T save(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	public <T> T delete(T entity, Object primaryKey) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T> T delete(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T> T update(T entity) throws Exception;

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <T> T merge(T entity) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param primaryKey
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	public <T> T find(Class<T> entityClass, Object primaryKey) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass, String orderProperty) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param order
	 * @param orderProperty
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass, String order, String orderProperty, int pageNow, int pageSize)
			throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param order
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass, String orderProperty, int pageNow, int pageSize) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param orderPropertites
	 *            Map<property,order>
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAll(Class<T> entityClass, Map<String, String> orderPropertites, int pageNow, int pageSize)
			throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param Property
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String whereProperty, Object whereValue) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param properties
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, int pageNow,
			int pageSize) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param properties
	 * @param orderProperty
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, String orderProperty,
			int pageNow, int pageSize) throws Exception;

	/**
	 * 
	 * @Title: findByProperties @Description: TODO @param entityClass @param
	 * whereProperties @param orderProperty @return @throws Exception @return
	 * List<T> @throws
	 */
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, String orderProperty)
			throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param properties
	 * @param order
	 * @param orderProperty
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, String order,
			String orderProperty, int pageNow, int pageSize) throws Exception;

	/**
	 * 
	 * @param entityClass
	 * @param whereProperties
	 * @param orderPropertites
	 * @param pageNow
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties,
			Map<String, String> orderPropertites, int pageNow, int pageSize) throws Exception;

	/**
	 * 
	 * @Title: findBySql @Description: sql @param entityClass @param sql @param
	 * params @param pageNow @param pageSize @return @throws Exception @return
	 * List<T> @throws
	 */
	public <T> List<T> findBySql(Class<T> entityClass, String sql, Map<String, Object> params, int pageNow,
			int pageSize) throws Exception;

	/**
	 * 
	 * @Title: findBySql @Description: 默认0页，10000页宽 @param entityClass @param
	 * sql @param params @return @throws Exception @return List<T> @throws
	 */
	public <T> List<T> findBySql(Class<T> entityClass, String sql, Map<String, Object> params) throws Exception;

	public <T> List<T> findBySql(Class<T> entityClass, String sql) throws Exception;

	public <T> List<T> findBySql(Class<T> entityClass, String sql, Pager pager) throws Exception;

	boolean executeNativeQuery(String sql);

	int executeQuery(String sql);
	
	public Long getTotalBySql(String sql) throws Exception;
}
