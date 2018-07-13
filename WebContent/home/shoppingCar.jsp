<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>飞凡网上书店</title>

  <script src="../js/jquery-3.3.1.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/jquery.validate.min.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<script type="text/javascript">
function onc(book_id){
	
	var book_number=document.getElementById(book_id).value;

	if(book_number==0){
		alert("数量不能为0")
		return false;
	}
	
	

	 $.ajax({
		  type : "post",
		  url : "bookManage.action",
		  data : {methodName:'getCart',book_id:book_id,book_number:book_number},
		  success : function(data){

			  location.reload();
		  }
	  }); 
}
function ifdel(){
	 if(confirm("确认删除？")){
		 return true;
	 }else{
		 return false;
	 }
	  
	  
 }
/* function delShopId(Book_id){
	 $.ajax({
		  type : "post",
		  url : "bookManage.action",
		  data : {methodName:'deleteShopCartId',book_id:book_id},
		  success : function(data){
			  location.reload();
		  }
	  }); 
} */

</script>
<body>


 <jsp:include page="homeScerch.jsp"></jsp:include> 


	<!--con-->
	<div class="w960 mt10">
		<div class="side left">

			<h3>图书分类</h3>

			<ul class="classify bgf7e4e4 bdf7e4e4">
				<c:forEach items="${sessionScope.bookCatelist}" var="v">
				<li> <a href="bookManage.action?methodName=selectBookListone&selectType=${v.book_category_name}">${v.book_category_name}</a></li>
				</c:forEach>
				<div class="fixed"></div>
			</ul>

		</div>
		<div class="w740 right">
			<div class="order02">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th width="6.5%" class="bd2"><!-- <input id="all" type="checkbox" name="c0" onclick="change()">全选</th> -->
						<th width="20%" class="bd2">书名</th>
						<th width="20%" class="bd2">单价</th>
						<th width="20%" class="bd2">数量</th>
						<th width="20%" class="bd2">小计</th>
						<th width="20%" class="bd2">操作</th>
					</tr>
					<c:forEach items="${sessionScope.cartList}" var="v">
					<tr>
					<td width="5%" class="text"><!-- <input type="checkbox" name="c1" onclick="rechan()"> --></td>
						<td width="20%" class="text">${v.book_name}</td>
						<td width="20%" class="text">${v.book_price}</td>
						<td width="20%" class="text"><input class="input"
							style="width: 50px; text-align: center;" type="text" id="${v.book_id}"
							value="${v.book_number}" onblur="onc(${v.book_id})"></td>
						<td width="20%" class="text">${v.subtotal}</td>
						<td width="20%" class="text"><a href="bookManage.action?methodName=deleteShopCartId&book_id=${v.book_id}" onclick="return ifdel()">删除</a> 
						</td>
					</tr>
					</c:forEach>


				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="bd2">
					<tr bgcolor="#fefcea">
					<td width="6.5%" align="right"><!-- <input id="revcheck" type="checkbox" name="r0" onclick="revs()">反选 </td> -->
						<td width="80%" align="right"><strong>订单总价：</strong></td>
						<td width="20%" align="left"><strong><span
								class="STYLE1">${sessionScope.subbb}</span></strong></td>
					</tr>
				</table>
				<table width="738" border="0" cellspacing="0" cellpadding="0"
					class="mt10">
					<tr>
						<td width="5%" align="center"></td>
					 	<c:if test="${empty  sessionScope.cartList}"></c:if> 
					 	<c:if test="${not empty sessionScope.cartList}">
					 		<td width="30%" align="center"><a href="bookManage.action?methodName=deleteShopCart" onclick="return ifdel()"><img
								src="../images/btn_shoppingcart.png" /></a></td>
						<td width="30%" align="center"><a href="index.jsp"><img
								src="../images/btn_jxgm.png" /></a></td>
						<td width="30%" align="center"><a href="settleOrder.jsp"><img
								src="../images/btn_accounts.png" /></a></td>
						<td width="5%"></td>
					 	</c:if> 
						
					</tr>
				</table>

			</div>
		</div>
		<div class="fixed"></div>
	</div>
	<!--end con-->

	<!--footer-->

	<div id="footer_wrap">
		<p>Copyright ©2014 飞凡教育，版权所有</p>
	</div>

	<!--end footer-->
</body>
<script type="text/javascript">
	var all = document.getElementById("all");
	var product = document.getElementsByName("c1"), l = product.length;
	all.onclick = function() {
		for (var i = l; i--;) {
			product[i].checked = all.checked;
		}
	};
	for (var i = l; i--;) {
		product[i].onclick = function() {
			var k = 0;
			for (var i = l; i--;)
				product[i].checked && k++;
			all.checked = l == k;
		};
	}

	//反选的方法
	function revs() {
		//获得反选按钮
		var reverse = document.getElementById("revcheck");
		//反选按钮被选中时，遍历所有按钮
		if (reverse.checked) {
			for (var i = 0; i < product.length; i++) {
				if (product[i].type == "checkbox" && product[i].checked == true) {
					product[i].checked = false;
				} else if (product[i].type == "checkbox"
						&& product[i].checked == false) {
					product[i].checked = true;
				}

			}
			//反选按钮未被选中时
		} else {
			for (var i = 0; i < product.length; i++) {
				if (product[i].type == "checkbox" && product[i].checked == true) {
					product[i].checked = false;
				} else if (product[i].type == "checkbox"
						&& product[i].checked == false) {
					product[i].checked = true;
				}

			}
		}
	}
</script>
</html>