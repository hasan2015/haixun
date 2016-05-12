/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoInstitution;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Yinstitution;

/**
 * 
 * @author Hasan
 * @Date 2015年10月26日 下午3:20:11
 *
 */
public class MapperInstitution extends BaseMapper<Yinstitution, DtoInstitution> {
	MapperUser mapperUser = new MapperUser();

	@Override
	public Yinstitution dto2entity(DtoInstitution dto) {
		if (dto != null) {
			Yinstitution entity = new Yinstitution();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Yinstitution dto2entity(DtoInstitution dto, Yinstitution oldEntity) {
		if (dto != null) {
			Yinstitution entity = new Yinstitution();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoInstitution entity2Dto(Yinstitution entity) {
		if (entity != null) {
			DtoInstitution dto = new DtoInstitution();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Yinstitution entity, DtoInstitution dto) {
		dto.setInstitutionid(entity.getInstitutionid());
		dto.setHomeurl(entity.getHomeurl());
		dto.setName(entity.getName());
		dto.setRefreshdate(entity.getRefreshdate());

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoInstitution dto, Yinstitution entity) {
		if (dto.getInstitutionid() != null)
			entity.setInstitutionid(dto.getInstitutionid());
		if (dto.getHomeurl() != null)
			entity.setHomeurl(dto.getHomeurl());
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getRefreshdate() != null)
			entity.setRefreshdate(dto.getRefreshdate());

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
