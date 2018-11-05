<%-- 
    Document   : index
    Created on : Oct 1, 2018, 3:53:28 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Course Connections </title>            
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <main>            
            <p id="home">Course Connections is designed as a replacement for traditional, inferior communication mediums
               for college students. Students are able to enter the courses they are currently enrolled in and get
               connected with their classmates. Course Connections creates an environment that allows students to
               ask questions, keep up with due dates, and form relationships with fellow classmates. </p>
            <img src="images/transparent_logo.png" alt="home logo" id="home_logo"/>
        </main>
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
