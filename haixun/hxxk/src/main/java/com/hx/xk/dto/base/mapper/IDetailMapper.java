package com.hx.xk.dto.base.mapper;

/**
 * 
 * This extends IMapper for detail object.
 *  
 * @param <EntityType>
 * @param <DtoType>
 */
public interface IDetailMapper<EntityType, DtoType> 
		extends IMapper<EntityType, DtoType> {
	/**
	 * Converts a DTO object to an entity object 
	 * with a master id generated already in master object;
	 * 
	 * @param dto
	 * @param master id
	 * @return
	 */
	EntityType dto2entity(DtoType dto, int masterId) throws Exception;
	void copyPropertiesToDto(EntityType entity, DtoType dto,boolean isDetail) ;
}
