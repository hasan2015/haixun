/**
 * 
 */
package com.hx.xk.service;

import java.util.List;

import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:22:19
 * 
 */
public interface IExtractQsnService {
	/**
	 * 匿名访问
	 * 
	 * @return
	 */
	public DtoResult login();

	/**
	 * 匿名登录，刷新可报班级信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public DtoResult extractOpenGrade() throws Exception;

	public DtoResult login(String name, String pwd);

	public DtoResult logout(String name);

	public List<DtoMygrade> extractMygrade(DtoUser user) throws Exception;

	public DtoGrade extractGrade(DtoUser user, DtoGrade grade) throws Exception;

	public DtoResult extractMyinfo(DtoUser user, String areaid) throws Exception;
}
