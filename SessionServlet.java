/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author boovaragan
 */
@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           HttpSession session = request.getSession(true); 
           String requestingPageName = request.getParameter("PageName"); 
           
            switch (requestingPageName) {
                case "ywatch":
                    {
                        String selectedwatch = request.getParameter("watch");
                        String noOfwatches = request.getParameter("numOfWatch");
                        session.setAttribute("selwatch", selectedwatch);
                        session.setAttribute("numwatch", noOfwatches);
                        RequestDispatcher dispatch = request.getRequestDispatcher("details.jsp");
                        dispatch.forward(request, response);
                        break;
                    }
                default:
                {
                        RequestDispatcher dispatch = request.getRequestDispatcher("Error.jsp");
                        dispatch.forward(request, response);
                        break;
                    }
            }
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}