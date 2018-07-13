package com.zsq.easyui.dao;

import com.zsq.easyui.entity.T_doc;

public class DocDaoImpl {
	
	
	/**
	 * ÃÌº”Õº∆¨
	 */
	public int insertDoc(T_doc doc) {
		String sql="insert into t_doc(id,file_name,mime) values(?,?,?)";	
		return new BaseDao<>().executeUpdate(sql, new Object[] {doc.getId(),doc.getFile_name(),doc.getMime()});
	}
	
	
	
}
