<%-- 
    Document   : index
    Created on : Nov 5, 2018, 11:05:58 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Course Connections</title>
        <meta name="google-signin-client_id" content="1023685466675-e6trlgn455lrvi81f95g2478cg526e5f.apps.googleusercontent.com">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-scope" content="profile email">
        <%@include file="head.jsp"%>
    </head>
    <body>
        
        <h1 id="index_title" class="special_text"><span>Course</span>Connections</h1>
        
        <div class="center_wrapper">
                 <div id="login_div">
                <p class="special_text">Welcome to Course Connections! Please click the button below to sign in.</p>
                <div id="login_register" class="g-signin2" data-onsuccess="onSignIn"></div>
            </div>
        </div>
    </body>
</html>
