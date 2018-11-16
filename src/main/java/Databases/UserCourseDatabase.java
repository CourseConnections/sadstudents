/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Databases;

import Beans.User;
import Beans.Course;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;

/**
 *
 * @author soup
 */
public class UserCourseDatabase {
    
    public static void addEntry(int userID, int CRN) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_a8045e306fa2069?reconnect=true&user=b5126a21c3502c&password=366ae8c4");
            String query = "INSERT INTO User_Course_Junction (UserID, CRN) VALUES (?, ?)";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, CRN);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {conn.close();} catch (Exception e) {}
        }
    }
    
    public static void delEntry(int userID, int CRN) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_a8045e306fa2069?reconnect=true&user=b5126a21c3502c&password=366ae8c4");
            String query = "DELETE FROM User_Course_Junction WHERE UserID = ? AND CRN = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, CRN);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {conn.close();} catch (Exception e) {}
        }
    }
    
    public static ArrayList<Course> getUserCourses(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<Course> courses = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_a8045e306fa2069?reconnect=true&user=b5126a21c3502c&password=366ae8c4");
            String query = "SELECT * FROM User_Course_Junction WHERE UserID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            
            courses = new ArrayList<Course>();
            
            while (rs.next()) {
                courses.add(CourseDatabase.getCourse(rs.getInt("CRN")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                try {conn.close();} catch (Exception e) {}
        }
        
        return courses;
    }
    
    public static ArrayList<User> getCourseUsers(int CRN) {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<User> users = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_a8045e306fa2069?reconnect=true&user=b5126a21c3502c&password=366ae8c4");
            String query = "SELECT * FROM User_Course_Junction WHERE CRN = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, CRN);
            ResultSet rs = ps.executeQuery();
            
            users = new ArrayList<User>();
            
            while (rs.next()) {
                users.add(UserDatabase.getUser(rs.getInt("UserID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                try {conn.close();} catch (Exception e) {}
        }
        
        return users;
    }
}