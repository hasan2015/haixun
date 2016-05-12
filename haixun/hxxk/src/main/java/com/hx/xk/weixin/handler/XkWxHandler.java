/**
 * 
 */
package com.hx.xk.weixin.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hx.xk.dto.DtoUser;
import com.hx.xk.service.IUserService;
import com.hx.xk.weixin.mp.commons.WxMsgType;
import com.hx.xk.weixin.mp.core.WxDefaultHandler;
import com.hx.xk.weixin.mp.vo.OutPutMsg;
import com.hx.xk.weixin.mp.vo.ReceiveMsg;

/**
 * @author Hasan
 * @Date 2015-8-13 下午4:21:09
 * 
 */
public class XkWxHandler extends WxDefaultHandler {
	private static final Logger log = LoggerFactory.getLogger(XkWxHandler.class);
	@Resource
	private IUserService userService;

	// IWxAccountService wxAccountService = (IWxAccountService)
	// SpringContextUtils.getBean("wxAccountService");

	// private static ExecutorService threadPool;
	// private String devicecode;// 设备码
	// private String openid; // 关注者openid

	public XkWxHandler() {
	}

	// public XkWxHandler(IWxAccountService wxAccountService, String devicecode,
	// String openid) {
	// this.wxAccountService = wxAccountService;
	// this.devicecode = devicecode;
	// this.openid = openid;
	// }
	//
	// public IWxAccountService getWxAccountService() {
	// return wxAccountService;
	// }
	//
	// public void setWxAccountService(IWxAccountService wxAccountService) {
	// this.wxAccountService = wxAccountService;
	// }

	/**
	 * 关注
	 */
	@Override
	public OutPutMsg eSub(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		om.setMsgType(WxMsgType.text.name());
		//
		om.setContent("妈盟教育即将来袭！敬请关注");
				//"欢迎关注妈盟，在这里您可以查看自己在各大培训机构（学校）的培训信息，也可以针对不合适自己的课程发起“调课”申请，系统会自动与他人发起的相应申请进行匹配。首期转载“杭州青少年活动中心”的培训信息，更加丰富的内容敬请期待！本公众号信息仅供参考，以官网信息为准。");
		
		// 自动创建一个帐号绑定openid
		if (rm.getEventKey() != null && rm.getEventKey().length() >= 8) {
			// 
			DtoUser user = new DtoUser();
			try {
				userService.createOrUpdate(user, rm.getFromUserName());
			} catch (Exception e) {
				log.error("eSub::" + e);
			}

		}

		if (log.isInfoEnabled()) {
			log.info("接收到订阅消息...");
			log.info("from={}, to={}, event={}", rm.getFromUserName(), rm.getToUserName(), rm.getEvent());
		}
		return om;
	}

	@Override
	public OutPutMsg eClick(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		om.setMsgType(WxMsgType.text.name());
		om.setContent("MENU_CLICK:" + rm.getEventKey());
		if (log.isInfoEnabled()) {
			log.info("接收到菜单点击消息...");
			log.info("from={}, to={}, event={}, key={}", rm.getFromUserName(), rm.getToUserName(), rm.getEvent(),
					rm.getEventKey());
		}
		return om;
	}

	@Override
	public OutPutMsg eScan(ReceiveMsg rm) {
		// 未关注 就关注再绑定 关注过就直接绑定
		if (rm.getEventKey() != null && rm.getEventKey().length() > 1) {
			// 根据openid 以及 设备码 先查看是否绑定过，没有绑定过就绑定，绑定过不用绑定
			// 设备号
			// devicecode = rm.getEventKey();
			// 用户的 openid
			// openid = rm.getFromUserName();
			try {
				// wxAccountService.bindManagedDeviceByOpenid(openid,
				// devicecode);
			} catch (Exception e) {
				log.error("已关注，直接绑定设备::" + e);
			}
		}

		if (log.isInfoEnabled()) {
			log.info("接收到扫描消息...");
			log.info("msgid={}, from={}, to={}, event={}, key={}, ticket={}", rm.getMsgId(), rm.getFromUserName(),
					rm.getToUserName(), rm.getEvent(), rm.getEventKey(), rm.getTicket());
		}
		return null;
	}

	@Override
	public OutPutMsg text(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		// om.setMsgType(rm.getMsgType());
		// om.setContent(rm.getContent() + "\n您的消息已经收到[微笑]");
		if (log.isInfoEnabled()) {
			log.info("接收到微信文本消息...");
			log.info("msgid={}, from={}, to={}, content={}", rm.getMsgId(), rm.getFromUserName(), rm.getToUserName(),
					rm.getContent());
		}
		return om;
	}

	@Override
	public OutPutMsg image(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		// om.setMsgType(rm.getMsgType());
		// om.setMediaId(rm.getMediaId());
		if (log.isInfoEnabled()) {
			log.info("接收到微信图像消息...");
			log.info("msgid={}, from={}, to={}, mediaid={}", rm.getMsgId(), rm.getFromUserName(), rm.getToUserName(),
					rm.getMediaId());
		}
		return om;
	}

	@Override
	public OutPutMsg voice(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		if (null != rm.getRecognition()) {
			// om.setMsgType(WxMsgType.text.name());
			// om.setContent("您的语音消息已接收.[微笑]\n内容为：" + rm.getRecognition());
		} else {
			// om.setMsgType(rm.getMsgType());
			// om.setMediaId(rm.getMediaId());
		}
		if (log.isInfoEnabled()) {
			log.info("接收到音频消息...");
			log.info("msgid={}, from={}, to={}, mediaid={}, trans={}", rm.getMsgId(), rm.getFromUserName(),
					rm.getToUserName(), rm.getMediaId(), rm.getRecognition());
		}
		return om;
	}

	@Override
	public OutPutMsg video(ReceiveMsg rm) {
		OutPutMsg om = new OutPutMsg(rm);
		// om.setMsgType(rm.getMsgType());
		// om.setMediaId(rm.getMediaId());
		if (log.isInfoEnabled()) {
			log.info("接收到视频消息...");
			log.info("msgid={}, from={}, to={}, mediaid={}, thumbmid={}", rm.getMsgId(), rm.getFromUserName(),
					rm.getToUserName(), rm.getMediaId(), rm.getThumbMediaId());
		}
		return om;
	}

}
