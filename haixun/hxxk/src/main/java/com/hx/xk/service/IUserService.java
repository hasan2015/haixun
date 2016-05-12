/**
 * 
 */
package com.hx.xk.service;

import java.sql.Timestamp;

import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:22:19
 * 
 */
public interface IUserService extends IService<DtoUser> { 

	/**
	 * 当前一个微信号仅能绑定一个账户（父、母只能管理一个子女）
	 * 
	 * @param id
	 * @param mobile
	 * @param wxaccountid
	 * @return
	 * @throws Exception
	 */
	public DtoResult bind(String id, String mobile, Integer wxaccountid) throws Exception;
	/**
	 * 更新最后访问时间
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DtoResult updateLatestDate(Integer userid,Timestamp date) throws Exception;

}
