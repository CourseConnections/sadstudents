<%-- 
    Document   : login
    Created on : Oct 15, 2018, 4:29:45 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="head.jsp"%>
    </head>
 
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <main>
            <head>
    <meta name="google-signin-client_id" content="1072198103357-r45ic3ng7erq39ojcub38dupd9sfma2d.apps.googleusercontent.com">
      
  </head>
  <body>
    <div id="my-signin2"></div>
  <script>
    function onSuccess(googleUser) {
      console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
      console.log('Logged in as: ' + googleUser.getBasicProfile().getEmail());
    }
    function onFailure(error) {
      console.log(error);
    }
    function renderButton() {
      gapi.signin2.render('my-signin2', {
        'scope': 'profile email',
        'width': 240,
        'height': 50,
        'longtitle': true,
        'theme': 'dark',
        'onsuccess': onSuccess,
        'onfailure': onFailure
      });
    }
  </script>
  <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
         
  </body>
        </main>
        <footer>
            <jsp:include page="footer.jsp"/>
        </footer>
    
</html>
