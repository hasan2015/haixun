package com.hx.xk.entity.base;

import org.springframework.beans.BeanUtils;

/**
 * A base implementation of IConvertor for DTO type is a derived class 
 * of entity type.
 *  
 * @param <EntityType>
 * @param <DtoType>
 */
@SuppressWarnings("unchecked")
public class BaseConvertor<EntityType, DtoType>
		implements IConvertor<EntityType, DtoType> {

	@Override
	public EntityType dto2entity(DtoType dto) {
		return (EntityType)dto;
	}

	@Override
	public DtoType entity2Dto(EntityType entity) {
		return (DtoType)entity;
	}

	@Override
	public void copyPropertiesToDto(EntityType source, DtoType target) {
		BeanUtils.copyProperties(source, target);
	}

	@Override
	public void copyPropertiesToEntity(DtoType source, EntityType target) {
		BeanUtils.copyProperties(source, target);
	}

}
