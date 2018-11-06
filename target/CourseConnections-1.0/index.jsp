<%-- 
    Document   : index
    Created on : Nov 5, 2018, 11:05:58 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp"%>
        <meta name="google-signin-client_id" content="1023685466675-e6trlgn455lrvi81f95g2478cg526e5f.apps.googleusercontent.com">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-scope" content="profile email">
        <title>Course Connections</title>
    </head>
    <body>
        
        <h1 id="index_title" class="special_text"><span>Course</span>Connections</h1>
        
        <div class="center_wrapper">
                 <div id="login_div">
                <p class="special_text">Welcome to Course Connections! Please click the button below to sign in.</p>
                <div id="login_register" class="g-signin2" data-onsuccess="onSignIn"></div>
            </div>
        </div>
        
        
        <script>
            //google callback. This function will redirect to our login servlet
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                console.log('ID: ' + profile.getId());
                console.log('Name: ' + profile.getName());
                console.log('Image URL: ' + profile.getImageUrl());
                console.log('Email: ' + profile.getEmail());
                console.log('id_token: ' + googleUser.getAuthResponse().id_token);
        
                //do not post all above info to the server because that is not secure.
                //just send the id_token
        
                var redirectUrl = 'account?action=login';
        
                //using jquery to post data dynamically
                var form = $('<form action="' + redirectUrl + '" method="post">' +
                        '<input type="hidden" name="id_token" value="' +
                        googleUser.getAuthResponse().id_token + '" />' +
                        '</form>');
                $('body').append(form);
                form.submit();
            }
        </script>
    </body>
</html>
