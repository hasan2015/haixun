/**
 * 
 */
package com.hx.xk.dto.base;

import java.io.Serializable;

import com.hx.xk.common.XkConstant;

/**
 * @author Hasan
 * @Date 2014-9-11 下午3:32:32
 * 
 */
public class Pager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6648596039888129493L;
	private Integer pageNow = XkConstant.PAGENOW_DEF;// 当前页号
	private Integer pageSize = XkConstant.PAGESIZE_DEF;// 每页记录数
	private Long total = 0L;// 总记录数
	private Object result;// 返回结果
	private String code = XkConstant.RESULT_CODE_SUCCESS;

	/**
	 * @return the pageNow
	 */
	public Integer getPageNow() {
		return pageNow;
	}

	/**
	 * @param pageNow
	 *            the pageNow to set
	 */
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
