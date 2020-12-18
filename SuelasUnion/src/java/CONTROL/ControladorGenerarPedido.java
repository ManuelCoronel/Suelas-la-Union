/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.EstadoJpaController;
import DAO.PedidoDeVentaJpaController;
import DAO.ProductoPedidoVentaJpaController;
import DTO.Cliente;
import DTO.Estado;
import DTO.PedidoDeVenta;
import DTO.Personal;
import DTO.Producto;
import DTO.ProductoPedidoVenta;
import DTO.TipoEntrega;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ronald
 */
@WebServlet(name = "ControladorGenerarPedido", urlPatterns = {"/ControladorGenerarPedido"})
public class ControladorGenerarPedido extends HttpServlet {

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
            out.println("<title>Servlet ControladorGenerarPedido</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorGenerarPedido at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void removeCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        request.getSession().removeAttribute("subTotalCarrito");
        request.getSession().removeAttribute("productosEnCarrito");
        request.getSession().removeAttribute("productosCarrito");
    }

    protected void generar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Cliente cliente = (Cliente) request.getSession().getAttribute("personalActual");
        List<Producto> productosCarrito = (List<Producto>) request.getSession().getAttribute("productosCarrito");
        List<Integer> cantidadesCarrito = (List<Integer>) request.getSession().getAttribute("listaCantidad");
        Estado estado = new Estado();
        Personal personal = new Personal();
        TipoEntrega tipoEntrega = new TipoEntrega();
        double total = Double.parseDouble(request.getSession().getAttribute("subTotalCarrito") + "");
        PedidoDeVentaJpaController pedidoVenta = new PedidoDeVentaJpaController();
        PedidoDeVenta pedido = new PedidoDeVenta();
        java.util.Date fecha = new Date();
        pedido.setFechaOrden(fecha);
        pedido.setTotal(total);
        pedido.setClienteCedula(cliente);
        personal.setId(3);
        pedido.setPersonalId(personal);
        estado.setId(1);
        pedido.setEstadoId(estado);
        tipoEntrega.setId(2);
        pedido.setTipoEntregaId(tipoEntrega);
        pedidoVenta.create(pedido);
        List<PedidoDeVenta> listaPedidos = pedidoVenta.findPedidoDeVentaEntities();
        pedido = listaPedidos.get(listaPedidos.size() - 1);
        ProductoPedidoVentaJpaController ProductoPedidos = new ProductoPedidoVentaJpaController();
        int pos = 0;
        for (Producto p : productosCarrito) {
            ProductoPedidoVenta pp = new ProductoPedidoVenta();
            pp.setTotal(total);
            pp.setCantidad((double) cantidadesCarrito.get(pos));
            pp.setPedidoDeVenta(pedido);
            pp.setProducto(p);
            ProductoPedidos.create(pp);
        }
        removeCarrito(request, response);
        actualizarUser(request, response);
    }

    protected void actualizarUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EstadoJpaController estadodao = new EstadoJpaController();
        List<Estado> estados = estadodao.findEstadoEntities();
        Cliente cliente = (Cliente) request.getSession().getAttribute("personalActual");
        DAO.ClienteJpaController clienteDAO = new DAO.ClienteJpaController();
        cliente = clienteDAO.findCliente(cliente.getCedula());
        request.getSession().setAttribute("tiposDeEstados", estados);
        request.getSession().setAttribute("personalActual", cliente);
        response.sendRedirect("vistas/jsp/compras.jsp");
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
        if (accion.equalsIgnoreCase("generarPedido")) {
            try {
                generar(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorGenerarPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
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
