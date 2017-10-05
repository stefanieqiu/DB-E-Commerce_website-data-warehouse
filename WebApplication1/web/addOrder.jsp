<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<base href="<%=basePath%>">
	
	<title>Add order</title>
	
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
	th.action="<%= path%>/servlet/OrderAction?action_flag=add";
	th.submit();

}

</script>
  </head>
  
  <body>
	<div align="center">
	
		<table width=70% style="margin:auto;">
			<tr><td align="center" height=150 valign="bottom">New order info</td></tr>
			<tr>
				<td>
					<form id="form1" name="form1" action="" method="post" enctype="multipart/form-data">
					<table border=1 style="margin:auto">
						<tr >
							<td>username</td>
							<td><input type="text" name="username" id="username"/></td>
							<td>order total</td>
							<td><input type="text" name="total" id="total"/></td>
                                                        <td>order time</td>
							<td><input type="text" name="time" id="time"/></td>
						</tr>
						
					</table> 
					</form>   				
				
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<button type="button" onclick="javascript:dosubmit();">Submit</button>
					<button type="button" onclick="javascript:location.href='main.jsp'">Back</button>
				
				</td>
			</tr>
			
		
		</table>
		
	</div>
  </body>
</html>
