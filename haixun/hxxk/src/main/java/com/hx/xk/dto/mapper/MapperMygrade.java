/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Ymygrade;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:33:28
 * 
 */
public class MapperMygrade extends BaseMapper<Ymygrade, DtoMygrade> {
	MapperGrade mapperGrade = new MapperGrade();
	MapperUser mapperUser = new MapperUser();

	@Override
	public Ymygrade dto2entity(DtoMygrade dto) {
		if (dto != null) {
			Ymygrade entity = new Ymygrade();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Ymygrade dto2entity(DtoMygrade dto, Ymygrade oldEntity) {
		if (dto != null) {
			Ymygrade entity = new Ymygrade();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoMygrade entity2Dto(Ymygrade entity) {
		if (entity != null) {
			DtoMygrade dto = new DtoMygrade();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Ymygrade entity, DtoMygrade dto) {
		dto.setMygradeid(entity.getMygradeid());
		dto.setReservedcode(entity.getReservedcode());
		dto.setApplystatus(entity.getApplystatus());
		dto.setOvertime(entity.getOvertime());

		dto.setYuser(mapperUser.entity2Dto(entity.getYuser()));
		dto.setYgrade(mapperGrade.entity2Dto(entity.getYgrade()));

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoMygrade dto, Ymygrade entity) {
		if (dto.getMygradeid() != null)
			entity.setMygradeid(dto.getMygradeid());
		if (dto.getReservedcode() != null)
			entity.setReservedcode(dto.getReservedcode());
		if (dto.getApplystatus() != null)
			entity.setApplystatus(dto.getApplystatus());
		if (dto.getOvertime() != null)
			entity.setOvertime(dto.getOvertime());

		if (dto.getYuser() != null)
			entity.setYuser(mapperUser.dto2entity(dto.getYuser()));
		if (dto.getYgrade() != null)
			entity.setYgrade(mapperGrade.dto2entity(dto.getYgrade()));

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
