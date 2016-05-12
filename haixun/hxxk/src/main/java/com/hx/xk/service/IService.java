package com.hx.xk.service;

import java.util.List;
import java.util.Map;

import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;

public interface IService<DtoType> {
	/**
	 * 只要数据库主键不重复，那么就会重新创建一条记录
	 * 
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public DtoResult create(DtoType item) throws Exception;

	// 暂停使用
	// public DtoResult update(DtoType item) throws Exception;

	// public DtoResult updateProperties(DtoType item) throws Exception;
	/**
	 * 保证设定为“id”（不一定是数据库的主键）的字段值唯一，判断是否创建还是修改
	 * 
	 * @param item
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DtoResult createOrUpdate(DtoType item, Object id) throws Exception;

	public DtoResult delete(Object id) throws Exception;

	public DtoType retrieveById(Object id) throws Exception;

	public List<DtoType> retrieveAll() throws Exception;

	public List<DtoType> retrieveList(int pageNow, int pageSize) throws Exception;

	public List<DtoType> retrieveByProperties(Map<String, Object> whereProperties) throws Exception;

	public List<DtoType> retrieveByProperties(Map<String, Object> whereProperties, int pageNow, int pageSize)
			throws Exception;

	public List<DtoType> retrieveByDto(DtoType dto) throws Exception;

	public List<DtoType> retrieveByDto(DtoType dto, int pageNow, int pageSize) throws Exception;

	public Pager retrieveList(Pager pager) throws Exception;

	public Pager retrieveNoDeleteList(Pager pager) throws Exception;

	public Pager retrieveByProperties(Map<String, Object> whereProperties, Pager pager) throws Exception;

	public Pager retrieveByDto(DtoType dto, Pager pager) throws Exception;

	public boolean retrieveByName(Long id, String name) throws Exception;
}