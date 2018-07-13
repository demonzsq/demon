package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_book_category 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_book_category{
	private Integer book_category_id;
	private String book_category_name;
	public T_book_category(){
	}
	public T_book_category(Integer book_category_id,String book_category_name){
		this.book_category_id=book_category_id;
		this.book_category_name=book_category_name;
	}
	public void setBook_category_id(Integer book_category_id){
		this.book_category_id=book_category_id;
	}
	public Integer getBook_category_id(){
		return book_category_id;
	}
	public void setBook_category_name(String book_category_name){
		this.book_category_name=book_category_name;
	}
	public String getBook_category_name(){
		return book_category_name;
	}
	public String toString() { 
		return "T_book_category[book_category_id=" + book_category_id + ",book_category_name=" + book_category_name + "]";
	}
}

