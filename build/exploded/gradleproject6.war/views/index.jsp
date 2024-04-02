<%-- 
    Document   : index
    Created on : Mar 26, 2024, 1:46:27 PM
    Author     : hoangquangthang
--%>
<%@page import="java.util.List" %>
<%@page import="entity.Product" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table border="1">
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Price</td>
            </tr>
            <% for (Product product : (List<Product>) request.getAttribute("products")) {  %>
             <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getName() %></td>
                <td><%= product.getPrice() %></td>
            <% } %>    
            </tr>
        </table>
    </body>
</html>
