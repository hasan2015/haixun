/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hx.xk.dto.DtoWxaccount;
import com.hx.xk.dto.mapper.MapperWxaccount;
import com.hx.xk.entity.Ywxaccount;
import com.hx.xk.service.IWxaccountService;

/**
 * 
 * @author Hasan
 * @Date 2015年10月23日 下午12:00:22
 *
 */
@Service("wxaccountService")
public class WxaccountServiceImpl extends AbstractService<Ywxaccount, DtoWxaccount>implements IWxaccountService {
	public WxaccountServiceImpl() {
		setEntityClass(Ywxaccount.class);
		setIdFieldName("openid");
		setConvertor(new MapperWxaccount());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Ywxaccount newEntityInstance() {
		return new Ywxaccount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoWxaccount newDtoInstance() {
		return new DtoWxaccount();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoWxaccount dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (dto.getOpenid() != null) {
			map.put("openid", dto.getOpenid());
		}
		if (dto.getWxaccountid() != null) {
			map.put("wxaccountid", dto.getWxaccountid());
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IWxaccountService#bind(com.hx.xk.dto.DtoWxaccount)
	 */

}
