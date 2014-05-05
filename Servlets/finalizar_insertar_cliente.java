/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import OracleConection.Main;
import static Servlets.Entrar.*;
import static Servlets.ingresar_cliente.tipo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "finalizar_insertar_cliente", urlPatterns = {"/finalizar_insertar_cliente"})
public class finalizar_insertar_cliente extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rut = request.getParameter("txtRut");
        String nombre = request.getParameter("txtNombre");
        
        try{
            rs=st.executeQuery("SELECT rut FROM cliente WHERE rut='" + rut + "'");
            rs.last();
            
            
            if (rs.getRow()==0){
                try{
                    
                    rs.first();
                    rs=st.executeQuery("INSERT INTO cliente (rut, nombre) values ('"+rut+"','"+nombre+"')");
                    //JOptionPane.showMessageDialog(null, "El cliente ha sido agregado satisfactoriamente.");
                    //response.sendRedirect("ingresar_venta.jsp");
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
            out.println("<br/><h4>Cliente agregado exitosamente!</h4>");
            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                
            }else{
                //Se ha intentado ingresar un cliente invalido (rut repetido)
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
                    out.println("<input id=\"txtRut\" name=\"txtRut\" type=\"text\" value=\""+ rut +"\"/><br/>");
                    out.println("Nombre:");
                    out.println("<input id=\"txtNombre\" name=\"txtNombre\" type=\"text\" value=\""+ nombre +"\"/><br/>");
           
                    out.println("<input id=\"botonGuardar\" value=\"Finalizar\" type=\"submit\" />");
                    out.println("</form>");
                    out.println("<br/>");
                    //Volver
                    out.println("<form name=\"datos\" action=\"adminMenu\" method=\"Post\">");
                    out.println("<input id=\"botonVolver\" value=\"Volver\" type=\"submit\" />");
                    out.println("</form>");
                    out.println("<br/><h4>ERROR! Cliente ya existe. (Rut repetido)</h4>");
            
                    out.println("</body>");
                    out.println("</html>");
                    //JOptionPane.showMessageDialog(null, "El cliente ya existe.");
                } finally {
                    out.close();
                }

            }
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        
        
        
        
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet finalizar_insertar_cliente</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet finalizar_insertar_cliente at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
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
