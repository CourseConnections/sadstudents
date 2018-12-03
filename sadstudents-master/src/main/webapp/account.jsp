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
        <title>Account</title>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <main>
            <img src="images/instagram_profile_image.png" alt="logo" id="logo">
            <h1 id="welcome_text" class="special_text">Hello, ${name}!</h1>
            <div class="content_col_left">
                <h2 class="special_text col_header">Courses</h2>
                <ul id="courses" class="special_text">
                    <c:forEach items="${courses}" var="course">
                        <li>${course.getTitle()}</li>
                    </c:forEach>
                        <li><a href="courses.jsp" >Edit Courses</a></li>
                </ul>
            </div>
            <div class="content_col_right">
                <h2 class="special_text col_header">Account Settings</h2>
                <ul id="settings" class="special_text">
                    <li>Friends</li>
                    <li>Ignore List</li>
                    <li>Manage Courses</li>
                    <li>Change Username</li>
                    <li>Sign out</li>
                </ul>
            </div>
            <br sytle="clear: both;">
        </main>
        <footer>           
           <jsp:include page="footer.jsp"/>
        </footer>
        
    </body>
</html>
