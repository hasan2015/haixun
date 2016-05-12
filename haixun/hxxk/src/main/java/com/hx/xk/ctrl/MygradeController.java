/**
 * 
 */
package com.hx.xk.ctrl;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoSchedule;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.DtoWxaccount;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.service.IMygradeService;
import com.hx.xk.service.IScheduleService;
import com.hx.xk.service.IService;
import com.hx.xk.vo.VoMygrade;

/**
 * @author Hasan
 * @Date 2015-6-9 上午8:22:13
 * 
 */
@Controller
public class MygradeController extends AbstractController<DtoMygrade, IService<DtoMygrade>> {
	private Log log = LogFactory.getLog(MygradeController.class);

	@Resource
	private IMygradeService mygradeService;
	@Resource
	private IScheduleService scheduleService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.ctrl.AbstractController#getService()
	 */
	@Override
	protected IService<DtoMygrade> getService() {
		return mygradeService;
	}

	@ResponseBody
	@RequestMapping(value = "/page/getMygradeinfoByid", method = RequestMethod.GET)
	public DtoResult retrieveMygradeinfoByid(@RequestParam("mygradeid") int mygradeid, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			// pager.setResult(re);
			return re;
		}
		// re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, false));
		try {
			DtoMygrade mygrade = mygradeService.retrieveById(mygradeid);//
			if (mygrade != null && mygrade.getYgrade() != null) {
				List<DtoSchedule> lDtoSchedules = scheduleService.retrieveByGradeid(mygrade.getYgrade().getGradeid());
				mygrade.getYgrade().setYschedules(new HashSet<>(lDtoSchedules));
			}
			re.setResult(mygrade);
			log.info(" retrieveMygradeinfoByid.success!" + mygrade.getYgrade().getCode());
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			log.error("retrieveMygradeinfoByid::" + e);
		}
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/getYearsOfMygrade", method = RequestMethod.GET)
	public DtoResult retrieveYearsOfMygrade(VoMygrade mygrade, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {

			return re;
		}
		try {
			DtoWxaccount dtoUser = (DtoWxaccount) re.getResult();
			re.setResult(mygradeService.retrieveYearsOfMygrade(dtoUser.getUser().getUserid(), mygrade.getArea(),
					mygrade.getWeeks(), mygrade.getStarttime(), mygrade.getEndtime(), mygrade.getTerm()));

		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error(" retrieveYearsOfMygrade.error::" + e);
		}

		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/getMygradesByYear", method = RequestMethod.GET)
	public DtoResult retrieveMygradesByYear(VoMygrade mygrade, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			// pager.setResult(re);
			return re;
		}
		// re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, false));
		try {
			DtoWxaccount dtoUser = (DtoWxaccount) re.getResult();
			re.setResult(mygradeService.retrieveMygradeByParams(dtoUser.getUser().getUserid(), mygrade.getArea(),
					mygrade.getYear(), mygrade.getYear(), mygrade.getWeeks(), mygrade.getStarttime(),
					mygrade.getEndtime(), mygrade.getTerm()));
			log.info(" retrieveMygradesByYear.success!");
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			// pager.setCode(re.getCode());
			log.error("retrieveMygradesByYear::" + e);
		}
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/retMygrades", method = RequestMethod.GET)
	public Pager retrieveMygrades(Pager pager, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			pager.setResult(re);
			return pager;
		}

		return retrieveMygrades((DtoUser) re.getResult(), false);
	}

	/**
	 * 根据时间段查找有效班级课表
	 * 
	 * @param pager
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/retMyschedules", method = RequestMethod.GET)
	public DtoResult retrieveMyscheduleByDate(@RequestParam(value = "startdate", required = false) String startdate,
			@RequestParam(value = "enddate", required = false) String enddate, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			// pager.setResult(re);
			return re;
		}

		try {
			DtoWxaccount dtoUser = (DtoWxaccount) re.getResult();
			re.setResult(mygradeService.retrieveMyscheduleByDate(dtoUser.getUser().getUserid(), startdate, enddate));
			log.info(" retrieveMyscheduleByDate.success!");
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			// pager.setCode(re.getCode());
			log.error("retrieveMyscheduleByDate::" + e);
		}
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/refMygrades", method = RequestMethod.GET)
	public Pager retrieveMygradesFromServer(Pager pager, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			pager.setResult(re);
			return pager;
		}

		return retrieveMygrades((DtoUser) re.getResult(), true);
	}

	private Pager retrieveMygrades(DtoUser user, boolean isRefresh) {
		Pager pager = new Pager();
		try {
			pager = mygradeService.retrieveMygrades(user, pager, false);
		} catch (Exception e) {
			DtoResult re = new DtoResult();
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			pager.setResult(re);
			log.error(" login.error::" + e);
		}

		return pager;

	}
}
