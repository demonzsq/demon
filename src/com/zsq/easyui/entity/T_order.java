package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_order 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_order{
	private Integer order_id;
	private Integer user_id;
	private String order_datetime;
	private String consignee;
	private String phone;
	private String postalcode;
	private String address;
	private Integer send_type;
	private String send_datetime;
	private float order_price;
	private Integer order_state;
	public T_order(){
	}
	public T_order(Integer order_id,Integer user_id,String order_datetime,String consignee,String phone,String postalcode,String address,Integer send_type,String send_datetime,float order_price,Integer order_state){
		this.order_id=order_id;
		this.user_id=user_id;
		this.order_datetime=order_datetime;
		this.consignee=consignee;
		this.phone=phone;
		this.postalcode=postalcode;
		this.address=address;
		this.send_type=send_type;
		this.send_datetime=send_datetime;
		this.order_price=order_price;
		this.order_state=order_state;
	}
	public void setOrder_id(Integer order_id){
		this.order_id=order_id;
	}
	public Integer getOrder_id(){
		return order_id;
	}
	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}
	public Integer getUser_id(){
		return user_id;
	}
	public void setOrder_datetime(String order_datetime){
		this.order_datetime=order_datetime;
	}
	public String getOrder_datetime(){
		return order_datetime;
	}
	public void setConsignee(String consignee){
		this.consignee=consignee;
	}
	public String getConsignee(){
		return consignee;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public String getPhone(){
		return phone;
	}
	public void setPostalcode(String postalcode){
		this.postalcode=postalcode;
	}
	public String getPostalcode(){
		return postalcode;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String getAddress(){
		return address;
	}
	public void setSend_type(Integer send_type){
		this.send_type=send_type;
	}
	public Integer getSend_type(){
		return send_type;
	}
	public void setSend_datetime(String send_datetime){
		this.send_datetime=send_datetime;
	}
	public String getSend_datetime(){
		return send_datetime;
	}
	public void setOrder_price(float order_price){
		this.order_price=order_price;
	}
	public float getOrder_price(){
		return order_price;
	}
	public void setOrder_state(Integer order_state){
		this.order_state=order_state;
	}
	public Integer getOrder_state(){
		return order_state;
	}
	public String toString() { 
		return "T_order[order_id=" + order_id + ",user_id=" + user_id + ",order_datetime=" + order_datetime + ","+
		"consignee=" + consignee + ",phone=" + phone + ",postalcode=" + postalcode + ","+
		"address=" + address + ",send_type=" + send_type + ",send_datetime=" + send_datetime + ","+
		"order_price=" + order_price + ",order_state=" + order_state + "]";
	}
}

