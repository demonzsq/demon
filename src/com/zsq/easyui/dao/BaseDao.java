package com.zsq.easyui.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.zsq.easyui.util.DBHelper;
import com.zsq.easyui.util.PageBean;




public class BaseDao<T>{

	/**
	 * 接口式匿名内部类定义
	 * 用于遍历结果集数据的回掉接口
	 */
//	public static interface CallBack<T>{
//		public List<T> forEach(ResultSet rs) throws SQLException;
//	}
	/**
	 * 	得到结果集赋值给相应的对象并添加到集合
	 * @param t
	 * @param rs
	 * @param clazz
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public <T>List<T> valuation(T t,ResultSet rs,Class<T> clazz) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException{
		List<T> list = new ArrayList<T>();	
		ResultSetMetaData rsMeta = rs.getMetaData(); //获取结果集数据	
		int columnCount = rsMeta.getColumnCount(); //获取列的个数
		while(rs.next()) {
			t=clazz.newInstance();
			for (int i = 0; i < columnCount; i++) {
				 String columnName = rsMeta.getColumnName(i+1); //获取每一列的名称
				 Object objectValue = rs.getObject(columnName); //获取该行对应列名称的值
//				 BeanUtils.populate(t, paramsMap);
//				 BeanUtils.copyProperty(t, columnName, objectValue);	 //利用BeanUtils将属性拷贝到对象中 

				 BeanUtils.setProperty(t, columnName, objectValue);
			}
			list.add(t); //将封装完毕的t对象存入list集合中
		}
		
		return list;
	}
	/**
	 * 查询支持分页
	 */
	public List<T> executeQuery(String sql,PageBean pageBean,Object[] params,Class<T> clazz){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			T t=null;
			try {
				conn=DBHelper.getConn();
				if(null!=pageBean && pageBean.isPagination()){ //需要执行分页操作
					String countSQL =this.getCountSql(sql);
					ps=conn.prepareStatement(countSQL);
					
					if(params !=null ) {						//设置展位符参数
						for (int i = 0; i < params.length; i++) {
							ps.setObject(i+1, params[i]);
						}
					}
					rs=ps.executeQuery();	
					if(rs.next()) {
						Object o=rs.getObject(1);
						pageBean.setTotal(o.toString());
					}
					DBHelper.close(null, ps, rs);
				}
				//第二次查指定页码并满足条件的记录
				if(null!=pageBean && pageBean.isPagination()) {
					sql=this.getPageSql(sql, pageBean);
				}
				ps=conn.prepareStatement(sql);
				rs=ps.executeQuery();
				return this.valuation(t, rs, clazz);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				DBHelper.close(conn, ps, rs);
			}
			return null;
		}
	
	

	/**
	 * 将普通的sql转化为查总记录数的
	 */
	private String getCountSql(String sql) {
		String countSQL="select count(*) from ("+sql+") s1";
		return countSQL;
	}
	
	/**
	 * 将普通的sql进行处理，求总页数的方法
	 */
	private String getPageSql(String sql,PageBean pageBean) {
		String pageSQL=sql + " limit "+pageBean.getStartIndex()+","+pageBean.getRows()+"";
		return pageSQL;
	}
	
	
	/**
	 * 增删改通用方法
	 */
	public int executeUpdate(String sql,Object[] params) {
		Connection conn=null;
		PreparedStatement ps=null;
		int result=-1;
		try {
			conn=DBHelper.getConn();
			conn.setAutoCommit(false);
			ps=conn.prepareStatement(sql);
			if(params !=null ) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			result=ps.executeUpdate();
			conn.commit();             //事物
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("失败");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}finally {
			DBHelper.close(conn, ps);
		}
		return result;
	}
}
