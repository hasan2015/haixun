/**
 * 
 */
package com.hx.xk.weixin.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hx.xk.common.util.XkUtil;
import com.hx.xk.weixin.service.IWeixinService;

/**
 * @author Hasan
 * @Date 2015-3-17 下午8:25:09
 * 
 */
public class TestService {
	private static IWeixinService weixinService;

	private static ClassPathXmlApplicationContext ctx;

	@BeforeClass
	public static void setUp() throws Exception {
		ctx = TestAll.getAppContext();
		weixinService = (IWeixinService) ctx.getBean("weixinService");
	}

	@AfterClass
	public static void tearDown() {
		weixinService = null;
	}

	@Test
	public void createmenu() throws Exception {
		weixinService.createMenu("");

	}

	// @Test
	public void sendTemplateMsgBP() throws Exception {
		weixinService.sendTMsgClock("o-HPPv0_eLeG-sMlrS0hh8rAOkW0", "恭喜您绑定血压计设备成功！", "0367206904229788",
				XkUtil.SDF_yyyy_MM_dd_.format(new java.util.Date()), "感谢您的支持！");

	}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
	@Test
	public void sendTMsgBPDetect() throws Exception {

	}

}
