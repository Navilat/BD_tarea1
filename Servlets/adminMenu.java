/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import OracleConection.Main;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import static Servlets.Entrar.conn;
import static Servlets.Entrar.rs;
import static Servlets.Entrar.st;
import static Servlets.Entrar.tipo;
import static Servlets.Entrar.user;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "adminMenu", urlPatterns = {"/adminMenu"})
public class adminMenu extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (finalizar_insertar_compra.gate==1){ //gate vale 1 si se ha agregado al menos un producto en "ingresar compra"
            //se agrega un registro en compra:
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            
	   Date date = new Date();
 
	   Calendar cal = Calendar.getInstance();
            try{
                rs = st.executeQuery("insert into COMPRA (id_compra, monto_total, fecha, hora) values ("+finalizar_insertar_compra.new_id_compra+","+finalizar_insertar_compra.total+",'"+dateFormat.format(cal.getTime())+"',CURRENT_TIMESTAMP)");
                finalizar_insertar_compra.gate=0;
            }catch(Exception ex){
            
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Administrador:</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: administrador.</h5>");
            out.println("<form name=\"ingresar_vendedor\" action=\"ingresar_vendedor\">");
            out.println("<input id=\"boton_ing_vendr\" value=\"Ingresar Vendedor\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"ingresar_cliente\" action=\"ingresar_cliente\">");
            out.println("<input id=\"boton_ing_cliente\" value=\"Ingresar Cliente\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"administrar_productos\" action=\"administrar_productos\">");
            out.println("<input id=\"boton_admin_prod\" value=\"Administrar Productos\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"ingresar_compra\" action=\"ingresar_compra\">");
            out.println("<input id=\"boton_ing_compra\" value=\"Ingresar Compra\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"ingresar_venta\" action=\"ingresar_venta\">");
            out.println("<input id=\"boton_ing_venta\" value=\"Ingresar Venta\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"ver_ventas\" action=\"ver_ventas\">");
            out.println("<input id=\"boton_ver_ventas\" value=\"Ver Ventas por Cliente\" type=\"submit\" />");
            out.println("</form>");
            out.println("<br/>");
            out.println("<a href=\"login.jsp\">volver</a>");
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
