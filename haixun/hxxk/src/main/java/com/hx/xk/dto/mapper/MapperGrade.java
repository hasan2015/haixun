/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Ygrade;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:33:28
 * 
 */
public class MapperGrade extends BaseMapper<Ygrade, DtoGrade> {

	MapperSpecialty mapperSpecialty = new MapperSpecialty();

	@Override
	public Ygrade dto2entity(DtoGrade dto) {
		if (dto != null) {
			Ygrade entity = new Ygrade();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Ygrade dto2entity(DtoGrade dto, Ygrade oldEntity) {
		if (dto != null) {
			Ygrade entity = new Ygrade();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoGrade entity2Dto(Ygrade entity) {
		if (entity != null) {
			DtoGrade dto = new DtoGrade();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Ygrade entity, DtoGrade dto) {
		dto.setGradeid(entity.getGradeid());
		dto.setAreaid(entity.getAreaid());
		dto.setCode(entity.getCode());
		dto.setArea(entity.getArea());
		dto.setYear(entity.getYear());
		dto.setTerm(entity.getTerm());
		dto.setDegree(entity.getDegree());
		dto.setStartdate(entity.getStartdate());
		dto.setEnddate(entity.getEnddate());
		dto.setTimes(entity.getTimes());
		dto.setType(entity.getType());
		dto.setPhone(entity.getPhone());
		dto.setCostdetail(entity.getCostdetail());
		dto.setCost(entity.getCost());
		dto.setDescription(entity.getDescription());
		dto.setAgelimit(entity.getAgelimit());
		dto.setGenderlimit(entity.getGenderlimit());
		dto.setAbility(entity.getAbility());
		dto.setManner(entity.getManner());
		dto.setLink(entity.getLink());
		dto.setApplystatus(entity.getApplystatus());

		dto.setYspecialty(mapperSpecialty.entity2Dto(entity.getYspecialty()));

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoGrade dto, Ygrade entity) {
		if (dto.getGradeid() != null)
			entity.setGradeid(dto.getGradeid());
		if (dto.getAreaid() != null)
			entity.setAreaid(dto.getAreaid());
		if (dto.getCode() != null)
			entity.setCode(dto.getCode());
		if (dto.getArea() != null)
			entity.setArea(dto.getArea());
		if (dto.getYear() != null)
			entity.setYear(dto.getYear());
		if (dto.getTerm() != null)
			entity.setTerm(dto.getTerm());
		if (dto.getDegree() != null)
			entity.setDegree(dto.getDegree());
		if (dto.getStartdate() != null)
			entity.setStartdate(dto.getStartdate());
		if (dto.getEnddate() != null)
			entity.setEnddate(dto.getEnddate());
		if (dto.getTimes() != null)
			entity.setTimes(dto.getTimes());
		if (dto.getType() != null)
			entity.setType(dto.getType());
		if (dto.getPhone() != null)
			entity.setPhone(dto.getPhone());
		if (dto.getDescription() != null)
			entity.setDescription(dto.getDescription());
		if (dto.getCostdetail() != null)
			entity.setCostdetail(dto.getCostdetail());
		if (dto.getCost() != null)
			entity.setCost(dto.getCost());
		if (dto.getDescription() != null)
			entity.setDescription(dto.getDescription());
		if (dto.getAgelimit() != null)
			entity.setAgelimit(dto.getAgelimit());
		if (dto.getGenderlimit() != null)
			entity.setGenderlimit(dto.getGenderlimit());
		if (dto.getAbility() != null)
			entity.setAbility(dto.getAbility());
		if (dto.getManner() != null)
			entity.setManner(dto.getManner());
		if (dto.getLink() != null)
			entity.setLink(dto.getLink());
		if (dto.getApplystatus() != null)
			entity.setApplystatus(dto.getApplystatus());

		if (dto.getYspecialty() != null)
			entity.setYspecialty(mapperSpecialty.dto2entity(dto.getYspecialty()));

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
