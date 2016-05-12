package com.hx.xk.service;

import com.hx.xk.dto.base.DtoResult;

public interface IResultService extends IService<DtoResult> {

	public DtoResult retrieveByCode(String code);
}
