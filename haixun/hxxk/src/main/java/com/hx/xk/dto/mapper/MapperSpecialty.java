/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Yspecialty;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:33:28
 * 
 */
public class MapperSpecialty extends BaseMapper<Yspecialty, DtoSpecialty> {

	@Override
	public Yspecialty dto2entity(DtoSpecialty dto) {
		if (dto != null) {
			Yspecialty entity = new Yspecialty();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Yspecialty dto2entity(DtoSpecialty dto, Yspecialty oldEntity) {
		if (dto != null) {
			Yspecialty entity = new Yspecialty();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoSpecialty entity2Dto(Yspecialty entity) {
		if (entity != null) {
			DtoSpecialty dto = new DtoSpecialty();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Yspecialty entity, DtoSpecialty dto) {
		dto.setSpecialtyid(entity.getSpecialtyid());
		dto.setName(entity.getName());

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoSpecialty dto, Yspecialty entity) {
		if (dto.getSpecialtyid() != null)
			entity.setSpecialtyid(dto.getSpecialtyid());
		if (dto.getName() != null)
			entity.setName(dto.getName());

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
