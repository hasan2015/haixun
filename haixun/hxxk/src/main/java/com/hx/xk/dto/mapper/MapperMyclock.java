/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoMyclock;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Ymyclock;

/**
 * 
 * @author Hasan
 * @Date 2016年3月23日 下午7:47:37
 *
 */
public class MapperMyclock extends BaseMapper<Ymyclock, DtoMyclock> {
	MapperMygrade mapperMygrade = new MapperMygrade(); 

	@Override
	public Ymyclock dto2entity(DtoMyclock dto) {
		if (dto != null) {
			Ymyclock entity = new Ymyclock();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Ymyclock dto2entity(DtoMyclock dto, Ymyclock oldEntity) {
		if (dto != null) {
			Ymyclock entity = new Ymyclock();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoMyclock entity2Dto(Ymyclock entity) {
		if (entity != null) {
			DtoMyclock dto = new DtoMyclock();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Ymyclock entity, DtoMyclock dto) {
		dto.setMyclockid(entity.getMyclockid());
		dto.setName(entity.getName());
		dto.setStarttime(entity.getStarttime());
		dto.setEndtime(entity.getEndtime());
		dto.setIntervalm(entity.getIntervalm());
		dto.setDuration(entity.getDuration());

		dto.setYmygrade(mapperMygrade.entity2Dto(entity.getYmygrade())); 

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	@Override
	public void copyPropertiesToEntity(DtoMyclock dto, Ymyclock entity) {
		if (dto.getMyclockid() != null)
			entity.setMyclockid(dto.getMyclockid());
		if (dto.getName() != null)
			entity.setName(dto.getName());
		if (dto.getStarttime() != null)
			entity.setStarttime(dto.getStarttime());
		if (dto.getEndtime() != null)
			entity.setEndtime(dto.getEndtime());
		if (dto.getIntervalm() != null)
			entity.setIntervalm(dto.getIntervalm());
		if (dto.getDuration() != null)
			entity.setDuration(dto.getDuration());

		if (dto.getYmygrade() != null)
			entity.setYmygrade(mapperMygrade.dto2entity(dto.getYmygrade())); 

		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
