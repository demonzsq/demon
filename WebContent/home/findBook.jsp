<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <title>飞凡网上书店</title>
    



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
  </head>
  
  <body>
	<!--top-->
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
    	<div class="order02 pb10">
    		<c:forEach items="${sessionScope.selectBooklist}" var="v">
        	<dl class="findbook">
            	<dt class="left"><img src="/mvcimag/${v.file_name}" width="150px" /></dt>
                <dd class="right">
                	<h4>${v.book_name}</h4>
                    <p><strong>作者：</strong>${v.book_name}</p>
                    <p><strong>价格：</strong>${v.book_price}</p>
                    <p><strong>出版社：</strong>${v.publishing}</p>
                    <p><strong>书籍简介：</strong>${v.book_desc}</p>
                    <p class="mt10"><a href="bookManage.action?methodName=getCart&book_id=${v.book_id}&book_number=0"><img src="../images/btn_shopping.png" /></a><a href="bookManage.action?methodName=addBookOrder&book_id=${v.book_id}"><img src="../images/btn_accounts.png" /></a> </p>
                </dd>
                <div class="fixed"></div>
            </dl>												
            </c:forEach>
            
    	</div> 
	
		<!-- page -->
        <div style="text-align: right;">
<!--         	每页 5 行 共 9 行 第 1 页 共 2 页 <a href='javascript:gotoPage(1)'>首页</a> <a href='javascript:gotoPage(1)'>上一页</a> <a href='javascript:gotoPage(2)'>下一页</a> <a href='javascript:gotoPage(2)'>尾页</a> 页数 <input type='text' id='pageNumber'  style='width:20px;' /> <a href='javascript:jumpPage()'>GO</a>
 -->       
 <z:page pageBean="${pageBean}"></z:page>
  </div>
        <!-- end page -->
    </div> 
 <div class="fixed"></div>   
</div>
<!--end con-->
	<!--end con-->

	<!--footer-->
	
<div id="footer_wrap">
	<p>Copyright ©2014 飞凡教育，版权所有</p>
</div>

	<!--end footer-->
</body>
</html>