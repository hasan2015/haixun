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
import com.hx.xk.dto.DtoMyclock;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.service.IMyclockService;
import com.hx.xk.service.IService;
import com.hx.xk.service.IUserService;

/**
 * 
 * @author Hasan
 * @Date 2016年3月28日 上午8:51:02
 *
 */
@Controller
public class MyclockController extends AbstractController<DtoMyclock, IService<DtoMyclock>> {

	private Log log = LogFactory.getLog(MyclockController.class);

	@Resource
	private IMyclockService myclockService;
	@Resource
	private IUserService userService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.ctrl.AbstractController#getService()
	 */
	@Override
	protected IService<DtoMyclock> getService() {
		return myclockService;
	}

	@ResponseBody
	@RequestMapping(value = "/page/retclocks", method = RequestMethod.GET)
	public DtoResult retrieveAllClocks(HttpSession session) {
		DtoResult re = checkLogin(session);
		if (!re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
			return re;

		try {
			re.setResult(myclockService.retrieveAll());
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			log.error(" retrieveClock=" + e);
		}

		return re;

	}
}
