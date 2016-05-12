/**
 * 
 */
package com.hx.xk.weixin.common;

import com.hx.xk.common.util.XkPropConfigurer;
import com.hx.xk.weixin.mp.vo.MPAct;
import com.hx.xk.weixin.mp.vo.ReceiveMsg;

/**
 * @author Hasan
 * @Date 2015-8-13 下午9:32:11
 * 
 */
public class WxUtil {
	public static MPAct getMPAct() {
		MPAct mpAct = new MPAct();
		mpAct.setMpId(XkPropConfigurer.getProp("mpId"));
		mpAct.setAppId(XkPropConfigurer.getProp("appId"));
		mpAct.setAppSecret(XkPropConfigurer.getProp("appSecret"));
		mpAct.setToken(XkPropConfigurer.getProp("token"));
		mpAct.setAESKey(XkPropConfigurer.getProp("aesKey"));

		String accessToken = XkPropConfigurer.getProp("accessToken", "NOT");
		if (!accessToken.equals("NOT") || !accessToken.isEmpty()) {
			mpAct.setAccessToken(accessToken);
			mpAct.setExpiresIn(7000 * 1000 + System.currentTimeMillis());
		}
		return mpAct;
	}

	public static void getAccessToken(String token, MPAct mpAct) {
		String accessToken = XkPropConfigurer.getProp("accessToken", "NOT");
		if ("NOT".equals(accessToken) || "".equals(accessToken) || accessToken.isEmpty()) {
			mpAct.setToken(token);
			mpAct.setExpiresIn(7000 * 1000 + System.currentTimeMillis());
		}
	}

	public static String getTransferCustomerService(ReceiveMsg rm) {

		return String.format(
				"<xml><ToUserName><![CDATA[%1$s]]></ToUserName>" + "<FromUserName><![CDATA[%2$s]]></FromUserName>"
						+ "<CreateTime>%3$s</CreateTime>"
						+ "<MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>",
				rm.getFromUserName(), rm.getToUserName(), rm.getCreateTime());

	}
}
