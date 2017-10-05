<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
	<base href="<%=basePath%>">
	
	
	<title>Welcome</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">	
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	


<script type="text/javascript">
function login(){
	var th = document.form1;
	if(th.username.value==""){
		alert("Username can not be empty!");
		th.username.focus();
		return;
	}
	if(th.pswd.value==""){
		alert("Password can not be empty!");
		th.pswd.focus();
		return;
	}
	
	th.action = "<%=path%>/servlet/LoginAction";
	th.submit();


}
function Clogin(){
	var th1 = document.form1;
	if(th1.username.value==""){
		alert("Username can not be empty!");
		th1.username.focus();
		return;
	}
	if(th1.pswd.value==""){
		alert("Password can not be empty!");
		th1.pswd.focus();
		return;
	}
	
	th1.action = "<%=path%>/servlet/CLoginAction";
	th1.submit();


}
</script>
	
  </head>
  
  <body>
  
   <div style="text-align:center">   
   <form name="form1" action="" method="post">
   <table  style="margin:auto">   
   <tr>   		
   		<td colspan="2">
   		Welcome
   		</td>   		
   	</tr>
   	<tr>
   		<td>Username:</td>
   		<td><input type="text" name="username"></input></td>   		
   	</tr>
   	<tr>
   		<td>Password:</td>
   		<td><input type="password" name="pswd"></input></td>   		
   	</tr>
   		<tr>
   		
   		<td colspan="2" align="center">
   		<button type="button" name="" value="" onclick="Clogin()">UserLogin</button>
                <button type="button" name="" value="" onclick="login()">AdminLogin</button>
   		<button type="button" name="" value="" onclick="javascript:location.href='register.jsp'">Register</button>
   		</td>   		
   	</tr>   
   
   </table>
   </form>
  </div>
 
   
  </body>
</html>
