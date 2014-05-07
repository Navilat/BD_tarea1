/*
 * To change this template, choose Tools | Templates
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
import javax.swing.*;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "finalizar_insertar_compra", urlPatterns = {"/finalizar_insertar_compra"})
public class finalizar_insertar_compra extends HttpServlet {
    static int gate=0;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int producto = Integer.parseInt(request.getParameter("txtProducto"));
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        int precio = Integer.parseInt(request.getParameter("txtPrecio"));
        int new_id_detalle=0;
        int new_id_compra=0;
        
        
        
         //Se obtiene el id_compra (fk) asociado al detalle
            try{
                rs = st.executeQuery("SELECT id_compra FROM compra");
                //ver si tabla esta vacia.
                rs.last();
                if (rs.getRow()==0){
                    new_id_compra=1;
                }else{
                    new_id_compra=Integer.parseInt(rs.getString(1))+1;
                }
                JOptionPane.showMessageDialog(null, "new_id_compra: "+new_id_compra);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        
        
        try{
                rs = st.executeQuery("SELECT id_detalle FROM detalle_compra");
                //ver si tabla esta vacia.
                rs.last();
                if (rs.getRow()==0){
                    new_id_detalle=1;
                }else{
                    new_id_detalle=Integer.parseInt(rs.getString(1))+1;
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        
        try{
            /*
            conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            */
            rs = st.executeQuery("insert into DETALLE_COMPRA (ID_DETALLE, ID_COMPRA, ID_PRODUCTO, CANTIDAD, PRECIO) values ("+new_id_detalle+","+new_id_compra+","+producto+","+cantidad+","+precio+")");
            JOptionPane.showMessageDialog(null, "Producto agregado a la compra.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ingresar Compra:</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: administrador.</h5>");
            out.println("<h4>Ingrese el detalle de la compra:</h4>");
            out.println("<br/>");
            out.println("<form name=\"datos\" action=\"finalizar_insertar_compra\" method=\"Post\">");
            out.println("Producto:");
            out.println("<input id=\"txtProducto\" name=\"txtProducto\" type=\"text\" /><br/>");
            out.println("Cantidad:");
            out.println("<input id=\"txtCantidad\" name=\"txtCantidad\" type=\"text\" /><br/>");
            out.println("Precio:");
            out.println("<input id=\"txtPrecio\" name=\"txtPrecio\" type=\"text\" /><br/>");
           
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
