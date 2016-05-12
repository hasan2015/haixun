/**
 * 
 */
package com.hx.xk.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.dto.DtoMygrade;
import com.hx.xk.dto.DtoSpecialty;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.dto.mapper.MapperMygrade;
import com.hx.xk.entity.Ymygrade;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IMygradeService;
import com.hx.xk.vo.VoMygrade;
import com.hx.xk.vo.VoMyschedule;
import com.hx.xk.vo.VoYear;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("mygradeService")
public class MyGradeServiceImpl extends AbstractService<Ymygrade, DtoMygrade> implements IMygradeService {
	@Resource
	private IExtractQsnService extractService;

	public MyGradeServiceImpl() {
		setEntityClass(Ymygrade.class);
		setIdFieldName("mygradeid");
		setConvertor(new MapperMygrade());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Ymygrade newEntityInstance() {
		return new Ymygrade();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoMygrade newDtoInstance() {
		return new DtoMygrade();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoMygrade dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getReservedcode() != null) {
			map.put("reservedcode", dto.getReservedcode());
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IMygradeService#retrieveMygrades(com.hx.xk.dto.DtoUser,
	 * com.hx.xk.dto.Pager, boolean)
	 */
	@Override
	@Transactional(value = "transactionManager")
	public Pager retrieveMygrades(DtoUser user, Pager pager, boolean isRefresh) throws Exception {
		DtoResult result = new DtoResult();
		List<DtoMygrade> ldto = new ArrayList<DtoMygrade>();
		if (isRefresh) {
			extractService.extractMygrade(user);
		}
		String fromString = "from Ymygrade m where m.yuser.userid=" + user.getUserid();
		if (pager.getTotal() == 0) {
			pager.setTotal(defaultDao.getTotalBySql("select count(m.mygradeid) " + fromString));
		}
		String sqlString = "select m " + fromString;
		List<Ymygrade> ly = defaultDao.findBySql(Ymygrade.class, sqlString, pager);
		for (Ymygrade y : ly) {
			DtoMygrade dto = convertor.entity2Dto(y);
			ldto.add(dto);
		}
		result.setResult(ldto);
		pager.setResult(result);
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IMygradeService#retrieveByUseridAndReservedcode(java
	 * .lang.Long, java.lang.String)
	 */
	@Override
	public DtoMygrade retrieveByUseridAndReservedcode(Integer userid, String reservedcode) throws Exception {
		DtoMygrade mygrade = null;
		String sqlString = "select y from Ymygrade y where y.yuser.userid=" + userid + " and y.reservedcode='"
				+ reservedcode + "'";
		List<Ymygrade> ly = defaultDao.findBySql(Ymygrade.class, sqlString);
		if (ly != null && ly.size() > 0)
			mygrade = convertor.entity2Dto(ly.get(0));

		return mygrade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IMygradeService#retrieveMygradeByParams(java.lang.
	 * Integer, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String[], java.lang.String, java.lang.String, java.lang.String,
	 * com.hx.xk.dto.base.Pager, java.lang.String, java.lang.Integer)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<VoMygrade> retrieveMygradeByParams(Integer userid, String area, String sYear, String eYear,
			String[] weeks, String starttime, String endtime, String term) throws Exception {
		String sql = "select mg.mygradeid,g.gradeid,g.code,g.area,g.term,g.description,g.applystatus,g.agelimit,g.degree,"
				+ "s.specialtyid,s.name,mg.reservedcode,mg.applystatus,mg.overtime";
		String from = " from Ymygrade mg,Ygrade g,Yspecialty s";
		String where = " where mg.ygrade.gradeid=g.gradeid and g.yspecialty.specialtyid=s.specialtyid";
		if ((weeks != null && weeks.length > 0) || (starttime != null && !starttime.isEmpty())
				|| (endtime != null && !endtime.isEmpty())) {
			from += ",Yschedule d";
			where += " and d.ygrade.gradeid=g.gradeid";
		}
		if (weeks != null && weeks.length > 0) {
			where += " and (";
			for (int i = 0; i < weeks.length; i++) {
				where += " week like '%" + weeks[i] + "%'";
				if (i < weeks.length - 1)
					where += " or";
			}
			where += ")";
		}
		if (starttime != null && !starttime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + starttime
					+ "'),CONCAT('2015-10-10 ',d.starttime))>=0";// 不晚于starttime
		}
		if (endtime != null && !endtime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + endtime
					+ "'),CONCAT('2015-10-10 ',d.endtime))<=0";// 不早于starttime
		}
		if (area != null && !area.isEmpty())
			where += " and g.area like '%" + area + "%'";
		if (term != null && !term.isEmpty())
			where += " and g.term like '%" + term + "%'";

		if (sYear != null && !sYear.isEmpty())
			where += " and g.year >=" + sYear;
		if (eYear != null && !eYear.isEmpty())
			where += " and g.year <=" + eYear;

		// if (applystatus != null)
		// if (applystatus.isEmpty())
		// where += " and g.applystatus is not null";
		// else
		// where += " and g.applystatus like '%" + applystatus + "%'";
		// if (specialtyid != null) {
		// where += " and s.specialtyid=" + specialtyid;
		// }
		where += " order by g.term";
		List<VoMygrade> vos = new ArrayList<VoMygrade>();
		List<Object[]> lobj = defaultDao.findBySql(Object[].class, sql + from + where);
		if (lobj.size() > 0) {
			for (Object[] objects : lobj) {
				VoMygrade vo = new VoMygrade();
				if (objects[0] != null)
					vo.setMygradeid(Integer.parseInt(objects[0].toString()));
				if (objects[1] != null)
					vo.setGradeid(Integer.parseInt(objects[1].toString()));
				if (objects[2] != null)
					vo.setCode(objects[2].toString());
				if (objects[3] != null)
					vo.setArea(objects[3].toString());
				if (objects[4] != null)
					vo.setTerm(objects[4].toString());
				if (objects[5] != null)
					vo.setDescription(objects[5].toString());
				if (objects[6] != null)
					vo.setApplystatus(objects[6].toString());
				if (objects[7] != null)
					vo.setAgelimit(objects[7].toString());
				if (objects[8] != null)
					vo.setDegree(objects[8].toString());
				if (objects[9] != null)
					vo.setSpecialtyid(Integer.parseInt(objects[9].toString()));
				if (objects[10] != null)
					vo.setSpecialtyName(objects[10].toString());

				if (objects[11] != null)
					vo.setReservedcode(objects[11].toString());
				if (objects[12] != null)
					vo.setGradeApplystatus(objects[12].toString());
				if (objects[13] != null)
					vo.setOvertime(objects[13].toString());

				vos.add(vo);
			}
		}
		// pager.setResult(vos);
		return vos;
	}

	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<DtoSpecialty> retrieveSpecialtyOfMygrade(Integer userid, String area, String sYear, String eYear,
			String[] weeks, String starttime, String endtime, String term, Pager pager) throws Exception {
		List<DtoSpecialty> lDtoSpecialties = new ArrayList<DtoSpecialty>();
		String sql = "select s.specialtyid,s.name,count(s.specialtyid)";
		String from = " from Ymygrade mg,Yspecialty s,Ygrade g";
		String where = " where mg.ygrade.gradeid=g.gradeid and g.yspecialty.specialtyid=s.specialtyid"
				+ " and g.applystatus is not null";
		if ((weeks != null && weeks.length > 0) || (starttime != null && !starttime.isEmpty())
				|| (endtime != null && !endtime.isEmpty())) {
			from += ",Yschedule d";
			where += " and d.ygrade.gradeid=g.gradeid";
		}
		if (weeks != null && weeks.length > 0) {
			where += " and (";
			for (int i = 0; i < weeks.length; i++) {
				where += " week like '%" + weeks[i] + "%'";
				if (i < weeks.length - 1)
					where += " or";
			}
			where += ")";
		}
		if (starttime != null && !starttime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + starttime
					+ "'),CONCAT('2015-10-10 ',d.starttime))>=0";// 不晚于starttime
		}
		if (endtime != null && !endtime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + endtime
					+ "'),CONCAT('2015-10-10 ',d.endtime))<=0";// 不早于starttime
		}
		if (area != null && !area.isEmpty())
			where += " and g.area like '%" + area + "%'";
		if (term != null && !term.isEmpty())
			where += " and g.term like '%" + term + "%'";

		sql += from + where + " group by s.specialtyid";
		// log.info(" retrieveSpecialtyOfOpengrade.sql=" + sql);
		List<Object[]> lobj = defaultDao.findBySql(Object[].class, sql, pager);
		// if (lobj.size() > 0) {
		for (Object[] objects : lobj) {
			DtoSpecialty dtoSpecialty = new DtoSpecialty();
			if (objects[0] != null)
				dtoSpecialty.setSpecialtyid(Integer.parseInt(objects[0].toString()));
			if (objects[1] != null)
				dtoSpecialty.setName(objects[1].toString());
			if (objects[2] != null)
				dtoSpecialty.setGradecount(Integer.parseInt(objects[2].toString()));

			lDtoSpecialties.add(dtoSpecialty);
		}
		// }

		return lDtoSpecialties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IMygradeService#retrieveYearsOfMygrade(java.lang.
	 * Integer, java.lang.String, java.lang.String[], java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<VoYear> retrieveYearsOfMygrade(Integer userid, String area, String[] weeks, String starttime,
			String endtime, String term) throws Exception {
		List<VoYear> lyear = new ArrayList<VoYear>();
		String sql = "select mg.mygradeid,g.year,count(g.year)";
		String from = " from Yuser u,Ymygrade mg,Ygrade g";
		String where = " where u.userid=mg.yuser.userid and mg.ygrade.gradeid=g.gradeid";
		if ((weeks != null && weeks.length > 0) || (starttime != null && !starttime.isEmpty())
				|| (endtime != null && !endtime.isEmpty())) {
			from += ",Yschedule d";
			where += " and d.ygrade.gradeid=g.gradeid";
		}
		if (weeks != null && weeks.length > 0) {
			where += " and (";
			for (int i = 0; i < weeks.length; i++) {
				where += " week like '%" + weeks[i] + "%'";
				if (i < weeks.length - 1)
					where += " or";
			}
			where += ")";
		}
		if (starttime != null && !starttime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + starttime
					+ "'),CONCAT('2015-10-10 ',d.starttime))>=0";// 不晚于starttime
		}
		if (endtime != null && !endtime.isEmpty()) {
			where += " and TIMESTAMPDIFF(MINUTE,CONCAT('2015-10-10 ','" + endtime
					+ "'),CONCAT('2015-10-10 ',d.endtime))<=0";// 不早于starttime
		}
		if (area != null && !area.isEmpty())
			where += " and g.area like '%" + area + "%'";
		if (term != null && !term.isEmpty())
			where += " and g.term like '%" + term + "%'";
		if (userid != null && userid > 0)
			where += " and u.userid=" + userid;

		sql += from + where + " group by g.year order by g.year desc,g.term";
		// log.info(" retrieveSpecialtyOfOpengrade.sql=" + sql);
		List<Object[]> lobj = defaultDao.findBySql(Object[].class, sql);
		// if (lobj.size() > 0) {
		for (Object[] objects : lobj) {
			VoYear voYear = new VoYear();
			if (objects[0] != null)
				voYear.setMygradeid(Integer.parseInt(objects[0].toString()));
			if (objects[1] != null)
				voYear.setYear(objects[1].toString());
			if (objects[2] != null)
				voYear.setYearCount(Integer.parseInt(objects[2].toString()));

			lyear.add(voYear);
		}
		// }

		return lyear;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hx.xk.service.IMygradeService#retrieveMyscheduleByDate(java.lang.
	 * Integer, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(value = "transactionManager", readOnly = true)
	public List<VoMyschedule> retrieveMyscheduleByDate(Integer userid, String startdate, String enddate)
			throws Exception {
		List<VoMyschedule> lMyschedules = new ArrayList<VoMyschedule>();
		String sql = "select mg.mygradeid,g.gradeid,sc.scheduleid,sc.week,sc.starttime,sc.endtime,sc.address,sp.specialtyid,sp.name"
				+ " from Ymygrade mg,Ygrade g,Yschedule sc,Yspecialty sp" + " where mg.ygrade.gradeid=g.gradeid"
				+ " and sc.ygrade.gradeid=g.gradeid" + " and g.yspecialty.specialtyid=sp.specialtyid "
				+ " and mg.yuser=" + userid;
		if (startdate != null && !startdate.isEmpty()) {
			sql += " and TIMESTAMPDIFF(DAY,'" + startdate + "', g.startdate)<=0";// 不晚于starttime
		}
		if (enddate != null && !enddate.isEmpty()) {
			sql += " and TIMESTAMPDIFF(DAY,'" + enddate + "', g.enddate)>=0";// 不早于enddate
		}
		List<Object[]> lobj = defaultDao.findBySql(Object[].class, sql);
		// if (lobj.size() > 0) {
		for (Object[] objects : lobj) {
			VoMyschedule voMyschedule = new VoMyschedule();
			if (objects[0] != null)
				voMyschedule.setMygradeid(Integer.parseInt(objects[0].toString()));
//			if (objects[1] != null)
				voMyschedule.setUserid(userid);
			if (objects[1] != null)
				voMyschedule.setGradeid(Integer.parseInt(objects[1].toString()));
			if (objects[2] != null)
				voMyschedule.setScheduleid(Integer.parseInt(objects[2].toString()));
			if (objects[3] != null)
				voMyschedule.setWeek(objects[3].toString());
			if (objects[4] != null)
				voMyschedule.setStarttime(objects[4].toString());
			if (objects[5] != null)
				voMyschedule.setEndtime(objects[5].toString());
			if (objects[6] != null)
				voMyschedule.setAddress(objects[6].toString());
			if (objects[7] != null)
				voMyschedule.setSpecialtyid(Integer.parseInt(objects[7].toString()));
			if (objects[8] != null)
				voMyschedule.setSpecialtyName(objects[8].toString());

			lMyschedules.add(voMyschedule);
		}
		return lMyschedules;
	}

}
