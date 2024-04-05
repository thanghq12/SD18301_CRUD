<%-- 
    Document   : edit
    Created on : Apr 4, 2024, 1:05:43 PM
    Author     : hoangquangthang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>edit product!</h1>
         <form action="/gradleproject6/product/edit" method="POST">
             <input type="hidden" name="id" value="${product.id}"/>
             <input type="text" name="name" value="${product.name}"/><br>
            <input type="text" name="price" value="${product.price}"/>
            <input type="submit" name="send" value="Thêm"/>
        </form>    
    </body>
</html>
