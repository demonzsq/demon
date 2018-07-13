<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>飞凡网上书店</title>
    


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="../css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
    

  </head>
  
  
  <body>
	<!--top-->
	


<jsp:include page="homeScerch.jsp"></jsp:include>

<!--end nav-->

	<!--end top-->

	<!--content-->
	<div class="w960 mt10">
		<div class="breadcrumb"></div>
	</div>

	<div class="w960">
     <jsp:include page="homeOrder.jsp"></jsp:include>
		<div class="w740 right">
			<div class="o-mt">
	        	<h2>已发货订单</h2>
	        </div>
	        <div class="order02">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <th width="10%" class="bd2">下单日期</th>
	                <th width="15%" class="bd2">收货人</th>
	                <th width="15%" class="bd2">收货人电话</th>
	                <th width="15%" class="bd2">收货人邮编</th>
	                <th width="15%" class="bd2">收货人地址</th>
	                <th width="10%" class="bd2">发货方式</th>
	                <th width="10%" class="bd2">订单总价</th>
	                <th width="10%" class="bd2">操作</th>
	              </tr>
	              
	              
	              
	             <c:forEach items="${sessionScope.orderMylist}" var="v">
	              <tr>
	                <td width="10%" class="text">${v.order_datetime}</td>
	                <td width="15%" class="text">${v.consignee}</td>
	                <td width="15%" class="text">${v.phone}</td>
	                <td width="15%" class="text">${v.postalcode}</td>
	                <td width="15%" class="text">${v.address}</td>
	                <td width="10%" class="text">
	                	<c:if test="${v.send_type==1}">平邮</c:if>
	                	<c:if test="${v.send_type==2}">快递</c:if>
	                </td>
	                <td width="10%" class="text">￥${v.order_price}</td>
	                <td width="10%" class="text">
	                	<a href="orderManageAction.action?methodName=orderSign&order_state=3&order_id=${v.order_id}">签收</a>
	                </td>
	              </tr>
	              </c:forEach>
	              
	              
	            </table>
	        </div>
	        
	        <!-- page -->
	        <div style="text-align: right;">
	        <!-- 	每页 5 行 共 1 行 第 1 页 共 1 页 <a href='javascript:gotoPage(1)'>首页</a> <a href='javascript:gotoPage(1)'>上一页</a> <a href='javascript:gotoPage(1)'>下一页</a> <a href='javascript:gotoPage(1)'>尾页</a> 页数 <input type='text' id='pageNumber'  style='width:20px;' /> <a href='javascript:jumpPage()'>GO</a> -->
	      	<z:page pageBean="${pageBean}"></z:page>
	        </div>
	        <!-- end page -->
	    </div>
 		<div class="fixed"></div>   
	</div>
	<!--end content-->

	<!--footer-->
	
<div id="footer_wrap">
	<p>Copyright ©2014 飞凡教育，版权所有</p>
</div>

	<!--end footer-->
</body>
</html>