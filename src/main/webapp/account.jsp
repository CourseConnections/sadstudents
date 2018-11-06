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
        <title>Account Page</title>
        <%@include file="head.jsp"%>  
    </head>
    <body>
        <header>
            <jsp:include page="header.jsp"/>
        </header>
        <main id="profile2">              
            <img src="images/instagram_profile_image.png" alt="logo" id="logo">
            <br style="clear:both">
            <div id="profile">
              <h5>Name:</h5> ${name}
              <h5>Username: </h5>
              <h5>Email:</h5> ${email}             
            </div>                    
        </main>
        <footer>           
           <jsp:include page="footer.jsp"/>
        </footer>
        
    </body>
</html>
