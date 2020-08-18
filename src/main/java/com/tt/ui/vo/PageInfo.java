package com.tt.ui.vo;

import java.util.List;

public class PageInfo<T> {
	private int pageNum;// 当前页数
	private int pageSize;// 每页显示的记录数
	private int totalCount;// 总记录数
	private int totalPage;// 总页数
	private List<T> lists;// 每页的显示的数据

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

}
