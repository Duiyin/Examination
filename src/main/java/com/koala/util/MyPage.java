package com.koala.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPage<T> implements Serializable {
	private static final long serialVersionUID = -7538513433895399481L;
	public final static int PAGESIZE = 20;
	private int pagesize = PAGESIZE;// 每页数量
	private List<T> items;// 对象集合
	private int totalcount;// 对象总数
	private int startindex = 0;// 起始查询记录
	private int pagecount;// 总页数
	private int page;// 当前页
	private List<Integer> pages = new ArrayList<Integer>();// 相近的5个页码
	private int pre;// 上一页码
	private int next;// 下一页码
	private boolean paging;// 数据是否超过1页

	public boolean isPaging() {
		if (this.pages.size() > 1)
			paging = true;
		else
			paging = false;
		return paging;
	}
	
	public MyPage() {
		this.totalcount = 0;
		this.pagesize = 1;
		this.page = 1;
	}

	public MyPage(int totalcount, int pagesize, int page) {
		this.items = new ArrayList<T>(0);
		this.totalcount = totalcount;
		if (this.totalcount < 0)
			this.totalcount = 0;
		this.pagesize = pagesize;
		if (this.pagesize < 1)
			this.pagesize = 1;
		this.page = page;
		if (this.page < 1)
			this.page = 1;
		this.startindex = (page - 1) * pagesize;

		this.pagecount = (int) Math.ceil(Arith.div(this.totalcount, this.pagesize));

		this.next = this.page + 1;
		if (this.next > this.pagecount)
			this.next = this.pagecount;

		this.pre = this.page - 1;
		if (this.pre < 1)
			this.pre = 1;

		// 显示5个相邻的页码
		int first = 1;
		int end = this.page + 2;
		if (this.page - 2 > 0) {
			first = this.page - 2;
		}
		if (end > this.pagecount) {
			end = this.pagecount;
		}
		if ((end - first) < 4 && (first + 4) <= this.pagecount) {// 后不足五个页码的补齐
			end = first + 4;
		}
		if ((end - first) < 4 && (end - 4) >= 1) {// 前不足五个页码的补齐
			first = end - 4;
		}

		int fornum = end - first + 1;
		this.pages = new ArrayList<Integer>();
		for (int i = 0; i < fornum; i++) {
			this.pages.add(first);// 存入页码
			first++;
		}
	}

	public List<List<T>> split(int num) {
		Map<Integer, List<T>> map = new HashMap<Integer, List<T>>();
		if (num < 1) {
			num = 1;
		}

		int column = (int) Math.ceil(Arith.div(totalcount, num));// 总数与列数相除，得每列数量
		if (column <= num) {
			num = column;// 不产生多余的集合
		}

		if (null != items && items.size() > 0) {
			for (int i = 1; i <= num; i++) {
				List<T> sublist = new ArrayList<T>();
				map.put(i, sublist);
			}

			int i = 1;
			for (T object : items) {
				List<T> temp = map.get(i);
				temp.add(object);
				if (i >= num) {
					i = 1;
				} else {
					i++;
				}
			}
		}

		List<List<T>> list = new ArrayList<List<T>>(map.values());
		return list;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

}
