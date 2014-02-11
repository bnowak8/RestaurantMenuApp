<%-- 
    Document   : RestarauntMenu
    Created on : Feb 8, 2014, 4:27:12 PM
    Author     : mashit
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Restaraunt Menu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1 id="mainTitle">Food Stuff</h1>
            </div>

            <div id="contentLabel">
                <h3 id="menuTitle">&nbsp;&nbsp;&nbsp;Menu</h3>
                
            </div>
            <div id="content">
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Item Name</th>
                        <th>Item Price</th>
                    </tr>
                    
                    
                    <c:forEach items="${menuList}" var="menuItem">
                        <tr>
                            <td><c:out value="${menuItem.id}"/></td>
                            <td><c:out value="${menuItem.name}"/></td>
                            <td><c:out value="${menuItem.price}"/></td>
                        </tr>
                    </c:forEach> 
                </table>
            </div>

            <div id="footer">

            </div>
        </div>
    </body>
</html>
