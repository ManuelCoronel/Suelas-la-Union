/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ClienteJpaController;
import DAO.PedidoDeVentaJpaController;
import DAO.ProductoJpaController;
import DAO.ProductoPedidoVentaJpaController;
import DTO.PedidoDeVenta;
import DTO.Producto;
import DTO.ProductoPedidoVenta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ronald
 */
@WebServlet(name = "ControladorDashboard", urlPatterns = {"/ControladorDashboard"})
public class ControladorDashboard extends HttpServlet {

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
            out.println("<title>Servlet ControladorDashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDashboard at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void totalVentas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PedidoDeVentaJpaController pedido = new PedidoDeVentaJpaController();
        List<PedidoDeVenta> pedidosLista = pedido.findPedidoDeVentaEntities();
        double totalVenta = 0;
        for (PedidoDeVenta p : pedidosLista) {
            totalVenta += p.getTotal();
        }
        request.getSession().setAttribute("totalventas", totalVenta);
        //response.sendRedirect("vistas/jsp/reporteGeneral.jsp");
    }

    public void totalDepartamentos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoPedidoVentaJpaController pedidos = new ProductoPedidoVentaJpaController();
        List<ProductoPedidoVenta> pedidosLista = pedidos.findProductoPedidoVentaEntities();
        double total1 = 0, total2 = 0, total3 = 0, total4 = 0, total5 = 0;
        for (ProductoPedidoVenta p : pedidosLista) {
            ProductoJpaController producto = new ProductoJpaController();
            Producto pro = producto.findProducto(p.getProducto().getId());
            int tipoPro = Integer.parseInt(pro.getTipoProducto());
            switch (tipoPro) {
                case 1:
                    total1 += p.getTotal();
                    break;
                case 2:
                    total2 += p.getTotal();
                    break;
                case 3:
                    total3 += p.getTotal();
                    break;
                case 4:
                    total4 += p.getTotal();
                    break;
                case 5:
                    total5 += p.getTotal();
                    break;
            }
            request.getSession().setAttribute("total1", total1);
            request.getSession().setAttribute("total2", total2);
            request.getSession().setAttribute("total3", total3);
            request.getSession().setAttribute("total4", total4);
            request.getSession().setAttribute("total5", total5);
        }
    }

    public void totalProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoJpaController productos = new ProductoJpaController();
        List<Producto> pros = productos.findProductoEntities();
        double totalProductosDisponibles = 0;
        for (Producto p : pros) {
            totalProductosDisponibles += p.getCantidadDisponible();
        }
        request.getSession().setAttribute("totalProductosDisponibles", totalProductosDisponibles);
    }

    public void totalClientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClienteJpaController cliente = new ClienteJpaController();
        int totalClientes = cliente.getClienteCount();
        request.getSession().setAttribute("totalClientes", totalClientes);
    }

    public void mostrarUltimosAgregados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductoJpaController productoDAO = new ProductoJpaController();
        List<Producto> k = productoDAO.findProductoEntities();
        request.getSession().setAttribute("mostrarCincoUltimos", k);
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
        if (action.equalsIgnoreCase("inicio")) {
            mostrarUltimosAgregados(request, response);
            totalVentas(request, response);
            totalProductos(request, response);
            totalClientes(request, response);
            response.sendRedirect("vistas/jsp/dashboard.jsp");
        }
        if (action.equalsIgnoreCase("reportegeneral")) {
            totalDepartamentos(request, response);
            response.sendRedirect("vistas/jsp/reporteGeneral.jsp");
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
