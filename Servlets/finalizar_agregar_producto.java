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
@WebServlet(name = "finalizar_agregar_producto", urlPatterns = {"/finalizar_agregar_producto"})
public class finalizar_agregar_producto extends HttpServlet {

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
        String cod = request.getParameter("txtCodigo");
        String nombre = request.getParameter("txtNombre");
        
        String categoria = request.getParameter("txtCategoria");
        String cantidad = request.getParameter("txtCantidad");
        String precio = request.getParameter("txtPrecio");
        
        int cod_int = Integer.parseInt(cod);
        int cantidad_int = Integer.parseInt(cantidad);
        int precio_int = Integer.parseInt(precio);
        
        try{
            conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            
            rs = st.executeQuery("insert into producto (id_producto, stock, descripcion, categoria, precio) values ("+cod_int+","+cantidad_int+", '"+nombre+"', '"+categoria+"', "+precio+")");
        }catch(Exception ex){
            
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agregar producto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: " + tipo + ".</h5><br/>");
            out.println("<h3>Ingrese los datos del nuevo producto:</h3>");
            out.println("<form name=\"datos_nuevo_producto\" action=\"finalizar_agregar_producto\" method=\"Post\">");
            out.println("Código del producto: ");
            out.println("<input id=\"txtCodigo\" name=\"txtCodigo\" type=\"text\" /><br/>");
            out.println("Nombre: ");
            out.println("<input id=\"txtNombre\" name=\"txtNombre\" type=\"text\" /><br/>");
            
            out.println("Categoría: ");
            out.println("<input id=\"txtCategoria\" name=\"txtCategoria\" type=\"text\" /><br/>");
            out.println("Cantidad: ");
            out.println("<input id=\"txtCantidad\" name=\"txtCantidad\" type=\"text\" /><br/>");
            out.println("Precio: ");
            out.println("<input id=\"txtPrecio\" name=\"txtPrecio\" type=\"text\" /><br/>");
            out.println("<input id=\"botonGuardar\" value=\"Agregar\" type=\"submit\" />");
            out.println("</form>");
            out.println("<br/><a href=\"administrar_productos\">volver</a>");
            out.println("<br/><h4>Producto agregado correctamente!</h4>");
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
