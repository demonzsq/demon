function showCheck(a){
	var c = document.getElementById("myCanvas");
  var ctx = c.getContext("2d");
	ctx.clearRect(0,0,1000,1000);
	ctx.font = "80px 'Microsoft Yahei'";
	ctx.fillText(a,0,100);
	ctx.fillStyle = "white";
}
var code ;    

function createCode(){       
    code = "";      
    var codeLength = 4;
    var selectChar = new Array(1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');      
    for(var i=0;i<codeLength;i++) {
       var charIndex = Math.floor(Math.random()*60);      
      code +=selectChar[charIndex];
    }      
    if(code.length != codeLength){      
      createCode();      
    }
/*    $.ajax({
  	   url:"../loginser",
  	   type:"post",
  	   data:{un:code},
  		success:function(data){
  				
  		}  	  
    });*/
    showCheck(code);
}
         
function validate(){
	var f=false;
    var inputCode = document.getElementById("J_codetext").value.toUpperCase(); //得到输入的验证码转为大写
    var codeToUp=code.toUpperCase();
    if(inputCode.length <=0) {
      document.getElementById("J_codetext").setAttribute("placeholder","输入验证码");
      createCode();
     return false;
      
    }
    else if(inputCode != codeToUp ){
      document.getElementById("J_codetext").value="";
      document.getElementById("J_codetext").setAttribute("placeholder","验证码错误");
      createCode();
      return false;
    }
    else {
      window.open(document.getElementById("J_down").getAttribute("data-link"));
      document.getElementById("J_codetext").value="";
      createCode();
      return true;
    }
}

function cl(){
	var account =  $("#adminuser").val();
	  var pwd = $("#adminpwd").val();
	  //code验证码值
	if(validate() && account!=null && pwd!=null && account!="" && pwd!=""){
		return true;
	}else if(account==null || pwd==null || account=="" || pwd==""){
		alert("账号、密码不能为空");
		document.getElementById("adminpwd").value="";
		return false;
	}else{
		alert("验证码错误");
		document.getElementById("adminpwd").value="";
		return false;
	}
}