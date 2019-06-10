/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author jcarb
 */
public class GenSearchServletPost extends HttpServlet {

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
            ArrayList<GeneralSearchResult> res = (ArrayList<GeneralSearchResult>)request.getAttribute("res");
            out.println("<ul class=\"alt\">");
            if (res.size() < 1) {
                out.println("<li>No results matched your request. Please try again.<li>");
            } else {
                for (int i = 0; i < res.size(); i++) {
                    out.println("<li>" + res.get(i).getName() + "<br><br>");
                    out.println(res.get(i).getDescrip() + "<br><br>");
                    if (res.get(i).getImages().size() > 0) {
                        out.println("<img src=\"" + res.get(i).getImages().get(0).getUrl() + "\" height=200><br><br>");
                    }
                    int j;
                    if (res.get(i).getAdds().size() > 0) {
                        for (j = 0; j < res.get(i).getAdds().size(); j++) {
                            out.println(res.get(i).getAdds().get(j).getType() + ":");
                            out.println(res.get(i).getAdds().get(j).getLine1());
                            if (res.get(i).getAdds().get(j).getLine2() != "") {
                                out.println(res.get(i).getAdds().get(j).getLine2());
                            }
                            if (res.get(i).getAdds().get(j).getLine3() != "") {
                                out.println(res.get(i).getAdds().get(j).getLine3());
                            }
                            out.println(res.get(i).getAdds().get(j).getCity() + ", " + res.get(i).getAdds().get(j).getState()
                                    + " " + res.get(i).getAdds().get(j).getZip() + "<br>");
                        }
                    }

                    if (res.get(i).getNumbers().size() > 0) {
                        for (j = 0; j < res.get(i).getNumbers().size(); j++) {
                            out.println(res.get(i).getNumbers().get(j).getType() + ":");
                            out.println(res.get(i).getNumbers().get(j).getNumber() + "<br>");
                        }
                    }

                    if (res.get(i).getEmails().size() > 0) {
                        for (j = 0; j < res.get(i).getEmails().size(); j++) {
                            if (res.get(i).getEmails().get(j) != "" && res.get(i).getEmails().get(j) != "0@0") {
                                out.println(res.get(i).getEmails().get(j) + "<br>");
                            }
                        }
                    }

                    if (res.get(i).getFees().size() > 0) {
                        for (j = 0; j < res.get(i).getFees().size(); j++) {
                            out.println("<br>");
                            if (res.get(i).getFees().get(j).getTitle() != "") {
                                out.println(res.get(i).getFees().get(j).getTitle() + ":<br>");
                            }
                            if (res.get(i).getFees().get(j).getDes() != "") {
                                out.println(res.get(i).getFees().get(j).getDes() + "<br>");
                            }
                            if (res.get(i).getFees().get(j).getCost() != "") {
                                out.println("$" + res.get(i).getFees().get(j).getCost() + "<br>");
                            }
                        }
                    }

                    if (res.get(i).getPasses().size() > 0) {
                        for (j = 0; j < res.get(i).getPasses().size(); j++) {
                            out.println("<br>");
                            if (res.get(i).getPasses().get(j).getTitle() != "") {
                                out.println(res.get(i).getPasses().get(j).getTitle() + ":<br>");
                            }
                            if (res.get(i).getPasses().get(j).getDes() != "") {
                                out.println(res.get(i).getPasses().get(j).getDes() + "<br>");
                            }
                            if (res.get(i).getPasses().get(j).getCost() != "") {
                                out.println("$" + res.get(i).getPasses().get(j).getCost() + "<br>");
                            }
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

                    if (res.get(i).getWeather() != "") {
                        out.println("<br>Weather Info:");
                        out.println("<br>" + res.get(i).getWeather());
                    }

                    if (res.get(i).getUrl() != "") {
                        out.println("<br><br>For information please visit:");
                        out.println("<br>");
                        out.println("<a href=\"" + res.get(i).getUrl() + "\"> Official " + res.get(i).getName() + " Page</a>");
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
