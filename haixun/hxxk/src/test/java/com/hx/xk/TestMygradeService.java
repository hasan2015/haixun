/**
 * 
 */
package com.hx.xk;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.service.IMygradeService;
import com.hx.xk.service.IService;
import com.hx.xk.vo.VoMygrade;
import com.hx.xk.vo.VoMyschedule;
import com.hx.xk.vo.VoYear;

/**
 * @author Hasan
 * @Date 2015-3-17 下午8:25:09
 * 
 */
public class TestMygradeService {
	private static IService<DtoUser> userService;
	private static IMygradeService service;
	private static ClassPathXmlApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUp() throws Exception {
		ctx = TestAll.getAppContext();
		service = (IMygradeService) ctx.getBean("mygradeService");
		userService = (IService<DtoUser>) ctx.getBean("userService");
	}

	@AfterClass
	public static void tearDown() {
		service = null;
		userService = null;
	}

	// @Test
	public void create() throws Exception {
		DtoMygrade d = new DtoMygrade();
		// d.setCode("Q2C03-2QH");
		service.create(d);
	}

	// @Test
	public void retrieveMygrades() throws Exception {
		DtoUser user = userService.retrieveById(1);// new DtoUser();
		// user.setUserid(1);
		// user.setId("330106200611020040");
		// user.setMobile("13857168929");
		//
		Pager pager = new Pager();
		pager.setPageNow(0);
		pager.setPageSize(10);
		pager = service.retrieveMygrades(user, pager, true);
		print(pager);
	}

	// @Test
	public void retrieveByDto() throws Exception {
		DtoMygrade d = new DtoMygrade();
		d.setActive(XkConstant.V_TRUE);

		Pager pager = new Pager();
		pager.setPageNow(0);
		pager.setPageSize(2);

		pager = service.retrieveByDto(d, pager);
		print(pager);
	}

	// @Test
	public void retrieveMygradeByParams() throws Exception {

		List<VoMygrade> ldto = service.retrieveMygradeByParams(1, null, "2014", "2014", null, null, null, null);

		System.err.println("size=" + ldto.size());
		for (VoMygrade u : ldto) {
			System.err.println("code=" + u.getReservedcode());
		}

	}

	// @Test
	public void retrieveYearsOfMygrade() throws Exception {
		List<VoYear> list = service.retrieveYearsOfMygrade(1, null, new String[] { "周日", "周六" }, null, null, null);
		System.err.println(list);
		for (VoYear y : list) {
			System.err.println(y.getYear() + "=" + y.getYearCount());
		}
	}

	private void print(Pager pager) {
		if (pager != null) {
			Object result = pager.getResult();
			if (result != null) {
				List<DtoMygrade> ldto = (List<DtoMygrade>) result;
				System.err.println("size=" + ldto.size());
				for (DtoMygrade u : ldto) {
					System.err.println("code=" + u.getReservedcode());
				}

			}
		}
	}

	@Test
	public void retrieveMyscheduleByDate() throws Exception {

		List<VoMyschedule> ldto = service.retrieveMyscheduleByDate(1, "2016-03-14", "2016-03-20");

		System.err.println("size=" + ldto.size());
		for (VoMyschedule u : ldto) {
			System.err.println(" name=" + u.getSpecialtyName());
		}

	}
}
