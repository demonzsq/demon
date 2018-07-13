package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_shopping_cart 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_shopping_cart{
	private Integer shopping_cart_id;
	private Integer user_id;
	private Integer book_id;
	private String book_name;
	private float book_price;
	private Integer book_number;
	private String cart_data;
	private float subtotal;
	public T_shopping_cart(){
	}
	public T_shopping_cart(Integer shopping_cart_id,Integer user_id,Integer book_id,String book_name,float book_price,Integer book_number,String cart_data,float subtotal){
		this.shopping_cart_id=shopping_cart_id;
		this.user_id=user_id;
		this.book_id=book_id;
		this.book_name=book_name;
		this.book_price=book_price;
		this.book_number=book_number;
		this.cart_data=cart_data;
		this.subtotal=subtotal;
	}
	public void setShopping_cart_id(Integer shopping_cart_id){
		this.shopping_cart_id=shopping_cart_id;
	}
	public Integer getShopping_cart_id(){
		return shopping_cart_id;
	}
	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}
	public Integer getUser_id(){
		return user_id;
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
	public void setBook_price(float book_price){
		this.book_price=book_price;
	}
	public float getBook_price(){
		return book_price;
	}
	public void setBook_number(Integer book_number){
		this.book_number=book_number;
	}
	public Integer getBook_number(){
		return book_number;
	}
	public void setCart_data(String cart_data){
		this.cart_data=cart_data;
	}
	public String getCart_data(){
		return cart_data;
	}
	public void setSubtotal(float subtotal){
		this.subtotal=subtotal;
	}
	public float getSubtotal(){
		return subtotal;
	}
	public String toString() { 
		return "T_shopping_cart[shopping_cart_id=" + shopping_cart_id + ",user_id=" + user_id + ",book_id=" + book_id + ","+
		"book_name=" + book_name + ",book_price=" + book_price + ",book_number=" + book_number + ","+
		"cart_data=" + cart_data + ",subtotal=" + subtotal + "]";
	}
}

