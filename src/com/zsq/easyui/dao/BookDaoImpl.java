package com.zsq.easyui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zsq.easyui.entity.BookPageBean;
import com.zsq.easyui.entity.T_book;
import com.zsq.easyui.util.DBHelper;


public class BookDaoImpl {


	/**
	 * 查询书籍，支持分页，模糊
	 */
	public List<T_book> selectBook(T_book book,int pageStart,int rows,String sqls) {
		// TODO Auto-generated method stub
		String sql="select* from t_book  where 1=1 "+sqls+" limit "+pageStart+","+rows+"";
		return new BaseDao().executeQuery(sql, null, null, T_book.class);
	}
	/**
	 * 统计总数据
	 */
	public int CountBook(String sqls) {
		String sql="select COUNT(*) from t_book where 1=1 "+sqls;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int n=0;
		try {
			conn=DBHelper.getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				n=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBHelper.close(conn, ps, rs);
		}
		return n;
	}
	
	
	/**
	 * 添加书籍
	 */
	public int insertBook(T_book book) {
		// TODO Auto-generated method stub
		String sql="insert into t_book(book_name,book_name_pinyin,book_category_id,book_author,book_price,publishing,book_desc) VALUES(?,?,?,?,?,?,?)";
		return new BaseDao<>().executeUpdate(sql,new Object[]{book.getBook_name(),book.getBook_name_pinyin(),book.getBook_category_id(),book.getBook_author(),book.getBook_price(),book.getPublishing(),book.getBook_desc()});
	}
	/**
	 * 删除书籍
	 * @param book_id
	 * @return
	 */
	public int deleteBook(int book_id) {
		String sql="delete from t_book where book_id="+book_id;
		return new BaseDao<>().executeUpdate(sql, null);
	}
	
	/**
	 * 修改书籍
	 */
	public int editBook(T_book b) {
		String sql="UPDATE t_book set book_name=?,book_name_pinyin=?,book_category_id=?,book_author=?,book_price=?,publishing=?,book_desc=? where book_id=?";
		return new BaseDao<>().executeUpdate(sql, new Object[] {b.getBook_name(),b.getBook_name_pinyin(),b.getBook_category_id(),b.getBook_author(),b.getBook_price(),b.getPublishing(),b.getBook_desc(),b.getBook_id()});
	}
	
	/**
	 * 上传图片后更改图片id
	 */
	public int updataImage(int book_image,int book_id) {
		String sql="UPDATE t_book set book_image=? where book_id=?";
		return new BaseDao<>().executeUpdate(sql, new Object[] {book_image,book_id});
	}
	/**
	 * 书籍上架
	 */
	public int bookupaway(T_book b) {
		String sql="UPDATE t_book set book_state="+(b.getBook_state()+1)+",deploy_datetime='"+b.getDeploy_datetime()+"' where book_id="+b.getBook_id();
		return new BaseDao<>().executeUpdate(sql,null);
	}
	/**
	 * 根据id查询
	 */
	public T_book getBookId(int book_id) {
		String sql="select * from t_book where book_id="+book_id;
		List<T_book> list = new BaseDao<T_book>().executeQuery(sql, null, null, T_book.class);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	/**
	 * 书籍上下架
	 */
	public int bookPutaway(T_book b) {
		String sql="UPDATE t_book set book_state="+(b.getBook_state()+1)+" where book_id="+b.getBook_id();
		return new BaseDao<>().executeUpdate(sql,null);
	}
	
	/**
	 * 得到sql书籍
	 */
	public List<BookPageBean> getBooklistTop(String sqls){
		String sql="select a.book_id,a.book_name,a.book_price,b.file_name from t_book a,t_doc b where a.book_image=b.id and book_state=2 ORDER BY "+sqls+" desc LIMIT 0,5";
		return new BaseDao().executeQuery(sql, null, null, BookPageBean.class);
	}
	
}
