<%-- 
    Document   : courses
    Created on : Nov 8, 2018, 1:26:06 PM
    Author     : soup
--%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page import="Beans.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
                <h2 class="special_text col_header">Add Course</h2>
                <div class="ui-widget">
                    <form id="course_add_form" action="course" method="get">
                        <input type="hidden" name="action" value="add"/>
                        <label>Choose a course from this list: </label>
                        <select class="chzn-select select_course" id="add_course" size="8" name="selected_course" form="course_add_form">
                            <c:forEach items="${courseList}" var="course">
                                <option value="${course.getCRN()}">${course.getAbbr()}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $(function() {
                                $(".chzn-select").chosen();
                            });
                        </script>
                        <input type="submit" style="font-family: 'Bree Serif';" value="Add Course"/>
                    </form>
                </div>
            </div>
            <div class="content_col_right">
                <h2 class="special_text col_header">Remove Course</h2>
                <div class="ui-widget">
                    <form id="course_remove_form" action="course" method="get">
                        <input type="hidden" name="action" value="remove"/>
                        <label>Choose a course from this list: </label>
                        <select class="chzn-select select_course" id="remove_course" size="8" name="selected_course" form="course_remove_form">
                            <c:forEach items="${courses}" var="course">
                                <option value="${course.getCRN()}">${course.getAbbr()}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $(function() {
                                $(".chzn-select").chosen();
                            });
                        </script>
                        <input type="submit" style="font-family: 'Bree Serif';" value="Remove Course"/>
                    </form>
                </div>
            </div>
            <div id="course_info">
                <h2 id="course_header" class="special_text">(Select a course to see more information about it here)</h2>
                <p><span class="special_text" style="font-size: 115%;">Title: </span><span id="course_title"></span></p>
                <p><span class="special_text" style="font-size: 115%;">Description: </span><span id="course_description"></span></p>
            </div>
            <script type="text/javascript">        
                <%  
                    ArrayList<String> courseAbbrs = new ArrayList<String>();
                    ArrayList<String> courseTitles = new ArrayList<String>();
                    ArrayList<String> courseDescs = new ArrayList<String>();
                    ArrayList<Course> courses = Databases.CourseDatabase.getAllCourses();
                    for (Course course : courses) {
                        courseAbbrs.add(course.getAbbr());
                        courseTitles.add(course.getTitle());
                        courseDescs.add(StringEscapeUtils.escapeJavaScript(course.getDescription()));
                    }
                %>

                var abbrs = [<% for (int i = 0; i < courseAbbrs.size(); i++) { %>"<%= courseAbbrs.get(i) %>"<%= i + 1 < courseAbbrs.size() ? ",":"" %><% } %>];
                var titles = [<% for (int i = 0; i < courseTitles.size(); i++) { %>"<%= courseTitles.get(i) %>"<%= i + 1 < courseTitles.size() ? ",":"" %><% } %>];
                var descs = [<% for (int i = 0; i < courseDescs.size(); i++) { %>"<%= courseDescs.get(i) %>"<%= i + 1 < courseDescs.size() ? ",":"" %><% } %>];
                
                $("#remove_course").change(function() {
                    var courseID = $("#remove_course").children("option").filter(":selected").val();
                    
                    $("#course_header").html(abbrs[courseID]);
                    $("#course_title").html(titles[courseID]);
                    $("#course_description").html(descs[courseID]);
                });
                
                $("#add_course").change(function() {
                    var courseID = $("#add_course").children("option").filter(":selected").val();
                    
                    $("#course_header").html(abbrs[courseID]);
                    $("#course_title").html(titles[courseID]);
                    $("#course_description").html(descs[courseID]);
                });
            </script>
        </main>
        <footer>           
            <%@include file="footer.jsp"%>
        </footer>
    </body>
</html>