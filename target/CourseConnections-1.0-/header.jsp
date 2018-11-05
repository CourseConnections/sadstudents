<%-- 
    Document   : header
    Created on : Oct 15, 2018, 4:31:15 PM
    Author     : soup
--%>
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    
<div id="login_register" class="special_text">
<a href="login.jsp">Login</a> | <a href="index.jsp">Register</a>    
</div>
    <h2>Welcome</h2>
<div id="signout">
<a href="#" onclick="signOut();">Sign out</a>
    <script>
    function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
    auth2.disconnect();
  }
</script>
</div>
<h1 id="title" class="special_text"><span>Course</span>Connections</h1>
<nav id="navbar">
    <ul class="special_text">
        <li><a href="index.jsp">Home</a></li>
        <li><a href="index.jsp">Chat</a></li>
        <li><a href="index.jsp">Courses</a></li>
        <li><a href="account.jsp">Account</a></li>
    </ul>
</nav>