package com.zsq.easyui.entity;

import java.util.Date;

/**
 * @ClassName:t_doc 
 * @Description: TODO(ÀàËµÃ÷£ºnull )
 * @author demon 
 * @date 2018-06-22 16:23:44
 */ 

public class T_doc{
	private Integer id;
	private String file_name;
	private String mime;
	public T_doc(){
	}
	public T_doc(Integer id,String file_name,String mime){
		this.id=id;
		this.file_name=file_name;
		this.mime=mime;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return id;
	}
	public void setFile_name(String file_name){
		this.file_name=file_name;
	}
	public String getFile_name(){
		return file_name;
	}
	public void setMime(String mime){
		this.mime=mime;
	}
	public String getMime(){
		return mime;
	}
	public String toString() { 
		return "T_doc[id=" + id + ",file_name=" + file_name + ",mime=" + mime + "]";
	}
}

