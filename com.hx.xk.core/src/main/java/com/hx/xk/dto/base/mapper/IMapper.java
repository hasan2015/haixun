package com.hx.xk.dto.base.mapper;

/**
 * A  interface to convert between entity object and DTO object.
 *
 * @param <EntityType>
 * @param <DtoType>
 */
public interface IMapper<EntityType, DtoType> {
	/**
	 * Converts a DTO object to an entity object;
	 * 
	 * @param dto
	 * @return
	 */
	EntityType dto2entity(DtoType dto);
	EntityType dto2entity(DtoType dto,EntityType oldEntity);
	//EntityType properties2entity(DtoType dto);
	
	/**
	 * Converts an entity object to DTO object;
	 * 
	 * @param entity
	 * @return
	 */
	DtoType entity2Dto(EntityType entity);
	DtoType entity2Dto(EntityType entity,boolean isDetail);
	
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
	void copyPropertiesToEntity(DtoType dto, EntityType entity,Boolean isNull);
	
	/**
	 * Indicate if it is a master-detail mapper.
	 * @return
	 */
	boolean hasDetails();

	/**
	 * Copy details from the DTO object to the entity object.
	 * This method must be called after masterId is already generated.
	 * @param dto
	 * @param entity
	 * @param id	
	 */
	void copyDetailsToEntity(DtoType dto, EntityType entity);

	/**
	 * Copy details from the entity object to the DTO object.
	 * @param dto
	 * @param entity
	 * @param id	
	 */
	void copyDetailsToDto(EntityType entity, DtoType dto);
}
