package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_user 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_user{
	private Integer user_id;
	private String user_name;
	private String user_pwd;
	private Integer user_type;
	public T_user(){
	}
	public T_user(Integer user_id,String user_name,String user_pwd,Integer user_type){
		this.user_id=user_id;
		this.user_name=user_name;
		this.user_pwd=user_pwd;
		this.user_type=user_type;
	}
	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}
	public Integer getUser_id(){
		return user_id;
	}
	public void setUser_name(String user_name){
		this.user_name=user_name;
	}
	public String getUser_name(){
		return user_name;
	}
	public void setUser_pwd(String user_pwd){
		this.user_pwd=user_pwd;
	}
	public String getUser_pwd(){
		return user_pwd;
	}
	public void setUser_type(Integer user_type){
		this.user_type=user_type;
	}
	public Integer getUser_type(){
		return user_type;
	}
	public String toString() { 
		return "T_user[user_id=" + user_id + ",user_name=" + user_name + ",user_pwd=" + user_pwd + ","+
		"user_type=" + user_type + "]";
	}
}

