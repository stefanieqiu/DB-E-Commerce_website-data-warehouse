<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.product.*" %>
<%@ page import="com.Cproduct.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("CProduct");
String username = (String)session.getAttribute("username");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Shopping Cart</title>
    
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
                                <td>
                                    Order Information
  				</td>
  			</tr>
                        
                         <tr>
                                <td>
                                    <HR width="200" color=#987cb9 SIZE="5" /><!-- color颜色，size大小 -->
                                                
                                </td>
                        </tr>
  			<tr>
  				<td >
                                    <form name="form1" action="" method="post">
<!--                                    <table border=1 width=100%>-->
                                        <tr align="center">
<!--   				<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>-->
                                            <td width=30%>Proname</td>
                                            <td width=30%>Proprice</td>
                                            <td>proInv</td>
                                             <td>proKind</td>
                                             <td>Amount</td>
                                            <td>Price</td>
                                            
                                        </tr>
                                       
   			<%
                            int sum=0;
   			if(list!=null && !list.isEmpty()){
                                
   				for(Map<String,Object> map :list){%>
   			
                                    <tr align="center">
<!--   				<td width=10%><input type="checkbox" name="ids" value="<%=map.get("proid") %>"/></td>-->
                                        <td width=30%><%=map.get("proname") %></td>
                                        <td width=30%><%=map.get("proprice") %></td>
                                        <td><%=map.get("proinv") %></td>
                                        <td><%=map.get("prokind") %></td>
                                        <td><%=map.get("number") %></td>
                                        <td><input type = "hidden" name = "proname" value = "<%=map.get("proname")%>"readonly/></td>
                                        <td><input type = "hidden" name = "proquantity" value = "<%=map.get("number")%>"readonly/></td>
                                        <td><input type = "hidden" name = "proinv" value = "<%=map.get("proinv")%>" readonly/></td>
                                        
                                <%
                                  Object a=map.get("proprice");
                                  int c = Integer.parseInt(a==null?"":a.toString());
                                  Object b = map.get("number");
                                  int d = Integer.parseInt(b==null?"":b.toString());
                                  int t = 0;
                                  
                                  t =c*d;
                                  out.print("<td>"+t+"</td>");
                                  sum += t;
                                %>

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
                        
                
                                <!--</table>--> 
                                <tr>
                                    <td><input width = 10% type = "hidden" name = "username" value = "<%=username%>"readonly/></td>
                                </tr>
                                <tr>
                                    <br>
                                    <br>
                                    <td>Total Price:</td>
                                   
                                </tr>   
                                <tr>
                                     <td width = 15%><input type = "text" name = "sum" value = "<%out.print(sum);%>"readonly/></td>
                                </tr>
<!--                                 out.print("<td>"+sum+"</td>");-->
                                <tr>
                                    <td>
                                         <tr>
                                            <td align="center">
                                            <button type="button" onclick="finishOrder();">pay</button>
                                            <button type="button" onclick="javascript:history.go(-1)">back</button>
                                            </td>
                                        </tr>
                             
                                    </td>
                                </tr>
                            </form>
                        </td>
                   </tr>

  		
  		</table>
  		
  	
  </div>
  </body>
</html>

<script type="text/javascript">
    function finishOrder(){
        var th = document.form1;
        th.action="<%=path%>/servlet/OrderAction?action_flag=add";
        th.submit();
    }
</script>