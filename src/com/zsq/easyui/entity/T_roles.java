package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_roles 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_roles{
	private Integer roles_id;
	private Integer ro_fid;
	private String ro_name;
	private String ro_describe;
	public T_roles(){
	}
	public T_roles(Integer roles_id,Integer ro_fid,String ro_name,String ro_describe){
		this.roles_id=roles_id;
		this.ro_fid=ro_fid;
		this.ro_name=ro_name;
		this.ro_describe=ro_describe;
	}
	public void setRoles_id(Integer roles_id){
		this.roles_id=roles_id;
	}
	public Integer getRoles_id(){
		return roles_id;
	}
	public void setRo_fid(Integer ro_fid){
		this.ro_fid=ro_fid;
	}
	public Integer getRo_fid(){
		return ro_fid;
	}
	public void setRo_name(String ro_name){
		this.ro_name=ro_name;
	}
	public String getRo_name(){
		return ro_name;
	}
	public void setRo_describe(String ro_describe){
		this.ro_describe=ro_describe;
	}
	public String getRo_describe(){
		return ro_describe;
	}
	public String toString() { 
		return "T_roles[roles_id=" + roles_id + ",ro_fid=" + ro_fid + ",ro_name=" + ro_name + ","+
		"ro_describe=" + ro_describe + "]";
	}
}

