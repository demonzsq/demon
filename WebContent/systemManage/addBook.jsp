<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="popover" style="padding: 2px 2px 20px 56px">
	<form id="addbookform" method="post">
		<table cellpadding="5">
		<tr>
			<td>
				<label for="bookname">书名:</label>
			</td>
			<td>
				<input class="easyui-textbox" name="book_name" data-options="required:true" style="width:200px">
			</td>
		</tr>
		<div><h3></h3></div>
		<tr>
			<td>
				<label for="bookname">类别:</label>
			</td>
			<td>
 
				 <input id="addBookCategory" name="book_category_id" class="easyui-combobox" data-options="editable:false,panelHeight:'auto',required:true" style="width: 200px;"> 
			</td>
		</tr>
		<div><h3></h3></div>
		<tr>
			<td>
				<label for="bookname">作者:</label>
			</td>
			<td>
				<input class="easyui-textbox" name="book_author" data-options="required:true" style="width:200px">
			</td>
		</tr>
		<div><h3></h3></div>
		<tr>
			<td>
				<label for="bookname">价格:</label>
			</td>
			<td>
				<input class="easyui-numberbox" name="book_price" data-options="required:true,min:0,prefix:'￥'" style="width:200px">
			</td>
		</tr>
		<div><h3></h3></div>
		<tr>
			<td>
				<label for="bookname">出版社:</label>
			</td>
			<td>
				<input class="easyui-textbox" name="publishing" data-options="required:true" style="width:200px">
			</td>
		</tr>
		<div><h3></h3></div>
		<tr>
			<td>
				<label for="bookname">简介:</label>
			</td>
			<td>
				<input class="easyui-textbox" name="book_desc" data-options="required:true,multiline:true" style="width:200px">
			</td>
		</tr>	
	</table>

	</form>
</div>
<script>
$('#addBookCategory').combobox({  
    url:'bookAction.action?methodName=getBookCategory',
    valueField:'book_category_id',  
    textField:'book_category_name',
}); 

</script>

