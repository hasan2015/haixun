/**
 * 
 */
package com.hx.xk.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.hx.xk.extra.qsn.ExtractRegisterfront;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IGradeService;
import com.hx.xk.service.IMygradeService;
import com.hx.xk.service.IScheduleService;
import com.hx.xk.service.ISpecialtyService;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("extractQsnService")
public class ExtractQsnServiceImpl implements IExtractQsnService {
	private Log log = LogFactory.getLog(ExtractQsnServiceImpl.class);
	private static final String URL_ROOT_QSNG = "http://hzcs.qsng.cn/hz-bsp/hz/";
	private static final String URL_GRADELIST = "apply-front.action";
	private static final String URL_MYINFO = "register-front!edit.action";

	@Resource
	private IMygradeService mygradeService;
	@Resource
	private IGradeService gradeService;
	@Resource
	private IScheduleService scheduleService;
	@Resource
	private ISpecialtyService specialtyService;

	@Override
	public DtoResult login(String name, String pwd) {
		DtoResult result = new DtoResult();
		//result.setCode(XkConstant.RESULT_CODE_USER_NOT_LOGIN);
		if (HttpRequest.durationMap.get(name) == null
				|| System.currentTimeMillis() - HttpRequest.durationMap.get(name) > 180000) {
			result = HttpRequest.sendPost(URL_ROOT_QSNG + "login-front!login.action",
					"username=" + name + "&password=" + pwd + "&memberId=" + "area=0", name);
			System.out.println("_user.login::name=" + name);
		}

		return result;
	}

	@Override
	public DtoResult logout(String name) {
		DtoResult re = HttpRequest.sendPost(URL_ROOT_QSNG + "login-front!logout.action", "", name);

		return re;
	}

	/**
	 * 已报班级
	 * 
	 * @param user
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public List<DtoMygrade> extractMygrade(DtoUser user) throws Exception {
		List<DtoMygrade> mygrades = new ArrayList<DtoMygrade>();
		List<DtoMygrade> remygrades = new ArrayList<DtoMygrade>();
		DtoResult result = login(user.getId(), user.getMobile());
		// 1、获取stuid
		Rule rule = new Rule(URL_ROOT_QSNG + URL_GRADELIST, null, null, "login", Rule.CLASS, Rule.GET);
		String[] hrefs = ExtractApplyfront.extract(user.getId(), rule);
		if (hrefs == null || hrefs.length < 2) {
			result.setCode(XkConstant.RESULT_CODE_FAILURE);
			return mygrades;
		}
		log.info(" href[0]=" + hrefs[0] + " href[1]=" + hrefs[1]);
		if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
			rule = new Rule(URL_ROOT_QSNG + hrefs[0], null, null, "tb", Rule.CLASS, Rule.GET);
			//
			mygrades = ExtractApplyList.extract(user.getId(), rule);
			if (mygrades != null && mygrades.size() > 0) {
				for (DtoMygrade mygrade : mygrades) {
					String link = mygrade.getLinkHref();
					String code = mygrade.getLinkText();
					// 根据 预约号 查询 我的 班级
					DtoMygrade dtoMygrade = mygradeService.retrieveByUseridAndReservedcode(user.getUserid(),
							mygrade.getReservedcode());
					// System.err.println(mygrade.getReservedcode());
					// DtoResult reMygrade = null;
					DtoGrade dtoGrade = null;
					if (dtoMygrade != null) {
						// 取得原班级
						dtoGrade = dtoMygrade.getYgrade();
						// dtoMygrade.setYgrade((DtoGrade)(result.getResult()));
						// dtoMygrade.setYuser(user);
						mygrade = dtoMygrade;
						// System.err.println("重复" + dtoGrade.getCode() + " rc="
						// + mygrade.getReservedcode());
					}
					// 判断code是否一致，不一致，判断是否存在，如果不存在那么增加，mygrade关联新的grade
					if (dtoGrade == null || !(dtoGrade.getCode().equals(code) && dtoGrade.getLink().contains(link))) {
						dtoGrade = gradeService.retrieveByLinkAndCode(link, code);

						if (dtoGrade == null) {
							dtoGrade = new DtoGrade();
							// 更新班级链接和代码（名称）
							dtoGrade.setLink(link);
							dtoGrade.setCode(code);
							result = gradeService.create(dtoGrade);
							dtoGrade = (DtoGrade) (result.getResult());
							// System.err.println("create::"+dtoGrade.getCode());
						}
						mygrade.setYgrade(dtoGrade);
					}
					// 仅仅部分信息，不计为已刷新
					// dtoGrade.setUpdatedDate(new
					// Timestamp(System.currentTimeMillis()));
					// dtoGrade = gradeService.retrieveByLinkAndCode(link,
					// code);//.retrieveByDto(dtoGrade);

					// result = gradeService.createOrUpdate(dtoGrade,
					// dtoGrade.getGradeid());
					// 更新mygrade基本信息
					mygrade.setReservedcode(mygrade.getReservedcode());
					mygrade.setApplystatus(mygrade.getApplystatus());
					mygrade.setOvertime(mygrade.getOvertime());
					//
					mygrade.setYuser(user);
					mygrade.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
					// 保存我的班级
					result = mygradeService.createOrUpdate(mygrade, mygrade.getMygradeid());
					mygrade = (DtoMygrade) result.getResult();
					remygrades.add(mygrade);
				}
			}
		}
		return remygrades;
	}

	// 班级信息
	@Override
	@Transactional(value = "transactionManager")
	public DtoGrade extractGrade(DtoUser user, DtoGrade grade) throws Exception {
		// List<DtoMygrade> mygrades = new ArrayList<DtoMygrade>();
		DtoResult result = login(user.getId(), user.getMobile());
		DtoGrade reGrade = null;
		if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
			Rule rule = new Rule(URL_ROOT_QSNG + grade.getLink(), null, null, "tb", Rule.CLASS, Rule.GET);
			// 班级
			reGrade = ExtractClazzView.extract(user.getId(), rule);
			if (reGrade != null) {// && grades.size() > 0) {
				// reGrade = grades.get(0);
				Set<DtoSchedule> yschedules = reGrade.getYschedules();
				// 存储 专业
				DtoSpecialty dtoSpecialty = new DtoSpecialty();
				dtoSpecialty.setName(reGrade.getSpecialtyName());
				List<DtoSpecialty> lSpecialties = specialtyService.retrieveByDto(dtoSpecialty);
				if (lSpecialties != null && lSpecialties.size() > 0) {
					dtoSpecialty.setSpecialtyid(lSpecialties.get(0).getSpecialtyid());
				}
				// 后续设置专业的其他属性
				result = specialtyService.createOrUpdate(dtoSpecialty, dtoSpecialty.getSpecialtyid());
				dtoSpecialty = (DtoSpecialty) result.getResult();
				// 存储 班级
				List<DtoGrade> lgGrades = gradeService.retrieveByDto(grade);// 因dto2map仅关系link和code所以同retrievebylinkandcode
				if (lgGrades != null && lgGrades.size() > 0) {
					reGrade.setGradeid(lgGrades.get(0).getGradeid());
				}
				reGrade.setYspecialty(dtoSpecialty);
				reGrade.setLink(grade.getLink());
				// 班级最后更新时间
				reGrade.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
				result = gradeService.createOrUpdate(reGrade, reGrade.getGradeid());
				reGrade = (DtoGrade) result.getResult();
				// 存储 课表
				for (DtoSchedule schedule : yschedules) {
					schedule.setYgrade(reGrade);
					List<DtoSchedule> lSchedules = scheduleService.retrieveByAllProps(schedule);
					if (lSchedules == null || lSchedules.size() == 0) {
						schedule.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
						scheduleService.create(schedule);
					}
					//TODO 需要记录课表更新时间
					
				}
			}
		}
		return reGrade;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IExtractQsnService#refreshOpenGrade()
	 */
	@Override
	@Transactional(value = "transactionManager")
	public DtoResult extractOpenGrade() throws Exception {
		DtoResult result = new DtoResult();
		// 匿名访问，获取session
		login();
		// 未登录，访问“可报班级”页面;抓取“已报班级”列表
		for (int i = 0; i < 4; i++) {
			// 获取分页数据
			Rule rule = new Rule(URL_ROOT_QSNG + URL_GRADELIST + "?area=" + i, null, null, "pages", Rule.CLASS,
					Rule.GET);
			Pager pager = ExtractApplyList.extractPager(rule);
			for (int j = 1; j < pager.getPageSize(); j++) {
				// 获取“班级”链接，查询班级信息
				rule = new Rule(URL_ROOT_QSNG + URL_GRADELIST + "?area=" + i + "&page.currentPage=" + j, null, null,
						"tb", Rule.CLASS, Rule.GET);
				List<DtoGrade> opengrades = ExtractApplyList.extractOpenGrades(rule);
				if (opengrades != null && opengrades.size() > 0) {
					for (DtoGrade opengrade : opengrades) {
						// 按每个班级的链接 获取班级详细信息
						rule = new Rule(URL_ROOT_QSNG + opengrade.getLinkHref(), null, null, "tb", Rule.CLASS,
								Rule.GET);
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
							// 班级最后更新时间
							grade.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
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
		return result;
	}

	@Override
	@Transactional(value = "transactionManager")
	public DtoResult extractMyinfo(DtoUser user, String areaid) throws Exception {
		DtoResult result = new DtoResult();
		String id=user.getId();
		String mobile=user.getMobile();
		// 匿名访问，获取session
		login(id, mobile);
		// 1、获取stuid
		Rule rule = new Rule(URL_ROOT_QSNG + URL_GRADELIST, null, null, "login", Rule.CLASS, Rule.GET);
		String[] hrefs = ExtractApplyfront.extract(user.getId(), rule);
		if (hrefs == null || hrefs.length < 2||hrefs[1]==null) {
			result.setCode(XkConstant.RESULT_CODE_FAILURE);
			return result;
		}
		log.info(" href[0]=" + hrefs[0] + " href[1]=" + hrefs[1]);
		// 2、获取个人信息
		rule = new Rule(URL_ROOT_QSNG + hrefs[1], null, null, "studentForm", Rule.CLASS, Rule.GET);
		user = ExtractRegisterfront.extract(user.getId(), rule);
		user.setId(id);
		user.setMobile(mobile);
		result.setResult(user);

		return result;
	}

	@Override
	public DtoResult login() {
		DtoResult re = HttpRequest.sendPost(URL_ROOT_QSNG + "apply-front.action", null,
				XkConstant.V_EXTRACT_USERNAME_ANONYMOUS);
		log.info("_user.login::name=" + XkConstant.V_EXTRACT_USERNAME_ANONYMOUS);
		return re;
	}
}
