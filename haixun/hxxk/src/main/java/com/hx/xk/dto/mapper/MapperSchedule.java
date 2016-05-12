/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoSchedule;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Yschedule;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:33:28
 * 
 */
public class MapperSchedule extends BaseMapper<Yschedule, DtoSchedule> {
	MapperGrade mapperGrade = new MapperGrade();

	@Override
	public Yschedule dto2entity(DtoSchedule dto) {
		if (dto != null) {
			Yschedule entity = new Yschedule();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Yschedule dto2entity(DtoSchedule dto, Yschedule oldEntity) {
		if (dto != null) {
			Yschedule entity = new Yschedule();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoSchedule entity2Dto(Yschedule entity) {
		if (entity != null) {
			DtoSchedule dto = new DtoSchedule();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Yschedule entity, DtoSchedule dto) {
		dto.setScheduleid(entity.getScheduleid());
		dto.setWeek(entity.getWeek());
		dto.setStarttime(entity.getStarttime());
		dto.setEndtime(entity.getEndtime());
		dto.setAddress(entity.getAddress());

		dto.setYgrade(mapperGrade.entity2Dto(entity.getYgrade()));

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoSchedule dto, Yschedule entity) {
		if (dto.getScheduleid() != null)
			entity.setScheduleid(dto.getScheduleid());
		if (dto.getScheduleid() != null)
			entity.setScheduleid(dto.getScheduleid());
		if (dto.getWeek() != null)
			entity.setWeek(dto.getWeek());
		if (dto.getStarttime() != null)
			entity.setStarttime(dto.getStarttime());
		if (dto.getEndtime() != null)
			entity.setEndtime(dto.getEndtime());
		if (dto.getAddress() != null)
			entity.setAddress(dto.getAddress());

		if (dto.getYgrade() != null)
			entity.setYgrade(mapperGrade.dto2entity(dto.getYgrade()));

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
