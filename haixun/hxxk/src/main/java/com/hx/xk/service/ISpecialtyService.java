/**
 * 
 */
package com.hx.xk.service;

import java.util.List;

import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.base.Pager;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:22:19
 * 
 */
public interface ISpecialtyService extends IService<DtoSpecialty> {
	public List<DtoSpecialty> retrieveSpecialtyOfOpengrade(String area, String term, String[] period, String starttime,
			String endtime, Pager pager) throws Exception;

	public List<DtoSpecialty> retrieveSpecialtyOfOpengrade(Pager pager) throws Exception;
}
