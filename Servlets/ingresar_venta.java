/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import static Servlets.Entrar.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "ingresar_venta", urlPatterns = {"/ingresar_venta"})
public class ingresar_venta extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ingresar Cliente:</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: " + tipo + ".</h5>");
            out.println("<h4>Ingrese los datos del nuevo cliente:</h4>");
            out.println("<br/>");
            out.println("<form name=\"datos\" action=\"finalizar_insertar_cliente\" method=\"Post\">");
            out.println("Rut:");
            out.println("<input id=\"txtRut\" name=\"txtRut\" type=\"text\" /><br/>");
            out.println("Nombre:");
            out.println("<input id=\"txtNombre\" name=\"txtNombre\" type=\"text\" /><br/>");
           
            out.println("<input id=\"botonGuardar\" value=\"Finalizar\" type=\"submit\" />");
            out.println("</form>");
            out.println("<br/>");
            //Volver
            out.println("<form name=\"datos\" action=\"adminMenu\" method=\"Post\">");
            out.println("<input id=\"botonVolver\" value=\"Volver\" type=\"submit\" />");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
