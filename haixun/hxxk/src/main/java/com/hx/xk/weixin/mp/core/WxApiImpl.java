package com.hx.xk.weixin.mp.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.hx.xk.common.HttpUtils;
import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkPropConfigurer;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.weixin.dto.mapper.DtoWxPageAuthFailureResult;
import com.hx.xk.weixin.dto.mapper.DtoWxPageAuthSuccessResult;
import com.hx.xk.weixin.dto.mapper.DtoWxResult;
import com.hx.xk.weixin.mp.commons.WxApiUrl;
import com.hx.xk.weixin.mp.commons.WxMsgType;
import com.hx.xk.weixin.mp.exception.WxRespException;
import com.hx.xk.weixin.mp.util.JsonMsgBuilder;
import com.hx.xk.weixin.mp.util.SimpleHttpReq;
import com.hx.xk.weixin.mp.vo.Article2;
import com.hx.xk.weixin.mp.vo.FollowList;
import com.hx.xk.weixin.mp.vo.Follower;
import com.hx.xk.weixin.mp.vo.Group;
import com.hx.xk.weixin.mp.vo.MPAct;
import com.hx.xk.weixin.mp.vo.Menu;
import com.hx.xk.weixin.mp.vo.OutPutMsg;
import com.hx.xk.weixin.mp.vo.Template;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;

/**
 * 
 * @author Hasan
 * @Date 2015-8-16 上午10:08:03
 * 
 */
public class WxApiImpl implements WxApi {

	private static final Logger log = LoggerFactory.getLogger(WxApiImpl.class);

	private MPAct mpAct;
	// private MPAct4Page mpAct4Page;

	/**
	 * 微信公众平台接口构建
	 * 
	 * @param mpAct
	 *            公众号信息
	 */
	public WxApiImpl(MPAct mpAct) {
		this.mpAct = mpAct;
	}

	@Override
	public String getPageAuthorizeCode(String redirecturl) throws WxRespException {
		// 跳转回调redirect_uri，应当使用https链接来确保授权code的安全性。
		// String host = XkPropConfigurer.getProp("getAccessToken4Page.url");
		String host = "http://" + XkPropConfigurer.getProp("dns.wx") + XkPropConfigurer.getProp("pageentry.url");
		String url = String.format(WxApiUrl.WX_OPEN_API + WxApiUrl.WEB_OAUTH2, XkPropConfigurer.getProp("appId"),
				host + redirecturl, "snsapi_base", "");
		String res = null;
		try {
			res = HttpUtils.get(url);

			if (res.isEmpty() || res.contains("errcode")) {
				DtoWxPageAuthFailureResult failureResult = JSON.parseObject(res, DtoWxPageAuthFailureResult.class);

				log.error("getPageAuthorizeCode::" + failureResult.getErrmsg());
				throw new WxRespException(res);
			}

			log.info("getPageAuthorizeCode.result=" + res);
		} catch (Exception e) {
			log.error("getPageAuthorizeCode::用户同意授权，获取code时出现异常!!!" + e);
		}
		return res;
	}

	public String getAccessToken4Page(String code) throws WxRespException {
		String url = String.format(WxApiUrl.WX_API_1 + WxApiUrl.OAUTH_TOKEN, XkPropConfigurer.getProp("appId"),
				XkPropConfigurer.getProp("appSecret"), code);
		// DtoResult result = new DtoResult();
		String res = null;
		try {
			res = HttpUtils.get(url);
			// result.setResult(JSON.parseObject(res,
			// DtoWxPageAuthSuccessResult.class));
			log.info("getAccessToken4Page.result=" + res);
			if (res.isEmpty() || res.contains("errcode")) {
				// DtoWxPageAuthFailureResult failureResult =
				// JSON.parseObject(res, DtoWxPageAuthFailureResult.class);
				throw new WxRespException(res);
			}
		} catch (Exception e) {
			log.error("getAccessToken4Page::刷新ACCESS_TOKEN时出现异常!!!" + e);
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.weixin.mp.core.WxApi#refreshAccessToken4Page()
	 */
	@Override
	public DtoResult refreshAccessToken4Page(String refreshtoken) throws WxRespException {
		String url = String.format(WxApiUrl.WX_API_1 + WxApiUrl.OAUTH_TOKEN_REFRESH, XkPropConfigurer.getProp("appId"),
				refreshtoken);
		DtoResult result = new DtoResult();
		String res = null;
		try {
			res = HttpUtils.get(url);

			if (res.isEmpty() || res.contains("errcode")) {
				result.setCode(XkConstant.RESULT_CODE_FAILURE);
				result.setResult(JSON.parseObject(res, DtoWxPageAuthFailureResult.class));
				// throw new WxRespException(res);
			}
			result.setResult(JSON.parseObject(res, DtoWxPageAuthSuccessResult.class));
			// mpAct4Page.createAccessToken(res);
			log.info("refreshAccessToken4Page::" + res);
		} catch (Exception e) {
			log.error("refreshAccessToken4Page::刷新ACCESS_TOKEN时出现异常!!!" + e);
		}
		return result;
	}

	@Override
	public String getAccessToken() throws WxRespException {
		String token = mpAct.getAccessToken();
		log.info("mpAct=" + mpAct + "token=" + token);

		if (null == token || token.isEmpty() || mpAct.getExpiresIn() < System.currentTimeMillis()) {
			synchronized (mpAct) {
				log.info("mpAct.getExpiresIn()=" + mpAct.getExpiresIn());
				log.info("System.currentTimeMillis()=" + System.currentTimeMillis());
				refreshAccessToken();
			}
			token = mpAct.getAccessToken();
		}
		return token;
	}

	@Override
	public void refreshAccessToken() throws WxRespException {
		String url = String.format(WxApiUrl.ACCESS_TOKEN_API, mpAct.getAppId(), mpAct.getAppSecret());
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
			log.info("refreshAccessToken.result=" + result);
		} catch (IOException e) {
			log.error("刷新ACCESS_TOKEN时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}

		mpAct.createAccessToken(result);
		log.info("mpAct.getAccessToken()=" + mpAct.getAccessToken());
	}

	@Override
	public List<String> getServerIp() throws WxRespException {
		String url = String.format(WxApiUrl.IP_LIST_API, getAccessToken());
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error("获取微信服务器IP时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}

		JSONObject tmp = JSON.parseObject(result);
		List<String> ips = JSONObject.parseArray(tmp.getString("ip_list"), String.class);

		return ips;
	}

	@Override
	public List<Menu> getMenu() throws WxRespException {
		String url = String.format(WxApiUrl.CUSTOM_MENU_API, "get", getAccessToken());
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error("获取当前自定义菜单时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}
		JSONObject tmp = JSON.parseObject(result).getJSONObject("menu");
		List<Menu> menus = JSON.parseArray(tmp.getString("button"), Menu.class);
		return menus;
	}

	@Override
	public String createQrcode(String scene_str) throws WxRespException {
		String url = String.format(WxApiUrl.QRCODE_CREATE, getAccessToken());
		String btn_json = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \""
				+ scene_str + "\"}}}";
		String result = null;
		try {
			result = HttpUtils.post(btn_json, XkConstant.V_CONTENTTYPE_APPLICATION_JSON, url);
		} catch (Exception e) {
			log.error("获取当前自定义菜单时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result != null) {
			String ticket = new JSONObject(JSON.parseObject(result)).getString("ticket");
			String qrcodeurl = new JSONObject(JSON.parseObject(result)).getString("url");
			try {
				getQrcode2Localhost(String.format(WxApiUrl.QRCODE_TICKET_URL, ticket), scene_str);

			} catch (Exception e) {
				e.printStackTrace();
				log.error("下载二维码图片出错!!!");
				log.error(e.getMessage(), e);
			}
		}
		return result;
	}

	// 下载文件到本地
	public void getQrcode2Localhost(String qrcodeurl, String devicecode) throws Exception {
		// 根据String形式创建一个URL对象，
		URL url = new URL(qrcodeurl);
		// 实列一个URLconnection对象，用来读取和写入此 URL 引用的资源
		URLConnection con = url.openConnection();
		// 获取一个输入流
		InputStream is = con.getInputStream();
		// 实列一个输出对象
		FileOutputStream fos = new FileOutputStream("D:/lexin205/lexin_" + devicecode + ".jpg");
		// 一个byte[]数组，一次读取多个字节
		byte[] bt = new byte[200];
		// 用来接收每次读取的字节个数
		int b = 0;
		// 循环判断，如果读取的个数b为空了，则is.read()方法返回-1，具体请参考InputStream的read();
		while ((b = is.read(bt)) != -1) {
			// 将对象写入到对应的文件中
			fos.write(bt, 0, b);
		}
		// 刷新流
		fos.flush();
		// 关闭流
		fos.close();
		is.close();
	}

	@Override
	public boolean createMenu(Menu... menus) throws WxRespException {
		// 创建JSON格式化
		PropertyFilter null_filter = new PropertyFilter() {
			@Override
			public boolean apply(Object object, String name, Object value) {
				if (name.equals("key") && (null == value || "".equals(value))) {
					return false;
				}
				if (name.equals("url") && (null == value || "".equals(value))) {
					return false;
				}
				return true;
			}
		};
		// 准备菜单数据
		Map<String, Object> buttons = new HashMap<>();
		buttons.put("button", Arrays.asList(menus));
		String btn_json = JSONObject.toJSONString(buttons, null_filter);
		if (log.isInfoEnabled()) {
			log.info("创建的微信自定义菜单: {}", btn_json);
		}
		// 提交并创建菜单
		String url = String.format(WxApiUrl.CUSTOM_MENU_API, "create", getAccessToken());
		DtoWxResult result = null;
		try {
			// result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON,
			// btn_json);
			result = HttpUtils.post(DtoWxResult.class, btn_json, XkConstant.V_CONTENTTYPE_APPLICATION_JSON, url);
		} catch (Exception e) {
			log.error("创建自定义菜单时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result == null || result.getErrcode() != XkConstant.RESULT_CODE_WEIXIN_SUCCESS) {
			throw new WxRespException(result.getErrmsg());
		}

		return true;
	}

	@Override
	public boolean deleteMenu() throws WxRespException {
		String url = String.format(WxApiUrl.CUSTOM_MENU_API, "delete", getAccessToken());
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error("删除微信自定义菜单时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}

		return true;
	}

	@Override
	public int creatGroup(String name) throws WxRespException {
		String url = String.format(WxApiUrl.GROUPS_API, "create", getAccessToken());
		String data = "{\"group\":{\"name\":\"" + name + "\"}}";
		String result = "";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
		} catch (IOException e) {
			log.error("创建微信分组时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || !result.contains("group")) {
			throw new WxRespException(result);
		}

		JSONObject tmp = JSON.parseObject(result).getJSONObject("group");

		return tmp.getInteger("id");
	}

	@Override
	public List<Group> getGroups() throws WxRespException {
		String url = String.format(WxApiUrl.GROUPS_API, "get", getAccessToken());
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error("获取所有分组时失败!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.indexOf("errcode") > -1) {
			throw new WxRespException(result);
		}

		String tmp = JSON.parseObject(result).getString("groups");
		List<Group> groups = JSON.parseArray(tmp, Group.class);

		return groups;
	}

	@Override
	public boolean renGroup(int id, String name) throws WxRespException {
		String url = String.format(WxApiUrl.GROUPS_API, "update", getAccessToken());
		name = name.length() > 30 ? name.substring(0, 30) : name;
		String data = "{\"group\":{\"id\":" + id + ",\"name\":\"" + name + "\"}}";
		String result = "";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
		} catch (IOException e) {
			log.error("修改分组名称时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}

		return true;
	}

	@Override
	public int getGroupId(String openId) throws WxRespException {
		String url = String.format(WxApiUrl.GROUPS_API, "getid", getAccessToken());
		String data = "{\"openid\":\"" + openId + "\"}";
		String result = "";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
		} catch (IOException e) {
			log.error("获取用户所在分组时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.indexOf("errcode") > -1) {
			throw new WxRespException(result);
		}

		int group_id = JSON.parseObject(result).getInteger("groupid");

		return group_id;
	}

	@Override
	public boolean move2Group(String openId, int groupId) throws WxRespException {
		String url = String.format(WxApiUrl.GROUPS_USER_API, getAccessToken());
		String data = "{\"openid\":\"" + openId + "\",\"to_groupid\":" + groupId + "}";
		String result = "";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, data);
		} catch (IOException e) {
			log.error("移动用户分组时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || !result.contains("ok")) {
			throw new WxRespException(result);
		}

		return true;
	}

	@Override
	public FollowList getFollowerList(String nextOpenId) throws WxRespException {
		String url = String.format(WxApiUrl.FOLLOWS_USER_API, getAccessToken(), nextOpenId);
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error("获取关注用户列表时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.indexOf("errcode") > -1) {
			throw new WxRespException(result);
		}

		FollowList followers = JSON.parseObject(result, FollowList.class);
		String openid = JSON.parseObject(result).getJSONObject("data").getString("openid");
		List<String> openids = JSON.parseArray(openid, String.class);
		followers.setOpenIds(openids);
		return followers;
	}

	@Override
	public Follower getFollower(String openId, String lang) throws WxRespException {
		String url = String.format(WxApiUrl.USER_INFO_API, getAccessToken(), openId, lang);
		String result = "";
		try {
			result = SimpleHttpReq.get(url);
		} catch (IOException e) {
			log.error("获取用户信息时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.indexOf("errcode") > -1) {
			throw new WxRespException(result);
		}

		Follower follower = JSON.parseObject(result, Follower.class);

		return follower;
	}

	@Override
	public boolean sendCustomerMsg(OutPutMsg msg) throws WxRespException {
		String url = String.format(WxApiUrl.CUSTOM_MESSAGE_API, getAccessToken());
		String custom_msg = "";
		WxMsgType type = WxMsgType.valueOf(msg.getMsgType());
		switch (type) {
		case text:
			custom_msg = JsonMsgBuilder.create().text(msg).build();
			break;
		case image:
			custom_msg = JsonMsgBuilder.create().image(msg).build();
			break;
		case voice:
			custom_msg = JsonMsgBuilder.create().voice(msg).build();
			break;
		case video:
			custom_msg = JsonMsgBuilder.create().video(msg).build();
			break;
		case music:
			custom_msg = JsonMsgBuilder.create().music(msg).build();
			break;
		case news:
			custom_msg = JsonMsgBuilder.create().news(msg).build();
			break;
		default:
			break;
		}
		String result = "";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, custom_msg);
		} catch (IOException e) {
			log.error("发送客服消息时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || !result.contains("ok")) {
			throw new WxRespException(result);
		}

		return true;
	}

	@Override
	public boolean sendTemplateMsg(String openId, String templateId, String topColor, String url, Template... templates)
			throws WxRespException {
		String api_url = String.format(WxApiUrl.TEMPLATE_MESSAGE_API, getAccessToken());
		String template_msg = JsonMsgBuilder.create().template(openId, templateId, topColor, url, templates).build();
		// template_msg="{\"touser\":\"oAxETwon1GK8S0GTj9m7pO7Ey2xA\",\"template_id\":\"pjHD-9dV-DXHpBg732_6kWBBpU_Z3da0rQtQB1jCzDM\",\"url\":\"\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"恭喜您绑定血压计设备成功！\",\"color\":\"#fffff\"},\"keyword1\":{\"value\":\"<font
		// size=\\\"24px\\\">0367206904229788</font>\",\"font-size\":\"24px\",\"color\":\"#0000FF\"},\"keyword2\":{\"value\":\"2015年08月29日\",\"color\":\"#0000FF\"},\"remark\":{\"value\":\"藍熙感谢您的支持！\",\"color\":\"#fffff\"}}}";
		DtoWxResult result = null;
		try {
			// result = SimpleHttpReq.post(api_url,
			// SimpleHttpReq.APPLICATION_JSON, template_msg);
			result = HttpUtils.post(DtoWxResult.class, template_msg, XkConstant.V_CONTENTTYPE_APPLICATION_JSON,
					api_url);
		} catch (Exception e) {
			log.error("发送模板消息时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result == null || result.getErrcode() != XkConstant.RESULT_CODE_WEIXIN_SUCCESS) {
			throw new WxRespException(result.getErrcode(), result.getErrmsg());
		}

		return true;
	}

	@Override
	public String upMedia(String mediaType, File file) throws WxRespException {
		String url = String.format(WxApiUrl.MEDIA_UP_API, mediaType, getAccessToken());
		String result = "";
		try {
			result = SimpleHttpReq.upload(url, file);
		} catch (IOException e) {
			log.error("上传多媒体文件时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}

		String media_id = JSON.parseObject(result).getString("media_id");

		return media_id;
	}

	@Override
	public void dlMedia(String mediaId, File file) throws WxRespException {

		String url = String.format(WxApiUrl.MEDIA_DL_API, getAccessToken(), mediaId);
		// 检查文件夹是否存在
		if (!file.exists()) {
			String path = file.getAbsolutePath();
			String separ = System.getProperties().getProperty("file.separator");
			File dir = new File(path.substring(0, path.lastIndexOf(separ)));
			dir.mkdirs();
		}

		try {
			SimpleHttpReq.download(url, file);
		} catch (IOException e) {
			log.error("下载多媒体文件时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public String[] upNews(Article2... articles2s) throws WxRespException {
		String url = String.format(WxApiUrl.NEWS_UPLOAD_API, getAccessToken());
		String upnews_msg = JsonMsgBuilder.create().uploadNews(articles2s).build();
		String result = "";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, upnews_msg);
		} catch (IOException e) {
			log.error("上传群发图文消息时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}
		JSONObject tmp = JSON.parseObject(result);
		String[] results = { tmp.getString("type"), tmp.getString("media_id"), tmp.getString("created_at") };
		return results;
	}

	@Override
	public String[] upVideo(String mediaId, String title, String description) throws WxRespException {
		String url = String.format(WxApiUrl.MEDIA_UPVIDEO_API, getAccessToken());
		String result = "";
		String upvideo_msg = JsonMsgBuilder.create().uploadVideo(mediaId, title, description).build();
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, upvideo_msg);
		} catch (IOException e) {
			log.error("上传群发消息中的视频时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || result.contains("errcode")) {
			throw new WxRespException(result);
		}
		JSONObject tmp = JSON.parseObject(result);
		String[] results = { tmp.getString("type"), tmp.getString("media_id"), tmp.getString("created_at") };
		return results;
	}

	@Override
	public String sendAll(OutPutMsg msg) throws WxRespException {
		String group_id = msg.getGroupId();
		List<String> to_users = msg.getToUsers();
		if (null != group_id && !group_id.isEmpty() && !to_users.isEmpty()) {
			throw new RuntimeException("群发消息只能选择一种模式");
		}

		String url = "";
		if (to_users.isEmpty()) {
			url = String.format(WxApiUrl.GROUP_NEWS_MESSAGE_API, "sendall", getAccessToken());
		} else {
			url = String.format(WxApiUrl.GROUP_NEWS_MESSAGE_API, "send", getAccessToken());
		}

		String result = "";
		String send_msg = JsonMsgBuilder.create().sendAll(msg).build();
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, send_msg);
		} catch (IOException e) {
			log.error("发送群消息时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || !result.contains("msg_id")) {
			throw new WxRespException(result);
		}

		JSONObject tmp = JSON.parseObject(result);

		return tmp.getString("msg_id");
	}

	@Override
	public boolean delSendAll(String msgId) throws WxRespException {
		String url = String.format(WxApiUrl.GROUP_NEWS_MESSAGE_API, "delete", getAccessToken());
		String result = "";
		String del_msg = "{\"msg_id\":" + msgId + "}";
		try {
			result = SimpleHttpReq.post(url, SimpleHttpReq.APPLICATION_JSON, del_msg);
		} catch (IOException e) {
			log.error("删除群发消息时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || !result.contains("ok")) {
			throw new WxRespException(result);
		}

		return true;
	}

	@Override
	public Map<String, Object> getJsAPISign(String url) throws WxRespException {
		// 1.创建JSTICKET
		String ticket = mpAct.getJsTicket();
		if (null == ticket || ticket.isEmpty() || mpAct.getJsExpiresIn() < System.currentTimeMillis()) {
			synchronized (mpAct) {
				refreshJsAPITicket();
			}
			ticket = mpAct.getJsTicket();
		}

		// 2.生成签名
		String sign_param = "jsapi_ticket=%1$s&noncestr=%2$s&timestamp=%3$s&url=%4$s";
		String nonce = UUID.randomUUID().toString().toLowerCase();
		long time = System.currentTimeMillis() / 1000;
		String sign = null;
		try {
			sign = SHA1.calculate(String.format(sign_param, ticket, nonce, time, url));
		} catch (AesException e) {
			log.error("生成JSTICKET的签名时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		// 3.返回参数与值
		Map<String, Object> sign_map = new HashMap<>();
		sign_map.put("url", url);
		sign_map.put("ticket", ticket);
		sign_map.put("nonce", nonce);
		sign_map.put("timestamp", time);
		sign_map.put("sign", sign);
		sign_map.put("appid", XkPropConfigurer.getProp("appId"));
		return sign_map;
	}

	@Override
	public void refreshJsAPITicket() throws WxRespException {

		String token_url = WxApiUrl.JSAPI_TICKET_URL + getAccessToken();

		String result = "";
		try {
			result = SimpleHttpReq.get(token_url);
		} catch (IOException e) {
			log.error("刷新JSTICKET时出现异常!!!");
			log.error(e.getLocalizedMessage(), e);
		}

		if (result.isEmpty() || !result.contains("ok")) {
			throw new WxRespException(result);
		}

		mpAct.createJsTicket(result);
	}

}
