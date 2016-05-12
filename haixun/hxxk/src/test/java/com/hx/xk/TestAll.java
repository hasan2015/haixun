package com.hx.xk;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RunWith(Suite.class)
@SuiteClasses({ TestService.class })
public class TestAll {
	private static ClassPathXmlApplicationContext ctx = null;

	@AfterClass
	public static void tearDown() {
		if (ctx != null) {
			ctx.close();
		}
	}

	public synchronized static ClassPathXmlApplicationContext getAppContext()
			throws Exception {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return ctx;
	}
}
