<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="side left"> 
			<h4>书籍管理</h4>   
	      	<ul class="classify E6">
		      	<li>&nbsp;&nbsp;<a href="bookManage.action?methodName=classify">新&nbsp;&nbsp;增</a></li>
		      	<li>&nbsp;&nbsp;<a href="bookManage.action?methodName=selectList&book_state=1">未上架</a></li>
		      	<li>&nbsp;&nbsp;<a href="bookManage.action?methodName=selectList&book_state=2">已上架</a></li>
		      	<li>&nbsp;&nbsp;<a href="bookManage.action?methodName=selectList&book_state=3">已下架</a></li>
		      	<div class="fixed"></div>
			</ul>
			<h4>订单管理</h4>        
		    <ul class="classify E6">
		      	<li>&nbsp;&nbsp;<a href="orderManageAction.action?methodName=selectList&order_state=1">未发货</a></li>
		      	<li>&nbsp;&nbsp;<a href="orderManageAction.action?methodName=selectList&order_state=2">已发货</a></li>
		      	<li>&nbsp;&nbsp;<a href="orderManageAction.action?methodName=selectList&order_state=3">已签收</a></li> 
		      	<div class="fixed"></div>
			</ul>
		</div>

</body>
</html>