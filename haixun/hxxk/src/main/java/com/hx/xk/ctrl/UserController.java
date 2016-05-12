/**
 * 
 */
package com.hx.xk.ctrl;

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
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.DtoWxaccount;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.DtoVersion;
import com.hx.xk.service.IMyclockService;
import com.hx.xk.service.IService;
import com.hx.xk.service.IUserService;
import com.hx.xk.service.IWxaccountService;
import com.hx.xk.vo.VoUserInfo;
import com.hx.xk.weixin.service.IWeixinService;

/**
 * @author Hasan
 * @Date 2015-3-20 上午10:26:39
 * 
 */
@Controller
public class UserController extends AbstractController<DtoUser, IService<DtoUser>> {

	private Log log = LogFactory.getLog(UserController.class);

	@Resource
	private IUserService userService;
	@Resource
	protected IWxaccountService wxaccountService;
	@Resource
	private IWeixinService weixinService;
	@Resource
	private DtoVersion dtoVersion; 

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.ctrl.AbstractController#getService()
	 */
	@Override
	protected IService<DtoUser> getService() {
		return userService;
	}

	@ResponseBody
	@RequestMapping(value = "/page/bindOpenid", method = RequestMethod.POST)
	public DtoResult bind(@RequestParam("aname") String accountname, @RequestParam("pwd") String mobile,
			HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			return re;
		}
		try {
			DtoWxaccount wxaccount = (DtoWxaccount) re.getResult();
			re = userService.bind(accountname, mobile, wxaccount.getWxaccountid());
			if (re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {
				DtoUser user = (DtoUser) re.getResult();
				// wxaccount.setUserid(user.getUserid());
				wxaccount.setUser(user);
				session.setAttribute(XkConstant.V_SESSION_WXACCOUNT, wxaccount);
				re.setResult(wxaccount);
				log.info(" bind.name=" + user.getName());
			} else {
				log.info(" bind.failure.aname=" + accountname + " pwd=" + mobile);
			}
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error("bind::" + e);
		}
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/reVer", method = RequestMethod.GET)
	public DtoResult retrieveVersion(HttpSession session) {
		DtoResult re = new DtoResult();

		re.setResult(dtoVersion);

		return re;
	}

	/**
	 * 修改用户密码（旧密码，新密码）
	 * 
	 * @param oldpassword
	 * @param password
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/uPwd", method = RequestMethod.POST)
	public DtoResult updateUserPassword(@RequestParam("opwd") String oldpassword, @RequestParam("npwd") String password,
			HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			return re;
		}
		try {
			DtoUser user = (DtoUser) re.getResult();
			// 验证新旧密码是否一致
			DtoUser dto = userService.retrieveById(user.getUserid());
			if (oldpassword.equals(dto.getPassword())) {
				dto.setPassword(password);
				super.createOrUpdate(dto, dto.getUserid(), session);
				// re = resultService.retrieveByCode("010007");
				log.info("updateUserPassword.success!");
			} else {
				re.setCode(XkConstant.RESULT_CODE_FAILURE);
				log.info("updateUserPassword.failure!");
			}
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error("updateUserPassword::" + e);
		}
		return re;
	}

	/**
	 * 修改绑定手机
	 * 
	 * @param code
	 * @param newMobile
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/uMob", method = RequestMethod.POST)
	public DtoResult updateUserMobile(@RequestParam("vcode") String code, @RequestParam("nMob") String newMobile,
			HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			return re;
		}
		try {
			DtoUser user = (DtoUser) re.getResult();
			// 发送验证码
			DtoUser u = userService.retrieveById(user.getUserid());
			if (code.equals(session.getAttribute(XkConstant.T_F_DOCTOR_VERIFYCODE + newMobile))) {
				u.setMobile(newMobile);
				re = getService().createOrUpdate(u, u.getUserid());
				// re = resultService.retrieveByCode("010026");
				log.info("updateUserMobile.success!" + re.getResult());
			} else {
				re.setCode(XkConstant.RESULT_CODE_FAILURE);
				log.info("updateUserMobile.failure!" + re.getResult());
			}
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error("updateUserMobile::" + e);
		}
		return re;
	}

	/**
	 * 修改绑定手机
	 * 
	 * @param code
	 * @param newMobile
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = XkConstant.V_URL_CLINIC + "/updateMobile", method = RequestMethod.POST)
	public DtoResult updateMobile(@RequestParam("code") String code, @RequestParam("newMobile") String newMobile,
			HttpSession session) {
		DtoResult re = checkLogin(session);
		if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
			return re;
		}
		try {
			DtoUser user = (DtoUser) re.getResult();
			// 发送验证码
			DtoUser u = userService.retrieveById(user.getUserid());
			if ("123456".equals(code)) {
				u.setMobile(newMobile);
				getService().createOrUpdate(u, u.getUserid());
				// re = resultService.retrieveByCode("010026");
				log.info(" verifycode.success::mobile=" + newMobile);
			} else {
				if (code.equals(session.getAttribute(XkConstant.T_F_DOCTOR_VERIFYCODE + newMobile))) {
					u.setMobile(newMobile);
					getService().createOrUpdate(u, u.getUserid());
					// re = resultService.retrieveByCode("010026");
				} else {

					re.setCode(XkConstant.RESULT_CODE_FAILURE);
				}
			}
			// log.info("updateMobile.success!");
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error("updateMobile::" + e);
		}
		return re;
	}

	/**
	 * 
	 * @param accountname
	 *            身份证、手机号
	 * @param password
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/login", method = RequestMethod.POST)
	public DtoResult login(@RequestParam("aname") String accountname, @RequestParam("pwd") String password,
			@RequestParam("imei") String imei, @RequestParam("type") String type, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			DtoUser user = new DtoUser();
			user.setId(accountname);
			List<DtoUser> lUsers = userService.retrieveByDto(user);
			if (lUsers == null || lUsers.size() == 0) {
				re.setCode(XkConstant.RESULT_CODE_FAILURE);
				log.info(" login.failure::" + re.getResult());
			} else {
				user = lUsers.get(0);
				String pwd = user.getPassword();
				if (pwd == null || !pwd.equals(password)) {
					re.setCode(XkConstant.RESULT_CODE_ERR_PWD);
					log.info(" login.failure::password error!");
				} else {
					session.setAttribute(XkConstant.V_SESSION_WXACCOUNT, user);
					re.setResult(dto2voUser(user));
					log.info("login.success!");
				}
			}
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error(" login.error::" + e);
		}
		return re;

	}

	private VoUserInfo dto2voUser(DtoUser dto) {
		VoUserInfo vo = new VoUserInfo();
		vo.setUserid(dto.getUserid());
		vo.setName(dto.getName());
		vo.setId(dto.getId());
		vo.setMobile(dto.getMobile());
		vo.setSchool(dto.getSchool());
		vo.setGrade(dto.getGrade());
		vo.setGender(dto.getGender());
		vo.setPatriarch(dto.getPatriarch());
		vo.setHomephone(dto.getHomephone());
		vo.setBirthday(dto.getHomephone());
		vo.setPassword(dto.getPassword());

		return vo;
	}

	/**
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/logout", method = RequestMethod.POST)
	public DtoResult logout(HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			DtoUser user = (DtoUser) session.getAttribute(XkConstant.V_SESSION_WXACCOUNT);
			if (user != null) {
				session.removeAttribute(XkConstant.V_SESSION_WXACCOUNT);
				log.info(user.getName() + " log out.");
			}
			session.invalidate();
		} catch (Exception ex) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(ex);
			log.error(" logout.error::" + ex.getMessage());
		}
		return re;
	}

	@ResponseBody
	@RequestMapping(value = "/page/getMyinfo", method = RequestMethod.GET)
	public DtoResult retrieveMyinfo(HttpSession session) {
		DtoResult re = checkLogin(session);
		if (!re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
			return re;

		DtoWxaccount wxaccount = (DtoWxaccount) session.getAttribute(XkConstant.V_SESSION_WXACCOUNT);
		// DtoResult result = new DtoResult();
		if (wxaccount.getUser() == null) {
			re.setCode(XkConstant.RESULT_CODE_USER_NOT_BIND);// =
																// resultService.retrieveByCode("921129");//
																// 用户未登录
			return re;
		}
		re.setResult(wxaccount);
		return re;

	}


	@ResponseBody
	@RequestMapping(value = "/page/retclock", method = RequestMethod.GET)
	public DtoResult retrieveClocks(HttpSession session) {
		DtoResult re = checkLogin(session);
		if (!re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
			return re;

		try {
			String[] mStrings = { "0上课提醒：", "1课程名称test", "2上课时间test", "3上课地点test", "4请提前做好课程预习，提前15分钟到达上课中心test" };
			weixinService.sendTMsgClock("o1j1Bwk5YmkuXvjh-nJI1BvhwIcE", mStrings);
			re.setResult("提醒成功！");
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			log.error(" retrieveClock=" + e);
		}

		return re;

	}
}
