/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Servlets;

import Utils.ParameterStringBuilder;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.ProcessBuilder;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

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
        
        String action = request.getParameter("action");
        ProcessBuilder pb = null;
        
        if (action == null)
            request.getServletContext().getRequestDispatcher("/menu.jsp").forward(request, response);
        
        if (action.equals("add")) {
            /*
            pb = new ProcessBuilder("curl",
                    "-H", "\"X-Auth-Token: bUj7z12R_NZRFdYGrolW10IMsm-4Bot8D-DXM36ZkSp\"",
                    "-H", "\"X-User-ID: initialuser\"",
                    "-H", "\"Content-type: application/json\"",
                    "https://courseconnections.rocket.chat/api/v1/commands.run",
                    "-d", "\'{\"command\":\"invite\",\"roomId\":\"sXBYJjvCpJ3EtGmAY\",\"params\",\"@jamie.gachie\"}\'");
            */
            
            /*
            pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/login",
                    "-d", "username=CourseConnections2018&password=2018ITCSProject!");
            */
            
            pb = new ProcessBuilder("curl",
                    "https://courseconnections.rocket.chat/api/v1/commands.run",
                    "-H", "X-Auth-Token: j13UxTxvJLweRb7yrKIveQMzbdFIzZl8EYNDJaTaTE0",
                    "-H", "X-User-ID: initialuser",
                    "-d", "command=invite&roomId=sXBYJjvCpJ3EtGmAY&params=@jamie.gachie");
            
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
            request.getServletContext().getRequestDispatcher("/courses.jsp").forward(request, response);
        }
        else if (action.equals("remove")) {
            
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