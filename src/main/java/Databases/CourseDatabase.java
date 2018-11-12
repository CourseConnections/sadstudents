/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Databases;

import Beans.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author soup
 */
public class CourseDatabase {
    private static DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public static ArrayList<Course> getAllCourses() {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<Course> courses = null;
        
        try {
            String query = "SELECT * FROM Course";
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            courses = new ArrayList<Course>();
            
            while (rs.next()) {
                courses.add(new Course(rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Professor"),
                        rs.getString("Prefix"),
                        rs.getString("Designation"),
                        rs.getInt("CRN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {conn.close();} catch (Exception e) {}
        }
        
        return courses;
    }
    
    public static Course getCourse(int CRN) {
        ArrayList<Course> courses = getAllCourses();
        
        for (Course course : courses) {
            if (course.getCRN() == CRN)
                return course;
        }
        
        return null;
    }
}
