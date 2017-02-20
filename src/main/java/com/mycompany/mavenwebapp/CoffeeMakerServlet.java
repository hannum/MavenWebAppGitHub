/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author hannutam
 */
@WebServlet(name = "CoffeeMakerServlet", urlPatterns = {"/coffeemaker"})
public class CoffeeMakerServlet extends HttpServlet {

    private ServletContext context;
    private final CoffeeMaker cm = CoffeeMaker.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("--------------- CoffeeMakerServlet init()");
        this.context = config.getServletContext();
    }

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
        System.out.println("CoffeeMakerServlet processRequest()");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CoffeeMakerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CoffeeMakerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

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
        //processRequest(request, response);

        System.out.println("req: " + request.getMethod() + " "
                + request.getParameter("action"));
        
        String action = request.getParameter("action");
        JSONObject jsonResponse = new JSONObject();
        
        if (action == null) {
            System.out.println("no action");
        } else {
            switch (action) {
                case "turnOnOff":
                    cm.pressOnOff();
                    break;
                case "espresso":
                case "espresso-lg":
                case "cappuccino":
                case "cappuccino-lg":
                case "latte":
                case "latte-lg":
                case "americano":
                case "americano-lg":
                    cm.brew();
                    break;
                case "fillWater":
                    cm.fillWater();
                    break;
                case "fillBeans":
                    cm.fillBeans();
                    break;
            }
        }

        jsonResponse.putAll(getCMStatus());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        System.out.println("jsonResponse: " + jsonResponse.toString());

        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
               
    }
    
   

    private HashMap getCMStatus() {
        HashMap status = new HashMap();
        status.put("on", cm.isOn());
        status.put("water", cm.getWater());
        status.put("beans", cm.getBeans());
        status.put("coffeesServed", cm.getPressed());
        return status;
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
