<%@ page contentType="text/html" language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
	<base href="<%=basePath%>">
	
	<title>Add customer</title>
	
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
	th.action="<%= path%>/servlet/CustomerAction?action_flag=add";
	th.submit();

}

</script>
  </head>
  
  <body>
	<div align="center">
	
		<table width=70% style="margin:auto;">
			<tr><td align="center" height=150 valign="bottom">New Customer info</td></tr>
			<tr>
				<td>
					<form id="form1" name="form1" action="" method="post" enctype="multipart/form-data">
					<table border=1 style="margin:auto">
						<tr >
						<td>Customer username</td>
						<td><input type="text" name="username" id="username"/></td>
						<td>password</td>
						<td><input type="password" name="pswd" id="pswd"></td>  
						</tr>
						<tr>
							<td>name</td>
							<td colspan="3"><input type="text" name="name" id="name"/></td>
						</tr>
                                                <tr>
							<td>email</td>
							<td colspan="3"><input type="text" name="email" id="email"/></td>
						</tr>
                                                <tr>
							<td>phone</td>
							<td colspan="3"><input type="text" name="phone" id="phone"/></td>
						</tr>
                                                <tr>
							<td>street</td>
							<td colspan="3"><input type="text" name="street" id="street"/></td>
						</tr>
                                                <tr>
							<td>city</td>
							<td colspan="3"><input type="text" name="city" id="city"/></td>
						</tr>
                                                <tr>
							<td>state</td>
							<td colspan="3"><input type="text" name="state" id="state"/></td>
						</tr>
                                                <tr>
							<td>zip</td>
							<td colspan="3"><input type="text" name="zip" id="zip"/></td>
						</tr>
					</table> 
					</form>   				
				
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button type="button" onclick="javascript:dosubmit();">Submit</button>
					<button type="button" onclick="javascript:location.href='changecus.jsp'">Back</button>
				
				</td>
			</tr>
			
		
		</table>
		
	</div>
  </body>
</html>
