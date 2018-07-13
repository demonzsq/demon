<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>飞凡网上书店</title>
    




<link href="../css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript">


  function register(){

	  var uname=document.getElementById("user_name").value;
	  var zmnumReg=/^[0-9a-zA-Z]{6,10}$/; 
	  if(uname!="" && !zmnumReg.test(uname)){
		  document.getElementById("span1").innerHTML="亲，用户名只能是6~10的数字或者字母哦";
		  return false;
	  }else{
		  document.getElementById("span1").innerHTML="";
		  return true;
		  }
	  
  }
  
  function register1(){
	  var upwd=document.getElementById("user_pwd").value;
	  var zmnumReg=/^[0-9a-zA-Z]{6,10}$/; 
	  if(upwd =="" || !zmnumReg.test(upwd)){
		  document.getElementById("span2").innerHTML="亲，密码由6~10的数字或者字母组成哦";
			return false; 
	  }else{
		  document.getElementById("span2").innerHTML="";
		  return true;
	  }
  }
  
  function register2(){
	  var a=register();
	  var b=register1();
	if(a==true&&b==true){
		return true;
	}else{
		return false;
	}

   }
  
 
  
   </script>
  </head>
  
  <body>
   
<jsp:include page="homeScerch.jsp"></jsp:include> 
<!--end nav-->
	<div class="logoin_wrap mt30">
		<h2>用户登陆</h2>
		<div id="table">
			<form id="form1" action="lonReAction.action?methodName=userLogin" onsubmit="return register2()" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr><td height="30px"></td></tr>
			</table>
			<table width="100%" height="35" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="44%" align="right" valign="middle" class="f14">用户名：</td>
					<td width="56%" align="left" valign="middle">
					<input type="text" name="user_name" value="" onblur="register()" id="user_name" class="input w180"><span style="color: red;" id="span1"></span>
					</td>
				</tr>
			</table>
			<table width="100%" height="35" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="44%" align="right" valign="middle" class="f14">密&nbsp;&nbsp;码：</td>
					<td width="56%" align="left" valign="middle">
					<input type="password" name="user_pwd" value="" id="user_pwd" class="input w180" onblur="register1()"><span style="color: red;" id="span2"></span>
					</td>
				</tr>
			</table>
			
			<table width="100%" height="35" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="44%" align="right" valign="middle" class="f14"></td>
					<td width="56%" align="left" valign="middle">
						<!-- 图片按钮，与type=submit按钮的效果是一样，也可以用来提交表单 -->
						<input type="image"  src="../images/admin_dl.png" />
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr><td>&nbsp;</td></tr>
			</table>
			</form>
	    </div>
	</div>
	
	
<div id="footer_wrap">
	<p>Copyright ©2014 飞凡教育，版权所有</p>
</div>

	<!--end footer-->
</body>
</html>