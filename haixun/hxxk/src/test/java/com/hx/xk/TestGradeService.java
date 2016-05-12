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
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.service.IGradeService;
import com.hx.xk.service.IScheduleService;
import com.hx.xk.service.IService;
import com.hx.xk.service.ISpecialtyService;

/**
 * @author Hasan
 * @Date 2015-3-17 下午8:25:09
 * 
 */
public class TestGradeService {
	private static IService<DtoUser> userService;
	private static IGradeService service;
	private static ISpecialtyService specialtyService;
	private static IScheduleService scheduleService;
	private static ClassPathXmlApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUp() throws Exception {
		ctx = TestAll.getAppContext();
		service = (IGradeService) ctx.getBean("gradeService");
		scheduleService = (IScheduleService) ctx.getBean("scheduleService");
		userService = (IService<DtoUser>) ctx.getBean("userService");
		specialtyService = (ISpecialtyService) ctx.getBean("specialtyService");
	}

	@AfterClass
	public static void tearDown() {
		service = null;
	}

	// @Test
	public void create() throws Exception {
		DtoGrade d = new DtoGrade();
		d.setCode("Q2C03-2QH");
		service.create(d);
	}

	@Test
	public void retrieveMygrades() throws Exception {
		DtoUser user = userService.retrieveById(1);// new DtoUser();
		// user.setUserid(1);
		// user.setId("330106200611020040");
		// user.setMobile("13857168929");
		//
		DtoGrade grade = new DtoGrade();
		grade.setCode("小星星表演5-B2");
		List<DtoGrade> lGrades = service.retrieveByDto(grade);
		if (lGrades != null && lGrades.size() > 0)
			grade = service.retrieveDetail(user, lGrades.get(0), true);
		System.err.println(" " + grade);
	}

	// @Test
	public void retrieveByDto() throws Exception {
		DtoGrade d = new DtoGrade();
		d.setActive(XkConstant.V_TRUE);

		Pager pager = new Pager();
		pager.setPageNow(0);
		pager.setPageSize(2);

		pager = service.retrieveByDto(d, pager);

		print(pager);
	}

	private void print(Pager pager) {
		if (pager != null) {
			Object result = pager.getResult();
			if (result != null) {
				List<DtoGrade> ldto = (List<DtoGrade>) result;
				System.err.println("size=" + ldto.size());
				for (DtoGrade u : ldto) {
					System.err.println("code=" + u.getCode());
				}

			}
		}
	}

	@Test
	public void deletebygradeid() throws Exception {
		scheduleService.deleteByGradeid(9);

	}

	@Test
	public void retrieveOpengrades() throws Exception {
		Pager pager = new Pager();
		pager.setPageNow(0);
		pager.setPageSize(2);
		pager = service.retrieveOpengrades(null, null,  null, null, null, pager);
		System.err.println(pager.getResult());
	}

	@Test
	public void retrieveSpecialtyOfOpengrade() throws Exception {
		Pager pager = new Pager();
		pager.setPageNow(0);
		pager.setPageSize(1112);
		String[] w={"周一","周二","周六","周日"};
		List<DtoSpecialty> sDtoSpecialties = specialtyService.retrieveSpecialtyOfOpengrade("发展中心", "秋季", w, "10:10",
				"11:30", pager);
		System.err.println(sDtoSpecialties.size());
	}
	@Test
	public void retrieveByLinkAndCode() throws Exception{
	 DtoGrade dtoGrade=	service.retrieveByLinkAndCode("644E4C61A2064ABBB473776EE5BC9B0F","Q1B10-1");
	 System.err.println(dtoGrade.getDescription());
	  dtoGrade=	service.retrieveByLinkAndCode("87AF8536885D4041912A5F610215C6D9","Q1B10-1");
	 System.err.println(dtoGrade.getDescription());
	}
	@Test
	public void retrieveGradeinfoByGradeid() throws Exception {
		DtoGrade dtoGrade=service.retrieveById(4978);
		System.err.println(dtoGrade.getYschedules().size());
		
	}
	@Test
	public void retrieveByGradeid()throws Exception{
		System.err.println(scheduleService.retrieveByGradeid(4978).size());
	}
}
