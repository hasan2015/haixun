package com.hx.xk.weixin.ctrl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkPropConfigurer;
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.DtoWxaccount;
//import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IGradeService;
import com.hx.xk.service.IResultService;
import com.hx.xk.service.IUserService;
import com.hx.xk.service.IWxaccountService;
import com.hx.xk.service.impl.WxaccountServiceImpl;
import com.hx.xk.weixin.common.WxUtil;
import com.hx.xk.weixin.dto.mapper.DtoWxPageAuthSuccessResult;
import com.hx.xk.weixin.handler.XkWxHandler;
import com.hx.xk.weixin.mp.web.WxSpringSupport;
import com.hx.xk.weixin.service.IWeixinService;

/**
 * 
 * @author Hasan
 * @Date 2015-8-12 下午10:11:25
 * 
 */
@Controller
public class WexinController extends WxSpringSupport {

	private Log log = LogFactory.getLog(WexinController.class);
	@Resource
	private IResultService resultService;
	@Resource
	private IWeixinService wexinService;
	@Resource
	private IUserService userService;
	@Resource
	private IExtractQsnService extractQsnService;
	@Resource
	private IGradeService gradeService;
	@Resource
	private IWxaccountService wxaccountService;

	@Override
	protected void init() {
		this.setMpAct(WxUtil.getMPAct());
		// 可实现自己的WxHandler
		this.setWxHandler(new XkWxHandler());
	}

	/**
	 * 微信api专用
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = XkConstant.V_URL_ROOT_WEIXIN + "/core", produces = { "text/plain;charset=UTF-8" })
	@ResponseBody
	public String wxCore(HttpServletRequest req) {
		String reply = "";
		try {
			// reply = wxInteract(req);
			// 区分POST与GET来源
			String method = req.getMethod();
			// 微信接入验证
			if ("GET".equals(method)) {
				reply = wxInteractGet(req);
			} else {
				reply = wxInteractPost(req);
				//
				// if (om != null) {
				// String topenid = om.getFromUserName();
				// req.getSession().setAttribute(XkConstant.S_SESSION_OPENID,
				// om.getToUserName());
				// req.getSession().setAttribute(XkConstant.S_SESSION_TOPENID,
				// topenid);
				// DtoTenant tenant = tenantService.retrieveByTopenid(topenid);
				//
				// if (tenant != null){
				// req.getSession().setAttribute(
				// XkConstant.S_SESSION_TENANT, tenant);
				// }
				//
				// reply = this.getWxBase().convert2XML(om);
				// }
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return reply;
	}

	/**
	 * 
	 * @param url
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = XkConstant.V_URL_ROOT_WEIXIN + "/getJsAPISignByUrl", method = RequestMethod.GET)
	public DtoResult getJsAPISignByUrl(String url, HttpSession session) {
		DtoResult re = new DtoResult();
		// WxApi wxApi = new WxApiImpl(WxUtil.getMPAct());
		try {
			re = wexinService.getJsAPISignByUrl(url);
			log.info("getJsAPISignByUrl.url=" + url);
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error("getJsAPISignByUrl::" + e);
		}

		return re;
	}

	/**
	 * 创建菜单
	 */
	@ResponseBody
	@RequestMapping(value = XkConstant.V_URL_ROOT_WEIXIN + "/createmenu", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	public DtoResult createmenus(String[] menus, HttpSession session) {
		DtoResult re = new DtoResult();
		// DtoResult re = new DtoResult();// checkLogin(session);
		// if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
		// re.setResponseCode(Integer.parseInt(re.getCode()));
		// re.setResponseMessage(re.getResult().toString());
		// return re;
		// }
		try {
			re = wexinService.createMenu(menus);

		} catch (Exception e) {
			// re = resultService.retrieveByCode("030001");

			log.error("receviceBloodpressure::" + e);
		}
		return re;
	}

	/**
	 * 发送模版消息
	 * 
	 * @param msgs
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = XkConstant.V_URL_ROOT_WEIXIN + "/sendTemplateMsg", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	public DtoResult sendTemplateMsg(String[] msgs, HttpSession session) {
		DtoResult re = new DtoResult();
		// DtoResult re = new DtoResult();// checkLogin(session);
		// if (re.getCode() != XkConstant.RESULT_CODE_SUCCESS) {
		// re.setResponseCode(Integer.parseInt(re.getCode()));
		// re.setResponseMessage(re.getResult().toString());
		// return re;
		// }
		try {
			// re = wexinService.sendTMsgBPDetect("11", "22", "33", "44", "55",
			// "66");

		} catch (Exception e) {
			// re = resultService.retrieveByCode("030001");

			log.error("sendTemplateMsg::" + e);
		}
		return re;
	}

	/**
	 * 访问myinfo-checkLogin-getcode-getToken-返回myinfo.jsp
	 * 访问myinfo-checkLogin-refreshToken-返回myinfo.jsp
	 * 访问myinfo-checkLogin-返回myinfo.jsp
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = XkConstant.V_URL_ROOT_WEIXIN + "/getMyinfo", method = RequestMethod.GET)
	public DtoResult retrieveMyinfo(HttpSession session) {
		DtoResult re = checkLogin(session);
		if (!re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
			return re;

		Object openid = session.getAttribute(XkConstant.S_SESSION_OPENID);
		Object pageAuthSuccessResult = session.getAttribute(XkConstant.S_SESSION_ACCESSTOKEN);
		try {
			// 刷新微信资料
			re = wexinService.reshreshWxaccountInfo(
					((DtoWxPageAuthSuccessResult) pageAuthSuccessResult).getAccess_token(), openid.toString(), "zh_CN");
			if (re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
				log.info(" retrieveMyinfo.wxnickname=" + ((DtoWxaccount) re.getResult()).getNickname());
		} catch (Exception e) {
			re.setCode(XkConstant.RESULT_CODE_FAILURE);
			re.setResult(e);
			log.error(" retrieveMyinfo::" + e);
		}

		return re;

	}

	/**
	 * 网页访问入口
	 */
	@ResponseBody
	@RequestMapping(value = XkConstant.V_URL_ROOT_WEIXIN + "/pageentry", method = RequestMethod.GET)
	public ModelAndView pageentry(@RequestParam("menu") String menu, @RequestParam("code") String code,
			@RequestParam("state") String state, HttpSession session) {
		ModelAndView view = null;
		// 网页授权
		log.info("weixinmenu.code=" + code + " stat=" + state + " menu=" + menu);

		try {
			DtoResult result = new DtoResult();
			result = wexinService.getPageAuthAccessToken(code);
			if (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {// 成功捉去token，跳转至目标页面
				DtoWxPageAuthSuccessResult wresult = (DtoWxPageAuthSuccessResult) result.getResult();
				session.setAttribute(XkConstant.S_SESSION_OPENID, wresult.getOpenid());
				session.setAttribute(XkConstant.S_SESSION_ACCESSTOKEN, wresult);
				/**/
				// 判断是否绑定过 全部用最高权限，强制同步最新微信公开资料，从而可以用session中的DtoUser来判断登录
				// result = userService.retrievebyOpenid(wresult.getOpenid());
				// DtoUser user = null;
				// if (!result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
				// {
				// 当天第一次（/刚关注），才刷新微信资料
				boolean refresh = true;
				DtoWxaccount dtoWxaccount = new DtoWxaccount();
				dtoWxaccount.setOpenid(wresult.getOpenid());
				List<DtoWxaccount> lDtoWxaccounts = wxaccountService.retrieveByDto(dtoWxaccount); // (DtoWxaccount)
				// 关注过
				if (lDtoWxaccounts != null && lDtoWxaccounts.size() > 0) {
					dtoWxaccount = lDtoWxaccounts.get(0);// （返回时，如果学员user存在，则会带上user）
					Timestamp uDate = dtoWxaccount.getUpdatedDate();
					if (uDate != null) {// 刷新过
						Date cDate = new Date();
						cDate = XkConstant.SDF_yyyyMMdd.parse(XkConstant.SDF_yyyyMMdd.format(cDate));
						if (uDate.getTime() > cDate.getTime()) {// 刷新时间大于当天0点整，则不刷新
							refresh = false;
						}
					}
				}
				if (refresh) {
					// 创建/更新微信资料（返回时，如果学员user存在，则会带上user）
					result = wexinService.reshreshWxaccountInfo(wresult.getAccess_token(), wresult.getOpenid(),
							"zh_CN");
					if (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {
						dtoWxaccount = (DtoWxaccount) result.getResult();
					}
				}

				// User不为空（已经绑定，则刷新最后登录时间
				DtoUser dtoUser = dtoWxaccount.getUser();
				if (dtoUser != null && dtoUser.getUserid() != null) {
					// result =
					// userService.updateLatestDate(dtoWxaccount.getUser().getUserid(),
					// new Timestamp(System.currentTimeMillis()));
					//
					// if
					// (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
					// {
					// DtoUser dtoUser = (DtoUser) result.getResult();
					// 如果最后访问时间超过“刷新间隔”，
					if (dtoUser.getLatestdate() != null && ((new Date().getTime()
							- 60 * 60 * 1000 * XkPropConfigurer.getIntvalueByProp("interval.autoextract")
							- dtoUser.getUpdatedDate().getTime()) >= 0)) {
						// 从学校刷新学员个人信息;
						result = extractQsnService.extractMyinfo(dtoUser, "0");// 暂时areaid=0
						if (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {
							dtoUser = (DtoUser) result.getResult();
							result = userService.createOrUpdate(dtoUser, dtoUser.getUserid());
							if (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {
								dtoUser = (DtoUser) result.getResult();
								dtoWxaccount.setUser(dtoUser);
							}
						}
						// 刷新我的班级列表（已报班级）TODO 可用单独线程处理
						List<DtoMygrade> lDtoMygrades = extractQsnService.extractMygrade(dtoUser);
						// 仅刷新“我的班级”中超过“刷新间隔”的班级（因查询条件会包含全部已报班级，所以只好提前全部更新）
						for (DtoMygrade mygrade : lDtoMygrades) {
							DtoGrade grade = gradeService.retrieveById(mygrade.getYgrade().getGradeid());
							if (grade != null
									&& (grade.getUpdatedDate() != null && (new Date().getTime()
											- 60 * 60 * 1000
													* XkPropConfigurer.getIntvalueByProp("interval.autoextract")
											- grade.getUpdatedDate().getTime()) >= 0)) {

								DtoGrade dtoGrade = extractQsnService.extractGrade(dtoUser, grade);
							}
						}

					}
					// }
				}
				session.setAttribute(XkConstant.V_SESSION_WXACCOUNT, dtoWxaccount);
				// ####################################################################
				// ####################################################################
				if (menu.equals("opengrade")) {// 开放班级
					view = new ModelAndView("redirect:/page/opengrade.jsp");
				}
				// else if (menu.equals("gradeinfo")) {// 班级信息
				// view = new ModelAndView("redirect:/page/gradeinfo.jsp");
				// }
				else if (menu.equals("mygrade")) {// 我的班级
					result = checkBinded(session);
					if (result.getCode() == XkConstant.RESULT_CODE_USER_NOT_BIND) {
						view = new ModelAndView("redirect:/page/bind.jsp");
					} else
						view = new ModelAndView("redirect:/page/mygrade.jsp");
				} else if (menu.equals("schoolinfo")) {// 学校信息
					view = new ModelAndView("redirect:/page/schoolinfo.jsp");
				} // 1
				else if (menu.equals("myapply")) {// 我的申请
					result = checkBinded(session);
					if (result.getCode() == XkConstant.RESULT_CODE_USER_NOT_BIND) {
						view = new ModelAndView("redirect:/page/bind.jsp");
					} else
						view = new ModelAndView("redirect:/page/myapply.jsp");
				} else if (menu.equals("applyrecord")) {// 申请记录
					view = new ModelAndView("redirect:/page/applyrecord.jsp");
				} // 2
				else if (menu.equals("myinfo")) {// 我的信息
					result = checkBinded(session);
					if (result.getCode() == XkConstant.RESULT_CODE_USER_NOT_BIND) {
						view = new ModelAndView("redirect:/page/bind.jsp");
					} else
						view = new ModelAndView("redirect:/page/myinfo.jsp");
				} else if (menu.equals("bind")) {// 绑定帐号
					view = new ModelAndView("redirect:/page/bind.jsp");
				} else if (menu.equals("myschedule")) {// 我的课表
					result = checkBinded(session);
					if (result.getCode() == XkConstant.RESULT_CODE_USER_NOT_BIND) {
						view = new ModelAndView("redirect:/page/bind.jsp");
					} else
						view = new ModelAndView("redirect:/page/myschedule.jsp");
				} else if (menu.equals("myclock")) {// 我的闹钟
					result = checkBinded(session);
					if (result.getCode() == XkConstant.RESULT_CODE_USER_NOT_BIND) {
						view = new ModelAndView("redirect:/page/bind.jsp");
					} else
						view = new ModelAndView("redirect:/page/myclock.jsp");
				} else if (menu.equals("support")) {// 平台客服
					view = new ModelAndView("redirect:/page/support.jsp");
				} // 3
				else {
					return new ModelAndView("redirect:/page/error.jsp");
				}
			}
			log.info("pageentry::" + result.getCode());
		} catch (Exception e) {
			log.error("weixinmenu::" + e);
		}

		return view;
	}

	/**
	 * 发送验证码
	 */
	@ResponseBody
	@RequestMapping(value = "/page/genverifycode", method = RequestMethod.GET)
	public DtoResult genverifycode(@RequestParam("phone") String phone, HttpSession session) {
		DtoResult re = checkLogin(session);
		try {
			// re = hbsmsHttpInvokerService.sendVerityCode(phone, "健康");
			// re = resultService.retrieveByCode("911102");// 成功
			session.setAttribute(XkConstant.V_VERITY + phone, re.getResult());
			// session.setAttribute(XkConstant.V_VERITY_PHONE, phone);
			session.setAttribute(XkConstant.S_VERIFYCODE_CREATEDATE + phone, System.currentTimeMillis());

			re.setResult(resultService.retrieveByCode("000000"));// 屏蔽验证码返回前端
			log.info("genverifycode.code=" + re.getResult());
		} catch (Exception e) {
			re = resultService.retrieveByCode("9030003");// 未知接口异常!
			log.error("genverifycode::" + e);
		}
		return re;
	}

	/**
	 * 验证码通过后，微信openid和用户account(手机号唯一确定）做关联，将表中的openid字段update;
	 * 帐号不存在则创建,且提示完善个人信息
	 * 
	 * @param tenantCode
	 * @param phone
	 * @param verifycode
	 * @param session
	 * @return @VoAccount
	 */
	@ResponseBody
	@RequestMapping(value = "/page/bindOpenid2Account", method = RequestMethod.POST)
	public DtoResult bindOpenid2Account(@RequestParam("phone") String phone, @RequestParam("pwd") String pwd,
			@RequestParam("verifycode") String verifycode, HttpSession session) {
		DtoResult re = checkLogin(session);
		if (!re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
			return re;

		try {
			// 判断验证码是否失效
			Object duration = session.getAttribute(XkConstant.S_VERIFYCODE_CREATEDATE + phone);
			if (duration == null || System.currentTimeMillis()
					- Long.parseLong(duration.toString()) > XkConstant.V_VERITYCODE_DURATION) {
				re = resultService.retrieveByCode("9020004");// 验证码失效，请重新获取,原来是（9000004）
			} else {
				// 验证码是否正确
				Object vcode = session.getAttribute(XkConstant.V_VERITY + phone);
				if (verifycode == null || !verifycode.equals(vcode)) {
					re = resultService.retrieveByCode("921125");// 930018验证出错！
				} else {
					String openid = session.getAttribute(XkConstant.S_SESSION_OPENID).toString();
					// Integer tenantCode = Integer
					// .valueOf(session.getAttribute(XkConstant.S_SESSION_TENANTCODE).toString());
					// re = wxAccountService.bindOpenid2Account(tenantCode,
					// phone, pwd, openid);
				}
			}

			log.info("bindOpenid2Account::" + re.getResult());
		} catch (Exception e) {
			re = resultService.retrieveByCode("9030003");// 未知接口异常!
			log.error("bindOpenid2Account::" + e);
		}
		return re;
	}

	/**
	 * 验证验证码
	 * 
	 * @param phone
	 * @param verifycode
	 *            验证码正确才给绑定，不正确返回验证码错误，重新获取
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/page/verifycode", method = RequestMethod.POST)
	public DtoResult verifycode(@RequestParam("phone") String phone, @RequestParam("verifycode") String verifycode,
			HttpSession session) {
		DtoResult re = checkLogin(session);
		if (!re.getCode().equals(XkConstant.RESULT_CODE_SUCCESS))
			return re;

		try {
			// 判断验证码是否失效
			Object duration = session.getAttribute(XkConstant.S_VERIFYCODE_CREATEDATE + phone);
			if (duration == null || System.currentTimeMillis()
					- Long.parseLong(duration.toString()) > XkConstant.V_VERITYCODE_DURATION) {
				re = resultService.retrieveByCode("9020004");// 验证码失效，请重新获取,原来是（9000004）
			} else {
				// 验证码是否正确
				Object vcode = session.getAttribute(XkConstant.V_VERITY + phone);
				if (verifycode == null || !verifycode.equals(vcode)) {
					re = resultService.retrieveByCode("921125");// 930018验证出错！
				} else {
					re = resultService.retrieveByCode("911106");// 验证码验证成功
					log.info("bindDetectDevice::" + re.getResult());
				}
			}
		} catch (Exception e) {
			re = resultService.retrieveByCode("9030003");// 未知接口异常!
			log.error("bindDetectDevice::" + e);
		}
		return re;
	}

	/**
	 * 判断session中是否存openid和tenantcode
	 * 
	 * @param session
	 * @return
	 */
	private DtoResult checkLogin(HttpSession session) {
		DtoResult result = new DtoResult();
		Object openid = session.getAttribute(XkConstant.S_SESSION_OPENID);
		Object pageAuthSuccessResult = session.getAttribute(XkConstant.S_SESSION_ACCESSTOKEN);
		if (openid == null || pageAuthSuccessResult == null) {
			result = resultService.retrieveByCode("9020005");// 微信端 登录失效
			return result;
		} /*
			 * if (openid == null || pageAuthSuccessResult == null) {// 重新获取 //
			 * 重新获取token try { result = wexinService.getPageAuthorizeCode(url);
			 * log.info(" checkLogin.getPageAuthorizeCode::" +
			 * result.getResult()); } catch (Exception e) {
			 * result.setCode(XkConstant.RESULT_CODE_FAILURE);
			 * result.setResult(e); log.error(" checkLogin::" + e); } } else {
			 * // 刷新token try { result =
			 * wexinService.refreshAccessToken4Page((DtoWxPageAuthSuccessResult)
			 * pageAuthSuccessResult); if
			 * (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {
			 * DtoWxPageAuthSuccessResult pa = (DtoWxPageAuthSuccessResult)
			 * result.getResult();
			 * session.setAttribute(XkConstant.S_SESSION_ACCESSTOKEN, pa);
			 * session.setAttribute(XkConstant.S_SESSION_OPENID,
			 * pa.getOpenid());
			 * 
			 * } else { log.info(" checkLogin.refreshAccessToken4Page::" +
			 * result.getResult()); } } catch (Exception e) {
			 * result.setCode(XkConstant.RESULT_CODE_FAILURE);
			 * result.setResult(e); log.error(
			 * " checkLogin.refreshAccessToken4Page::" + e); } }
			 */
		return result;
	}

	private DtoResult checkBinded(HttpSession session) {
		DtoWxaccount wxaccount = (DtoWxaccount) session.getAttribute(XkConstant.V_SESSION_WXACCOUNT);
		DtoResult result = new DtoResult();
		if (wxaccount.getUser() == null) {
			result.setCode(XkConstant.RESULT_CODE_USER_NOT_BIND);// =
																	// resultService.retrieveByCode("921129");//
																	// 用户未登录
			return result;
		}
		result.setResult(wxaccount);
		return result;
	}
}
