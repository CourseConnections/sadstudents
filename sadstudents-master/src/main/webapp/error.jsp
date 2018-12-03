<%-- 
    Document   : error
    Created on : Nov 6, 2018, 6:23:19 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
        <title>Error</title>
    </head>
    <body>
        
        <h1 id="index_title" class="special_text"><span>Course</span>Connections</h1>
        
        <div class="center_wrapper">
                 <div id="login_div">
                <p class="special_text">Whoops! Looks like you encountered an error:</p>
                <p id="error_msg">${errorMsg}</p>
                <p class="special_text">Please go back and try again, or return to the login page <a href="index.jsp">here</a>.</p>
            </div>
        </div>
    </body>
</html>
