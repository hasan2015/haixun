/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Yuser;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:33:28
 * 
 */
public class MapperUser extends BaseMapper<Yuser, DtoUser> {

	@Override
	public Yuser dto2entity(DtoUser dto) {
		if (dto != null) {
			Yuser entity = new Yuser();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Yuser dto2entity(DtoUser dto, Yuser oldEntity) {
		if (dto != null) {
			Yuser entity = new Yuser();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoUser entity2Dto(Yuser entity) {
		if (entity != null) {
			DtoUser dto = new DtoUser();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Yuser entity, DtoUser dto) {
		dto.setUserid(entity.getUserid());
		dto.setName(entity.getName());
		dto.setId(entity.getId());
		dto.setMobile(entity.getMobile());
		dto.setSchool(entity.getSchool());
		dto.setGrade(entity.getGrade());
		dto.setGender(entity.getGender());
		dto.setPatriarch(entity.getPatriarch());
		dto.setHomephone(entity.getHomephone());
		dto.setBirthday(entity.getBirthday());
		dto.setDescription(entity.getDescription());
		dto.setPassword(entity.getPassword());
		dto.setStatus(entity.getStatus());
		dto.setLatestdate(entity.getLatestdate());

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoUser dto, Yuser entity) {
		if (dto.getUserid() != null)
			entity.setUserid(dto.getUserid());
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getId() != null)
			entity.setId(dto.getId());
		if (dto.getMobile() != null)
			entity.setMobile(dto.getMobile());
		if (dto.getSchool() != null)
			entity.setSchool(dto.getSchool());
		if (dto.getGrade() != null)
			entity.setGrade(dto.getGrade());
		if (dto.getGender() != null)
			entity.setGender(dto.getGender());
		if (dto.getPatriarch() != null)
			entity.setPatriarch(dto.getPatriarch());
		if (dto.getHomephone() != null)
			entity.setHomephone(dto.getHomephone());
		if (dto.getBirthday() != null)
			entity.setBirthday(dto.getBirthday());
		if (dto.getDescription() != null)
			entity.setDescription(dto.getDescription());
		if (dto.getPassword() != null)
			entity.setPassword(dto.getPassword());
		if (dto.getStatus() != null)
			entity.setStatus(dto.getStatus());
		if (dto.getLatestdate() != null)
			entity.setLatestdate(dto.getLatestdate());
		
		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
