<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script src="js/bookList.js"></script>

 <div id="layout" class="easyui-layout" data-options="fit:true">    
    <div data-options="region:'north'">
    	
    	
    	<!-- 搜索 -->
		<div id="search" style="padding:10px 20px;">
			
			关键词：<input id="searchValue" type="text" name="key" /><a id="searchBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
		</div>
	</div>   
    <div data-options="region:'center',title:'书籍列表',fit:true">
    	 <input id="bookstate" name="dept"> 
		<!-- dataGrid -->
		<table id="booklist"></table>
		<!-- 弹出窗 -->
		<div id="showDialog"></div>
	</div>   
 </div> 


<div id="popover"></div> <!-- 弹出框 -->