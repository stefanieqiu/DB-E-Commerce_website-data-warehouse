<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="com.util.*" %>
<%@ page import="com.Order.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String username = (String)session.getAttribute("username");
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listOrder");

DividePage dividePage = (DividePage) request.getAttribute("dividePage");
if(list==null){
	OrderService service = new OrderDao();
	int totalRecord = service.getItemCount("");
	dividePage = new DividePage(5,totalRecord,1);
	int start = dividePage.fromIndex();
	int end = dividePage.toIndex();
	list = service.mosttotalOrder(username);
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>view most</title>
    
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
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=90%>
   			<tr align="center">
   				<!--<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>-->
   				<td width=30%>NAME</td>
                                <!--<td width=30%>TITLE</td>-->
                                 <!--<td width=30%>STORE NUMBER</td>-->
   				<!--<td>SALARY</td>-->
   			
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<!--<td width=10%><input type="checkbox" name="ids" value="<%=map.get("username") %>"/></td>-->
   				<td width=30%><%=map.get("username") %></td>
                                <!--<td width=30%><%=map.get("email") %></td>-->
   				<!--<td width=30%><%=map.get("title") %></td>-->
                                <!--<td width=30%><%=map.get("sid") %></td>-->
   				<!--<td><%=map.get("salary") %></td>-->
   				
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<!--<td width=10%><input type="checkbox" name="" /></td>-->
   				<td width=30%></td>
   				<td width=30%></td>
                                <td width=30%></td>
                                <td width=30%></td>
                                <td width=30%></td>
   				<td></td>
   			
   			</tr><%
   			
   			}   			
   			 %>
   			
   	
   			
   		
   		</table>   		
   		</form>
   		</td>
   	
   	</tr>
  		
  		</table>
  		
  	
  </div>
  </body>
</html>
