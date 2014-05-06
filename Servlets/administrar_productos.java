/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import OracleConection.Main;
import static Servlets.Entrar.conn;
import static Servlets.Entrar.rs;
import static Servlets.Entrar.st;
import static Servlets.Entrar.tipo;
import static Servlets.Entrar.user;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nacho
 */
@WebServlet(name = "administrar_productos", urlPatterns = {"/administrar_productos"})
public class administrar_productos extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrar productos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: " + tipo + ".</h5>");
            out.println("<br/>");
            
            try{
            conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            
            rs = st.executeQuery("select * from producto");
            while(rs.next()){
                out.println(""+rs.getString(1)+" - "+rs.getString(3)+" - (categoria)"+rs.getString(4)+" - stock: "+rs.getString(2)+" - $"+rs.getString(5)+"<br/>");
            }
        }catch(Exception ex){
            
        }
            
            out.println("<br/><form name=\"editar_producto\" action=\"editar_producto\" method=\"Post\">");
            out.println("Ingrese ID del producto a editar: ");
            out.println("<input id=\"txtID\" name=\"txtID\" type=\"text\" />");
            out.println("<input id=\"boton_edit_producto\" value=\"Editar\" type=\"submit\" />");
            out.println("</form><br/><br/>");
            out.println("<form name=\"agregar_producto\" action=\"agregar_producto\">");
            out.println("<input id=\"boton_agr_producto\" value=\"Agregar nuevo producto\" type=\"submit\" />");
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
