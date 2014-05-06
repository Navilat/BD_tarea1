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
@WebServlet(name = "editar_producto", urlPatterns = {"/editar_producto"})
public class editar_producto extends HttpServlet {

    static String ID;
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
        
        ID = request.getParameter("txtID");
        int ID_int = Integer.parseInt(ID);
        String nombre;
        String precio;
        String cantidad;
        String categoria;
        
        int precio_int;
        int cantidad_int;
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Editar producto:</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: " + tipo + ".</h5><br/>");
            out.println("<h3>Modifique los campos a editar: </h3>");
            out.println("<form name=\"editar\" action=\"finalizar_editar_producto\" method=\"Post\">");
            out.println("<ul>");
            out.println("<li>ID : "+ID_int+"</li>");
            try{
        
            conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            
            rs = st.executeQuery("select * from producto where id_producto="+ID_int+"");
            rs.next();
            cantidad = rs.getString("stock");
            nombre = rs.getString("descripcion");
            categoria = rs.getString("categoria");
            precio = rs.getString("precio");
            
        
            
            out.println("<li>Stock : "+cantidad+"</li>");
            out.println("<li>Nombre : <input id=\"nombre_nuevo\" name=\"nombre_nuevo\" type=\"text\" value=\""+nombre+"\" /></li>");
            out.println("<li>Categoria : <input id=\"categoria_nueva\" name=\"categoria_nueva\" type=\"text\" value=\""+categoria+"\" /></li>");
            out.println("<li>Precio : <input id=\"precio_nuevo\" name=\"precio_nuevo\" type=\"text\" value=\""+precio+"\" /></li>");
            out.println("</ul>");
            }catch(Exception ex){
            
        }
            out.println("</br><input name=\"boton_editar\" value\"Guardar\" type=\"submit\" />");
            out.println("</form>");
            out.println("</br><a href=\"administrar_productos\">volver</a>");
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
