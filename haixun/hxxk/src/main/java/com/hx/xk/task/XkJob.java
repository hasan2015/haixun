package com.hx.xk.task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkPropConfigurer;
import com.hx.xk.dto.DtoGrade;
import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IGradeService;
import com.hx.xk.service.IInstitutionService;
import com.hx.xk.service.IUserService;

public class XkJob {
	@Resource
	private IGradeService gradeService;
	@Resource
	private IUserService userService;
	@Resource
	private IExtractQsnService extractQsnService;
	@Resource
	private IInstitutionService institutionService;

	private Log log = LogFactory.getLog(XkJob.class);

	/**
	 * 
	 * 
	 */
	public void refeshOpenGrade() {
		try {
			extractQsnService.extractOpenGrade();
			// 存储最后更新时间
			institutionService.refreshdateQSN(new Timestamp(System.currentTimeMillis()));
			log.info(" refeshOpenGrade.ok!");
		} catch (Exception e) {
			log.error(" refeshOpenGrade::" + e);
		}
	}

	public void refreshMygradeAndGradeinfo() {
		refreshMygradeAndGradeinfo(null);
	}

	/**
	 * 刷新当天未被刷新过的班级（因查询条件会包含全部已报班级，所以只好提前全部更新）
	 * 
	 * @param dtoUser
	 */
	public void refreshMygradeAndGradeinfo(DtoUser dtoUser) {
		try {
			List<DtoUser> lDtoUsers = new ArrayList<>();
			if (dtoUser != null)
				lDtoUsers.add(dtoUser);
			else
				lDtoUsers = userService.retrieveAll();

			for (DtoUser dtoUser2 : lDtoUsers) {
				// 刷新已报班级列表
				List<DtoMygrade> lDtoMygrades = extractQsnService.extractMygrade(dtoUser2);
				// 刷新当天未被刷新过的班级（因查询条件会包含全部已报班级，所以只好提前全部更新）
				for (DtoMygrade mygrade : lDtoMygrades) {
					DtoGrade grade = mygrade.getYgrade();// gradeService.retrieveById(mygrade.getYgrade().getGradeid());
					if (grade != null)
						if (grade.getUpdatedDate() == null
								|| (new Date().getTime()
										- 60 * 60 * 1000
												* XkPropConfigurer.getIntvalueByProp("interval.autoextract")
										- grade.getUpdatedDate().getTime()) >= 0) {

							DtoGrade dtoGrade = extractQsnService.extractGrade(dtoUser2, grade);
							log.info(" refreshMygradeAndGradeinfo1.user2=" + grade.getCode());
						}
					log.info(" refreshMygradeAndGradeinfo2.user2" + mygrade.getYgrade().getGradeid());
				}
				Thread.sleep(100);
				log.info(" refreshMygradeAndGradeinfo3.user2=" + dtoUser2.getName());
			}

			// 存储最后更新时间
			institutionService.refreshdateQSN(new Timestamp(System.currentTimeMillis()));
			log.info(" refreshMygradeAndGradeinfo4.ok!");
		} catch (Exception e) {
			log.error(" refreshMygradeAndGradeinfo::" + e);
		}
	} 
}
