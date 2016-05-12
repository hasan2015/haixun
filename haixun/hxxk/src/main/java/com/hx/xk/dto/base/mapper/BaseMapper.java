package com.hx.xk.dto.base.mapper;

import org.springframework.beans.BeanUtils;

import com.hx.xk.dto.base.DtoBase;
import com.hx.xk.entity.base.BaseEntity;
import com.hx.xk.entity.base.BaseEntityXk;

/**
 * A base implementation of IConvertor for DTO type is a derived class of entity
 * type.
 * 
 * @param <EntityType>
 * @param <DtoType>
 */
public class BaseMapper<EntityType extends BaseEntity, DtoType extends DtoBase>
		implements IDetailMapper<EntityType, DtoType> {

	@Override
	public EntityType dto2entity(DtoType dto) {
		return null;
	}

	@Override
	public DtoType entity2Dto(EntityType entity) {
		return entity2Dto(entity, true);
	}

	@Override
	public DtoType entity2Dto(EntityType entity, boolean isDetail) {
		return null;
	}

	@Override
	public void copyPropertiesToDto(EntityType entity, DtoType dto) {
		BeanUtils.copyProperties(entity, dto);
	}

	@Override
	public void copyPropertiesToEntity(DtoType dto, EntityType entity) {
		BeanUtils.copyProperties(dto, entity);
	}

	public void copyCommmonPropertiesToDto(EntityType entity, DtoType dto) {
		if (entity instanceof BaseEntityXk) {
			BaseEntityXk entityDds = (BaseEntityXk) entity;
			dto.setCreatedBy(entityDds.getCreatedBy());
			dto.setCreatedDate(entityDds.getCreatedDate());
			dto.setUpdatedBy(entityDds.getUpdatedBy());
			dto.setUpdatedDate(entityDds.getUpdatedDate());
			dto.setActive(entityDds.getActive());
		}
	}

	public void copyCommonPropertiesToEntity(DtoType dto, EntityType entity) {
		if (entity instanceof BaseEntityXk) {
			BaseEntityXk entityDds = (BaseEntityXk) entity;
			if (dto.getCreatedBy() != null)
				entityDds.setCreatedBy(dto.getCreatedBy());
			if (dto.getCreatedDate() != null)
				entityDds.setCreatedDate(dto.getCreatedDate());
			if (dto.getUpdatedBy() != null)
				entityDds.setUpdatedBy(dto.getUpdatedBy());
			if (dto.getUpdatedDate() != null)
				entityDds.setUpdatedDate(dto.getUpdatedDate());
			// if (dto.getStatus() != null)
			entityDds.setActive(dto.getActive());
		}
	}

	@Override
	public boolean hasDetails() {
		return false;
	}

	@Override
	public EntityType dto2entity(DtoType dto, int masterId) throws Exception {
		throw new Exception("dto2entity(DtoType dto, int masterId) is not implemented");
	}

	@Override
	public void copyDetailsToEntity(DtoType dto, EntityType entity) {
		// empty implementation
	}

	@Override
	public void copyDetailsToDto(EntityType entity, DtoType dto) {
		// empty implementation
	}

	@Override
	public void copyPropertiesToDto(EntityType entity, DtoType dto, boolean isDetail) {
		// empty implementation
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.dto.mapper.IMapper#properties2entity(java.lang.Object)
	 */
	// @Override
	// public EntityType properties2entity(DtoType dto) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dd.dds.dto.mapper.IMapper#copyPropertiesToEntity(java.lang.Object,
	 * java.lang.Object, java.lang.Boolean)
	 */
	@Override
	public void copyPropertiesToEntity(DtoType dto, EntityType entity, Boolean isNull) {
		// empty implementation

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.dto.mapper.IMapper#dto2entity(java.lang.Object,
	 * java.lang.Boolean)
	 */
	@Override
	public EntityType dto2entity(DtoType dto, EntityType oldEntity) {
		return dto2entity(dto);
	}
}
