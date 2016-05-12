/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.mapper.MapperResult;
import com.hx.xk.entity.base.Xresult;
import com.hx.xk.service.IResultService;

/**
 * @author Hasan
 * @Date 2015-3-16 下午10:30:58
 * 
 */
@Service("resultService")
public class ResultServiceImpl extends AbstractService<Xresult, DtoResult>implements IResultService {
	private static Log log = LogFactory.getLog(ResultServiceImpl.class);
	private Map<String, DtoResult> results = new HashMap<String, DtoResult>();

	public ResultServiceImpl() {
		setEntityClass(Xresult.class);
		setIdFieldName("resultId");
		setConvertor(new MapperResult());
	}

	@Override
	protected Xresult newEntityInstance() {
		return new Xresult();
	}

	@Override
	protected DtoResult newDtoInstance() {
		return new DtoResult();
	}

	@Override
	protected boolean isPhysicalDeleted() {
		return false;
	}

	@Override
	protected Map<String, Object> dto2Map(DtoResult dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getCode() != null) {
			map.put("code", dto.getCode());
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.IResultService#retrieveByCode(java.lang.String)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public DtoResult retrieveByCode(String code) {
		if (results.size() == 0) {
			List<DtoResult> ldto = new ArrayList<DtoResult>();
			try {
				ldto = retrieveAll();
			} catch (Exception e) {
				log.error("ResultServiceImpl.retrieveByCode.error::" + e);
			}
			for (DtoResult dto : ldto) {
				results.put(dto.getCode(), dto);
			}
		}

		return results.get(code) != null ? results.get(code)
				: new DtoResult(XkConstant.RESULT_CODE_FAILURE, "错误代码未定义！");
	}

}
