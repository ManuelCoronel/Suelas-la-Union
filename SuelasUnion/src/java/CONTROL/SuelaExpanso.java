/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANUEL
 */
@WebServlet(name = "SuelaExpanso", urlPatterns = {"/SuelaExpanso"})
public class SuelaExpanso extends HttpServlet {
//  ProductoDAO pdao = new ProductoDAO();
//    ProductoDTO pdto = new ProductoDTO();
//    SuelasDAO dao = new SuelasDAO();
//    SuelasDTO dto = new SuelasDTO();

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SuelaExpanso</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SuelaExpanso at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void listarSuelas(HttpServletRequest request, HttpServletResponse response) {
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
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("listar")) {
            String modelo, color, talla;
            modelo = request.getParameter("modelo");
            talla = request.getParameter("talla");
            color = request.getParameter("color_id");
            response.sendRedirect("vistas/html/suelasExpanso.jsp?talla=" + talla + "&modelo=" + modelo);
        } else if (accion.equalsIgnoreCase("toList")) {
            response.sendRedirect("vistas/html/suelaExpanso.jsp");
        } else if (accion.equalsIgnoreCase("formeditar")) {
            response.sendRedirect("vistas/html/actualizarSuelas.jsp");
        } else if (accion.equalsIgnoreCase("Actualizar")) {
            int productoId = Integer.parseInt(request.getParameter("id"));
            String modelo = request.getParameter("modelo");
            String talla = request.getParameter("talla");
            double cantidad = Double.parseDouble(request.getParameter("cantidad"));
            double precio = Double.parseDouble(request.getParameter("precio"));
            response.sendRedirect("vistas/html/suelasExpanso.jsp");
        }
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
