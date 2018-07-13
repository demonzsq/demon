package com.zsq.easyui.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * ��ҳ������
 * @author Administrator
 *
 */
public class PageBean {

	private int page = 1;  //��ǰҳ            				 ��ͼ�㴫�ݹ���
	private int rows = 4;	//ÿҳ��ʾ���ټ�¼    		 ��ͼ�㴫�ݹ���
	private int total = 0;	//�ܼ�¼��
	private boolean pagination=true; //�Ƿ��ҳ		��ͼ�㴫�ݹ���
	
	
	private String url;    //�����ַ
	private Map<String, String[]> map;
	
	
	
	public PageBean() {
		super();
	}


	/**
	 * ��ʼ��pageBean,��ʵ����pageBean������������ø÷���
	 * @return
	 */
	public void initRequest(HttpServletRequest request) {
		this.setpage(request.getParameter("page"));
		this.setRows(request.getParameter("rows"));
		this.setPagination(request.getParameter("pagination"));
		this.url=request.getContextPath()+request.getServletPath();
		this.map=request.getParameterMap();
	}
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String[]> getMap() {
		return map;
	}
	public void setMap(Map<String, String[]> map) {
		this.map = map;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setpage(String page) {
		if(!"".equals(page) && page!=null) {
			this.page=Integer.parseInt(page);
		}
	}
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setRows(String rows) {
		if(!"".equals(rows) && rows!=null) {
			this.rows=Integer.parseInt(rows);
		}
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setTotal(String total) {
		this.total = Integer.parseInt(total);
	}
	public boolean isPagination() {
		return pagination;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public void setPagination(String pagination) {
		if("false".equals(pagination) && pagination !=null) {
			this.pagination=false;
		}
	}

	
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rows=" + rows + ", total=" + total + ", pagination=" + pagination + "]";
	}
	
	/**
	 * �����ʼ��¼���±�
	 */
	public int getStartIndex() {
		return (this.page - 1)* this.rows;
	}
	
	/**
	 * ĩҳ����ҳ���� = �ܼ�¼�� % ÿҳ��ʾ��¼�� ==0 ? �ܼ�¼��/ ÿҳ��ʾ��¼��: �ܼ�¼�� / ÿҳ��ʾ��¼��+1
	 */
	public int getTotalPage() {
		int totalPage = this.total / this.rows;
		if(this.total % this.rows != 0) {
			totalPage++;
		}
		return totalPage;
	}
	
	/**
	 * ��һҳ
	 */
	public int getPreviousPage() {
		int previousPage = this.page - 1;
		if(previousPage < 1) {
			previousPage = 1;
		}
		return previousPage;
	}
	
	/**
	 * ��һҳ
	 */
	public int getNextPage() {
		int nextPage  = this.page +1;
		if(nextPage > this.getTotalPage()) {
			nextPage = this.getTotalPage();
		}
		return nextPage;
	}

}
