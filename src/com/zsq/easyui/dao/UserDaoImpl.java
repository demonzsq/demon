package com.zsq.easyui.dao;

import java.util.List;

import com.zsq.easyui.entity.T_function;
import com.zsq.easyui.entity.T_user;

public class UserDaoImpl {

	/**
	 * 登陆
	 * @param user
	 * @return
	 */
	public T_user selectUser(T_user user) {
		// TODO Auto-generated method stub
		String sql="select * from t_user where user_name='"+user.getUser_name()+"' and user_pwd='"+user.getUser_pwd()+"'";
		List<T_user> list = new BaseDao<T_user>().executeQuery(sql, null, null, T_user.class);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	
	/**
	 * 根据管理员获得权限功能
	 */
	public List<T_function> selectFunc(String user_name){
		String sql="select * from t_function where fun_id in(select fun_id from func_roles where roles_id in(select roles_id from user_roles where user_id=(select user_id from t_user where user_name='"+user_name+"'))) and fun_fid=0";
		return new BaseDao().executeQuery(sql, null, null, T_function.class);
	}
	
	/**
	 * 根据权限title获得树
	 */
	public List<T_function> selecttree(String fun_name){
		String sql="select * from t_function where fun_fid in(select fun_id from t_function where fun_name='"+fun_name+"')";
		return new BaseDao().executeQuery(sql, null, null, T_function.class);
	}
}
