/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import OracleConection.Main;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
@WebServlet(name = "Entrar", urlPatterns = {"/Entrar"})
public class Entrar extends HttpServlet {
    
    static Connection conn=null;
    static Statement st=null;
    static ResultSet rs=null;
    static String user;
    static String pass;
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
        user = request.getParameter("txtUserName");
        pass = request.getParameter("txtPassword");
        try {
            int rowcount=0;
            conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            
            //para hacer consulta
            rs=st.executeQuery("SELECT tipo FROM usuario WHERE nombre='" + user + "' AND contrasena='" + pass + "'");
            
            /* Para contar filas de una tabla
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst(); // no rs.first() porque luego se saltaría el primer elemento
            }
            */
            
            /* SUPUESTO: Los nombres de los usuarios no se repiten. Si se repitieran, en el peor de los casos se pueden tener
             * usuarios de igual nombre y contraseña, entonces el login tendria que ser ingresando el rut del usuario (PK).
             * Ante nombres y contraseñas iguales entre usuarios, el siguiente codigo responderia al primer usuario encontrado.
             */
            rs.beforeFirst();
            while (rs.next()) {
                if(rs.getString(1).equals("admin")){
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
            out.println("<h5>" + user + " como: admin.</h5>");
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
                    //response.sendRedirect("admin_menu.jsp");
                }
                else if(rs.getString(1).equals("vendedor")){
                    response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Vendedor</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: vendedor.</h5>");
            out.println("<form name=\"ingresar_cliente\" action=\"ingresar_cliente\">");
            out.println("<input id=\"boton_ing_cliente\" value=\"Ingresar Cliente\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"ingresar_venta\" action=\"ingresar_venta\">");
            out.println("<input id=\"boton_ingr_venta\" value=\"Ingresar Venta\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"ver_ventas\" action=\"ver_ventas\">");
            out.println("<input id=\"boton_ver_ventas\" value=\"Ver Ventas por Cliente\" type=\"submit\" />");
            out.println("</form>");
            out.println("<form name=\"mis_ventas\" action=\"mis_ventas\">");
            out.println("<input id=\"boton_mis_ventas\" value=\"Mis Ventas\" type=\"submit\" />");
            out.println("</form>");
            out.println("<br/>");
            out.println("<a href=\"login.jsp\">volver</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
                    //response.sendRedirect("vendedor_menu.jsp");
                }
                else {
                    response.sendRedirect("menu.jsp"); //En caso de que el usuario buscado sea de tipo distinto de 'admin' o 'vendedor'
                }
            }
            response.sendRedirect("menu.jsp"); //Si en la consulta no se encuentra ningun registro del nombre buscado, no se entra al while anterior y el usuario buscado no existe.

            /*El WHILE anterior no es realmente necesario para el caso, pues en la consulta debiese encontrarse solo un
             * registro/usuario, sin embargo lo dejo escrito de esa forma para usarla despues (cuando se necesite de esa forma)
             */
            
            /*
            jtxtrut.setText(rs.getString(1));
            jtxtcontrasena.setText(rs.getString(2));
            jtxtnombre.setText(rs.getString(3));
            jtxttipo.setText(rs.getString(4));
            jtxtcomision.setText(rs.getString(5));
            rut=jtxtrut.getText();
            */
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        /*Respaldo de lo que había antes:
        if(user.equals("admin")){
            response.sendRedirect("admin_menu.jsp");
        }
        else if(user.equals("vendedor")){
            response.sendRedirect("vendedor_menu.jsp");
        }
        else {
            response.sendRedirect("menu.jsp");
        }
        */
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
