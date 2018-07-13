package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_book 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_book{
	private Integer book_id;
	private String book_name;
	private String book_name_pinyin;
	private Integer book_category_id;
	private String book_author;
	private float book_price;
	private Integer book_image;
	private String publishing;
	private String book_desc;
	private Integer book_state;
	private String deploy_datetime;
	private Integer sales_volume;
	public T_book(){
	}
	public T_book(Integer book_id,String book_name,String book_name_pinyin,Integer book_category_id,String book_author,float book_price,Integer book_image,String publishing,String book_desc,Integer book_state,String deploy_datetime,Integer sales_volume){
		this.book_id=book_id;
		this.book_name=book_name;
		this.book_name_pinyin=book_name_pinyin;
		this.book_category_id=book_category_id;
		this.book_author=book_author;
		this.book_price=book_price;
		this.book_image=book_image;
		this.publishing=publishing;
		this.book_desc=book_desc;
		this.book_state=book_state;
		this.deploy_datetime=deploy_datetime;
		this.sales_volume=sales_volume;
	}
	public void setBook_id(Integer book_id){
		this.book_id=book_id;
	}
	public Integer getBook_id(){
		return book_id;
	}
	public void setBook_name(String book_name){
		this.book_name=book_name;
	}
	public String getBook_name(){
		return book_name;
	}
	public void setBook_name_pinyin(String book_name_pinyin){
		this.book_name_pinyin=book_name_pinyin;
	}
	public String getBook_name_pinyin(){
		return book_name_pinyin;
	}
	public void setBook_category_id(Integer book_category_id){
		this.book_category_id=book_category_id;
	}
	public Integer getBook_category_id(){
		return book_category_id;
	}
	public void setBook_author(String book_author){
		this.book_author=book_author;
	}
	public String getBook_author(){
		return book_author;
	}
	public void setBook_price(float book_price){
		this.book_price=book_price;
	}
	public float getBook_price(){
		return book_price;
	}
	public void setBook_image(Integer book_image){
		this.book_image=book_image;
	}
	public Integer getBook_image(){
		return book_image;
	}
	public void setPublishing(String publishing){
		this.publishing=publishing;
	}
	public String getPublishing(){
		return publishing;
	}
	public void setBook_desc(String book_desc){
		this.book_desc=book_desc;
	}
	public String getBook_desc(){
		return book_desc;
	}
	public void setBook_state(Integer book_state){
		this.book_state=book_state;
	}
	public Integer getBook_state(){
		return book_state;
	}
	public void setDeploy_datetime(String deploy_datetime){
		this.deploy_datetime=deploy_datetime;
	}
	public String getDeploy_datetime(){
		return deploy_datetime;
	}
	public void setSales_volume(Integer sales_volume){
		this.sales_volume=sales_volume;
	}
	public Integer getSales_volume(){
		return sales_volume;
	}
	public String toString() { 
		return "T_book[book_id=" + book_id + ",book_name=" + book_name + ",book_name_pinyin=" + book_name_pinyin + ","+
		"book_category_id=" + book_category_id + ",book_author=" + book_author + ",book_price=" + book_price + ","+
		"book_image=" + book_image + ",publishing=" + publishing + ",book_desc=" + book_desc + ","+
		"book_state=" + book_state + ",deploy_datetime=" + deploy_datetime + ",sales_volume=" + sales_volume + "]";
	}
}

