package com.zsq.easyui.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mapper.Mapper;

import com.demon.bookstoremvc.framework.DispatCherAction;
import com.demon.bookstoremvc.framework.ModelDriver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsq.easyui.dao.BaseDao;
import com.zsq.easyui.dao.BookDaoImpl;
import com.zsq.easyui.dao.UserDaoImpl;
import com.zsq.easyui.entity.T_function;
import com.zsq.easyui.entity.T_user;
import com.zsq.easyui.util.MD5;

import jdk.nashorn.internal.runtime.JSONFunctions;



public class UserAction extends DispatCherAction implements ModelDriver<T_user>{

	T_user user=new T_user();

	@Override
	public T_user getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public UserAction() {
	}
	
	/**
	 * 登陆
	 */
	public String userLogin(HttpServletRequest req,HttpServletResponse resp) {
	 UserDaoImpl iub=new UserDaoImpl();
		MD5 md5 = new MD5();
		user.setUser_pwd(md5.getMD5ofStr(user.getUser_pwd()));
		T_user user2=iub.selectUser(user);
		if(null !=user2) {
					if(user2.getUser_type()==1) {
						req.getSession().setAttribute("admin", user2.getUser_name());
						return "adminsuccess";
					}else {
						req.getSession().setAttribute("username", user2.getUser_name());
						req.getSession().setAttribute("user_id", user2.getUser_id());
						return "listbooktop";
					}
				}else {
					return "error";
				}
			}
	
	
	
	/**
	 * 获得管理员的权限功能
	 * @throws IOException 
	 */
	public String getfun(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		ObjectMapper json=new ObjectMapper();

		UserDaoImpl iub=new UserDaoImpl();
		
		List<T_function> li= iub.selectFunc("admins");

		String jsons = json.writeValueAsString(li);
		
		PrintWriter out=resp.getWriter();
		out.write(jsons);
		
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 根据权限title得到树
	 * @throws IOException 
	 */
	public String gettree(HttpServletRequest req,HttpServletResponse resp) throws IOException {

	
		 
		ObjectMapper json=new ObjectMapper();
		UserDaoImpl iub=new UserDaoImpl();
		List<T_function> list = iub.selecttree(req.getParameter("title"));
		StringBuffer str = new StringBuffer();
	
		str.append("[");
		for (T_function fun : list) {
			String jsoncen="{\"id\":\""+fun.getFun_id()+"\",\"text\": \""+fun.getFun_name()+"\",\"attribute\":{\"url\":\""+fun.getUrl()+"\"}},";
			 str.append(jsoncen);
		}
		str.append("]");
		
		str.replace(str.lastIndexOf(","),str.lastIndexOf(",")+1,"");
		PrintWriter out=resp.getWriter();
		out.write(str.toString());		
		out.flush();
		out.close();
		return null;
	}
	
	
	
	
}
