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
	 * �ӿ�ʽ�����ڲ��ඨ��
	 * ���ڱ�����������ݵĻص��ӿ�
	 */
//	public static interface CallBack<T>{
//		public List<T> forEach(ResultSet rs) throws SQLException;
//	}
	/**
	 * 	�õ��������ֵ����Ӧ�Ķ�����ӵ�����
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
		ResultSetMetaData rsMeta = rs.getMetaData(); //��ȡ���������	
		int columnCount = rsMeta.getColumnCount(); //��ȡ�еĸ���
		while(rs.next()) {
			t=clazz.newInstance();
			for (int i = 0; i < columnCount; i++) {
				 String columnName = rsMeta.getColumnName(i+1); //��ȡÿһ�е�����
				 Object objectValue = rs.getObject(columnName); //��ȡ���ж�Ӧ�����Ƶ�ֵ
//				 BeanUtils.populate(t, paramsMap);
//				 BeanUtils.copyProperty(t, columnName, objectValue);	 //����BeanUtils�����Կ����������� 

				 BeanUtils.setProperty(t, columnName, objectValue);
			}
			list.add(t); //����װ��ϵ�t�������list������
		}
		
		return list;
	}
	/**
	 * ��ѯ֧�ַ�ҳ
	 */
	public List<T> executeQuery(String sql,PageBean pageBean,Object[] params,Class<T> clazz){
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			T t=null;
			try {
				conn=DBHelper.getConn();
				if(null!=pageBean && pageBean.isPagination()){ //��Ҫִ�з�ҳ����
					String countSQL =this.getCountSql(sql);
					ps=conn.prepareStatement(countSQL);
					
					if(params !=null ) {						//����չλ������
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
				//�ڶ��β�ָ��ҳ�벢���������ļ�¼
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
	 * ����ͨ��sqlת��Ϊ���ܼ�¼����
	 */
	private String getCountSql(String sql) {
		String countSQL="select count(*) from ("+sql+") s1";
		return countSQL;
	}
	
	/**
	 * ����ͨ��sql���д�������ҳ���ķ���
	 */
	private String getPageSql(String sql,PageBean pageBean) {
		String pageSQL=sql + " limit "+pageBean.getStartIndex()+","+pageBean.getRows()+"";
		return pageSQL;
	}
	
	
	/**
	 * ��ɾ��ͨ�÷���
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
			conn.commit();             //����
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			try {
				conn.rollback();
				System.out.println("ʧ��");
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
