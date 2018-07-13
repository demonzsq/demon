package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:user_roles 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:45
 */ 

public class User_roles{
	private Integer urid;
	private Integer user_id;
	private Integer roles_id;
	public User_roles(){
	}
	public User_roles(Integer urid,Integer user_id,Integer roles_id){
		this.urid=urid;
		this.user_id=user_id;
		this.roles_id=roles_id;
	}
	public void setUrid(Integer urid){
		this.urid=urid;
	}
	public Integer getUrid(){
		return urid;
	}
	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}
	public Integer getUser_id(){
		return user_id;
	}
	public void setRoles_id(Integer roles_id){
		this.roles_id=roles_id;
	}
	public Integer getRoles_id(){
		return roles_id;
	}
	public String toString() { 
		return "User_roles[urid=" + urid + ",user_id=" + user_id + ",roles_id=" + roles_id + "]";
	}
}

