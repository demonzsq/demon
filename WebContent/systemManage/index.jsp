<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/easyui/themes/icon.css" rel="stylesheet" />
<link href="css/style.css"" rel="stylesheet" />

<script src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/systemManage/js/main.js"></script>

</head>
<body class="easyui-layout">   
<!-- 顶部   -->
    <div data-options="region:'north',border:true" class="demon_head_bg">
    	<div class="head">
	    	<span style="float:right;" class="demon_head_right">
				<strong class="easyui-tooltip" style="float:right;" title="2条未读消息">admin，欢迎您！</strong><br>
				<a href="#" >网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|<a href="#">安全退出</a></p>
			</span>
	    	<h2>demon书籍 总后台管理系统</h2>
    	</div>
    </div>       
    <div data-options="region:'west',title:'菜单导航'" style="width:200px;">
		   	 <div id="menu" class="easyui-accordion" data-options="fit:true,border:false"></div>
		   	 </div>   
<!-- 中间  -->
    <div data-options="region:'center'" style="padding:5px;background:#eee;">
    		  <div id="tab" class="easyui-tabs" data-options="border:false,fit:true"> 
   				<div title="首页" style="padding: 20px; display: none;">
   					<h1>你好！管理员</h1>
   				</div>
	</div>
    <div data-options="region:'south'" class="demon_below_bq">
    	<h4 align="center">demon    copyrighted</h4>
    mlp</div> 
    
</body> 
</html>