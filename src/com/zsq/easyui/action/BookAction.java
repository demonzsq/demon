package com.zsq.easyui.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.demon.bookstoremvc.framework.DispatCherAction;
import com.demon.bookstoremvc.framework.ModelDriver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsq.easyui.dao.BookCategoryDaoImpl;
import com.zsq.easyui.dao.BookDaoImpl;
import com.zsq.easyui.dao.DocDaoImpl;
import com.zsq.easyui.entity.BookPageBean;
import com.zsq.easyui.entity.T_book;
import com.zsq.easyui.entity.T_book_category;
import com.zsq.easyui.entity.T_doc;
import com.zsq.easyui.util.PageBean;
import com.zsq.easyui.util.PinYinUtil;

public class BookAction extends DispatCherAction implements ModelDriver<T_book>{
	
	T_book book=new T_book();
	@Override
	public T_book getModel() {
		// TODO Auto-generated method stub
		return book;
	}
	public BookAction() {
	}
	
	 BookDaoImpl bdi = new	BookDaoImpl();
	/**
	 * 得到所有书籍
	 * @throws IOException 
	 */
	public String selectBook(HttpServletRequest req,HttpServletResponse resp) throws IOException {

		String book_state=req.getParameter("book_state");    //得到书籍状态

		String seachValue=req.getParameter("searchValue");   //得到搜索框的值
		String sqls="";
		
		if(null!=book_state && !book_state.equals("0")) {        //上下架状态
			sqls+=" and book_state="+book_state;
		}
		if(null!=seachValue && !"".equals(seachValue)) {
			sqls+=" and concat(book_name,book_name_pinyin,book_author,book_price,publishing) like '%"+seachValue.trim()+"%' ";
		}
		
		int page = Integer.parseInt(req.getParameter("page"));  //当前页
		int rows = Integer.parseInt(req.getParameter("rows"));  //每页显示记录数
	
		int countBook = bdi.CountBook(sqls);				//得到总记录数
		
		String sort=req.getParameter("sort");					//排序的列
		String order=req.getParameter("order");					//升序或降序
		
		String orderby="";
		if(null!=sort && !"".equals(sort)) {					//如果排序
			 orderby=" ORDER BY "+sort+" "+order;
		}
				
		//计算出分页起始位置
		int pageStart = (page-1) * rows;   
		
		sqls=sqls+orderby;
		List<T_book> list = bdi.selectBook(null, pageStart, rows, sqls);
		
		Map<String, Object> map=new HashMap<>();
		map.put("total", countBook);
		map.put("rows", list);
		
		
		ObjectMapper json = new ObjectMapper();
		String jsons=json.writeValueAsString(map);
		PrintWriter out=resp.getWriter();
		out.write(jsons);
		out.flush();
		out.close();
		
		return null;
	}

	
	/**
	 *   得到书籍分类
	 * @throws IOException 
	 */
	public String getBookCategory(HttpServletRequest req,HttpServletResponse resp) throws IOException {

		BookCategoryDaoImpl bcdi = new BookCategoryDaoImpl();
		List<T_book_category> list = bcdi.getBookCategory("");
		
		ObjectMapper json=new ObjectMapper();
		String jsons=json.writeValueAsString(list);

		PrintWriter out=resp.getWriter();
		out.write(jsons);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 添加书籍
	 */
	public String addbook(HttpServletRequest req,HttpServletResponse resp) {
		book.setBook_name_pinyin(PinYinUtil.toPinyin(book.getBook_name()));
		int i = bdi.insertBook(book);
		return null;
	}
	
	/**
	 * 删除书籍
	 */
	public String delbook(HttpServletRequest req,HttpServletResponse resp) {

	    int index=Integer.parseInt(req.getParameter("inde"));
		
		for (int i = 0; i <=index; i++) {
			int book_id=Integer.parseInt(req.getParameter("book_id["+i+"]"));
			int j = bdi.deleteBook(book_id);
		}
		return null;
	}
	
	/**
	 * 修改书籍
	 * @throws IOException 
	 */
	public String editbook(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		book.setBook_name_pinyin(PinYinUtil.toPinyin(book.getBook_name()));
		int i = bdi.editBook(book);
		
		ObjectMapper json=new ObjectMapper();
		String jsons=json.writeValueAsString(i);
		PrintWriter out=resp.getWriter();
		out.write(jsons);
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * 根据书籍id查询对象
	 */
	
	public String getIdBook(HttpServletRequest req,HttpServletResponse resp) {
		
		return null;
	}
	
	public String upBookImage(HttpServletRequest req,HttpServletResponse resp) {
		int insertDoc=0;
		String parameter = req.getParameter("book_id");
		//获取请求内容是否为 "multipart/form-data"类型
		boolean isMultipart=ServletFileUpload.isMultipartContent(req);
		//判断是否isMultipart为真
		if(isMultipart){
			try {
			//FileItemFactory  工厂
			FileItemFactory   factory=new  DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			//获取from表单的FileItem的对象集合
	 		List<FileItem> items = upload.parseRequest(req);//错误
	 		
	 		UUID uuid =  UUID.randomUUID();
				for(FileItem  it :items){
					//判断集合中type为file
					if(it.isFormField()==false){
						
						//判断文件上传类型
						String name=it.getName();
						List<String> Filetype=Arrays.asList(".gif",".bmp",".jpg",".jpeg",".png");
						String name1=name.substring(name.lastIndexOf("."));
						if(Filetype.contains(name1)){
							name = uuid.toString() + new Date().getTime()+ name;
							Long imageId = new Date().getTime();
							File f=new File(req.getSession().getServletContext().getRealPath("/upload"),name);
							it.write(f);
							String filePath = req.getSession().getServletContext().getRealPath("/upload")+name;
							
							
							int imageid=Integer.parseInt(imageId.toString().substring(7));//得到系统毫秒数后六位
							String mime=filePath.substring(filePath.lastIndexOf("."));		//得到文件后缀名
							 insertDoc = new DocDaoImpl().insertDoc(new T_doc(imageid, name, mime));
							if(insertDoc>0) {
								int updataImage = new BookDaoImpl().updataImage(imageid, Integer.parseInt(parameter));
								if(updataImage>0) {
									return null;
								}
							}
						}else{
							System.out.println("上传失败 文件类型不支持");
						}
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
			return null;
	}
	
	
	/**
	 * 书籍上下架
	 * @throws IOException 
	 */
	public String bookPutaway(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		BookDaoImpl bdi=new BookDaoImpl();
		T_book book1 = bdi.getBookId(Integer.parseInt(req.getParameter("book_id")));
		int i=0;
		if(book1.getBook_state()==1) {
			String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			
			book1.setDeploy_datetime(format);	

			 i = bdi.bookupaway(book1);
		}else {
			 i = bdi.bookPutaway(book1);
		}
		return null;
	}
	
	
	/**
	 * 首页书籍数据绑定
	 */
	public String selectbooktop(HttpServletRequest req,HttpServletResponse resp) {
		BookDaoImpl bdi=new BookDaoImpl();
		List<BookPageBean> booklistTop = bdi.getBooklistTop("deploy_datetime");//得到新书

		req.getSession().setAttribute("booklistTop", booklistTop);
		List<BookPageBean> booklistTop2 = bdi.getBooklistTop("sales_volume");//得到热销
		req.getSession().setAttribute("booklistTop2", booklistTop2);
		return null;
	}
}

