<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.product.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String username = (String)session.getAttribute("username");
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listProduct");

DividePage dividePage = (DividePage) request.getAttribute("dividePage");

String productName = (String) request.getAttribute("productName");
if(list==null){
	ProductService service = new ProductDao();
	int totalRecord = service.getItemCount("");
	dividePage = new DividePage(20,totalRecord,1);
	int start = dividePage.fromIndex();
	int end = dividePage.toIndex();
	list = service.listProduct("", start, end);
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>rank product</title>
    
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
   <div>
   <table width=60% align="center">
   <tr>
   		<td align="left"><font size=2>welcome，<%=username%><br><a href="javascript:logout();">logout</a></font></td>
   </tr>
   	<tr>
   		<td align="center">
   		<form name = "form2" action="" method="post">
   		<table>
   			<tr>
   				<td colspan="2">search product</td>
   				
   			</tr>
   			<tr>
   				<td >product kind</td>
   				<td ><input type="text" name="proname" value="<%= productName!=null?productName:"" %>"/></td>
   				
   			</tr>
   			
   			<tr>
   				<td colspan="2" align="center">
   					<button type="button" onclick="searchProduct()" >serach</button>
                                        
   					   					
   					
   				</td>   				
   			</tr>   		
   		</table>  	
   		</form>	
   			
   		</td>
   	</tr>
   	
   	<tr>
   		<td height=50> </td>
   	</tr>
   	<tr>
   		<td> result</td>
   	</tr>
   	
   	<tr>
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=100%>
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>
   				<td width=30%>proname</td>
   				<td width=30%>proprice</td>
   				<td>proinv</td>
                                <td>prokind</td>
   			
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<td width=10%><input type="checkbox" name="ids" value="<%=map.get("proid") %>"/></td>
   				<td width=30%><%=map.get("proname") %></td>
   				<td width=30%><%=map.get("proprice") %></td>
   				<td><%=map.get("proinv") %></td>
                                <td><%=map.get("prokind") %></td>
   				
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="" /></td>
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
   	
   	<tr>
   		<td>
   			
   			<button type="button" onclick="javascript:view();" >view</button>
                        <button type="button" onclick="javascript:addToCart();" >addtocart</button>
   		
   		</td>
   	</tr>
   	
   	
   			
   
   
   
   
   </table>
   
   
   
   </div>
   
   
  </body>
</html>

<script type="text/javascript">
        function searchProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=search";
		th.submit();
	}
    
        function selectAll(flag){
		
		var ids = document.getElementsByName("ids");
		for(var i = 0 ; i < ids.length ; i++){
			ids[i].checked = flag;
		}
	
	}
        
        function getSelectedCount(){
	
		var ids = document.getElementsByName("ids");
		var count = 0;
		for(var i = 0 ; i < ids.length ; i++)
		{
						
			ids[i].checked==true?count++:0;					
		}
		return count;
	
	}
        function getSelectedValue(){
		var ids = document.getElementsByName("ids");
		
		for(var i = 0 ; i < ids.length ; i++)
		{
						
			if(ids[i].checked){
				return ids[i].value;
			}				
		}
		
	}
	
	function view(){
	
		if(getSelectedCount()<1){
			
			alert("please choose one item at least");
			return;
		
		}else if(getSelectedCount()>1){
			alert("please choose one item at most");
			return;
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=view&proid="+getSelectedValue();
		th.submit();		
	
	}
        
        function addToCart(){
	
		if(getSelectedCount()<1){
			
			alert("please choose one item at least");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=addtocart&proid="+getSelectedValue();
		th.submit();		
	
	}
        
        
</script>
