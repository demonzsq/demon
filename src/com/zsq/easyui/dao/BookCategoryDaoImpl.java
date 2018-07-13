package com.zsq.easyui.dao;

import java.util.List;

import com.zsq.easyui.entity.T_book_category;

public class BookCategoryDaoImpl {


	public List<T_book_category> getBookCategory(String sqls) {
		// TODO Auto-generated method stub
		String sql="select * from t_book_category where 1=1"+sqls;
		
		return new BaseDao().executeQuery(sql, null, null, T_book_category.class);
	}

}
