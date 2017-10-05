<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.salesman.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listSales");
DividePage dividePage = (DividePage) request.getAttribute("dividePage");
String productName = (String) request.getAttribute("productName");
if(list==null){
	SalesService service = new SalesDao();
	int totalRecord = service.getItemCount("");
	dividePage = new DividePage(20,totalRecord,1);
	int start = dividePage.fromIndex();
	int end = dividePage.toIndex();
	list = service.listSales("", start, end);
}
	
%>

<html>
   <head>
    <base href="<%=basePath%>">
    
    <title>salesman</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    </head>
    <body>
        <tr>
   		<td> RESULT</td>
   	</tr>
   	
   	<tr>
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=90% align = center>
   			<tr align="center">
   				<!--<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>-->
   				<td width=30%>NAME</td>
   				<td width=30%>EMAIL</td>
                                <td width=30%>TITLE</td>
                                 <td width=30%>STORE NUMBER</td>
   				<td>SALARY</td>
   			
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<!--<td width=10%><input type="checkbox" name="ids" value="<%=map.get("email") %>"/></td>-->
   				<td width=30%><%=map.get("name") %></td>
                                <td width=30%><%=map.get("email") %></td>
   				<td width=30%><%=map.get("title") %></td>
                                <td width=30%><%=map.get("sid") %></td>
   				<td><%=map.get("salary") %></td>
   				
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
   	
<!--   	<tr>
   		<td colspan="4" align="center">
   			<%=dividePage.getPageCount()  %> pages
   			<a href="javascript:first();">First page</a>   
   			<a href="javascript:forward();">Previous page</a> 
   			<a href="javascript:next();">Next page</a> 
   			<a href="javascript:end();">Last page</a> 
   			To<select name="select" onchange="changePage(this.value)">
   			
   			<%
   			int pageCount = dividePage.getPageCount();
   			if(pageCount>0){
   			for(int i = 1 ; i<=pageCount;i++){%>
   			
   			<option value="<%=i %>" <%= (i==dividePage.getCurrentPage()?"selected":"")%>>  <%=i %>
   			</option>
   			
   			<%			
   			}
   			}else{
   				%>
   				<option value="1">1</option>   
   			 <%}			
   			
   			%>
   					
   			</select>
   		
   		</td>
   	</tr>-->
        <tr>
   		<td>
   			
   			<button type="button" onclick="javascript:history.go(-1)">Back</button>
   		
   		</td>
   	</tr>
    </body>
</html>
