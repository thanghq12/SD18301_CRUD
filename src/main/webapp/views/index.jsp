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
                 <td>Action</td>
            </tr>
            <% for (Product product : (List<Product>) request.getAttribute("products")) {  %>
             <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getName() %></td>
                <td><%= product.getPrice() %></td>
                <td><a href="/gradleproject6/product/edit?id=<%= product.getId() %>">Sửa</a>
                    <button onclick="confirmDelete(<%= product.getId() %>)">Xóa</button>
                </td>
            <% } %>    
            </tr>
        </table>
    </body>
</html>
<script>
    function confirmDelete(productId) {
        if(confirm("Bạn có muốn xóa sản phẩm này không ?" )) {
            
            window.location.href = "/gradleproject6/product/delete?id="+productId;
        }
    }
</script>