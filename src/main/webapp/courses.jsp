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
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css">
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.jquery.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.css">
    </head>
    <body>
        <header>
            <%@include file="header.jsp"%>
        </header>
        <main>
            <h1 class="special_text page_header">What courses are you taking?</h1>
            <div class="content_col_left">
                <h2 class="special_text col_header">Add Courses</h2>
                <div class="ui-widget">
                    <form id="course_add_form" action="course" method="get">
                        <input type="hidden" name="action" value="add"/>
                        <label>Choose a course from this list: </label>
                        <select class="chzn-select" name="selected_course" form="course_add_form">
                            <c:forEach items="${courseList}" var="course">
                                <option value="${course.getCRN()}">${course.getAbbr()}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $(function() {
                                $(".chzn-select").chosen();
                            });
                        </script>
                        <input type="submit" value="Add Course"/>
                    </form>
                </div>
            </div>
            <div class="content_col_right">
                <h2 class="special_text col_header">Remove Courses</h2>
                <div class="ui-widget">
                    <form id="course_remove_form" action="course" method="get">
                        <input type="hidden" name="action" value="remove"/>
                        <label>Choose a course from this list: </label>
                        <select class="chzn-select" name="selected_course" form="course_remove_form">
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.getCRN()}">${course.getAbbr()}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $(function() {
                                $(".chzn-select").chosen();
                            });
                        </script>
                        <input type="submit" value="Remove Course"/>
                    </form>
                </div>
            </div>
        </main>
        <footer>           
            <%@include file="footer.jsp"%>
        </footer>
            
    </body>
</html>