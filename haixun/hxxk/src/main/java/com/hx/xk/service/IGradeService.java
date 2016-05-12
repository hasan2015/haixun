/**
 * 
 */
package com.hx.xk.service;

import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.Pager;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:22:19
 * 
 */
public interface IGradeService extends IService<DtoGrade> {
	/**
	 * 获得班级的详细信息
	 * 
	 * @param grade
	 * @param isRefresh
	 *            =true;从第三方服务器端获取用户的已报班级信息，且间隔时间最小不超过60s;首先更新hxserver数据
	 * @return
	 * @throws Exception
	 */
	public DtoGrade retrieveDetail(DtoUser user, DtoGrade grade, boolean isRefresh) throws Exception;

	/**
	 * //根据 区域、学期、专业、时间段（周、时分hh:mm）查询可报班级（applystatus="",即状态不为null）
	 *
	 * @param area
	 * @param week
	 * @param starttime
	 * @param endtime
	 * @param term
	 * @param pager
	 * @param applystatus
	 * @param specialtyid
	 * @return
	 * @throws Exception
	 */
	public Pager retrieveOpengradeByParams(String area, String[] weeks, String starttime, String endtime, String term,
			Pager pager, String applystatus, Integer specialtyid) throws Exception;

	public Pager retrieveOpengradesBySpecialtyid(String area, String[] weeks, String starttime, String endtime,
			String term, Pager pager, Integer specialtyid) throws Exception;

	public Pager retrieveOpengrades(String area, String[] weeks, String starttime, String endtime, String term,
			Pager pager) throws Exception;

	public DtoGrade retrieveByLinkAndCode(String link,String code) throws Exception;
}
