package com.hx.xk.entity.base;

/**
 * A  interface to convert between entity object and DTO object.
 *  
 * @param <EntityType>
 * @param <DtoType>
 */
public interface IConvertor<EntityType, DtoType> {
	/**
	 * Converts a DTO object to an entity object;
	 * 
	 * @param dto
	 * @return
	 */
	EntityType dto2entity(DtoType dto);
	
	/**
	 * Converts an entity object to DTO object;
	 * 
	 * @param entity
	 * @return
	 */
	DtoType entity2Dto(EntityType entity);
	
	/**
	 * Copy field values from the entity object to the DTO object.
	 * @param entity
	 * @param dto
	 */
	void copyPropertiesToDto(EntityType entity, DtoType dto);

	/**
	 * Copy field values from the DTO object to the entity object.
	 * @param entity
	 * @param dto
	 */
	void copyPropertiesToEntity(DtoType dto, EntityType entity);
}
