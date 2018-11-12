<%-- 
    Document   : chat
    Created on : Nov 9, 2018, 9:54:28 AM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <title>Chat</title>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
        <main>
            <div id="chat_div">
                <iframe src="https://courseconnections.rocket.chat?layout=embedded" id="chat_frame"></iframe>
            </div>
        </main>
        <footer>           
            <jsp:include page="footer.jsp"/>
        </footer>
    </body>
</html>
