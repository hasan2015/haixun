package com.hx.xk.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Hasan
 * @date 2014-3-2 下午5:07:20
 * 
 */
public class XkConstant {
	public static final SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat SDF_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat SDF_yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	public static final SimpleDateFormat SDF_yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final DecimalFormat DF_00 = new DecimalFormat("0.00");
	// dao查询
	public static final int PAGESIZE_MAX = 999999999; // 1,000,000
	public static final int PAGESIZE_DEF = 10; // 默认分页大小
	public static final int PAGENOW_DEF = 0; //
	public static final int DEFAULT_PV = 0;
	public static final String V_DEF_PASSWORD = "123456";// 默认初始密码
	public static final String ORDERBY_ASC = "ASC";
	public static final String ORDERBY_DESC = "DESC";
	public static final String FUZZY_PROPERTY_PREFIX = "%";
	public static final String FUZZY_UNDERLINE = "_";
	public static final String FUZZY_LEFT_BRACKET = "[";
	public static final String FUZZY_RIGHT_BRACKET = "]";
	public static final String FUZZY_NOTLINKE = "notlike ";
	public static final String FUZZY_ALL_NE_DOOR = "%99__";
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String ORDERBY_REPLYDATE = "replydate";
	public static final short V_DELETE_STATUS = -99;// 删除状态值
	// dto
	public static final String RESULT_CODE_SUCCESS = "000000";
	public static final String RESULT_CODE_FAILURE = "-1";// 临时chang量
	public static final String RESULT_CODE_ERR_PWD = "-2";// 临时chang量
	public static final String RESULT_CODE_USER_NOT_LOGIN = "-3";// 临时chang量
	public static final String RESULT_CODE_USER_NOT_BIND = "-4";// 未绑定

	// public static final int RESULT_CODE_FAILURE = 1;
	public static final String RESULT_STR_SUCCESS = "success";
	public static final String RESULT_ID = "id";
	public static final String RESULT_RESULT = "result";
	// url
	public static final String URL_UPLOAD_FOLDER_OTHER = "other";
	public static final String URL_UPLOAD_FOLDER_IMAGE = "images";
	public static final String URL_UPLOAD_FOLDER_VOICE = "voices";
	public static final String URL_UPLOAD_FOLDER = "upload";//

	public static final String URL_UPLOAD_FOLDER_IMAGE_DOCTOR_QRCODE = "doctor/qrcode/max";// 医生二维码
	public static final String URL_UPLOAD_FOLDER_IMAGE_DOCTOR_QRCODE_MIN = "doctor/qrcode/min";// 医生名片二维码
	public static final String URL_UPLOAD_FOLDER_IMAGE_DOCTOR_LICENSE = "doctor/license";// 医生执照
	public static final String URL_UPLOAD_FOLDER_IMAGE_DOCTOR_WORKCARD = "doctor/workcard";// 医生工牌
	public static final String URL_UPLOAD_FOLDER_IMAGE_DOCTOR_PORTRAIT = "doctor/portrait";// 医生头像
	public static final String URL_UPLOAD_FOLDER_IMAGE_PATIENT_PORTRAIT = "patient/portrait";// 患者头像
	public static final String URL_UPLOAD_FOLDER_IMAGE_PATIENT_HR = "patient/hr";// 患者健康档案
	public static final String URL_UPLOAD_FOLDER_IMAGE_ACCOUNT_PORTRAIT = "account/portrait";// 账户头像
	public static final String URL_UPLOAD_FOLDER_IMAGE_CHAT_IMAGE = "chat/images";// 聊天图片
	public static final String URL_UPLOAD_FOLDER_IMAGE_CHAT_OTHER = "chat/other";// 聊天其他
	public static final String URL_UPLOAD_FOLDER_IMAGE_CHAT_VOICE = "chat/voices";// 聊天音频
	public static final String URL_UPLOAD_FOLDER_IMAGE_DOCTOR_DOSSIER = "doctor/dossier";// 医生病历夹
	public static final String URL_UPLOAD_FOLDER_IMAGE_COURSE_IMAGE = "course/image";// 医生课堂图片

	public static final String DOWNLOAD_FOLDER_MOBILEDOCTOR_RELEASE = "download/apk/ddsdoctor";// 下载路径
	public static final String DOWNLOAD_ANDROID_DOCTOR = "ddsdoctor";

	public static final String DOWNLOAD_FOLDER = "download";//
	public static final String DOWNLOAD_FOLDER_MOBILECLIENT_RELEASE = "download/apk/ddsclinic";//
	public static final String DOWNLOAD_ANDROID_CLINIC = "ddsclinic";
	public static final String DOWNLOAD_ANDROID_SUFFIX = ".apk";

	// value
	public static final short V_TRUE = 1;// 是，启用，开，活动,女 ,已读
	public static final short V_FALSE = 0;// 默认的， 否，禁用，关，静止，男,未取消关注 ，推送 未读
	public static final short V_NO_CHECK = -1;// （没有审核过的）医院科室医生, 未推送
	public static final short V_T_USER_TYPE_DOCTOR = 1;// 医生
	public static final short V_T_USER_TYPE_PATIENT = 2;// 患者
	public static final short V_T_USER_TYPE_ACCOUNT = 3;// 账户
	public static final short V_T_CHAT_TYPE_TEXT = 0;
	public static final short V_T_CHAT_TYPE_IMAGE = 1;
	public static final short V_T_CHAT_TYPE_VOICE = 2;
	public static final int V_T_EXTRA_STATUS_UNAPPROVED = 0;// 待确认
	public static final int V_T_EXTRA_STATUS_APPROVED = 1;// 已确认
	public static final int V_T_EXTRA_STATUS_REFUSED = 2;// 已拒绝
	public static final short V_T_EXTRA_STATUS_EXPIRATION = 3;// 已过期

	// public static final short V_T_MOBILE_VERITY_TYPE_FORGET = 0;// 忘记密码
	// public static final short V_T_MOBILE_VERITY_TYPE_RESET = 1;// 重置 注册

	public static final long V_T_ACCOUNT_SUPERADMIN = 1001;// 系统超级管理员用户 不能删除
	// public static final long V_T_ROLE_SUPERADMIN = 1001;// 超级管理员 roleid
	public static final long V_T_ROLE_ASSISTANT = 1002;// 医学助手id

	public static final long V_T_DICTIONARY_HEALTHRCORD = 7006; // 健康档案提醒
	public static final long V_T_DICTIONARY_EXTRAREGISTER = 7005; // 门诊加号数据字典
	public static final long V_T_DICTIONARY_ASSISTANT = 7004; // 医生助手
	public static final long V_T_DICTIONARY_CHAT = 7003; // 在线咨询
	public static final long V_T_DICTIONARY_MESSAGE = 7002; // 消息提醒
	public static final long V_T_DICTIONARY_DOSSIERPHOTO = 4001;// 病历夹图片类型
	public static final long V_T_DICTIONARY_EXTRAREGISTER_REMIND = 7025;// 患者加号提醒
	public static final long V_T_DICTIONARY_MESSAGE_SYSTEMMESSAGE = 9002;// 消息类型（系统消息）

	// public static final long V_T_DICTIONARY_SMSTEMPLATE_VERITY = 8003; //
	// 验证短信
	// public static final long V_T_DICTIONARY_SMSTEMPLATE_NOTICE = 8002; //
	// 通知短信

	// 短信模板id
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_REGISTER = 1001;// 注册验证
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_FORGETPASSWORD = 1002;// 忘记密码
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_UPDATEMOBILE = 1003;// 修改手机号
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_DOCTOR = 1004;// 医生账号密码告知
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_UPDATEDOCTORINFORMATION = 1005;// 后台医生信息修改
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_EXTRAREGISTERSUCCESS = 1006;// 加号申请成功
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_EXTRAREGISTERFAILURE = 1007;// 加号申请拒绝
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_DOCTORATTENTION = 1008;// 医生关注
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_SETASSFORDOCTORATTENTION = 1009;// 为医生设置助手
	public static final long V_T_SMSTEMPLATE_SMSTEMPLATEId_SENDFORDOCTOR = 1010;// 医生汇报
	public static final String V_SIGN = "【医点科技】";// 短信签名

	// 加号申请状态
	public static final short V_T_EXTRAREGISTER_UNCONFIRMED = 0; // 未确认
	public static final short V_T_EXTRAREGISTER_CONFIRMED = 1; // 已确认
	public static final short V_T_EXTRAREGISTER_NEGATED = 2; // 回绝
	public static final short V_T_EXTRAREGISTER_OVERDUE = 3; // 过期的

	public static final String UPLOAD_FILE_TYPE_ARTICLE = "article";
	public static final String V_SESSION_ACCOUNT = "DtoAccount";
	public static final String V_SESSION_DOCTOR = "DtoDoctor";// 医生
	public static final String V_SESSION_PATIENT = "DtoPatient";// 患者
	public static final String V_SESSION_WXACCOUNT = "DtoWxaccount";
	public static final String[] V_WEEK = { "日", "一", "二", "三", "四", "五", "六" };
	// url action
	public static final String V_URL_ROOT = "/ddsmms";
	public static final String V_URL_COMMON = "/page/common";// 通用接口
	public static final String V_URL_MMS = "/page/mms";// mms端接口
	public static final String V_URL_CLINIC = "/page/clinic"; // 诊所端接口路径前缀
	public static final String V_URL_DOCTOR = "/page/doctor"; // 患者端接口路径前缀

	public static final String V_URL_MMS_LOGIN = "/page/mms/login.jsp"; // 运维端登陆页面
	public static final String V_URL_MMS_LOGIN_JUDGE = "/page/mms/login.jsp?logout=1";
	//
	public static final String V_URL_RETRIEVE_DOCTORDETAIL = "/retrieveDoctorDetail"; //
	public static final String V_URL_UPDATE_USERPASSWORD = "/updateUserPassword"; //
	public static final String V_URL_RESET_USERPASSWORD = "/resetUserPassword";
	public static final String V_URL_RETRIEVE_CHAT_LIST = "/retrieveChatList";
	public static final String V_URL_RETRIEVE_CHAT_UNREADCOUNT = "/retrieveUnreadCount";
	//
	public static final String V_URL_LOGIN = "/login";
	public static final String V_URL_LOGOUT = "/logout";
	public static final String V_URL_GENVERITYCODE = "/genveritycode";
	public static final String V_URL_VERITYCODE = "/veritycode";
	public static final String V_URL_EXISTMOBILE = "/existMobile";

	public static final String V_URL_REGISTER = "/register";
	public static final String V_URL_ACTION_SUFFIX = ".do";
	public static final String V_URL_RETRIEVEANDRIODVERSION = "/retrieveAndriodVersion";
	public static final String V_URL_RETRIEVEIOSVERSION = "/retrieveIOSVersion";

	// url page
	public static final String V_URL_PORTAL_HOME = "/page/portal/trading.jsp";
	public static final String V_URL_MMS_HOME = "/page/console/CanteenStaffCenterBefore.jsp";
	public static final String V_PAGE_URL_LOGIN = "/page/console/login.jsp";

	public static final String V_URL_LOGIN_ACTION = "/page/mms/login.do";

	public static final String V_PAGE_URL_DETAILEDCLASS = "/page/mms/detailedclass.jsp";
	public static final String V_URL_DETAILEDCLASS_ACTION = "/page/mms/retrieveCourseById.do";

	public static final String V_URL_CINIIC_LOGIN_ACTION = "/page/clinic/login.do"; // 诊所端登陆
	public static final String V_URL_DOCTOR_LOGIN_ACTION = "/page/doctor/login.do"; // 患者端登陆
	public static final String V_URL_CINIIC_CHECKRELEASE_ACTION = "/page/clinic/checkRelease.do";
	public static final String V_URL_GETVERISON_ACTION = "/page/console/getVeriosn";

	// 登陆不过滤页
	public static final String[] V_URL_NOTFILTER = new String[] {};
	// controller url
	public static final String V_URL_ROOT_WEIXIN = "wx";

	public static final String T_F_DOCTOR_VERIFYCODE = "verifycode";
	public static final String T_F_DOCTOR_VERIFYCODEDATE = "verifycodedate";
	// httpclient
	public static final String V_CONTENTTYPE_TEXT_PLAIN = "text/plain";
	public static final String V_CONTENTTYPE_TEXT_HTML = "text/html";
	public static final String V_CONTENTTYPE_TEXT_XML = "text/xml";
	public static final String V_CONTENTTYPE_APPLICATION_XML = "application/xml";
	public static final String V_CONTENTTYPE_APPLICATION_JSON = "application/json";
	// 微信
	public static final int RESULT_CODE_WEIXIN_SUCCESS = 0;// 微信返回码 请求成功
	// public static final int V_COUNTRY_LANG_ = 0;// 微信返回码 请求成功

	// session
	public static final String S_SESSION_OPENID = "openid";
	public static final String S_SESSION_ACCESSTOKEN = "DtoWxPageAuthSuccessResult";
	// 验证码
	public static final String V_VERITY = "verity";
	public static final String V_VERITY_PHONE = "phone";
	// 验证码过期时间
	public static final String S_VERIFYCODE_CREATEDATE = "randomNumberCreateTime";
	public static final long V_VERITYCODE_DURATION = 120000;// 2分钟
	// qsn 爬取
	public static final String V_EXTRACT_USERNAME_ANONYMOUS = "anonymous";
	public static final int V_T_INSTI_ID_QSN = 1;
	public static final int V_EXTRACT_REFRESH_INTERVAL = 24;//默认信息间隔24H

}
