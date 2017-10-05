<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> map = (Map<String,Object>)request.getAttribute("productMap");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>view product</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div align="center">
  		
  		<table width=60% style="margin:auto">
  			
  			<tr>
  				<td height=100>
  					
  				</td>
  			</tr>
  			<tr>
  				<td >
  					product information
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<table width = 99% border =1 >
	  					<tr align="center">
	  						<td width = 20%>product name</td>
	  						<td width = 30%><%=map.get("proname") %></td>
	  						<td width = 20%>product price</td>
	  						<td><%=map.get("proprice") %></td>
	  						
	  					
	  					</tr>
	  					<tr align="center">
	  						<td >inv</td>
	  						<td colspan=3><%=map.get("proinv") %></td>					
	  					</tr>
                                                <tr align="center">
	  						<td >kind</td>
	  						<td colspan=3><%=map.get("prokind") %></td>					
	  					</tr>
  					</table>
  				</td>
  			</tr>
  			<tr>
  				<td align="center">
  					<!--<button type="button" onclick="javascript:location.href='<%=path %>/main.jsp'">Submit</button>-->
  					<button type="button" onclick="javascript:history.go(-1)">Back</button>
  				</td>
  			</tr>
  		
  		</table>
  		
  	
  </div>
  </body>
</html>
