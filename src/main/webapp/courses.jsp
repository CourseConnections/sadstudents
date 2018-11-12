<%-- 
    Document   : courses
    Created on : Nov 8, 2018, 1:26:06 PM
    Author     : soup
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <title>Courses</title>
        <%@include file="head.jsp"%>
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
        <main>
            <h1 class="special_text page_header">What courses are you taking?</h1>
            <div class="content_col_left">
                <h2 class="special_text col_header">Your Courses</h2>
                <ul id="courses">
                    <c:forEach items="${courses}" var="course">
                        <li>${course.getTitle()}</li>
                        </c:forEach>
                    <li><button onclick="return addCourse()">Join ITCS 4155</button></li>
                    
                    <script>
                        function addCourse() {
                            
                            alert("Clicked!");
                            
                            $.ajax({
                                type: "GET",
                                url: "https://courseconnections.rocket.chat/api/v1/commands.run",
                                data: '{"command":"invite","roomId":"sXBYJjvCpJ3EtGmAY","params":"@jamie.gachie"}',
                                headers: '{"X-Auth-Token":"bUj7z12R_NZRFdYGrolW10IMsm-4Bot8D-DXM36ZkSp","X-User-ID":"initialuser","Content-type":"application/json"}',
                                contentType: "application/json",
                                beforeSend: function(request) {
                                    request.setRequestHeader("X-Auth-Token", "bUj7z12R_NZRFdYGrolW10IMsm-4Bot8D-DXM36ZkSp");
                                    request.setRequestHeader("X-User-ID", "initialuser");
                                    request.setRequestHeader("Content-type", "application/json");
                                },
                                success: function(data) {
                                    console.log(data);
                                    //do something when request is successfull
                                },
                                processData: false,
                                dataType: "json"
                            });
                        }
                    </script>
                </ul>
            </div>
            <div class="content_col_right">
                <h2 class="special_text col_header">All Courses</h2>
                <ul id="course_list">
                    <c:forEach items="${courseList}" var="course">
                        <li>${course.getTitle()}
                        </c:forEach>
                </ul>
            </div>
        </main>
        <footer>           
            <%@include file="footer.jsp"%>
        </footer>
        
    </body>
</html>