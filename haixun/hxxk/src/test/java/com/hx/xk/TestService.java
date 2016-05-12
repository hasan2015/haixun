/**
 * 
 */
package com.hx.xk;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkPropConfigurer;
import com.hx.xk.dto.DtoMyclock;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.service.IInstitutionService;
import com.hx.xk.service.IMyclockService;
import com.hx.xk.service.IUserService;

/**
 * @author Hasan
 * @Date 2015-3-17 下午8:25:09
 * 
 */
public class TestService {
	private static IUserService userservice;
	private static IInstitutionService institutionService;
	private static IMyclockService myclockService;
	private static ClassPathXmlApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUp() throws Exception {
		ctx = TestAll.getAppContext();
		userservice = (IUserService) ctx.getBean("userService");
		myclockService = (IMyclockService) ctx.getBean("myclockService");
		institutionService = (IInstitutionService) ctx.getBean("institutionService");
	}

	@AfterClass
	public static void tearDown() {
		userservice = null;
		myclockService = null;
	}

	@Test
	public void updateLatestdate() throws Exception {
		userservice.updateLatestDate(1, new Timestamp(System.currentTimeMillis()));
	}

	@Test
	public void createOrUpdate() throws Exception {
		DtoUser d = new DtoUser();
		// d.setWxopenid("123456");
		d.setName("赵钱孙李2");
		userservice.createOrUpdate(d, "123456");
	}

	@Test
	public void refreshdateQSN() throws Exception {
		institutionService.refreshdateQSN(new Timestamp(System.currentTimeMillis()));
	}

	@Test
	public void retrieveByDto() throws Exception {
		DtoUser d = new DtoUser();
		d.setActive(XkConstant.V_TRUE);

		Pager pager = new Pager();
		pager.setPageNow(0);
		pager.setPageSize(2);

		pager = userservice.retrieveByDto(d, pager);
		if (pager != null) {
			Object result = pager.getResult();
			if (result != null) {
				List<DtoUser> ldto = (List<DtoUser>) result;
				System.err.println("size=" + ldto.size());
				for (DtoUser u : ldto) {
					System.err.println("name=" + u.getName());
				}

			}
		}
	}

	@Test
	public void fff() {
		try {
			System.err.println(
					new Date().getTime() - 60 * 60 * 1000 * XkPropConfigurer.getIntvalueByProp("interval.autoextract")
							- XkConstant.SDF_yyyyMMddHHmmss.parse("2016-03-15 16:25:09").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void createClock() throws Exception{
		for (int i = 0; i < 5; i++) {

			DtoMyclock myclock=new DtoMyclock();
			myclock.setName("体操II"+i);
			myclock.setStarttime(new Timestamp(System.currentTimeMillis()));
			myclock.setEndtime(new Timestamp(System.currentTimeMillis()));
			myclock.setIntervalm(1);
			myclock.setDuration(10);
			myclockService.create(myclock);
		}
	}

	@Test
	public void retrieveClock() throws Exception {
		List<DtoMyclock> list = myclockService.retrieveAll();
		for (DtoMyclock clock : list)
			System.err.println(clock.getName());
	}

}
