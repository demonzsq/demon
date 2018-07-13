<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <title>飞凡网上书店</title>
    <!-- 引用三级联动 -->
	<script src="../js/jquery-3.3.1.min.js"></script>
   	 <script src="../js/distpicker.data.js"></script>
	<script src="../js/distpicker.js"></script>
	<script src="../js/main.js"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
   
  </head>
  
  <body>
   
<jsp:include page="homeScerch.jsp"></jsp:include> 
<!--end nav-->

	<!--end top-->

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
			<div class="o-mt">
	        	<h2>结算订单页</h2>
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
						<%-- <td width="20%" class="text"><a href="bookManage.action?methodName=deleteShopCartId&book_id=${v.book_id}">删除</a>  --%>
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
	        
	        
	        <div class="order02 pb10">
	        
	        	<form action="orderManageAction.action?methodName=addOrder" method="post">
	        	<table width="738" align="center" cellpadding="0" cellspacing="0">
	        		<tr class="bd2">
	        			<th width="20%" align="center" class="bd2">收货人</td>
	        			<td width="80%"  align="left" class="bd1 pl9" >
	        				<input type="text" name="consignee" value="" id="" class="input w300"><span class="required">*</span>
	        			</td>
	        		</tr>
	        		<tr class="bd2">
	        			<th width="20%" align="center" class="bd2">收货人电话</td>
	        			<td width="80%"  align="left" class="bd1 pl9" >
	        				<input type="text" name="phone" value="" id="" class="input w300"><span class="required">*</span>
	        			</td>
	        		</tr>
	        		<tr class="bd2">
	        			<th width="20%" align="center" class="bd2">收货人邮编</td>
	        			<td width="80%"  align="left" class="bd1 pl9" >
	        				<input type="text" name="postalcode" value="" id="" class="input w300"><span class="required">*</span>
	        			</td>
	        		</tr>
	        		<tr class="bd2">
	        			<th width="20%" align="center" class="bd2">地址</td>
	        			<td width="80%"  align="left" class="bd1 pl9" >
	        					<div data-toggle="distpicker">
								    <select id="eprovinceName" data-province="湖南省" name="provinceName"></select>
								    <select id="ecityName" data-city="长沙市" name="cityName"></select>
								    <select id="edistrictName" data-district="芙蓉区" name="districtName"></select>
      								<span class="required">*</span>
      							</div>
	        			</td>
	        		</tr>
	        		<tr class="bd2">
	        			<th width="20%" align="center" class="bd2">详细地址</td>
	        			<td width="80%"  align="left" class="bd1 pl9" >
	        				<input type="text" name="address" value="" id="" class="input w300"><span class="required">*</span>
	        			</td>
	        		</tr>
	        		
	        		<tr class="bd2">
	        			<th width="20%" align="center" class="bd2">发货方式</td>
	        			<td width="80%"  align="left" class="bd1 pl9">
	        				<select name="send_type" id="" style="width:305px;"><option value="">--请选择--</option>
	        					<option value="1">平邮</option>
	        					<option value="2">快递</option></select><span class="required">*</span>
	        			</td>
	        		</tr>
	        	</table>
	        	
	        	<table width="738" border="0" cellspacing="0" cellpadding="0" class="mt10" align="center">
	        		<tr>
	        			<td width="20%" align="center" class="bd2"></td>
	        			<td width="80%" align="left" class="pl9" >
	        				<input type="submit" value="确定" class="btn"/>
	        			</td>
	        		</tr>
        		</table>
        		</form>
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
</html>