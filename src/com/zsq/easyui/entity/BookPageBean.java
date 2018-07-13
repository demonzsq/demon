package com.zsq.easyui.entity;

public class BookPageBean {

	
	private int book_id;
	private String book_name;
	private String book_price;
	private String file_name;
	public BookPageBean() {
		// TODO Auto-generated constructor stub
	}
	public BookPageBean(String book_name, String book_price, String file_name) {
		super();
		this.book_name = book_name;
		this.book_price = book_price;
		this.file_name = file_name;
	}
	public BookPageBean(int book_id, String book_name, String book_price, String file_name) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_price = book_price;
		this.file_name = file_name;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	@Override
	public String toString() {
		return "BookPageBean [book_id=" + book_id + ", book_name=" + book_name + ", book_price=" + book_price
				+ ", file_name=" + file_name + "]";
	}
	
	
	
}
