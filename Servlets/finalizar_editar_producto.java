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
import static Servlets.editar_producto.ID;
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
@WebServlet(name = "finalizar_editar_producto", urlPatterns = {"/finalizar_editar_producto"})
public class finalizar_editar_producto extends HttpServlet {

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
        
        String nombre_nuevo = request.getParameter("nombre_nuevo");
        String categoria_nueva = request.getParameter("categoria_nueva");
        String precio_nuevo = request.getParameter("precio_nuevo");
        int ID_int = Integer.parseInt(ID);
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet finalizar_editar_producto</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h5>" + user + " como: " + tipo + ".</h5><br/>");
            try{
                conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            
            rs = st.executeQuery("select * from producto where id_producto="+ID_int+"");
            rs.next();
            
            out.println("Datos anteriores: </br>");
            out.println("<ul>");
            out.println("<li>ID : "+ID+"</li>");
            out.println("<li>Stock : "+rs.getString("stock")+"</li>");
            out.println("<li>Nombre : "+rs.getString("descripcion")+"</li>");
            out.println("<li>Categoria : "+rs.getString("categoria")+"</li>");
            out.println("<li>Precio : $"+rs.getString("precio")+"</li>");
            out.println("</ul></br>");
            out.println("Datos nuevos: </br>");
            out.println("<ul>");
            out.println("<li>ID : "+ID+"</li>");
            out.println("<li>Stock : "+rs.getString("stock")+"</li>");
            out.println("<li>Nombre : "+nombre_nuevo+"</li>");
            out.println("<li>Categoria : "+categoria_nueva+"</li>");
            out.println("<li>Precio : $"+precio_nuevo+"</li>");
            out.println("</ul>");
            
            }catch(Exception ex){
                
            }
            
            out.println("</br><a href=\"administrar_productos\">volver</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
        
        try{
        
                conn=Main.Enlace(conn);
            st=Main.sta(st);
            rs=Main.EnlEst(rs);
            rs.next();
            
            rs = st.executeQuery("update producto set descripcion='"+nombre_nuevo+"', categoria='"+categoria_nueva+"', precio='"+precio_nuevo+"' where id_producto="+ID+"");
            
            
        }catch(Exception ex){
            
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
