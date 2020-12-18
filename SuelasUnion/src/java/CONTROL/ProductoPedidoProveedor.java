/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ProductoPedidoProveedorJpaController;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ProductoPedidoProveedor", urlPatterns = {"/ProductoPedidoProveedor"})
public class ProductoPedidoProveedor extends HttpServlet {

    ProductoPedidoProveedorJpaController pppDAO = new ProductoPedidoProveedorJpaController();

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
    public void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DTO.ProductoPedidoProveedor> ppp = (List<DTO.ProductoPedidoProveedor>) pppDAO.findProductoPedidoProveedorEntities();
        request.getSession().setAttribute("listaProductosPedidoProveedor", ppp);
        response.sendRedirect("vistas/jsp/productoPedidoProveedor.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listar(request, response);
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
        if (action.equalsIgnoreCase("buscar")) {
            buscarPorId(request, response);
        }
    }

    public void buscarPorId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DTO.ProductoPedidoProveedor> ppp;
        DAO.ProductoPedidoProveedorJpaController pedidosTodo = new DAO.ProductoPedidoProveedorJpaController();
        String param = request.getParameter("idPedido");
        if (param == null || param.equalsIgnoreCase("")) {
            ppp = (List<DTO.ProductoPedidoProveedor>) pedidosTodo.findProductoPedidoProveedorEntities();
        } else {
            int id = Integer.parseInt(param);
            DAO.PedidoProveedorJpaController pedidosProv = new DAO.PedidoProveedorJpaController();
            ppp = (List<DTO.ProductoPedidoProveedor>) pedidosProv.findPedidoProveedor(id).getProductoPedidoProveedorList();
        }
        request.getSession().setAttribute("listaProductosPedidoProveedor", ppp);
        response.sendRedirect("vistas/jsp/productoPedidoProveedor.jsp");
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
