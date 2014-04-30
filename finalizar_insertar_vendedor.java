/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import OracleConection.Main;
import static Servlets.Entrar.*;
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
 * @author Nacho
 */
@WebServlet(name = "finalizar_insertar_vendedor", urlPatterns = {"/finalizar_insertar_vendedor"})
public class finalizar_insertar_vendedor extends HttpServlet {

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
        String rut = request.getParameter("txtRut");
        String contrasena = request.getParameter("txtContrasena");
        String nombre = request.getParameter("txtNombre");
        String contrasena2 = request.getParameter("txtContrasena2"); 
        
        if (contrasena.equals(contrasena2)){
        
            try {
                int rowcount=0;
                conn=Main.Enlace(conn);
                st=Main.sta(st);
                rs=Main.EnlEst(rs);
                rs.next();
            
                //para hacer consulta
                rs=st.executeQuery("INSERT INTO usuario (rut, contrasena, nombre, tipo, comision) values ('"+rut+"','"+contrasena+"','"+nombre+"','vendedor','0')");
                JOptionPane.showMessageDialog(null, "Vendedor guardado");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Ingresar Vendedor:</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h5>" + user + " como: administrador.</h5>");
                out.println("<h4>Ingrese los datos del nuevo vendedor:</h4>");
                out.println("<br/>");
                out.println("<form name=\"datos\" action=\"finalizar_insertar_vendedor\" method=\"Post\">");
                out.println("Rut:");
                out.println("<input id=\"txtRut\" name=\"txtRut\" type=\"text\" /><br/>");
                out.println("Contraseña:");
                out.println("<input id=\"txtContrasena\" name=\"txtContrasena\" type=\"password\" /><br/>");
                out.println("Repetir contraseña:");
                out.println("<input id=\"txtContrasena2\" name=\"txtContrasena2\" type=\"password\" /><br/>");
                out.println("Nombre:");
                out.println("<input id=\"txtNombre\" name=\"txtNombre\" type=\"text\" /><br/>");
                out.println("<input id=\"botonGuardar\" value=\"Finalizar\" type=\"submit\" />");
                out.println("</form>");
                out.println("<br/>");
            
                out.println("<form name=\"datos\" action=\"adminMenu\" method=\"Post\">");
                out.println("<input id=\"botonVolver\" value=\"Volver\" type=\"submit\" />");
                out.println("</form>");
            
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
            
        }else{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Ingresar Vendedor:</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h5>" + user + " como: administrador.</h5>");
                out.println("<h4>Ingrese los datos del nuevo vendedor:</h4>");
                out.println("<br/>");
                out.println("<form name=\"datos\" action=\"finalizar_insertar_vendedor\" method=\"Post\">");
                out.println("Rut:");
                out.println("<input id=\"txtRut\" name=\"txtRut\" type=\"text\" value=\""+rut+"\"/><br/>");
                out.println("Contraseña:");
                out.println("<input id=\"txtContrasena\" name=\"txtContrasena\" type=\"password\" value=\""+contrasena+"\"/><br/>");
                out.println("Repetir contraseña:");
                out.println("<input id=\"txtContrasena2\" name=\"txtContrasena2\" type=\"password\" value=\""+contrasena2+"\"/><br/>");
                out.println("Nombre:");
                out.println("<input id=\"txtNombre\" name=\"txtNombre\" type=\"text\" value=\""+nombre+"\"/><br/>");
                out.println("<input id=\"botonGuardar\" value=\"Finalizar\" type=\"submit\" />");
                out.println("</form>");
                out.println("<br/>");
            
                out.println("<form name=\"datos\" action=\"adminMenu\" method=\"Post\">");
                out.println("<input id=\"botonVolver\" value=\"Volver\" type=\"submit\" />");
                out.println("</form>");
            
                out.println("</body>");
                out.println("</html>");
            } finally {
                out.close();
            }
            JOptionPane.showMessageDialog(null, "Contraseñas no coinciden");
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
