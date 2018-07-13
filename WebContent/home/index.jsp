<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE title PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<html>

    <title>飞凡网上书店</title>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<link href="../systemManage/css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
  </head>
<script type="text/javascript">
$(function() {
	booklisttop();
});

function booklisttop(){
	$.ajax({
		type:"POST",//为post请求
        url:"bookAction.action?methodName=selectbooktop",//这是我在后台接受数据的文件名
        async:false,
        
	});  
}

</script>
  <body>
  		   <jsp:include page="homeScerch.jsp"></jsp:include> 
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
			<div class="main_div">
				<div class="banner left"><img src="../images/banner.png" /></div>
				<div class="fixed"></div>
			</div>
			
			<div class="main_div mt10">
	        	<h2>
	        		<cite class="left">新书上架</cite>
	        		<div class="fixed"></div> 
	            </h2>
	
	            <ul class="arivals">
	            	 <c:forEach items="${sessionScope.booklistTop}" var="v">
	            	<li>
	            		<a href="bookManage.action?methodName=getBookId&book_id=${v.book_id}"><img src="${pageContext.request.contextPath}/upload/${v.file_name}" /></a><p>${v.book_name}</p><p class="red">${v.book_price}</p>
	            	</li>	
	            	</c:forEach> 
	            	<div class="fixed"></div> 
	            </ul>
	        </div>
	        <div class="main_div mt10">
	        	<h2>
	        		<cite class="left">热销图书</cite>
	        		<div class="fixed"></div> 
	            </h2>
	           	
	            <ul class="arivals">
	            	<c:forEach items="${sessionScope.booklistTop2}" var="v">
	            	<li>
	            		<a href="bookManage.action?methodName=getBookId&book_id=${v.book_id}"><img src="${pageContext.request.contextPath}/upload/${v.file_name}" /></a><p>${v.book_name}</p><p class="red">${v.book_price}</p>
	            	</li>	
	            	</c:forEach>
	            
	            	<div class="fixed"></div> 
	            </ul>
	        </div>
		</div>    
	 <div class="fixed"></div>   
	</div>
	<!--end con-->

	<!--footer-->
	
<div id="footer_wrap">
	<p>Copyright ©2014 飞凡教育，版权所有</p>
</div>


</body>

</html>
