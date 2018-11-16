/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Databases;

import Beans.User;
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
public class UserDatabase {
    
    public static void addUser(String name, String username, String email) {
        addUser(new User(name, username, email, 0));
    }
    
    private static void addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_a8045e306fa2069?reconnect=true&user=b5126a21c3502c&password=366ae8c4");
            String query = "INSERT INTO User (Name, Username, Email) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                try {conn.close();} catch (Exception e) {}
        }
    }
    
    public static ArrayList<User> getAllUsers() {
        Connection conn = null;
        PreparedStatement ps = null;
        ArrayList<User> users = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-01.cleardb.net/heroku_a8045e306fa2069?reconnect=true&user=b5126a21c3502c&password=366ae8c4");
            String query = "SELECT * FROM User";
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            users = new ArrayList<User>();
            
            while (rs.next()) {
                users.add(new User(rs.getString("Name"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getInt("UserID")));
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
    
    public static User getUser(String email) {
        ArrayList<User> users = getAllUsers();
        
        for (User user : users) {
            if (user.getEmail().equals(email))
                return user;
        }
        
        return null;
    }
    
    public static User getUser(int userID) {
        ArrayList<User> users = getAllUsers();
        
        for (User user : users) {
            if (user.getUserID() == userID)
                return user;
        }
        
        return null;
    }
    
    public static boolean checkUsername(String username) {
        ArrayList<User> users = getAllUsers();
        
        for (User user : users) {
            if (user.getUsername().equals(username))
                return true;
        }
        
        return false;
    }
}