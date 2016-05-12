/**
 * 
 */
package com.hx.xk.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.DtoUser;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.mapper.MapperUser;
import com.hx.xk.entity.Yuser;
import com.hx.xk.entity.Ywxaccount;
import com.hx.xk.service.IExtractQsnService;
import com.hx.xk.service.IGradeService;
import com.hx.xk.service.IUserService;

/**
 * @author Hasan
 * @Date 2015-3-20 上午9:23:43
 * 
 */
@Service("userService")
public class UserServiceImpl extends AbstractService<Yuser, DtoUser> implements IUserService {
	@Resource
	private IGradeService gradeService;
	@Resource
	private IExtractQsnService extractQsnService;

	public UserServiceImpl() {
		setEntityClass(Yuser.class);
		setIdFieldName("userid");
		setConvertor(new MapperUser());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newEntityInstance()
	 */
	@Override
	protected Yuser newEntityInstance() {
		return new Yuser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dd.dds.service.impl.AbstractService#newDtoInstance()
	 */
	@Override
	protected DtoUser newDtoInstance() {
		return new DtoUser();
	}

	@Override
	protected Map<String, Object> dto2Map(DtoUser dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (dto.getMobile() != null) {
			map.put("mobile", dto.getMobile());
		}
		if (dto.getId() != null) {
			map.put("id", dto.getId());
		}

		return map;
	}

	@Override
	@Transactional
	public DtoResult bind(String id, String mobile, Integer wxaccountid) throws Exception {
		DtoResult result = new DtoResult();
		DtoUser dtoUser =new DtoUser();; 
		// 判断用户(id)是否存在密码是否正确
		String sql = "select y from Yuser y where id ='" + id + "'";// + " and
																	// mobile =
																	// '" +
																	// mobile +
																	// "'";
		List<Yuser> lyusers = defaultDao.findBySql(Yuser.class, sql);
		if (lyusers == null || lyusers.size() == 0) {
			// id(身份证）不存在，则根据id+mobile登录学校测试一下，如果ok，则存储并绑定成功
			//.login(id, mobile);因为是否登录成功，都会有正常的返回，所以只能通过获取个人信息来判断是否是有效帐号
			dtoUser.setId(id);
			dtoUser.setMobile(mobile);
			result = extractQsnService.extractMyinfo(dtoUser,"0");
			if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
				//result = extractQsnService.extractMyinfo(dtoUser, "0");
//				if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
					result = create((DtoUser) result.getResult());
					dtoUser=(DtoUser)result.getResult();
//				} else {
//					result.setCode(XkConstant.RESULT_CODE_FAILURE);
//					result.setResult("绑定失败：用户不存在或密码错误（-3）！");
//					return result;
//				}
			} else {
				result.setCode(XkConstant.RESULT_CODE_FAILURE);
				result.setResult("绑定失败：用户不存在或密码错误！");
				return result;
			}
		} else if (!lyusers.get(0).getMobile().equals(mobile)) {
			// id存在，但是mobile不一样
			result.setCode(XkConstant.RESULT_CODE_FAILURE);
			result.setResult("绑定失败：用户不存在或密码错误！");
			return result;
		}else{ 
			dtoUser=convertor.entity2Dto(lyusers.get(0));
		}
//		if (result.getCode() == XkConstant.RESULT_CODE_SUCCESS) {
			Ywxaccount ywxaccount = defaultDao.find(Ywxaccount.class, wxaccountid);
			if (ywxaccount == null) {
				result.setCode(XkConstant.RESULT_CODE_FAILURE);
				result.setResult("绑定失败：登录失效！");
				return result;
			}
			// id存在，绑定原有学生帐号
			
			ywxaccount.setYuser(convertor.dto2entity(dtoUser));
			defaultDao.update(ywxaccount);
			result.setResult(dtoUser);
//		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hx.xk.service.IUserService#updateLatestDate(java.sql.Timestamp)
	 */
	@Override
	@Transactional
	public DtoResult updateLatestDate(Integer userid, Timestamp date) throws Exception {
		DtoResult result = new DtoResult();
		// createOrUpdate(user, userid);
		Yuser yuser = defaultDao.find(Yuser.class, userid);
		if (yuser == null) {
			result.setCode(XkConstant.RESULT_CODE_FAILURE);
			return result;
		}
		DtoUser dtoUser = convertor.entity2Dto(yuser);
		// // 如果最后访问时间不是当天，则刷新个人信息;
		// if (yuser.getLatestdate() != null && yuser.getLatestdate().getDay()
		// != new Date().getDay()) {
		// result = extractQsnService.extractMyinfo(dtoUser, "0");// 暂时areaid=0
		// if (result.getCode().equals(XkConstant.RESULT_CODE_SUCCESS)) {
		// dtoUser = (DtoUser) result.getResult();
		// }
		// //刷新已报班级列表
		// List<DtoMygrade> lDtoMygrades =
		// extractQsnService.extractMygrade(dtoUser);
		// //刷新当天未被刷新过的班级（因查询条件会包含全部已报班级，所以只好提前全部更新）
		// for(DtoMygrade mygrade:lDtoMygrades){
		// DtoGrade
		// grade=gradeService.retrieveById(mygrade.getYgrade().getGradeid());
		// if(grade!=null&&(grade.getUpdatedDate()!=null&&grade.getUpdatedDate().getDay()!=new
		// Date().getDay())){
		//
		// DtoGrade dtoGrade= extractQsnService.extractGrade(dtoUser, grade);
		// }
		// }
		//
		// }
		//
		// yuser.setLatestdate(date);
		// defaultDao.update(yuser);
		dtoUser.setLatestdate(date);
		result = createOrUpdate(dtoUser, yuser.getUserid());
		return result;
	}
}
