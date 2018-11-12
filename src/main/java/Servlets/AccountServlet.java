/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Servlets;

import Beans.Course;
import Beans.User;
import Databases.CourseDatabase;
import Databases.UserDatabase;
import Utils.IdTokenVerifierAndParser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.sql.Connection;
import javax.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 *
 * @author soup
 */
public class AccountServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        String action = request.getParameter("action");
        
        if (action == null)
            request.getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
        
        if (action.equals("login")) {
            
            try {
                String idToken = request.getParameter("id_token");
                GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);
                String name = (String) payLoad.get("name");
                String email = payLoad.getEmail();
                
                User user = UserDatabase.getUser(email);
                
                if (user == null)
                    UserDatabase.addUser(name, "@jamie.gachie", email);
                
                ArrayList<Course> courses = CourseDatabase.getAllCourses();
                
                if (courses == null)
                    request.getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
                
                HttpSession session = request.getSession(true);
                session.setAttribute("name", name);
                session.setAttribute("email", email);
                session.setAttribute("courseList", courses);
                request.getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
        }
        else if (action.equals("logout")) {
            HttpSession session = request.getSession(true);
            session.removeAttribute("name");
            session.removeAttribute("email");
            
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {
            request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
