/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Servlets;

import Beans.User;
import Databases.CourseDatabase;
import Databases.UserCourseDatabase;
import Databases.UserDatabase;
import Utils.IdTokenVerifierAndParser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import javax.servlet.http.HttpSession;
import Utils.Utils;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

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
                
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("name", name);
                
                User user = UserDatabase.getUser(email);
                
                if (user == null)
                    request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
                
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("username", user.getUsername());
                session.setAttribute("userID", user.getUserID());
                
                session.setAttribute("courseList", CourseDatabase.getAllCourses());
                session.setAttribute("courses", UserCourseDatabase.getUserCourses(user.getUserID()));
                
                request.getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
        }
        else if (action.equals("logout")) {
            HttpSession session = request.getSession(true);
            session.removeAttribute("name");
            session.removeAttribute("email");
            session.removeAttribute("username");
            session.removeAttribute("userID");
            session.removeAttribute("courses");
            
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else if (action.equals("register")) {
            HttpSession session = request.getSession(true);
            
            if ((String) session.getAttribute("name") == null)
                Utils.dispatchError(request, response, "Please log in with your Google account before continuing!");
            else if (UserDatabase.getUser((String) session.getAttribute("email")) != null)
                Utils.dispatchError(request, response, "An account associated with this Google account already exists!");
            
            String name = request.getParameter("name");
            String email = (String) session.getAttribute("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            UserDatabase.addUser(name, username, email);
            User user = UserDatabase.getUser(email);
            
            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userID", user.getUserID());
            
            session.setAttribute("courses", UserCourseDatabase.getUserCourses(user.getUserID()));
            
            
            ProcessBuilder pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/users.create",
                    "-H", "X-Auth-Token: j13UxTxvJLweRb7yrKIveQMzbdFIzZl8EYNDJaTaTE0",
                    "-H", "X-User-ID: initialuser",
                    "-H", "Content-type:application/json",
                    "-d", "{\"name\":\"" + user.getName() + "\",\"email\":\"" + user.getEmail() + "\",\"username\":\"" + user.getUsername() + "\",\"password\":\"" + password + "\",\"sendWelcomeEmail\":true}");
            
            pb.redirectErrorStream(true);
            Process p = pb.start();
            InputStream is = p.getInputStream();
            
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] contents = new byte[1024];
            
            int bytesRead = 0;
            String strFileContents = "";
            while((bytesRead = bis.read(contents)) != -1) {
                strFileContents += new String(contents, 0, bytesRead);
            }
            
            request.getServletContext().log(strFileContents);
            
            request.getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
        }
        else {
            Utils.dispatchError(request, response, "Unknown error.");
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
