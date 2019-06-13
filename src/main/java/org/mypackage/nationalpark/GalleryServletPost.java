package org.mypackage.nationalpark;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GalleryServletPost
 * @author Jennifer Carballo
 * Prints result from Pre-Servlet to JSP result page
 */
public class GalleryServletPost extends HttpServlet {

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
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            ArrayList<Image> result = (ArrayList<Image>)request.getAttribute("res");
            
            out.println("<div class=\"row\">");
            out.println("<div class=\"col-12\">");
            out.println("<h3>" + result.get(0).getCred() + "</h3>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"row\">");
            out.println("<div class=\"col-12\">");

            out.println("<!-- Slideshow -->");
            out.println("<div class=\"slideshow-container\">");
            int total = result.size();
            for (int i = 0; i < result.size(); i++) {
                out.println("<div class=\"mySlides fade\">");
                out.println("<div class=\"numbertext\">" + (i + 1) + "/" + total + "</div>");
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
                out.println("<span class=\"dot\" onclick=\"currentSlide(" + (j + 1) + ")\"></span>");
            }
            out.println("</div>");
        } finally {
            //do nothing
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
