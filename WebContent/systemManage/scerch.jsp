<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="top_div">
		<p class="left">您好${sessionScope.admin}，欢迎来到飞凡网上书店 ！</p>
		<p class="right">
			<a href="#">后台管理</a> | <a href="#">退出系统</a> | <a href="bookManage.action?methodName=selectbooktop">网站首页</a>
		</p>
		<div class="fixed"></div>
	</div>

<div id="header_div">
	<div id="search">
		<!--form的id与下面文本框的id不能修改，因为使用了id选择器定义样式 -->
		<form name="bookForm" id="form" method="post" action="#">
		<table width="413" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="text" name="" value="" id="input">
				</td>
				<td>
					<input type="image" name="imageField" src="images/btn_search.png" />
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
 
<div id="nav">
	<ul id="menu"></ul>
</div>

</body>
</html>