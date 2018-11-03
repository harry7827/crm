package com.crm.domain;

import java.util.List;

public class PageBean<T> {
	Integer currPage; //（当前页）
    Integer pageSize; //（每页显示的记录数）  3条
    Integer totalCount; //（总记录数）
    Integer totalPage; //（总页数）
    List<T> list; //（客户列表）
	public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
    
}
