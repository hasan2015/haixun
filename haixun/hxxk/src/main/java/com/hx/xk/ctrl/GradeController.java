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
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoSchedule;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.service.IGradeService;
import com.hx.xk.service.IScheduleService;
import com.hx.xk.service.IService;
import com.hx.xk.service.ISpecialtyService;
import com.hx.xk.vo.VoOpengrade;

/**
 * @author Hasan
 * @Date 2015-6-9 上午8:22:13
 * 
 */
@Controller
public class GradeController extends AbstractController<DtoGrade, IService<DtoGrade>> {
	private Log log = LogFactory.getLog(GradeController.class);

	@Resource
	private IGradeService gradeService;
	@Resource
	private ISpecialtyService specialtyService;
	@Resource
	private IScheduleService scheduleService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.ctrl.AbstractController#getService()
	 */
	@Override
	protected IService<DtoGrade> getService() {
		return gradeService;
	}

	@ResponseBody
	@RequestMapping(value = "/page/getGradeinfoByGradeid", method = RequestMethod.GET)
	public DtoResult retrieveGradeinfoByGradeid(@RequestParam("gradeid") int gradeid, Pager pager,
			HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			// pager.setResult(re);
			return re;
		}
		// re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, false));
		try {
			DtoGrade grade = gradeService.retrieveById(gradeid);//
			if (grade != null) {
				List<DtoSchedule> lDtoSchedules = scheduleService.retrieveByGradeid(gradeid);
				grade.setYschedules(new HashSet<>(lDtoSchedules));
			}
			re.setResult(grade);
			log.info(" retrieveGradeinfoByGradeid.success!" + grade.getCode());
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			log.error("retrieveGradeinfoByGradeid::" + e);
		}
		return re;
	}

	/**
	 * 根据专业id及其他条件查找 可报班级
	 * 
	 * @param opengrade
	 * @param pager
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/getOpengradesBySpecialtyid", method = RequestMethod.GET)
	public Pager retrieveOpengradesBySpecialtyid(VoOpengrade opengrade, Pager pager, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			pager.setResult(re);
			return pager;
		}
		// re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, false));
		try {
			pager = gradeService.retrieveOpengradeByParams(opengrade.getArea(), opengrade.getWeeks(),
					opengrade.getStarttime(), opengrade.getEndtime(), opengrade.getTerm(), pager, "",
					opengrade.getSpecialtyid());// .retrieveAll());
			log.info(" retrieveOpengradesBySpecialtyid.success!");
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			pager.setResult(re.getResult());
			pager.setCode(re.getCode());
			log.error("retrieveOpengradesBySpecialtyid::" + e);
		}
		return pager;
	}

	/**
	 * 查询存在”可报班级“的 所有的专业列表+可报班级数量。（目前是所有专业）
	 * 
	 * @param pager
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/getSpecialtyOfOpengrade", method = RequestMethod.GET)
	public DtoResult retrieveSpecialtyOfOpengrade(VoOpengrade opengrade, //
			Pager pager, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			pager.setResult(re);
			return re;
		}
		try {
			re.setResult(specialtyService.retrieveSpecialtyOfOpengrade(opengrade.getArea(), opengrade.getTerm(),
					opengrade.getWeeks(), opengrade.getStarttime(), opengrade.getEndtime(), pager));

		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error(" retrieveSpecialtyOfOpengrade.error::" + e);
		}

		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/retAllGrades", method = RequestMethod.GET)
	public Pager retrieveAllGrades(Pager pager, HttpSession session) {
		DtoResult re = new DtoResult();// checkLogin(session);
		// if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
		// pager.setResult(re);
		// return pager;
		// }
		// re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, false));
		try {
			pager = gradeService.retrieveOpengrades(null, null, null, null, null, pager);// .retrieveAll());
			log.info(" retrieveAllGrades.success!");
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			pager.setResult(re.getResult());
			pager.setCode(re.getCode());
			log.error("retrieveAllGrades::" + e);
		}
		return pager;
	}

	@ResponseBody
	@RequestMapping(value = "/page/retGrade", method = RequestMethod.GET)
	public DtoResult retrieveGrade(DtoGrade grade, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			// pager.setResult(re);
			return re;
		}
		re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, false));

		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/refGradeFS", method = RequestMethod.GET)
	public DtoResult retrieveGradeFromServer(DtoGrade grade, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			// pager.setResult(re);
			return re;
		}
		re.setResult(retrieveGrade((DtoUser) re.getResult(), grade, true));

		return re;
	}

	private DtoGrade retrieveGrade(DtoUser user, DtoGrade grade, boolean isRefresh) {
		DtoResult re = new DtoResult();
		try {
			grade = gradeService.retrieveDetail(user, grade, false);
			re.setResult(grade);
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error(" login.error::" + e);
		}

		return grade;

	}
}
