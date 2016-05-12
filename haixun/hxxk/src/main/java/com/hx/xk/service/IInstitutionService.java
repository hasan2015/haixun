/**
 * 
 */
package com.hx.xk.service;

import java.sql.Timestamp;

import com.hx.xk.dto.DtoInstitution;
import com.hx.xk.dto.base.DtoResult;

/**
 * 
 * @author Hasan
 * @Date 2015年10月26日 下午4:38:36
 *
 */
public interface IInstitutionService extends IService<DtoInstitution> {
	public DtoResult refreshdateQSN(Timestamp refreshdate) throws Exception;
	
}
