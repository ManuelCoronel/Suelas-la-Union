/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ProductoPedidoVentaJpaController;
import DTO.ProductoPedidoVenta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author CAMILA
 */
@WebServlet(name = "ControladorProductoPedidoVenta", urlPatterns = {"/ControladorProductoPedidoVenta"})
public class ControladorProductoPedidoVenta extends HttpServlet {

    ProductoPedidoVentaJpaController ppvDAO = new ProductoPedidoVentaJpaController();

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

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    public void listarProductoPedidoVenta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DAO.PedidoDeVentaJpaController pedidoDAO = new DAO.PedidoDeVentaJpaController();
        String id = (request.getParameter("id"));
        List<ProductoPedidoVenta> productos = new ArrayList<>();
        if (id != null) {
            if (id.equalsIgnoreCase("")) {
                productos = (List<ProductoPedidoVenta>) ppvDAO.findProductoPedidoVentaEntities();
            } else {
                productos = (List<ProductoPedidoVenta>) pedidoDAO.findPedidoDeVenta(Integer.parseInt(id)).getProductoPedidoVentaList();
            }
        } else {
            productos = (List<ProductoPedidoVenta>) ppvDAO.findProductoPedidoVentaEntities();
        }
        request.getSession().setAttribute("listaPPV", productos);
        response.sendRedirect("vistas/jsp/listaProductoPedidoVenta.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listarProductoPedidoVenta(request, response);
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
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("buscar_pedido")) {
            listarProductoPedidoVenta(request, response);
        }
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
