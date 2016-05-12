/**
 * 
 */
package com.hx.xk.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hx.xk.common.util.XkPropConfigurer;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author Hasan
 * @Date 2015-5-8 上午11:16:42
 * 
 */
public class JPushSingleton {
	protected static final Logger LOG = LoggerFactory
			.getLogger(JPushSingleton.class);

	JPushClient jpushClient = null;
	private static JPushSingleton instance = new JPushSingleton();

	private JPushSingleton() {
		jpushClient = new JPushClient(XkPropConfigurer
				.getContextProperty("push.masterSecret").toString(),
				XkPropConfigurer.getContextProperty(
						"phsh.appKey").toString(), true,0L);
	}

	public static JPushSingleton getInstance() {

		return instance;
	}

	public PushResult sendPushObject_all_alias_alert(String alias, String msg) {
		PushPayload payload = PushPayload.newBuilder()
				.setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(msg)).build();
		PushResult result = null;
		try {
			result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);
			LOG.info("HTTP Status: " + e.getStatus() + "::Error Code: "
					+ e.getErrorCode() + "::Error Message: "
					+ e.getErrorMessage() + "::Msg ID: " + e.getMsgId());
		}
		return result;
	}

	// demo App defined in resources/jpush-api.conf
	// private static final String appKey ="46ffd71fe6dd20ed9483b3fe";
	// private static final String masterSecret = "45392852e0b34770da920271";

	private static final String appKey = "8f388972b1b2975567bc4677";
	private static final String masterSecret = "4c2c44bc1cf01719deeae29d";

	public static final String TITLE = "Test from API example";
	public static final String ALERT = "医点提醒你，在线求医，找一点- alert";
	public static final String MSG_CONTENT = "Test from API Example - msgContent";
	public static final String REGISTRATION_ID = "0900e8d85ef";
	public static final String TAG = "tag_api";
	public void testSendPush() {
		// HttpProxy proxy = new HttpProxy("localhost", 3128);
		// Can use this https proxy: https://github.com/Exa-Networks/exaproxy
		// JPushClient jpushClient = new JPushClient(
		// DdsPropertyPlaceholderConfigurer.getContextProperty(
		// "push.masterSecret").toString(),
		// DdsPropertyPlaceholderConfigurer.getContextProperty(
		// "phsh.appKey").toString(), 3);

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = buildPushObject_all_alias_alert();

		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error(
					"Error response from JPush server. Should review and fix it. ",
					e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}

	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}

	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.alias("alias1"))
				.setNotification(Notification.alert(ALERT)).build();
	}

	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.tag("tag1"))
				.setNotification(Notification.android(ALERT, TITLE, null))
				.build();
	}

	public static PushPayload buildPushObject_android_and_ios() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag("tag1"))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert("alert content")
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setTitle("Android Title")
												.build())
								.addPlatformNotification(
										IosNotification
												.newBuilder()
												.incrBadge(1)
												.addExtra("extra_key",
														"extra_value").build())
								.build()).build();
	}

	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.ios())
				.setAudience(Audience.tag_and("tag1", "tag_all"))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setAlert(ALERT).setBadge(5)
												.setSound("happy")
												.addExtra("from", "JPush")
												.build()).build())
				.setMessage(Message.content(MSG_CONTENT))
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(
						Audience.newBuilder()
								.addAudienceTarget(
										AudienceTarget.tag("tag1", "tag2"))
								.addAudienceTarget(
										AudienceTarget
												.alias("alias1", "alias2"))
								.build())
				.setMessage(
						Message.newBuilder().setMsgContent(MSG_CONTENT)
								.addExtra("from", "JPush").build()).build();
	}

}
