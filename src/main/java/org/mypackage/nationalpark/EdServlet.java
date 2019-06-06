/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.nationalpark;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jcarb
 */
public class EdServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String desig = request.getParameter("designation");
            String state = request.getParameter("state");
            String key = request.getParameter("type");
            EdSearchRequest req = new EdSearchRequest();//Creating class Object
            ArrayList<EdSearchResult> res = req.process(desig, state, key);

            out.println("<ul class=\"alt\">");
            if (res.size() < 1) {
                out.println("<li>No results matched your request. Please try again.<li>");
            } else {
                for (int i = 0; i < res.size(); i++) {
                    out.println("<li>Type: " + res.get(i).getType() + "<br><br>");
                    if (res.get(i).getType().equals("Lesson Plan")) {
                        if (!res.get(i).getTitle().equals("")) {
                            out.println(res.get(i).getTitle() + "<br><br>");
                        }
                        if (!res.get(i).getSubject().equals("")) {
                            out.println(res.get(i).getSubject() + "<br><br>");
                        }
                        if (!res.get(i).getObjective().equals("")) {
                            out.println(res.get(i).getObjective() + "<br><br>");
                        }
                        if (!res.get(i).getUrl().equals("")) {
                            out.println("<a href=\"" + res.get(i).getUrl() + "\">Learn More Here</a>");
                        }
                    } else {
                        if (!res.get(i).getTitle().equals("")) {
                            out.println(res.get(i).getTitle() + "<br><br>");
                        }
                        if (!res.get(i).getListingDes().equals("")) {
                            out.println(res.get(i).getListingDes() + "<br>");
                        }
                        if (!res.get(i).getImageURL().equals("")) {
                            out.println("<br>" + "<img src=\"" + res.get(i).getImageURL() + "\" height=200><br>");
                        }
                        if (!res.get(i).getUrl().equals("")) {
                            out.println("<br><a href=\"" + res.get(i).getUrl() + "\">Learn More Here</a>");
                        }
                    }
                    out.println("</li>");
                }
            }
            out.println("</ul>");
        } finally {
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EdServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EdServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
