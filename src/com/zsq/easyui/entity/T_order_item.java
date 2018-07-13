package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_order_item 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_order_item{
	private Integer order_item_id;
	private Integer order_id;
	private Integer book_id;
	private Integer quantity;
	public T_order_item(){
	}
	public T_order_item(Integer order_item_id,Integer order_id,Integer book_id,Integer quantity){
		this.order_item_id=order_item_id;
		this.order_id=order_id;
		this.book_id=book_id;
		this.quantity=quantity;
	}
	public void setOrder_item_id(Integer order_item_id){
		this.order_item_id=order_item_id;
	}
	public Integer getOrder_item_id(){
		return order_item_id;
	}
	public void setOrder_id(Integer order_id){
		this.order_id=order_id;
	}
	public Integer getOrder_id(){
		return order_id;
	}
	public void setBook_id(Integer book_id){
		this.book_id=book_id;
	}
	public Integer getBook_id(){
		return book_id;
	}
	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}
	public Integer getQuantity(){
		return quantity;
	}
	public String toString() { 
		return "T_order_item[order_item_id=" + order_item_id + ",order_id=" + order_id + ",book_id=" + book_id + ","+
		"quantity=" + quantity + "]";
	}
}

