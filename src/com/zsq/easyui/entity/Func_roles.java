package com.zsq.easyui.entity;


/**
 * @ClassName:func_roles 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class Func_roles{
	private Integer fr_id;
	private Integer roles_id;
	private Integer fun_id;
	public Func_roles(){
	}
	public Func_roles(Integer fr_id,Integer roles_id,Integer fun_id){
		this.fr_id=fr_id;
		this.roles_id=roles_id;
		this.fun_id=fun_id;
	}
	public void setFr_id(Integer fr_id){
		this.fr_id=fr_id;
	}
	public Integer getFr_id(){
		return fr_id;
	}
	public void setRoles_id(Integer roles_id){
		this.roles_id=roles_id;
	}
	public Integer getRoles_id(){
		return roles_id;
	}
	public void setFun_id(Integer fun_id){
		this.fun_id=fun_id;
	}
	public Integer getFun_id(){
		return fun_id;
	}
	public String toString() { 
		return "Func_roles[fr_id=" + fr_id + ",roles_id=" + roles_id + ",fun_id=" + fun_id + "]";
	}
}

