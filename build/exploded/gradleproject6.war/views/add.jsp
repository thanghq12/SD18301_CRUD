<%-- 
    Document   : add
    Created on : Apr 2, 2024, 12:42:26 PM
    Author     : hoangquangthang
--%>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> HIHI</h1>
        <form action="/gradleproject6/product/add" method="POST">
            <input type="text" name="name" /><br>
            <input type="text" name="price"/>
            <input type="submit" name="send" value="Thêm"/>
            <% List<String> errors = (List<String>) request.getAttribute("errors");
       if (errors != null && !errors.isEmpty()) {
           for (String error : errors) {
               %>
               <div class="error"><%= error %></div>
               <%
           }
       }
    %>
        </form>    
    </body>
</html>
