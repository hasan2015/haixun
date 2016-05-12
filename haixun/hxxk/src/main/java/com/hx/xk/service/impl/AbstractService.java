package com.hx.xk.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dao.IDefaultDao;
import com.hx.xk.dto.base.DtoBase;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.dto.base.mapper.IMapper;
import com.hx.xk.entity.base.BaseEntity;
import com.hx.xk.entity.base.BaseEntityXk;
import com.hx.xk.service.IService;

/**
 * The abstract service provide the default implementation of IService.
 * 
 * @param <EntityType>
 * @param <DtoType>
 */
public abstract class AbstractService<EntityType extends BaseEntity, DtoType extends DtoBase>
		implements IService<DtoType> {
	private static Log log = LogFactory.getLog(AbstractService.class);

	@Autowired
	protected IDefaultDao defaultDao;

	protected Class<EntityType> entityClass;
	protected IMapper<EntityType, DtoType> convertor;
	protected String idFieldName;

	abstract protected EntityType newEntityInstance();

	abstract protected DtoType newDtoInstance();

	protected Map<String, Object> dto2Map(DtoType dto) {
		return new HashMap<String, Object>();
	}

	protected boolean isPhysicalDeleted() {
		return true;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoType> retrieveAll() throws Exception {
		List<EntityType> list = this.defaultDao.findAll(this.entityClass);
		List<DtoType> ldto = new ArrayList<DtoType>();
		if (list != null && list.size() > 0) {
			for (EntityType entity : list) {
				ldto.add(convertor.entity2Dto(entity));
			}
		}
		return ldto;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoType> retrieveList(int pageNo, int pageSize) throws Exception {
		String sql = "select x from " + this.entityClass.getName() + " x";
		List<EntityType> list = this.defaultDao.findBySql(this.entityClass, sql, null, pageNo, pageSize);
		List<DtoType> ldto = new ArrayList<DtoType>();
		if (list != null && list.size() > 0) {
			for (EntityType entity : list) {
				ldto.add(convertor.entity2Dto(entity));
			}
		}
		return ldto;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public Pager retrieveList(Pager pager) throws Exception {
		// if (pager.getTotal() == null || pager.getTotal() == 0)
		pager.setTotal(defaultDao.getTotalBySql("select count(1) from " + this.entityClass.getName()));
		List<EntityType> list = defaultDao.findByProperties(this.entityClass, null, pager.getPageNow(),
				pager.getPageSize());

		// String sql = "select x from " + this.entityClass.getName() + " x";
		// List<EntityType> list = this.defaultDao.findBySql(this.entityClass,
		// sql, null, pageNo, pageSize);
		List<DtoType> ldto = new ArrayList<DtoType>();
		if (list != null && list.size() > 0) {
			for (EntityType entity : list) {
				ldto.add(convertor.entity2Dto(entity));
			}
		}
		pager.setResult(ldto);
		return pager;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public Pager retrieveNoDeleteList(Pager pager) throws Exception {
		// if (pager.getTotal() == null || pager.getTotal() == 0)
		pager.setTotal(defaultDao.getTotalBySql("select count(x." + idFieldName + ") from " + this.entityClass.getName()
				+ " x " + "where x.status <> " + XkConstant.V_DELETE_STATUS));

		String sql = "select x from " + this.entityClass.getName() + " x where x.status <> "
				+ XkConstant.V_DELETE_STATUS + " order by x.createdDate desc ";

		List<EntityType> list = this.defaultDao.findBySql(this.entityClass, sql, null, pager.getPageNow(),
				pager.getPageSize());
		List<DtoType> ldto = new ArrayList<DtoType>();
		if (list != null && list.size() > 0) {
			for (EntityType entity : list) {
				ldto.add(convertor.entity2Dto(entity));
			}
		}
		pager.setResult(ldto);
		return pager;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoType> retrieveByProperties(Map<String, Object> whereProperties) throws Exception {
		List<EntityType> entities = defaultDao.findByProperties(entityClass, whereProperties);

		whereProperties.put("", "is null");
		if (entities != null) {
			List<DtoType> dtoes = new ArrayList<DtoType>();
			for (EntityType entity : entities) {
				dtoes.add(convertor.entity2Dto(entity));
			}
			return dtoes;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoType> retrieveByProperties(Map<String, Object> whereProperties, int pageNow, int pageSize)
			throws Exception {
		List<EntityType> entities = defaultDao.findByProperties(entityClass, whereProperties, pageNow, pageSize);
		if (entities != null) {
			List<DtoType> dtoes = new ArrayList<DtoType>();
			for (EntityType entity : entities) {
				dtoes.add(convertor.entity2Dto(entity));
			}
			return dtoes;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public Pager retrieveByProperties(Map<String, Object> whereProperties, Pager pager) throws Exception {
		String sql = "select count(x." + idFieldName + ") from " + this.entityClass.getName() + " x where 1=1 ";
		if (whereProperties != null) {
			for (String obj : whereProperties.keySet()) {
				sql += " and " + obj + "= " + whereProperties.get(obj);
			}
		}
		if (pager.getTotal() == null || pager.getTotal() == 0)
			pager.setTotal(defaultDao.getTotalBySql(sql));
		List<EntityType> entities = defaultDao.findByProperties(entityClass, whereProperties, pager.getPageNow(),
				pager.getPageSize());
		if (entities != null) {
			List<DtoType> dtoes = new ArrayList<DtoType>();
			for (EntityType entity : entities) {
				dtoes.add(convertor.entity2Dto(entity));
			}
			pager.setResult(dtoes);
			return pager;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoType> retrieveByDto(DtoType dto) throws Exception {

		List<EntityType> entities = defaultDao.findByProperties(entityClass, dto2Map(dto));
		if (entities != null) {
			List<DtoType> dtoes = new ArrayList<DtoType>();
			for (EntityType entity : entities) {
				dtoes.add(convertor.entity2Dto(entity));
			}
			return dtoes;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoType> retrieveByDto(DtoType dto, int pageNow, int pageSize) throws Exception {
		List<EntityType> entities = defaultDao.findByProperties(entityClass, dto2Map(dto), pageNow, pageSize);
		if (entities != null) {
			List<DtoType> dtoes = new ArrayList<DtoType>();
			for (EntityType entity : entities) {
				dtoes.add(convertor.entity2Dto(entity));
			}
			return dtoes;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public Pager retrieveByDto(DtoType dto, Pager pager) throws Exception {
		Map<String, Object> whereProperties = dto2Map(dto);
		String sql = "select count(x." + idFieldName + ") from " + this.entityClass.getName() + " x where 1=1 ";
		if (whereProperties != null) {
			for (String obj : whereProperties.keySet()) {
				sql += " and " + obj.replace("%", "") + " like '" + whereProperties.get(obj) + "'";
			}
		}
		// if (pager.getTotal() == null || pager.getTotal() == 0)
		pager.setTotal(defaultDao.getTotalBySql(sql));
		List<EntityType> entities = defaultDao.findByProperties(entityClass, dto2Map(dto), pager.getPageNow(),
				pager.getPageSize());
		if (entities != null) {
			List<DtoType> dtoes = new ArrayList<DtoType>();
			for (EntityType entity : entities) {
				dtoes.add(convertor.entity2Dto(entity));
			}
			pager.setResult(dtoes);
			return pager;
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public DtoType retrieveById(Object id) throws Exception {
		EntityType entity = defaultDao.find(this.entityClass, id);
		if (entity != null) {
			return convertor.entity2Dto(entity);
		}

		return null;
	}

	@Override
	@Transactional(value = "transactionManager")
	public DtoResult create(DtoType dto) throws Exception {
		DtoResult result = new DtoResult();
		dto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		EntityType entity = defaultDao.save(convertor.dto2entity(dto));
		if (convertor.hasDetails()) {
			convertor.copyDetailsToEntity(dto, entity);
			entity = defaultDao.merge(entity);
		}
		// result.setCode(XkConstant.RESULT_CODE_SUCCESS);
		result.setResult(convertor.entity2Dto(entity));// entity);
		return result;
	}

	// @Override
	// @Transactional(value = "transactionManager")
	// public DtoResult update(DtoType dto) throws Exception {
	// DtoResult result = new DtoResult();
	// EntityType entity = defaultDao.update(convertor.dto2entity(dto));
	// if (convertor.hasDetails()) {
	// convertor.copyDetailsToEntity(dto, entity);
	// entity = defaultDao.update(entity);
	// }
	// result.setCode(XkConstant.RESULT_CODE_SUCCESS);
	// result.setResult(entity);
	// return result;
	// }

	/**
	 * 该方法废弃
	 */
	// @Override
	// @Transactional(value = "transactionManager")
	// public DtoResult updateProperties(DtoType dto)
	// throws Exception {
	// DtoResult result = new DtoResult();
	// EntityType entity = defaultDao.update(convertor.dto2entity(dto,
	// oldEntity));
	// if (convertor.hasDetails()) {
	// convertor.copyDetailsToEntity(dto, entity);
	// entity = defaultDao.update(entity);
	// }
	// result.setCode(XkConstant.RESULT_CODE_SUCCESS);
	// result.setResult(entity);
	// return result;
	// }

	@Override
	@Transactional(value = "transactionManager")
	public DtoResult createOrUpdate(DtoType dto, Object id) throws Exception {
		DtoResult re = new DtoResult();
		List<EntityType> lsd = defaultDao.findByProperty(this.entityClass, idFieldName, id);

		if (lsd != null && lsd.size() > 0) {
			EntityType entity = lsd.get(0);
			
			dto.setUpdatedDate(new Timestamp(System.currentTimeMillis()));			
			convertor.copyPropertiesToEntity(dto, entity);
			if (convertor.hasDetails()) {
				convertor.copyDetailsToEntity(dto, entity);
			}
			entity = defaultDao.update(entity);
			re.setResult(convertor.entity2Dto(entity));
		} else {
			dto.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			re = create(dto);
		}
		// re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		return re;
	}

	@Override
	@Transactional(value = "transactionManager")
	public DtoResult delete(Object id) throws Exception {
		DtoResult re = new DtoResult();
		List<EntityType> list = defaultDao.findByProperty(this.entityClass, idFieldName, id);

		if (list != null && list.size() > 0) {
			EntityType entity = list.get(0);
			if (!isPhysicalDeleted() && entity instanceof BaseEntityXk) {
				BaseEntityXk entityDds = (BaseEntityXk) entity;
				entityDds.setActive(XkConstant.V_FALSE);
				defaultDao.update(entityDds);
			} else {
				defaultDao.delete(entity);
			}

		}

		re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		return re;
	}

	public boolean retrieveByName(Long id, String name) throws Exception {
		boolean flag = false;
		String sql = "select count(x." + idFieldName + ") from " + this.entityClass.getName() + " x where 1=1 ";
		if (null != id) {
			sql += "  and x." + idFieldName + " not in (" + id + ") ";
		}
		if (null != name) {
			sql += "  and x.name = '" + name + "' ";
		}
		Long total = defaultDao.getTotalBySql(sql);
		if (total > 0) {
			flag = true;
		}
		return flag;
	}

	// -- getter and setter --

	public Class<EntityType> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<EntityType> entityClass) {
		this.entityClass = entityClass;
	}

	public IMapper<EntityType, DtoType> getConvertor() {
		return convertor;
	}

	public void setConvertor(IMapper<EntityType, DtoType> convertor) {
		this.convertor = convertor;
	}

	public String getIdFieldName() {
		return idFieldName;
	}

	public void setIdFieldName(String idFieldName) {
		this.idFieldName = idFieldName;
	}
}
