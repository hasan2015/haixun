package com.hx.xk.weixin.service;

import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.weixin.dto.mapper.DtoWxPageAuthSuccessResult;

/**
 * 
 * @author Hasan
 * @Date 2015-8-14 上午8:58:14
 * 
 */
public interface IWeixinService {
	public DtoResult createQrcode(String deviceSN) throws Exception;

	/**
	 * 创建自定义菜单
	 * 
	 * @param menus
	 * @return
	 * @throws Exception
	 */
	public DtoResult createMenu(String... menus) throws Exception;

	/**
	 * 微信模版消息 血压测量结果通知
	 * 
	 * @param openid
	 * @param msgs
	 *            first,highPressure,lowPressure,heart,bloodPressureLevel,remark
	 * @return
	 * @throws Exception
	 */
	// public DtoResult sendTMsgBPDetect(String openid, String realName, String
	// date, VoBloodPressure voBP)
	// throws Exception;

	/**
	 * 微信模版消息 血压计绑定成功通知
	 * 
	 * @param openid
	 * @param msgs
	 *            first,血压计设备ID,绑定时间,remark
	 * @return
	 * @throws Exception
	 */
	public DtoResult sendTMsgClock(String openid, String... msgs) throws Exception;

	/**
	 * 用户同意授权，获取code
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public DtoResult getPageAuthAccessToken(String code) throws Exception;

	public DtoResult getJsAPISignByUrl(String url) throws Exception;

	/**
	 * 从微信服务器下载图片到本地
	 * 
	 * @param mediaId
	 *            微信传过来的mediaId
	 * @param path
	 *            本地服务器路径
	 * @param Objectkey
	 *            要上传到云端的路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public DtoResult downloadimage(String mediaId, String path, String Objectkey) throws Exception;

	public DtoResult getPageAuthorizeCode(String url) throws Exception;

	public DtoResult refreshAccessToken4Page(DtoWxPageAuthSuccessResult pageAuthSuccessResult) throws Exception;

	/**
	 * 查询微信资料，返回DtoWxaccount
	 * @param accessToken
	 * @param openid
	 * @param lang
	 * @return
	 * @throws Exception
	 */
	public DtoResult reshreshWxaccountInfo(String accessToken, String openid, String lang) throws Exception;
}
