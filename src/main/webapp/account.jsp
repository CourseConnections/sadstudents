<%-- 
    Document   : account
    Created on : Oct 29, 2018, 3:55:20 PM
    Author     : Kayla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <title>Account Page</title>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <main>
            <img src="images/instagram_profile_image.png" alt="logo" id="logo">
            <br>
            <br>
            <br>
            <h1 id="welcome_text" class="special_text">Hello, ${name}!</h1>
            <br style="clear:both">
            <div id="courses_column">
                <h2 class="special_text account_header">Courses</h2>
                <ul id="courses">
                    <c:forEach items="${courses}" var="course">
                        <li>${course.getTitle()}</li>
                    </c:forEach>
                </ul>
            </div>
            <div id="settings_column">
                <h2 class="special_text account_header">Account Settings</h2>
                <ul id="settings" class="special_text">
                    <li>Friends</li>
                    <li>Ignore List</li>
                    <li>Manage Courses</li>
                    <li>Change Username</li>
                    <li>Sign out</li>
                </ul>
            </div>
        </main>
        <footer>           
           <jsp:include page="footer.jsp"/>
        </footer>
        
    </body>
</html>
