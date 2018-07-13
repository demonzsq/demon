package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_function 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_function{
	private Integer fun_id;
	private Integer fun_fid;
	private String fun_name;
	private String url;
	private String fun_describe;
	public T_function(){
	}
	public T_function(Integer fun_id,Integer fun_fid,String fun_name,String url,String fun_describe){
		this.fun_id=fun_id;
		this.fun_fid=fun_fid;
		this.fun_name=fun_name;
		this.url=url;
		this.fun_describe=fun_describe;
	}
	public void setFun_id(Integer fun_id){
		this.fun_id=fun_id;
	}
	public Integer getFun_id(){
		return fun_id;
	}
	public void setFun_fid(Integer fun_fid){
		this.fun_fid=fun_fid;
	}
	public Integer getFun_fid(){
		return fun_fid;
	}
	public void setFun_name(String fun_name){
		this.fun_name=fun_name;
	}
	public String getFun_name(){
		return fun_name;
	}
	public void setUrl(String url){
		this.url=url;
	}
	public String getUrl(){
		return url;
	}
	public void setFun_describe(String fun_describe){
		this.fun_describe=fun_describe;
	}
	public String getFun_describe(){
		return fun_describe;
	}
	public String toString() { 
		return "T_function[fun_id=" + fun_id + ",fun_fid=" + fun_fid + ",fun_name=" + fun_name + ","+
		"url=" + url + ",fun_describe=" + fun_describe + "]";
	}
}

