<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.product.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//获取 username;
String username = (String)session.getAttribute("username");
//获取从 servlet ProductActiion 中 传递的参数(数据库查询的结果)
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listProduct");
// 获取 分页对象
DividePage dividePage = (DividePage) request.getAttribute("dividePage");
// 获取查询的关键词
String productName = (String) request.getAttribute("productName");
if(list==null){
	//第一次进 Cmain.jsp页面，默认加载所有的产品
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
    
    <title>Customer shopping</title>
    
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
                <button type="button" name="" value="" onclick="javascript:location.href='orders.jsp'">view order</button>
   </tr>
   	<tr>
   		<td align="center">
   		<form name = "form2" action="" method="post">
   		<table>
   			<tr>
   				<td colspan="2">search product</td>
   				
   			</tr>
   			<tr>
   				<td >product name</td>
   				<td ><input type="text" name="proname" value="<%= productName!=null?productName:"" %>"/></td>
   				
   			</tr>
   			
   			<tr>
   				<td colspan="2" align="center">
   					<button type="button" onclick="searchProduct()" >search</button>
                                        <button type="button" onclick="rank()" >rank by price</button>	
   				</td>   				
   			</tr>
                        <tr>
                            <td colspan = "2" align = "center">
                                <button type = "button" onclick = "stProduct()">Strategy</button>
                                <button type = "button" onclick = "adProduct()">Adventure</button>
                                <button type = "button" onclick = "acProduct()">Action</button>
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
                                <td>number to buy</td>    
                                <td width=10%>update number<input type="text" name="number" </input></td>
   			
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
                                <td width=10%><%=map.get("number") %></td>
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
                        <button type="button" onclick="javascript:updateNumber();" >updatenumber</button>
   		
   		</td>
   	</tr>
   	
<!--   	<tr>
   		<td colspan="4" align="center">
   			all <%=dividePage.getPageCount()  %> page    
   			<a href="javascript:first();">first page</a>   
   			<a href="javascript:forward();">previous page</a> 
   			<a href="javascript:next();">next page</a> 
   			<a href="javascript:end();">last page</a> 
   			skip to<select name="select" onchange="changePage(this.value)">
   			
   			<%
   			int pageCount = dividePage.getPageCount();
   			if(pageCount>0){
   			for(int i = 1 ; i<=pageCount;i++){%>
   			
   			<option value="<%=i %>" <%= (i==dividePage.getCurrentPage()?"selected":"")%>>  <%=i %>
   			</option>
   			
   			<%			
   			}
   			
   			}else{// 无记录
   				%>
   				<option value="1">1</option>   
   			 <%}			
   			
   			%>
   					
   			</select>
   		
   		</td>
   	</tr>-->
   			
   
   
   
   
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
        function acProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/ProductAction?action_flag=acsearch";
		th.submit();
	}
	
        function adProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/ProductAction?action_flag=adsearch";
		th.submit();
	}
        function stProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/ProductAction?action_flag=stsearch";
		th.submit();
	}
	
	function first(){
		
		window.location.href = "<%=path%>/servlet/CProductAction?action_flag=list&pageNum=1";
		
	}
	function next(){
		
		window.location.href = "<%=path%>/servlet/CProductAction?action_flag=list&pageNum=<%=dividePage.getCurrentPage()+1%>";		
	
	}
	function forward(){
		
		window.location.href = "<%=path%>/servlet/CProductAction?action_flag=list&pageNum=<%=dividePage.getCurrentPage()-1%>";
		
	}
	function end(){
		
		window.location.href = "<%=path%>/servlet/CProductAction?action_flag=list&pageNum=<%=dividePage.getPageCount() %>";
			
	}
	
	function changePage(currentPage){
	
		window.location.href = "<%=path%>/servlet/CProductAction?action_flag=list&pageNum="+currentPage;
	
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
			alert("please choose only one item");
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
        
         function updateNumber(){
	
		if(getSelectedCount()<1){
			
			alert("please choose one item at least");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/CProductAction?action_flag=update&proid="+getSelectedValue();
		th.submit();		
	
	}
        
        function rank(){
            
            var th = document.form2;
		th.action="<%=path%>/servlet/CProductAction?action_flag=rank";
		th.submit();
	}
	
	
        
        function logout(){
	
	window.location.href="<%=path %>/servlet/LogoutAction?action_flag=logout";
		
	}
</script>