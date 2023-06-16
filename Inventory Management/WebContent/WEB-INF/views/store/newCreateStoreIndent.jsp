<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,main.models.storeModels.*" %>
<!DOCTYPE html>
<html>
<head>
<style>
        table {
            width: auto;
            border-collapse: collapse;
            margin-bottom: 20px;
            margin-left: auto;
            margin-right: auto;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

    </style>
</head>
<body>
	
<%-- 	<% StoreIndentsList sl = (StoreIndentsList)request.getAttribute("data"); %>
	<%List<StoreIndentProductsList> list = sl.getProductsList(); %>
 	<table>
		<tr>
			
			<th>product id</th>
			<th>Quantity</th>
		
		</tr>
		
 		<% for(StoreIndentProductsList p:list){
			%>
			<tr>
			
				<td><%=p.getProdId() %></td>
				<td><%=p.getQuantity() %></td>
			
			</tr>
		<% }%>
	</table>
 --%>
</body>
</html>
