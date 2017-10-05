<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Register</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function dosubmit(){
	
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
        if(th.name.value==""){
		alert("Name can not be empty!");
		th.name.focus();
		return;
	}
        var x=th.email.value;
        var atpos=x.indexOf("@");
        var dotpos=x.lastIndexOf(".");
        if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length){
            alert("please enter vaild e-mail form!");
            return ;
        }
        
        
        
        var phone = th.phone.value;
         if(!phone.match(/^\d{10}/)) {
            alert("wrong phone number.");  
        return ; 
         } 
	th.action="<%=path%>/servlet/RegisterAction";
        th.submit();

}
function back(){
 	alert("Back to home page");
	th = document.form1;
	th.acton="<%=path%>/index.jsp";
	th.submit;
}

</script>

  </head>
  
  <body>
    <div style="text-align:center">   
   <form action="" name="form1" method="post">
   <table  style="margin:auto">   
   <tr>   		
   		<td colspan="3">
   		User register
   		</td>   		
   	</tr>
   	<tr>
   		<td>Username:</td>
   		<td><input type="text" name="username"></input></td>  
   		<td>Must fill this blank.</td> 		
   	</tr>
   	<tr>
   		<td>Password:</td>
   		<td><input type="password" name="pswd"></input></td>  
   		<td>Must fill this blank.</td>  		
   		
   	</tr>
        <tr>
   		<td>Your name:</td>
   		<td><input type="text" name="name"></input></td>  
                <td>Must fill this blank.</td>                 
   	</tr>
        <tr>
   		<td>Email Address:</td>
   		<td><input type="text" name="email"></input></td>  
   	</tr>
        <tr>
   		<td>phone:</td>
   		<td><input type="text" name="phone"></input></td>  
   	</tr>
        <tr>
   		<td>Street Address:</td>
   		<td><input type="text" name="street"></input></td>  
   	</tr>
         <tr>
   		<td>City:</td>
   		<td><input type="text" name="city"></input></td>  
   	</tr>
         <tr>
   		<td>State:</td>
   		<td><input type="text" name="state"></input></td>  
   	</tr>
         <tr>
   		<td>Zip code:</td>
   		<td><input type="text" name="zip"></input></td>  
   	</tr>
   	<tr>
   		
   		<td colspan="3" align="center">
   		<button type="button" name="" onclick="dosubmit()" >Submit</button>
   		<button type="button" name="" value="" onclick="javascript:location.href='index.jsp'" >Back</button>
   		</td>   		
   	</tr>   
   
   </table>
   </form>
  
  </div>
 
  </body>
</html>