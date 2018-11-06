<%-- 
    Document   : header
    Created on : Oct 15, 2018, 4:31:15 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${name != null}">
      Welcome, ${name}!
</c:if>

<a href="#" onclick="signOut();">Sign out</a>
<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>
       
<h1 id="title" class="special_text"><span>Course</span>Connections</h1>
<nav id="navbar">
    <ul class="special_text">
        <li><a href="menu.jsp">Home</a></li>
        <li><a href="#">Chat</a></li>
        <li><a href="#">Courses</a></li>
        <li><a href="account.jsp">Account</a></li>
    </ul>
</nav>