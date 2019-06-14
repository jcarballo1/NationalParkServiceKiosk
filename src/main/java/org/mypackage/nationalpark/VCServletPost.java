package org.mypackage.nationalpark;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * VCServletPost
 * @author Jennifer Carballo
 * Prints result from Pre-Servlet to JSP result page
 */
public class VCServletPost extends HttpServlet {

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
            ArrayList<VCenterSearchResult> res = (ArrayList<VCenterSearchResult>)request.getAttribute("res");
            
            out.println("<ul class=\"alt\">"); //for listing
            if (res.size() < 1) {
                out.println("<li>No results matched your request. Please try again.<li>");
            } else {
                for (int i = 0; i < res.size(); i++) {
                    out.println("<li>Type: " + res.get(i).getType() + "<br><br>");
                    out.println(res.get(i).getName() + "<br><br>");
                    out.println(res.get(i).getDescrip() + "<br>");

                    int j;
                    if (res.get(i).getAddies().size() > 0) {
                        for (j = 0; j < res.get(i).getAddies().size(); j++) {
                            out.println(res.get(i).getAddies().get(j).getType() + ":");
                            out.println(res.get(i).getAddies().get(j).getLine1());
                            if (res.get(i).getAddies().get(j).getLine2() != "") {
                                out.println(res.get(i).getAddies().get(j).getLine2());
                            }
                            if (res.get(i).getAddies().get(j).getLine3() != "") {
                                out.println(res.get(i).getAddies().get(j).getLine3());
                            }
                            out.println(res.get(i).getAddies().get(j).getCity() + ", " + res.get(i).getAddies().get(j).getState()
                                    + " " + res.get(i).getAddies().get(j).getZip() + "<br>");
                        }
                    }

                    if (!res.get(i).getDirect().equals("")) {
                        out.println("<br>" + res.get(i).getDirect() + "<br>");
                    }

                    if (res.get(i).getNumbers().size() > 0) {
                        out.println("<br>");
                        for (j = 0; j < res.get(i).getNumbers().size(); j++) {
                            out.println(res.get(i).getNumbers().get(j).getType() + ":");
                            out.println(res.get(i).getNumbers().get(j).getNumber() + "<br>");
                        }
                    }

                    if (res.get(i).getEmails().size() > 0) {
                        out.println("<br>");
                        for (j = 0; j < res.get(i).getEmails().size(); j++) {
                            if (res.get(i).getEmails().get(j) != "" && res.get(i).getEmails().get(j) != "0@0") {
                                out.println(res.get(i).getEmails().get(j) + "<br>");
                            }
                        }
                    }

                    if (res.get(i).getType().equals("Campground")) {
                        if (!res.get(i).getWheelchair().equals("")) {
                            out.println("<br>");
                            out.println(res.get(i).getWheelchair());
                            out.println("<br>");
                        }

                        if (!res.get(i).getAda().equals("")) {
                            out.println("<br>");
                            out.println(res.get(i).getAda());
                            out.println("<br>");
                        }

                        if (!res.get(i).getToilets().equals("")) {
                            out.println("<br>Toilets: " + res.get(i).getToilets());
                        }

                        if (!res.get(i).getShowers().equals("")) {
                            out.println("<br>Showers: " + res.get(i).getShowers());
                        }

                        if (!res.get(i).getInternet().equals("")) {
                            out.println("<br>Internet: " + res.get(i).getInternet());
                        }

                        if (!res.get(i).getWater().equals("")) {
                            out.println("<br>Potable Water: " + res.get(i).getWater());
                        }

                        if (!res.get(i).getWeather().equals("")) {
                            out.println("<br>Weather Info:<br>" + res.get(i).getWeather() + "<br>");
                        }

                        if (!res.get(i).getFees().equals("") && !res.get(i).getFees().equals("0")) {
                            out.println("<br>" + res.get(i).getFees() + "<br>");
                        }
                    }

                    if (res.get(i).getHours().size() > 0) {
                        for (j = 0; j < res.get(i).getHours().size(); j++) {
                            out.println("<br>");
                            out.println("Standard Hours:<br>");
                            out.println(res.get(i).getHours().get(j).getDes() + "<br>");

                            Iterator<String> iter = res.get(i).getHours().get(j).getStanHours().keySet().iterator();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                out.println(key + ": " + res.get(i).getHours().get(j).getStanHours().get(key) + "<br>");
                            }
                        }
                    }

                    if (res.get(i).getType().equals("Campground")) {
                        if (!res.get(i).getRegulationURL().equals("")) {
                            out.println("<br>For regulations please visit:");
                            out.println("<br>");
                            out.println("<a href=\"" + res.get(i).getRegulationURL() + "\"> Official " + res.get(i).getName() + " Regulations Page</a><br>");
                        }
                    }

                    if (!res.get(i).getUrl().equals("")) {
                        out.println("<br>For information please visit:");
                        out.println("<br>");
                        out.println("<a href=\"" + res.get(i).getUrl() + "\"> Official " + res.get(i).getName() + " Page</a>");
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
