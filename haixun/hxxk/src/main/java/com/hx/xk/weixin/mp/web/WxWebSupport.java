package com.hx.xk.weixin.mp.web;


import com.hx.xk.weixin.mp.core.WxBase;
import com.hx.xk.weixin.mp.core.WxHandler;
import com.hx.xk.weixin.mp.vo.MPAct;

/**
 * 微信WEB环境接口设计
 *
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2015/1/13
 */
public interface WxWebSupport {

    /**
     * 设置微信公众号信息
     *
     * @param mpAct 公众号信息
     */
    void setMpAct(MPAct mpAct);

    /**
     * 设置微信消息处理器
     *
     * @param wxHandler 微信消息处理器
     */
    void setWxHandler(WxHandler wxHandler);

    /**
     * 获取微信基础功能
     *
     * @return 基础功能
     */
    WxBase getWxBase();
}
