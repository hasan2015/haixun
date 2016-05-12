/**
 * 
 */
package com.hx.xk;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoSchedule;
import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.extra.HttpRequest;
import com.hx.xk.extra.Rule;
import com.hx.xk.extra.qsn.ExtractApplyList;
import com.hx.xk.extra.qsn.ExtractApplyfront;
import com.hx.xk.extra.qsn.ExtractClazzView;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IScheduleService;
import com.hx.xk.service.IService;
import com.hx.xk.task.XkJob;

/**
 * @author Hasan
 * @Date 2015-3-17 下午8:25:09
 * 
 */
public class TestJob {
	private static final String URL_ROOT_QSNG = "http://hzcs.qsng.cn/hz-bsp/hz/";
	private static IService<DtoUser> userService;
	private static IService<DtoMygrade> mygradeService;
	private static IService<DtoGrade> gradeService;
	private static IScheduleService scheduleService;
	private static IExtractQsnService extractQsnService;
	private static IService<DtoSpecialty> specialtyService;
	private static ClassPathXmlApplicationContext ctx;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void setUp() throws Exception {
		ctx = TestAll.getAppContext();
		userService = (IService<DtoUser>) ctx.getBean("userService");
		mygradeService = (IService<DtoMygrade>) ctx.getBean("mygradeService");
		gradeService = (IService<DtoGrade>) ctx.getBean("gradeService");
		scheduleService = (IScheduleService) ctx.getBean("scheduleService");
		extractQsnService = (IExtractQsnService) ctx.getBean("extractQsnService");
		specialtyService = (IService<DtoSpecialty>) ctx.getBean("specialtyService");
	}

	@AfterClass
	public static void tearDown() {
		userService = null;
		mygradeService = null;
		extractQsnService = null;
	}

	@Test
	public void create() throws Exception {
		String homeurl = "http://hzcs.qsng.cn/hz-bsp/hz/";
		String linkHrefopengrade = "apply-front.action";
		String linkHrefBeijing = "apply-front!applyList.action";
		// 登录
		DtoUser dtoUser = new DtoUser();
		dtoUser.setId("330106200611020040");
		dtoUser.setMobile("13857168929");
		List<DtoUser> lUsers = userService.retrieveByDto(dtoUser);
		if (lUsers != null && lUsers.size() > 0) {
			dtoUser = lUsers.get(0);
		} else {
			DtoResult reUser = userService.create(dtoUser);
			dtoUser = (DtoUser) reUser.getResult();
		}
		DtoResult result = login(dtoUser.getId(), dtoUser.getMobile());
		if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
			// 可报班级，登录后获取打开已报班级页面链接
			Rule rule = new Rule(homeurl + linkHrefopengrade, null, null, "login", Rule.CLASS, Rule.GET);
			String[] applyfront = ExtractApplyfront.extract("330106200611020040", rule);
			// 已报班级
			rule = new Rule(homeurl + applyfront[0], null, null, "tb", Rule.CLASS, Rule.GET);
			List<DtoMygrade> mygrades = ExtractApplyList.extract("330106200611020040", rule);
			if (mygrades != null && mygrades.size() > 0) {
				for (DtoMygrade mygrade : mygrades) {// .subList(0, 1110)) {
					rule = new Rule(homeurl + mygrade.getLinkHref(), null, null, "tb", Rule.CLASS, Rule.GET);
					// 班级
					DtoGrade dtoGrade = null;
					// List<DtoGrade> grades =
					// ExtractClazzView.extract("330106200611020040", rule);
					// for (DtoGrade grade : grades) {
					DtoGrade grade = ExtractClazzView.extract("330106200611020040", rule);
					if (grade != null) {
						Map<String, Object> whereProperties = new HashMap<String, Object>();
						// 存储（刷新=重新存储 专业
						whereProperties.put("name", grade.getSpecialtyName());
						List<DtoSpecialty> lSpecialties = specialtyService.retrieveByProperties(whereProperties);
						DtoSpecialty dtoSpecialty = null;
						if (lSpecialties != null && lSpecialties.size() > 0) {
							dtoSpecialty = lSpecialties.get(0);
						} else {
							dtoSpecialty = new DtoSpecialty();
							dtoSpecialty.setName(grade.getSpecialtyName());
							DtoResult reSpecialty = specialtyService.create(dtoSpecialty);
							dtoSpecialty = (DtoSpecialty) reSpecialty.getResult();
						}
						grade.setYspecialty(dtoSpecialty);
						grade.setLink(mygrade.getLinkHref());
						// 存储 班级
						whereProperties.clear();
						whereProperties.put("code", grade.getCode());
						List<DtoGrade> lgGrades = gradeService.retrieveByProperties(whereProperties);
						DtoResult reGrade = null;
						if (lgGrades != null && lgGrades.size() > 0) {
							reGrade = gradeService.createOrUpdate(grade, lgGrades.get(0).getGradeid());
							// dtoGrade = lgGrades.get(0);
						} else {
							// dtoGrade=new DtoGrade();
							reGrade = gradeService.create(grade);

						}
						dtoGrade = (DtoGrade) reGrade.getResult();
						// 存储 课表
						Set<DtoSchedule> yschedules = grade.getYschedules();
						for (DtoSchedule schedule : yschedules) {
							schedule.setYgrade(dtoGrade);
							List<DtoSchedule> lSchedules = scheduleService.retrieveByAllProps(schedule);
							if (lSchedules == null || lSchedules.size() == 0) {
								scheduleService.create(schedule);
							}
						}
					}
					// 存储mygrade
					mygrade.setYgrade(dtoGrade);
					mygrade.setYuser(dtoUser);
					List<DtoMygrade> lMygrades = mygradeService.retrieveByDto(mygrade);
					DtoResult reMygrade = null;
					if (lMygrades != null & lMygrades.size() > 0) {
						reMygrade = mygradeService.createOrUpdate(mygrade, lMygrades.get(0).getMygradeid());
					} else {
						reMygrade = mygradeService.create(mygrade);
					}
					DtoMygrade dto = (DtoMygrade) reMygrade.getResult();

				}
			}
		}
	}

	@Test
	public void refreshOpengrade() throws Exception {
		String homeurl = "http://hzcs.qsng.cn/hz-bsp/hz/";
		String linkHrefopengrade = "apply-front.action";
		// 匿名访问，获取session
		login();
		// 未登录，访问“可报班级”页面;抓取“已报班级”列表
		for (int i = 0; i < 4; i++) {
			// 获取分页数据
			Rule rule = new Rule(homeurl + linkHrefopengrade + "?area=" + i, null, null, "pages", Rule.CLASS, Rule.GET);
			Pager pager = ExtractApplyList.extractPager(rule);
			for (int j = 1; j < pager.getPageSize(); j++) {
				rule = new Rule(homeurl + linkHrefopengrade + "?area=" + i + "&page.currentPage=" + j, null, null, "tb",
						Rule.CLASS, Rule.GET);
				List<DtoGrade> opengrades = ExtractApplyList.extractOpenGrades(rule);

				if (opengrades != null && opengrades.size() > 0) {
					for (DtoGrade opengrade : opengrades) {// .subList(0, 1110))
															// {
						rule = new Rule(homeurl + opengrade.getLinkHref(), null, null, "tb", Rule.CLASS, Rule.GET);
						// 获取“班级”链接，查询班级信息
						DtoGrade grade = ExtractClazzView.extractOpenGrade(rule);
						if (grade != null) {
							grade.setApplystatus(opengrade.getApplystatus());
							grade.setAreaid(i);
							Map<String, Object> whereProperties = new HashMap<String, Object>();
							// 班级的专业（创建/更新)
							whereProperties.put("name", grade.getSpecialtyName());
							List<DtoSpecialty> lSpecialties = specialtyService.retrieveByProperties(whereProperties);
							DtoSpecialty dtoSpecialty = null;
							if (lSpecialties != null && lSpecialties.size() > 0) {
								dtoSpecialty = lSpecialties.get(0);// 更新
							} else {
								dtoSpecialty = new DtoSpecialty();// 创建
								dtoSpecialty.setName(grade.getSpecialtyName());
								DtoResult reSpecialty = specialtyService.create(dtoSpecialty);
								dtoSpecialty = (DtoSpecialty) reSpecialty.getResult();
							}
							grade.setYspecialty(dtoSpecialty);
							grade.setLink(opengrade.getLinkHref());
							// 班级（创建/更新)
							whereProperties.clear();
							whereProperties.put("code", grade.getCode());
							List<DtoGrade> lgGrades = gradeService.retrieveByProperties(whereProperties);
							DtoResult reGrade = null;
							if (lgGrades != null && lgGrades.size() > 0) {
								int gradeid = lgGrades.get(0).getGradeid();
								reGrade = gradeService.createOrUpdate(grade, gradeid);
								// 清空原有课表
								scheduleService.deleteByGradeid(gradeid);
							} else {
								// dtoGrade=new DtoGrade();
								reGrade = gradeService.create(grade);

							}
							DtoGrade dtoGrade = null;
							dtoGrade = (DtoGrade) reGrade.getResult();
							// 班级的课表（创建，后续考虑记录变化历史）
							Set<DtoSchedule> yschedules = grade.getYschedules();
							for (DtoSchedule schedule : yschedules) {
								schedule.setYgrade(dtoGrade);
								scheduleService.create(schedule);
							}
						}

					}
				}
			}
		}
	}

	@Test
	public void refreshMYgrade() throws Exception {
		String homeurl = "http://hzcs.qsng.cn/hz-bsp/hz/";
		String linkHrefopengrade = "apply-front.action";
		// 登录
		DtoUser dtoUser = new DtoUser();
		dtoUser.setId("330106200611020040");
		dtoUser.setMobile("13857168929");
		List<DtoUser> lUsers = userService.retrieveByDto(dtoUser);
		if (lUsers != null && lUsers.size() > 0) {
			dtoUser = lUsers.get(0);
		} else {
			DtoResult reUser = userService.create(dtoUser);
			dtoUser = (DtoUser) reUser.getResult();
		}
		DtoResult result = login(dtoUser.getId(), dtoUser.getMobile());
		if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
			// 登录后，访问“可报班级”页面，从而获取“已报班级”页面链接
			Rule rule = new Rule(homeurl + linkHrefopengrade, null, null, "login", Rule.CLASS, Rule.GET);
			String[] applyfront = ExtractApplyfront.extract("330106200611020040", rule);
			// 访问“已报班级”页面，抓取“已报班级”列表
			rule = new Rule(homeurl + applyfront[0], null, null, "tb", Rule.CLASS, Rule.GET);
			List<DtoMygrade> mygrades = ExtractApplyList.extract("330106200611020040", rule);
			if (mygrades != null && mygrades.size() > 0) {
				for (DtoMygrade mygrade : mygrades) {// .subList(0, 1110)) {
					rule = new Rule(homeurl + mygrade.getLinkHref(), null, null, "tb", Rule.CLASS, Rule.GET);
					// 获取“班级”链接，查询班级信息
					DtoGrade dtoGrade = null;
					DtoGrade grade = ExtractClazzView.extract("330106200611020040", rule);
					if (grade != null) {
						Map<String, Object> whereProperties = new HashMap<String, Object>();
						// 班级的专业（创建/更新)
						whereProperties.put("name", grade.getSpecialtyName());
						List<DtoSpecialty> lSpecialties = specialtyService.retrieveByProperties(whereProperties);
						DtoSpecialty dtoSpecialty = null;
						if (lSpecialties != null && lSpecialties.size() > 0) {
							dtoSpecialty = lSpecialties.get(0);// 更新
						} else {
							dtoSpecialty = new DtoSpecialty();// 创建
							dtoSpecialty.setName(grade.getSpecialtyName());
							DtoResult reSpecialty = specialtyService.create(dtoSpecialty);
							dtoSpecialty = (DtoSpecialty) reSpecialty.getResult();
						}
						grade.setYspecialty(dtoSpecialty);
						grade.setLink(mygrade.getLinkHref());
						// 班级（创建/更新)
						whereProperties.clear();
						whereProperties.put("code", grade.getCode());
						List<DtoGrade> lgGrades = gradeService.retrieveByProperties(whereProperties);
						DtoResult reGrade = null;
						if (lgGrades != null && lgGrades.size() > 0) {
							int gradeid = lgGrades.get(0).getGradeid();
							reGrade = gradeService.createOrUpdate(grade, gradeid);
							// dtoGrade = lgGrades.get(0);
							// 清空原有课表
							scheduleService.deleteByGradeid(gradeid);
						} else {
							// dtoGrade=new DtoGrade();
							reGrade = gradeService.create(grade);

						}
						dtoGrade = (DtoGrade) reGrade.getResult();
						// 班级的课表（创建，后续考虑记录变化历史）
						Set<DtoSchedule> yschedules = grade.getYschedules();
						for (DtoSchedule schedule : yschedules) {
							schedule.setYgrade(dtoGrade);
							scheduleService.create(schedule);
						}
					}
					// 存储mygrade
					mygrade.setYgrade(dtoGrade);
					mygrade.setYuser(dtoUser);
					List<DtoMygrade> lMygrades = mygradeService.retrieveByDto(mygrade);
					DtoResult reMygrade = null;
					if (lMygrades != null & lMygrades.size() > 0) {
						reMygrade = mygradeService.createOrUpdate(mygrade, lMygrades.get(0).getMygradeid());
					} else {
						reMygrade = mygradeService.create(mygrade);
					}
					DtoMygrade dto = (DtoMygrade) reMygrade.getResult();

				}
			}
		}
	}

	public DtoResult login(String name, String pwd) {
		DtoResult re = HttpRequest.sendPost(URL_ROOT_QSNG + "login-front!login.action",
				"username=" + name + "&password=" + pwd + "&memberId=" + "area=1", name);
		System.out.println("_user.login::name=" + name);
		return re;
	}

	public DtoResult login() {
		DtoResult re = HttpRequest.sendPost(URL_ROOT_QSNG + "apply-front.action", null, "anonymous");
		System.out.println("_user.login::name=" + "anonymous");
		return re;
	}

	public DtoResult logout(String name) {
		DtoResult re = HttpRequest.sendPost(URL_ROOT_QSNG + "login-front!logout.action", "", name);

		return re;
	}

	@Test
	public void refreshMyinfo() throws Exception {
		DtoUser user = new DtoUser();
		user.setId("330106200611020040");
		user.setMobile("13857168929");
		DtoResult result = extractQsnService.extractMyinfo(user, "0");
		System.err.println(((DtoUser) result.getResult()).getName());
	}

	@Test
	public void extractMygrade() throws Exception {
		DtoUser user = new DtoUser();
		user.setId("330106200611020040");
		user.setMobile("13857168929");
		user = userService.retrieveById(1);
		List<DtoMygrade> lDtoMygrades = extractQsnService.extractMygrade(user);
		System.err.println(lDtoMygrades.size());
		for (DtoMygrade mygrade : lDtoMygrades) {
			DtoGrade grade = mygrade.getYgrade();// gradeService.retrieveById(mygrade.getYgrade().getGradeid());
			if (grade != null && grade.getGradeid() == 4978)
				if (grade.getUpdatedDate() == null
						|| (new Date().getTime() - 24 * 60 * 60 * 1000 * XkConstant.V_EXTRACT_REFRESH_INTERVAL
								- grade.getUpdatedDate().getTime()) >= 0) {

					// DtoGrade dtoGrade =
					// extractQsnService.extractGrade(dtoUser2, grade);
					System.err.println(" refreshMygradeAndGradeinfo1.user2=" + grade.getCode());
				}
			System.err.println(" refreshMygradeAndGradeinfo2.user2" + mygrade.getYgrade().getGradeid());
		}

	}

	@Test
	public void extractGrade() throws Exception {
		DtoUser user = new DtoUser();
		user.setId("330106200611020040");
		user.setMobile("13857168929");
		user = userService.retrieveById(1);
		List<DtoGrade> lDtoGrades = gradeService.retrieveAll();
		for (DtoGrade dtoGrade : lDtoGrades) {

			extractQsnService.extractGrade(user, dtoGrade);
		}

	}

}
