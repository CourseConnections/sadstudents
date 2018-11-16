<%-- 
    Document   : register
    Created on : Nov 13, 2018, 4:27:36 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <%@include file="head.jsp"%>
    </head>
    <body>
        
        <h1 id="index_title" class="special_text"><span>Course</span>Connections</h1>
        
        <div class="center_wrapper">
                 <div id="login_div">
                <p class="special_text">Welcome, new user! Please fill out the information below to create a new account, so you can get connected!</p>
                <form method="post" action="account?action=register">
                    <label for="name" id="for_name" class="special_text">Enter your first and last name: </label>
                    <input name="name" id="name" type="text"/>
                    <br>
                    <label for="username" id="for_username" class="special_text">Enter your desired username: </label>
                    <input name="username" id="username" type="text"/>
                    <br>
                    <label for="password" id="for_password" class="special_text">Enter a password: </label>
                    <input name="password" id="password" type="password"/>
                    <input type="submit" value="Submit" id="submit_button"/>
                </form>
                <br>
            </div>
        </div>
    </body>
</html>