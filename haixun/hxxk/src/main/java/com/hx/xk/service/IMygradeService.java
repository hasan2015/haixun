/**
 * 
 */
package com.hx.xk.service;

import java.util.List;

import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.vo.VoMygrade;
import com.hx.xk.vo.VoMyschedule;
import com.hx.xk.vo.VoYear;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:22:19
 * 
 */
public interface IMygradeService extends IService<DtoMygrade> {

	/**
	 * 
	 * 
	 * @param user
	 * @param pager
	 * @param isRefresh
	 *            =true;从第三方服务器端获取用户的已报班级信息，且间隔时间最小不超过10s;首先更新hxserver数据
	 * @return
	 * @throws Exception
	 */
	public Pager retrieveMygrades(DtoUser user, Pager pager, boolean isRefresh) throws Exception;

	/**
	 * 根据 预约号 查询 我的 班级
	 * 
	 * @param userid
	 * @param reservedcode
	 * @return
	 * @throws Exception
	 */
	public DtoMygrade retrieveByUseridAndReservedcode(Integer userid, String reservedcode) throws Exception;

	public List<VoMygrade> retrieveMygradeByParams(Integer userid, String area, String sYear, String eYear,
			String[] weeks, String starttime, String endtime, String term) throws Exception;

	public List<DtoSpecialty> retrieveSpecialtyOfMygrade(Integer userid, String area, String sYear, String eYear,
			String[] weeks, String starttime, String endtime, String term, Pager pager) throws Exception;

	/**
	 * 根据条件，找到有班级的年度
	 * 
	 * @param userid
	 * @param area
	 * @param weeks
	 * @param starttime
	 * @param endtime
	 * @param term
	 * @return
	 * @throws Exception
	 */
	public List<VoYear> retrieveYearsOfMygrade(Integer userid, String area, String[] weeks, String starttime,
			String endtime, String term) throws Exception;

	public List<VoMyschedule> retrieveMyscheduleByDate(Integer userid, String startdate, String enddate)
			throws Exception;

}
