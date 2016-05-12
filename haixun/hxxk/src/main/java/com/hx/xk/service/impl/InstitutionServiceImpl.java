/**
 * 
 */
package com.hx.xk.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.DtoInstitution;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.mapper.MapperInstitution;
import com.hx.xk.entity.Yinstitution;
import com.hx.xk.service.IInstitutionService;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("institutionService")
public class InstitutionServiceImpl extends AbstractService<Yinstitution, DtoInstitution>
		implements IInstitutionService {
	public InstitutionServiceImpl() {
		setEntityClass(Yinstitution.class);
		setIdFieldName("institutionid");
		setConvertor(new MapperInstitution());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Yinstitution newEntityInstance() {
		return new Yinstitution();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoInstitution newDtoInstance() {
		return new DtoInstitution();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoInstitution dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IInstitutionService#refreshdate(java.sql.Timestamp)
	 */
	@Override
	@Transactional
	public DtoResult refreshdateQSN(Timestamp refreshdate) throws Exception { 		
		DtoInstitution dtoInstitution = new DtoInstitution();
		dtoInstitution.setRefreshdate(new Timestamp(System.currentTimeMillis()));
		return createOrUpdate(dtoInstitution, XkConstant.V_T_INSTI_ID_QSN);

	}

}
