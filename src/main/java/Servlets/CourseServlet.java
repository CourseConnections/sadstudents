/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Servlets;

import Databases.CourseDatabase;
import Databases.UserCourseDatabase;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author soup
 */
public class CourseServlet extends HttpServlet {
    
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
        
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        ProcessBuilder pb = null;
        
        if (action.equals("add")) {
            String username = (String) session.getAttribute("username");
            int userID = (int) session.getAttribute("userID");
            int CRN = Integer.parseInt(request.getParameter("selected_course"));
            String abbr = CourseDatabase.getCourse(CRN).getAbbr().toLowerCase();
            
            pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/rooms.get",
                    "-H", "X-Auth-Token: j13UxTxvJLweRb7yrKIveQMzbdFIzZl8EYNDJaTaTE0",
                    "-H", "X-User-ID: initialuser",
                    "-H", "Content-type: application/json");
            
            pb.redirectErrorStream(true);
            Process p = pb.start();
            String message = getMessage(p);
            
            request.getServletContext().log(message);
            StringBuilder builder = new StringBuilder(message);
            int end = message.indexOf(abbr);
            String roomID = builder.substring(end-27, end-10);
            
            pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/commands.run",
                    "-H", "X-Auth-Token: j13UxTxvJLweRb7yrKIveQMzbdFIzZl8EYNDJaTaTE0",
                    "-H", "X-User-ID: initialuser",
                    "-d", "command=invite&roomId=" + roomID + "&params=" + username);
            
            pb.redirectErrorStream(true);
            p = pb.start();
            InputStream is = p.getInputStream();
            
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] contents = new byte[1024];
            
            int bytesRead = 0;
            String strFileContents = "";
            while((bytesRead = bis.read(contents)) != -1) {
                strFileContents += new String(contents, 0, bytesRead);
            }
            
            UserCourseDatabase.addEntry(userID, CRN);
            
            session.setAttribute("courses", UserCourseDatabase.getUserCourses(userID));
            
            request.getServletContext().log(strFileContents);
            request.getServletContext().getRequestDispatcher("/courses.jsp").forward(request, response);
        }
        else if (action.equals("remove")) {
            String username = (String) session.getAttribute("username");
            int userID = (int) session.getAttribute("userID");
            int CRN = Integer.parseInt(request.getParameter("selected_course"));
            String abbr = CourseDatabase.getCourse(CRN).getAbbr().toLowerCase();
            
            pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/rooms.get",
                    "-H", "X-Auth-Token: j13UxTxvJLweRb7yrKIveQMzbdFIzZl8EYNDJaTaTE0",
                    "-H", "X-User-ID: initialuser",
                    "-H", "Content-type: application/json");
            
            pb.redirectErrorStream(true);
            Process p = pb.start();
            String message = getMessage(p);
            
            request.getServletContext().log(message);
            StringBuilder builder = new StringBuilder(message);
            int end = message.indexOf(abbr);
            String roomID = builder.substring(end-27, end-10);
            
            pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/commands.run",
                    "-H", "X-Auth-Token: j13UxTxvJLweRb7yrKIveQMzbdFIzZl8EYNDJaTaTE0",
                    "-H", "X-User-ID: initialuser",
                    "-d", "command=kick&roomId=" + roomID + "&params=" + username);
            
            pb.redirectErrorStream(true);
            p = pb.start();
            InputStream is = p.getInputStream();
            
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] contents = new byte[1024];
            
            int bytesRead = 0;
            String strFileContents = "";
            while((bytesRead = bis.read(contents)) != -1) {
                strFileContents += new String(contents, 0, bytesRead);
            }
            
            UserCourseDatabase.delEntry(userID, CRN);
            
            session.setAttribute("courses", UserCourseDatabase.getUserCourses(userID));
            
            request.getServletContext().log(strFileContents);
            request.getServletContext().getRequestDispatcher("/courses.jsp").forward(request, response);
        }
        else if (action == null) {
            request.setAttribute("courseList", CourseDatabase.getAllCourses());
            request.getServletContext().getRequestDispatcher("/courses.jsp");
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
    
    private String getMessage(Process p) throws IOException{
        InputStream is = p.getInputStream();
        
        BufferedInputStream bis = new BufferedInputStream(is);
        byte[] contents = new byte[1024];
        
        int bytesRead = 0;
        String message = "";
        while ((bytesRead = bis.read(contents)) != -1) {
            message += new String(contents, 0, bytesRead);
        }
        
        return message;
    }
}