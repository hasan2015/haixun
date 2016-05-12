package com.hx.xk.weixin.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hx.xk.common.HttpUtils;
import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkPropConfigurer;
import com.hx.xk.dao.IDefaultDao;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.DtoWxaccount;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.service.IResultService;
import com.hx.xk.service.IWxaccountService;
import com.hx.xk.weixin.common.WxUtil;
import com.hx.xk.weixin.dto.mapper.DtoWxPageAuthFailureResult;
import com.hx.xk.weixin.dto.mapper.DtoWxPageAuthSuccessResult;
import com.hx.xk.weixin.mp.commons.WxApiUrl;
import com.hx.xk.weixin.mp.core.WxApi;
import com.hx.xk.weixin.mp.core.WxApiImpl;
import com.hx.xk.weixin.mp.exception.WxRespException;
import com.hx.xk.weixin.mp.util.SimpleHttpReq;
import com.hx.xk.weixin.mp.vo.Menu;
import com.hx.xk.weixin.mp.vo.Template;
import com.hx.xk.weixin.service.IWeixinService;

@Service("weixinService")
public class WeixinServiceImpl implements IWeixinService {

	private Log log = LogFactory.getLog(WeixinServiceImpl.class);
	private WxApi wxApi;
	@Resource
	protected IResultService resultService;
	@Resource
	protected IWxaccountService wxaccountService;
	@Autowired
	protected IDefaultDao defaultDao;

	public WeixinServiceImpl() {
		wxApi = new WxApiImpl(WxUtil.getMPAct());
	}

	@Override
	public DtoResult createQrcode(String deviceSN) throws Exception {
		String qrcode = wxApi.createQrcode(deviceSN);
		log.info("createQrcode.qrcode=" + qrcode);
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xk.hb.weixin.service.IWeixinService#createMenu(com.xk.hb.weixin.mp
	 * .vo.Menu)
	 */
	@Override
	public DtoResult createMenu(String... menus) throws Exception {
		DtoResult result = new DtoResult();
		String host = "http://" + XkPropConfigurer.getProp("dns.wx") + XkPropConfigurer.getProp("pageentry.url");

		String url;// = String.format(WxApiUrl.WX_OPEN_API +
					// WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
		// host + "class", "snsapi_userinfo", "");
		Menu m1 = new Menu("班级");
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "opengrade", "snsapi_userinfo", "");
		// url="http://" +
		// XkPropConfigurer.getProp("dns.wx")+"/hxxk/page/opengrade.jsp" ;
		Menu opengrade = new Menu("可报班级", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "mygrade", "snsapi_userinfo", "");
		Menu gradeinfo = new Menu("我的班级", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "schoolinfo", "snsapi_userinfo", "");
		Menu schoolinfo = new Menu("学校信息", Menu.VIEW, url);
		m1.setSubButtons(Arrays.asList(opengrade, gradeinfo, schoolinfo));
		// 2
		// url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2,
		// XkPropConfigurer.getProp("appId"), host + "bs",
		// "snsapi_userinfo", "");
		Menu m2 = new Menu("调课");
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "myapply", "snsapi_userinfo", "");
		Menu myapply = new Menu("我的申请", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "applyrecord", "snsapi_userinfo", "");
		Menu applyrecord = new Menu("申请记录", Menu.VIEW, url);
		m2.setSubButtons(Arrays.asList(myapply, applyrecord));
		// 2
		Menu m3 = new Menu("我");
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "myinfo", "snsapi_userinfo", "");
		// url = "http://" + XkPropConfigurer.getProp("dns.wx") +
		// "/hxxk/wx/myinfojsp.do";
		Menu myinfo = new Menu("我的资料", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "bind", "snsapi_userinfo", "");
		Menu bind = new Menu("绑定帐号", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "myschedule", "snsapi_userinfo", "");
		Menu myschedule = new Menu("我的课表", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "myclock", "snsapi_userinfo", "");
		Menu myclock = new Menu("我的闹钟", Menu.VIEW, url);
		url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + "support", "snsapi_userinfo", "");
		// url="http://" +
		// XkPropConfigurer.getProp("dns.wx")+"/hxxk/page/register.jsp" ;
		Menu mydonate = new Menu("平台客服", Menu.VIEW, url);
		m3.setSubButtons(Arrays.asList(myinfo, myschedule, myclock, mydonate));
		try {
			// wxApi.deleteMenu();
			wxApi.createMenu(m1, m2, m3);
		} catch (WxRespException e) {
			log.error("创建菜单失败::" + e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xk.hb.weixin.service.IWeixinService#sendTemplateMsg()
	 */
	// @Override
	// @Transactional
	// public DtoResult sendTMsgBPDetect(String openid, String realName, String
	// date, VoBloodPressure voBP)
	// throws Exception {
	// DtoResult result = new DtoResult();
	// // if (msgs.length != 5) {// 姓名、时间、高压，低压，心率
	// // return resultService.retrieveByCode("9030008");
	// // }
	// // 填充单位、分析检测结果
	// String hpColor = "";
	// String lpColor = "";
	// String hr = "";//voBP.getHeartRate() + defaultDao.find(SignItem.class,
	// XkConstant.V_SIGNITEM_ID_HEART).getItemUnit();
	// // 分析
	// String[] assess ={};// HbAssessBP.assessBPAdult(voBP);
	//
	// // 等级
	// String levelColor = "";
	// switch (assess[0]) {
	// case "1":
	// levelColor = "#8ec077";// 绿色-正常
	// hpColor = lpColor = "#173177";
	// break;
	// case "2":
	// levelColor = "#8ec077";// 绿色-正常高值
	// hpColor = lpColor = "#173177";
	// break;
	// case "3":
	// levelColor = "#c07777";// 红色-偏低
	// hpColor = lpColor = "#173177";// 蓝色0000FF--20150826调整为黄色FFFF00橙色FFA500
	// break;
	// default:
	// levelColor = "#c07777";// 红色-偏高
	// hpColor = lpColor = "#293660";
	// }
	//
	// // wxApi = new WxApiImpl(WxUtil.getMPAct());
	//
	// Template first = new Template("first", "#fffff", "【" + realName + "】" +
	// "在" + date + "检测了血压：");
	// Template highPressure = new Template("highPressure", hpColor,
	// voBP.getSystolicPressure().intValue()
	// ;//+ defaultDao.find(SignItem.class,
	// XkConstant.V_SIGNITEM_ID_SYS).getItemUnit());
	// Template lowPressure = new Template("lowPressure", lpColor,
	// voBP.getDiastolicPressure().intValue()
	// ;//+ defaultDao.find(SignItem.class,
	// XkConstant.V_SIGNITEM_ID_DIA).getItemUnit());
	// Template heart = new Template("heart", "#173177", hr);
	// Template bloodPressureLevel = new Template("bloodPressureLevel",
	// levelColor, assess[1]);
	//
	// // hbassessbpresultservice.retrieveContentByLevelcode("1", assess[0]);
	// String ass = "测量时手臂或手腕出现了抖动，测量不准确，数据未保存。测量过程请保持放松、安静。";
	// Template remark = new Template("remark", "#173177",
	//// voBP.isMovementErrorFlag() ? ass
	//// :
	// hbassessbpresultservice.retrieveContentByLevelcode(XkConstant.V_TSIGNITEMGROUP_ID_BP,
	// assess[0]);
	// try {
	// wxApi.sendTemplateMsg(openid,
	// XkPropConfigurer.getProp("bloodpressure.detect.templateid"), "#FF0000",
	// "",
	// first, highPressure, lowPressure, heart, bloodPressureLevel, remark);
	// } catch (Exception e) {
	// result.setCode(XkConstant.RESULT_CODE_FAILURE);
	// result.setResult(e.getLocalizedMessage());
	// log.error("sendTMsgBPDetect::" + e);
	// }
	//
	// return result;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xk.hb.weixin.service.IWeixinService#sendTMsgBindBP(java.lang.String
	 * [])
	 */
	@Override
	public DtoResult sendTMsgClock(String openid, String... msgs) throws Exception {
		DtoResult result = new DtoResult();
		if (msgs.length != 5) {
			result.setCode(XkConstant.RESULT_CODE_FAILURE);
			return result;
		}
		// wxApi = new WxApiImpl(WxUtil.getMPAct());

		Template first = new Template("first", "#fffff", msgs[0]);
		Template keyword1 = new Template("keyword1", "#0000FF", msgs[1]);
		Template keyword2 = new Template("keyword2", "#0000FF", msgs[2]);
		Template keyword3 = new Template("keyword3", "#0000FF", msgs[3]);
		Template remark = new Template("remark", "#fffff", msgs[4]);
		wxApi.sendTemplateMsg(openid, XkPropConfigurer.getProp("clock.templateid"), "#FF0000", "", first, keyword1,
				keyword2, keyword3, remark);

		return result;
	}

	@Override
	public DtoResult getPageAuthorizeCode(String url) throws Exception {
		DtoResult result = new DtoResult();
		result.setResult(wxApi.getPageAuthorizeCode(url));
		return result;
	}

	@Override
	public DtoResult refreshAccessToken4Page(DtoWxPageAuthSuccessResult pageAuthSuccessResult) throws Exception {
		DtoResult result = new DtoResult();
		String refreshtoken = pageAuthSuccessResult.getRefresh_token();
		long expiresIn = pageAuthSuccessResult.getExpires_in();
		if (null == refreshtoken || refreshtoken.isEmpty() || expiresIn < System.currentTimeMillis()) {
			// synchronized (mpAct) {
			// log.info("mpAct.getExpiresIn()=" + mpAct.getExpiresIn());
			// log.info("System.currentTimeMillis()=" +
			// System.currentTimeMillis());
			// refreshAccessToken();
			result = wxApi.refreshAccessToken4Page(refreshtoken);

			// }
			// token = mpAct.getAccessToken();
		}

		// result.setResult(wxApi.refreshAccessToken4Page(refreshtoken));
		return result;
	}

	@Override
	public DtoResult getPageAuthAccessToken(String code) throws Exception {
		// return wxApi.getAccessToken4Page(code);
		String url = String.format(WxApiUrl.WX_API_1 + WxApiUrl.OAUTH_TOKEN, XkPropConfigurer.getProp("appId"),
				XkPropConfigurer.getProp("appSecret"), code);
		DtoResult result = new DtoResult();
		String res = null;
		try {
			res = HttpUtils.get(url);

			result.setResult(JSON.parseObject(res, DtoWxPageAuthSuccessResult.class));
			if (res.isEmpty() || res.contains("errcode")) {
				DtoWxPageAuthFailureResult failureResult = JSON.parseObject(res, DtoWxPageAuthFailureResult.class);
				result.setCode(XkConstant.RESULT_CODE_FAILURE);
				result.setResult(failureResult);
				log.info("getPageAuthAccessToken::" + failureResult.getErrmsg());
			} else
				log.info("getPageAuthAccessToken.result=" + res);
		} catch (Exception e) {
			log.error("getPageAuthAccessToken::刷新ACCESS_TOKEN时出现异常!!!" + e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xk.hb.service.intf.weixin.IWeixinService#getJsAPISignByUrl(java.lang
	 * .String)
	 */
	@Override
	public DtoResult getJsAPISignByUrl(String url) throws Exception {
		DtoResult re = new DtoResult();
		// wxApi = new WxApiImpl(WxUtil.getMPAct());
		// try {
		Map<String, Object> config = wxApi.getJsAPISign(url);
		re.setResult(config);
		// log.info("getJsAPISignByUrl.url=" + url);
		// } catch (WxRespException e) {
		// re.setCode(XkConstant.RESULT_CODE_FAILURE);
		// re.setResult(e);
		// log.error("getJsAPISignByUrl::" + e);
		// }

		return re;
	}

	@Override
	public DtoResult downloadimage(String mediaId, String path, String filename) throws Exception {
		DtoResult re = new DtoResult();
		// 上传到微信的图片路径
		String url = String.format(WxApiUrl.MEDIA_DL_API, wxApi.getAccessToken(), mediaId);

		File targetFile = new File(path);
		SimpleHttpReq.download(url, targetFile);

		// aliyuncsservice.uploadFile(filename, path);
		// 原始图删除
		File orgtempfile = new File(path);
		orgtempfile.delete();

		return re;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.weixin.service.IWeixinService#getUserinfo(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public DtoResult reshreshWxaccountInfo(String accessToken, String openid, String lang) throws Exception {
		// return wxApi.getAccessToken4Page(code);
		String url = String.format(WxApiUrl.WX_API_1 + WxApiUrl.OAUTH_USERINFO, accessToken, openid, lang);
		DtoResult result = new DtoResult();
		String res = null;
		try {
			res = HttpUtils.get(url);
			// res = "{\"name\":\"老张头\"}";
			if (res.isEmpty() || res.contains("errcode")) {
				DtoWxPageAuthFailureResult failureResult = JSON.parseObject(res, DtoWxPageAuthFailureResult.class);
				result.setCode(XkConstant.RESULT_CODE_FAILURE);
				result.setResult(failureResult);
				log.info("getUserinfo::" + failureResult.getErrmsg());
			} else {
				DtoWxaccount wxaccount = JSON.parseObject(res, DtoWxaccount.class);
				// wxaccount.setCreatedDate(new
				// Timestamp(System.currentTimeMillis()));
				// 保存微信号资料
				result = wxaccountService.createOrUpdate(wxaccount, openid);
				// result.setResult(wxaccount);
				log.info("getUserinfo.result=" + res);
			}
		} catch (Exception e) {
			log.error("getUserinfo::刷新ACCESS_TOKEN时出现异常!!!" + e);
		}

		return result;
	}

	public static void main(String[] args) {
		String res = "{\"nickname\":\"老张头\"}";
		DtoUser user = JSON.parseObject(res, DtoUser.class);
		System.err.println(user.getNickname());
	}
}
