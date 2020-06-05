package com.ecg.system.pojo;

import java.io.Serializable;
import java.util.List;

//分页的封装类

public class PageResult implements Serializable{
	
	
	private long total;//总记录数
	private List rows;//查询出来的区间集合
	
	
	public PageResult() {
	}
	
	public PageResult(long total, List rows) {
		// TODO Auto-generated constructor stub
		super();
		this.total = total;
		this.rows = rows;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	
	
	
	

}
