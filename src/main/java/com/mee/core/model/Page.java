package com.mee.core.model;

import java.util.List;

/**
 * @author funnyzpc
 * 数据分页显示中，当前页的信息,默认每页20条
 */
public class Page<T> {
	/**
	 * 查询参数
	 *
	 */
	private Object params;
	/**
	 * 总的条数
	 *
	 */
	private int total;
	/**
	 * 当前请求页
	 *
	 */
	private int index;
	/**
	 * 每页条数
	 *
	 */
	private int size = 10;

    /**
	 * 当前页数据
	 *
	 */
	private List<T> data;

	/**
	 * 查询参数
	 * @return
	 */
	public Object getParams() {
		return params;
	}
	/**
	 * 查询参数
	 * @param params
	 */
	public void setParams(Object params) {
		this.params = params;
	}
	/**
	 * 总的记录数
	 * @return
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * 设置总的记录数
	 * @param total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 *  获取页数
	 * @return
	 */
	public int getPageCount(){
		return (int)Math.ceil(total * 1.0 / size);
	}

	/**
	 * 每页记录数
	 * @return
	 */
	public int getSize() {
		return size;
	}
	/**
	 * 每页记录数
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * 数据列表
	 * @return
	 */
	public List<T> getData() {
		return data;
	}
	/**
	 * 数据列表
	 * @param data
	 */
	public Page setData(List<T> data) {
		this.data = data;
		return this;
	}
	
	/** 获取总记录数 **/
	public int getCount(){
		return null == data ? 0 : data.size();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Page() {
		super();
	}
	
	public Page(int index) {
		super();
		this.index = index;
	}
	public Page(Object params, int index, int size) {
		super();
		this.params = params;
		this.index = index;
		this.size = size;
	}
}
