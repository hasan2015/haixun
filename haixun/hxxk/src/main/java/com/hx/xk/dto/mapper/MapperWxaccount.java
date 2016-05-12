/**
 * 
 */
package com.hx.xk.dto.mapper;

import com.hx.xk.dto.DtoWxaccount;
import com.hx.xk.dto.base.mapper.BaseMapper;
import com.hx.xk.entity.Ywxaccount;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:33:28
 * 
 */
public class MapperWxaccount extends BaseMapper<Ywxaccount, DtoWxaccount> {
	MapperUser mapperUser = new MapperUser();

	@Override
	public Ywxaccount dto2entity(DtoWxaccount dto) {
		if (dto != null) {
			Ywxaccount entity = new Ywxaccount();
			copyPropertiesToEntity(dto, entity);
			return entity;
		}

		return null;
	}

	@Override
	public Ywxaccount dto2entity(DtoWxaccount dto, Ywxaccount oldEntity) {
		if (dto != null) {
			Ywxaccount entity = new Ywxaccount();
			copyPropertiesToEntity(dto, oldEntity);
			return entity;
		}

		return null;
	}

	@Override
	public DtoWxaccount entity2Dto(Ywxaccount entity) {
		if (entity != null) {
			DtoWxaccount dto = new DtoWxaccount();
			copyPropertiesToDto(entity, dto);
			return dto;
		}

		return null;
	}

	@Override
	public void copyPropertiesToDto(Ywxaccount entity, DtoWxaccount dto) {
		dto.setWxaccountid(entity.getWxaccountid());
		dto.setOpenid(entity.getOpenid());
		dto.setNickname(entity.getNickname());
		dto.setSex(entity.getSex());
		dto.setProvince(entity.getProvince());
		dto.setCity(entity.getCity());
		dto.setCountry(entity.getCountry());
		dto.setHeadimgurl(entity.getHeadimgurl());
		dto.setPrivilege(entity.getPrivilege());
		dto.setUnionid(entity.getUnionid());
		dto.setStatus(entity.getStatus());

		dto.setUser(mapperUser.entity2Dto(entity.getYuser()));

		super.copyCommmonPropertiesToDto(entity, dto);
	}

	/**
	 * isNu
	 */
	@Override
	public void copyPropertiesToEntity(DtoWxaccount dto, Ywxaccount entity) {
		if (dto.getWxaccountid() != null)
			entity.setWxaccountid(dto.getWxaccountid());
		if (dto.getOpenid() != null)
			entity.setOpenid(dto.getOpenid());
		if (dto.getNickname() != null)
			entity.setNickname(dto.getNickname());
		if (dto.getSex() != null)
			entity.setSex(dto.getSex());
		if (dto.getProvince() != null)
			entity.setProvince(dto.getProvince());
		if (dto.getCity() != null)
			entity.setCity(dto.getCity());
		if (dto.getCountry() != null)
			entity.setCountry(dto.getCountry());
		if (dto.getHeadimgurl() != null)
			entity.setHeadimgurl(dto.getHeadimgurl());
		if (dto.getPrivilege() != null)
			entity.setPrivilege(dto.getPrivilege());
		if (dto.getUnionid() != null)
			entity.setUnionid(dto.getUnionid());
		if (dto.getStatus() != null)
			entity.setStatus(dto.getStatus());
		
		if(dto.getUser()!=null){//必须要有，否则不会存储userid
			entity.setYuser(mapperUser.dto2entity(dto.getUser()));
		}
		
		super.copyCommonPropertiesToEntity(dto, entity);
	}
}
