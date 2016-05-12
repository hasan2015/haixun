package com.hx.xk.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dao.IDefaultDao;
import com.hx.xk.dto.base.Pager;

/**
 * @author Hasan
 * 
 */
@Repository("defaultDao")
public class DefaultDaoImpl implements IDefaultDao {
	@PersistenceContext(unitName = "jpa-xk")
	protected EntityManager em;

	@Override
	public <T> T save(T entity) throws Exception {
		em.persist(entity);
		return entity;
	}

	@Override
	public <T> T delete(T entity, Object primaryKey) throws Exception {
		em.remove(em.getReference(entity.getClass(), primaryKey));
		return entity;
	}

	@Override
	public <T> T delete(T entity) throws Exception {
		if (em.contains(entity)) {
			em.remove(entity);
		} else {
			entity = em.merge(entity);
			if (em.contains(entity)) {
				em.remove(entity);
			} else {
				throw new Exception("Why merge() does not work?");
			}
		}
		return entity;
	}

	@Override
	public <T> T update(T entity) throws Exception {
		em.merge(entity);
		return entity;
	}

	@Override
	public <T> T merge(T entity) throws Exception {
		return em.merge(entity);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) throws Exception {
		return em.find(entityClass, primaryKey, properties);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findAll(Class<T> entityClass) throws Exception {
		return this.findAll(entityClass, null, null, XkConstant.PAGENOW_DEF, XkConstant.PAGESIZE_MAX);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findAll(Class<T> entityClass, String orderProperty) throws Exception {
		return this.findAll(entityClass, XkConstant.ORDERBY_ASC, orderProperty, XkConstant.PAGENOW_DEF,
				XkConstant.PAGESIZE_MAX);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findAll(Class<T> entityClass, String orderProperty, int pageNow, int pageSize) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (orderProperty != null)
			map.put(orderProperty, XkConstant.ORDERBY_ASC);

		return this.findAll(entityClass, map, pageNow, pageSize);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findAll(Class<T> entityClass, String order, String orderProperty, int pageNow, int pageSize)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (order != null && orderProperty != null)
			map.put(orderProperty, order);

		return this.findAll(entityClass, map, pageNow, pageSize);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findAll(Class<T> entityClass, Map<String, String> orderPropertites, int pageNow, int pageSize)
			throws Exception {
		return this.findByProperties(entityClass, null, orderPropertites, pageNow, pageSize);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findByProperty(Class<T> entityClass, String whereProperty, Object whereValue) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(whereProperty, whereValue);

		return this.findByProperties(entityClass, map);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties) throws Exception {
		return this.findByProperties(entityClass, whereProperties, XkConstant.PAGENOW_DEF, XkConstant.PAGESIZE_MAX);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, int pageNow,
			int pageSize) throws Exception {
		return this.findByProperties(entityClass, whereProperties, null, null, pageNow, pageSize);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, String orderProperty,
			int pageNow, int pageSize) throws Exception {

		return this.findByProperties(entityClass, whereProperties, XkConstant.ORDERBY_ASC, orderProperty, pageNow,
				pageSize);
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> properties, String order,
			String orderProperty, int pageNow, int pageSize) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (order != null && orderProperty != null)
			map.put(orderProperty, order);

		return this.findByProperties(entityClass, properties, map, pageNow, pageSize);
	}

	@Override
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties, String orderProperty)
			throws Exception {

		return this.findByProperties(entityClass, whereProperties, orderProperty, XkConstant.PAGENOW_DEF,
				XkConstant.PAGESIZE_MAX);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(value = "transactionManager", readOnly = true)
	public <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> whereProperties,
			Map<String, String> orderPropertites, int pageNow, int pageSize) throws Exception {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = cb.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		// where
		if (whereProperties != null && whereProperties.size() > 0) {
			Predicate pre = null;
			int i = 0;
			for (String prop : whereProperties.keySet()) {
				if (prop.startsWith(XkConstant.FUZZY_PROPERTY_PREFIX)) {
					Expression<String> e = root.get(prop.substring(1));
					String value = whereProperties.get(prop).toString();
					boolean notlike = value.startsWith(XkConstant.FUZZY_NOTLINKE);
					int len = XkConstant.FUZZY_NOTLINKE.length();
					if (i++ == 0) {
						pre = notlike ? cb.notLike(e, value.substring(len)) : cb.like(e, value);
					} else {
						pre = cb.and(pre, notlike ? cb.notLike(e, value.substring(len)) : cb.like(e, value));
					}
				} else {
					Path<String> e = getPropByProps(root, prop);
					// 添加where的参数
					if (i++ == 0) {
						pre = cb.equal(e, cb.parameter(Object.class, prop));
					} else
						pre = cb.and(pre, cb.equal(e, cb.parameter(Object.class, prop)));
				}
			}
			criteriaQuery.where(pre);
		}
		// order by
		if (orderPropertites != null && orderPropertites.size() > 0) {
			List<Order> lo = new ArrayList<Order>();
			for (String prop : orderPropertites.keySet()) {
				Path<String> e = getPropByProps(root, prop);
				lo.add(orderPropertites.get(prop).toUpperCase().equals(XkConstant.ORDERBY_ASC) ? cb.asc(e)
						: cb.desc(e));
			}

			criteriaQuery.orderBy(lo);
		}
		//
		Query tq = em.createQuery(criteriaQuery);
		// 为where参数添加输入值
		if (whereProperties != null && whereProperties.size() > 0)
			for (String prop : whereProperties.keySet()) {
				if (prop.startsWith(XkConstant.FUZZY_PROPERTY_PREFIX))
					continue;
				tq.setParameter(prop, whereProperties.get(prop));
			}
		tq.setFirstResult(pageNow * pageSize);
		tq.setMaxResults(pageSize);

		return tq.getResultList();
	}

	private Path<String> getPropByProps(Root<?> root, String prop) {
		String[] props = prop.split("\\.");
		Path<String> e;
		if (props.length > 1) // 兼容联合主键
		{
			e = root.get(props[0]);
			for (int j = 1; j < props.length; j++) {
				e = e.get(props[j]);
			}
		} else
			e = root.get(prop);
		return e;
	}

	@Override
	public <T> List<T> findBySql(Class<T> entityClass, String sql, Map<String, Object> params, int pageNow,
			int pageSize) throws Exception {
		Query tq = em.createQuery(sql);
		// 为where参数添加输入值
		if (params != null && params.size() > 0) {
			// for (int i = 0; i < params.size(); i++) {
			// tq.setParameter(i + 1, params.get(i));
			// }
			for (String prop : params.keySet()) {
				tq.setParameter(prop, params.get(prop));
			}
		}
		tq.setFirstResult(pageNow * pageSize);
		tq.setMaxResults(pageSize);

		return tq.getResultList();
	}

	@Override
	public <T> List<T> findBySql(Class<T> entityClass, String sql, Map<String, Object> params) throws Exception {
		return this.findBySql(entityClass, sql, params, XkConstant.PAGENOW_DEF, XkConstant.PAGESIZE_MAX);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xk.drlds.dao.IBaseDao#find(java.lang.Class, java.lang.Object)
	 */
	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey) throws Exception {
		return this.find(entityClass, primaryKey, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xk.drlds.dao.IBaseDao#findBySql(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public <T> List<T> findBySql(Class<T> entityClass, String sql) throws Exception {
		return this.findBySql(entityClass, sql, new HashMap<String, Object>());
	}

	@Override
	public boolean executeNativeQuery(String sql) {
		em.createNativeQuery(sql); 
		return true;
	}
	@Override
	public int executeQuery(String sql) {
		Query tq =em.createQuery(sql);
		int result=tq.executeUpdate();////影响的记录数
		return result;
	}
	@Override
	public Long getTotalBySql(String sql) throws Exception {
		Query tq = em.createQuery(sql);

		return (Long) tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.dao.IBaseDao#findBySql(java.lang.Class, java.lang.String,
	 * com.hx.xk.dto.Pager)
	 */
	@Override
	public <T> List<T> findBySql(Class<T> entityClass, String sql, Pager pager) throws Exception {
		return this.findBySql(entityClass, sql, null, pager.getPageNow(), pager.getPageSize());
	}
}
