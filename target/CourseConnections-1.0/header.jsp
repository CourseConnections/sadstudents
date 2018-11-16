<%-- 
    Document   : header
    Created on : Oct 15, 2018, 4:31:15 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${name != null}">
    <a id="signout" class="special_text" href="account?action=logout" onclick="signOut();">Sign out</a>
</c:if>

<h1 id="title" class="special_text"><span>Course</span>Connections</h1>
<nav id="navbar">
    <ul class="special_text">
        <li><a href="home.jsp">Home</a></li>
        <li><a href="chat.jsp">Chat</a></li>
        <li><a href="courses.jsp">Courses</a></li>
        <li><a href="account.jsp">Account</a></li>
    </ul>
</nav>