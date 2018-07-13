<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div id="top_div">			
<p class="left">您好，欢迎来到飞凡网上书店 ！</p>
<c:if test="${sessionScope.username==null}"><p class="right"><a href="login.jsp">登陆</a> | <a href="register.jsp">注册</a>  | <a href="bookManage.action?methodName=selectbooktop">网站首页</a></p></c:if>
<c:if test="${sessionScope.username!=null}"><p class="right">${sessionScope.username} <a href="bookManage.action?methodName=getCartLi">我的购物车</a>| <a href="orderManageAction.action?methodName=selectMyList&order_state=1">我的订单</a>  | <a href="bookManage.action?methodName=selectbooktop">网站首页</a></p></c:if>
<!-- 	<p class="right"><a href="login.jsp">登陆</a> | <a href="register.jsp">注册</a> | <a href="shoppingCar.jsp">我的购物车</a>| <a href="listOrder1.jsp">我的订单</a>  | <a href="index.jsp">网站首页</a></p> -->
	<div class="fixed"></div>
</div>

<div id="header_div">
	<div id="search">
		<!--form的id与下面文本框的id不能修改，因为使用了id选择器定义样式 -->
		<form name="bookForm" id="form" method="post" action="bookManage.action?methodName=selectBookList">
		<table width="413" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<input type="text" name="selectType" value="" id="input">
				</td>
				<td>
					<input type="image" name="imageField" src="../images/btn_search.png" />
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