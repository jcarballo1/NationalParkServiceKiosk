package org.mypackage.nationalpark;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
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
public class GalleryServlet extends HttpServlet {

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
            GalleryRequest req = new GalleryRequest();
            ArrayList<Image> result = req.sendGet(desig);
            
            out.println("<!-- Slideshow -->");
            out.println("<div class=\"slideshow-container\">");
            int total = result.size();
            for (int i = 0; i < result.size(); i++) {
                out.println("<div class=\"mySlides fade\">");
                out.println("<div class=\"numbertext\">" + (i+1) + "/" + total + "</div>");
                out.println("<img src=\"" + result.get(i).getUrl() + "\" style=\"width:100%\">");
                out.println("<div class=\"text\">" + result.get(i).getCaption() + "</div>");
                out.println("</div>");
            }
            out.println("<a class=\"prev\" onclick=\"plusSlides(-1)\">&#10094;</a>");
            out.println("<a class=\"next\" onclick=\"plusSlides(1)\">&#10095;</a>");
            out.println("</div>");
            out.println("<br>");
            out.println("<div style=\"text-align:center\">");
            for (int j = 0; j < result.size(); j++) {
                out.println("<span class=\"dot\" onclick=\"currentSlide(" + (j+1) + ")\"></span>");
            }
            out.println("</div>");
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
            Logger.getLogger(GalleryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GalleryServlet.class.getName()).log(Level.SEVERE, null, ex);
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
