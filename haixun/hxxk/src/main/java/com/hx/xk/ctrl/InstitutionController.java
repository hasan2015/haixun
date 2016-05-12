/**
 * 
 */
package com.hx.xk.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.DtoInstitution;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.DtoVersion;
import com.hx.xk.service.IInstitutionService;
import com.hx.xk.service.IService;
import com.hx.xk.service.IUserService;
import com.hx.xk.service.IWxaccountService;

/**
 * 
 * @author Hasan
 * @Date 2015年10月26日 下午6:16:56
 *
 */
@Controller
public class InstitutionController extends AbstractController<DtoInstitution, IService<DtoInstitution>> {

	private Log log = LogFactory.getLog(InstitutionController.class);

	@Resource
	private IInstitutionService institutionService;
	@Resource
	private IUserService userService;
	@Resource
	protected IWxaccountService wxaccountService;

	@Resource
	private DtoVersion dtoVersion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.ctrl.AbstractController#getService()
	 */
	@Override
	protected IService<DtoInstitution> getService() {
		return institutionService;
	}

	@ResponseBody
	@RequestMapping(value = "/page/getInstitution", method = RequestMethod.GET)
	public DtoResult retrieveInstitution(HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			return re;
		}
		try {
			DtoInstitution dtoInstitution = institutionService.retrieveById(XkConstant.V_T_INSTI_ID_QSN);
			re.setResult(dtoInstitution);
			log.info(" retrieveInstitution.refreshdate=" + dtoInstitution.getRefreshdate());

		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error("retrieveInstitution::" + e);
		}
		return re;
	}

}
