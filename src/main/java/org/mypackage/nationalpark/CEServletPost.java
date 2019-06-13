package org.mypackage.nationalpark;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CEServletPost
 * @author Jennifer Carballo
 * Prints result from Pre-Servlet to JSP result page
 */
public class CEServletPost extends HttpServlet {

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
            ArrayList<CESearchResult> res = (ArrayList<CESearchResult>) request.getAttribute("res"); //grabs result created from request class
            
            out.println("<ul class=\"alt\">"); //for listing
            if (res.size() < 1) {
                out.println("<li>No results matched your request. Please try again.<li>");
            } else {
                for (int i = 0; i < res.size(); i++) {
                    out.println("<li>Type: " + res.get(i).getType() + "<br><br>");
                    if (res.get(i).getType().equals("Alert")) { //process for alerts
                        if (!res.get(i).getTitle().equals("")) {
                            out.println(res.get(i).getTitle() + "<br><br>");
                        }

                        if (!res.get(i).getCategory().equals("")) {
                            out.println(res.get(i).getCategory() + "<br><br>");
                        }

                        if (!res.get(i).getDescription().equals("")) {
                            out.println(res.get(i).getDescription() + "<br><br>");
                        }

                        if (!res.get(i).getUrl().equals("")) {
                            out.println("For more information please visit:<br>");
                            out.println("<a href=\"" + res.get(i).getUrl() + "\"> Official " + res.get(i).getCategory() + " Alert Page</a>");
                        }
                    } else if (res.get(i).getType().equals("Article")) { //process for articles
                        if (!res.get(i).getTitle().equals("")) {
                            out.println(res.get(i).getTitle() + "<br>");
                        }

                        if (!res.get(i).getListingDes().equals("")) {
                            out.println("<br>" + res.get(i).getListingDes() + "<br>");
                        }

                        if (!res.get(i).getImageURL().equals("")) {
                            out.println("<br>" + "<img src=\"" + res.get(i).getImageURL() + "\" height=200><br>");
                        }

                        if (!res.get(i).getUrl().equals("")) {
                            out.println("<br>For more information:<br>");
                            out.println("<a href=\"" + res.get(i).getUrl() + "\">Official Article</a>");
                        }
                    } else if (res.get(i).getType().equals("Event")) { //process for events
                        if (!res.get(i).getTitle().equals("")) {
                            out.println(res.get(i).getTitle() + "<br>");
                        }
                        if (!res.get(i).getDescription().equals("")) {
                            out.println("<br>" + res.get(i).getDescription() + "");
                        }
                        if (!res.get(i).getLocation().equals("")) {
                            out.println("Where: " + res.get(i).getLocation());
                        }
                        if (res.get(i).getDates().size() > 0) {
                            out.println("<br>When: ");
                            for (int j = 0; j < res.get(i).getDates().size(); j++) {
                                if (j == res.get(i).getDates().size() - 1) {
                                    out.println(res.get(i).getDates().get(j));
                                } else {
                                    out.println(res.get(i).getDates().get(j) + ", ");
                                }
                            }
                        }
                        if (!res.get(i).getTimeStart().equals("")) {
                            out.println("<br>From: " + res.get(i).getTimeStart());
                        }
                        if (!res.get(i).getTimeEnd().equals("")) {
                            out.println(" To: " + res.get(i).getTimeEnd() + "<br>");
                        }
                        if (!res.get(i).getFees().equals("")) {
                            out.println("<br>Fee Info: " + res.get(i).getFees() + "<br>");
                        }
                        if (!res.get(i).getContactName().equals("")) {
                            out.println("<br>Contact " + res.get(i).getContactName() + " at:");
                        } else if (!res.get(i).getContactPhone().equals("") || !res.get(i).getEmail().equals("")) {
                            out.println("<br>Event Contact Info:");
                            if (!res.get(i).getContactPhone().equals("")) {
                                out.println("<br>" + res.get(i).getContactPhone());
                            }
                            if (!res.get(i).getEmail().equals("")) {
                                out.println("<br>" + res.get(i).getEmail());
                            }
                            out.println("<br>");
                        }
                        if (!res.get(i).getUrl().equals("")) {
                            out.println("<br>For more information please visit:<br>");
                            out.println("<a href=\"" + res.get(i).getUrl() + "\"> Official " + res.get(i).getTitle() + " Event Page</a>");
                        }
                    } else { //process for news releases
                        if (!res.get(i).getTitle().equals("")) {
                            out.println(res.get(i).getTitle() + "<br>");
                        }
                        if (!res.get(i).getReleaseDate().equals("")) {
                            out.println("<br>Release Date: " + res.get(i).getReleaseDate() + "<br>");
                        }
                        if (!res.get(i).getAbs().equals("")) {
                            out.println("<br>" + res.get(i).getAbs() + "<br>");
                        }
                        if (!res.get(i).getImageURL().equals("")) {
                            out.println("<br>" + "<img src=\"" + res.get(i).getImageURL() + "\" height=200><br>");
                        }
                        if (!res.get(i).getUrl().equals("")) {
                            out.println("<br>For more information please visit:<br>");
                            out.println("<a href=\"" + res.get(i).getUrl() + "\"> Official News Release Page</a>");
                        }
                    }
                    out.println("</li>"); //end of single result
                }
            }
            out.println("</ul>"); //end
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
