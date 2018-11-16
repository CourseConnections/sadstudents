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
            <h2 class="special_text">What is CourseConnections?</h2>
            <p>Course Connections is a free to use, multi-room, text chat application designed for college students
                as a replacement for traditional, inferior communication mediums (i.e. Piazza and Canvas Discussion
                boards). With Course Connections, a student would be able to enter what classes they are taking
                during a semester and be automatically joined with other students in the same course within their
                own chat board. This will give students a resource to ask questions, keep up with due dates, and form
                relationships with fellow classmates.</p>
            
            <h2 class="special_text">Why Do I Need CourseConnections?</h2>
            <p>With Course Connections, students will now be able to communicate and interact with other students
                that are the same courses. One of the main issues for college students is that they have inferior
                means of communication. The problem is that students would then have to find other like minded
                students and exchange contact information to try to communicate. Course Connections alleviates that
                issue by automatically matching students based on the classes they are currently taking with other 
                students. This also helps students in online classes, where communication is much harder to
                coordinate. Generally in online classes, students would have to figure out ways to communicate and
                exchange contact information. This process can be extremely challenging and tedious.</p>
            <img src="images/transparent_logo.png" alt="home logo" id="home_logo"/>
        </main>
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
