package com.zsq.easyui.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
/*	public static void main(String[] args) {
		System.out.println(getConn());
	}*/

	
	private static String driver;
	private static String url;
	private static String user;
	private static String pwd;
	
	
	
	
	static {
		   try {
				   InputStream is=DBHelper.class.getResourceAsStream("config.properties");
					
				   Properties properties = new Properties();
				   properties.load(is);
				   
				   driver=properties.getProperty("driver");
				   url=properties.getProperty("url");
				   user=properties.getProperty("user");
				   pwd=properties.getProperty("pwd");
				   
				   Class.forName(driver);
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			   		e.printStackTrace();
		   }
	}
	
	
	public static Connection getConn() {
		Connection conn=null;
		
		try {
			conn=DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn,PreparedStatement ps) {
		close(conn, ps, null);
	}
	public static void close(Connection conn) {
		close(conn, null);
	}
	
}
