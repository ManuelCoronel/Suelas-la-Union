/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ProductoPedidoVentaJpaController;
import DTO.Producto;
import DTO.ProductoPedidoVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ..
 */
@WebServlet(name = "ControladorDetalles", urlPatterns = {"/ControladorDetalles"})
public class ControladorDetalles extends HttpServlet {

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
            out.println("<title>Servlet ControladorDetalles</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDetalles at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("detalles")) {
            verDetalles(request, response);
        }
    }

    public void verDetalles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idPedido = Integer.parseInt(request.getParameter("id"));
        ProductoPedidoVentaJpaController p = new ProductoPedidoVentaJpaController();
        List<ProductoPedidoVenta> list = p.findProductoPedidoVentaEntities();
        List<Producto> listCompleta = new ArrayList<>();
        List<ProductoPedidoVenta> listCompletaPedido = new ArrayList<>();
        for (ProductoPedidoVenta productoPedidoVenta : list) {
            if (productoPedidoVenta.getPedidoDeVenta().getId().equals(idPedido)) {
                listCompleta.add(productoPedidoVenta.getProducto());
                listCompletaPedido.add(productoPedidoVenta);
            }
        }
        request.getSession().setAttribute("listCompletaPedido", listCompletaPedido);
        request.getSession().setAttribute("listaCompleta", listCompleta);
        response.sendRedirect("vistas/jsp/detallesCompras.jsp");
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
