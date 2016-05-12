/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hx.xk.dto.DtoMyclock;
import com.hx.xk.dto.mapper.MapperMyclock;
import com.hx.xk.entity.Ymyclock;
import com.hx.xk.service.IMyclockService;

/**
 * 
 * @author Hasan
 * @Date 2016年3月28日 上午8:49:09
 *
 */
@Service("myclockService")
public class MyclockServiceImpl extends AbstractService<Ymyclock, DtoMyclock> implements IMyclockService {
	public MyclockServiceImpl() {
		setEntityClass(Ymyclock.class);
		setIdFieldName("myclockid");
		setConvertor(new MapperMyclock());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Ymyclock newEntityInstance() {
		return new Ymyclock();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoMyclock newDtoInstance() {
		return new DtoMyclock();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoMyclock dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		return map;
	}

}
