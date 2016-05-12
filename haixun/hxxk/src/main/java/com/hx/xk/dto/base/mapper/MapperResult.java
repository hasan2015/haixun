/**
 * 
 */
package com.hx.xk.dto.base.mapper;

import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.entity.base.Xresult;

/**
 * @author Hasan
 * @Date 2015-3-16 下午10:20:20
 * 
 */
public class MapperResult extends BaseMapper<Xresult, DtoResult> {

	@Override
	public Xresult dto2entity(DtoResult dto) {
		if (dto != null) {
			Xresult entity = new Xresult();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoResult entity2Dto(Xresult entity) {
		if (entity != null) {
			DtoResult dto = new DtoResult();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Xresult entity, DtoResult dto) {

		dto.setCode(entity.getCode());
		dto.setResult(entity.getResult());

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	@Override
	public void copyPropertiesToEntity(DtoResult dto, Xresult entity) {
		entity.setCode(dto.getCode());
		entity.setResult(dto.getResult().toString());

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}