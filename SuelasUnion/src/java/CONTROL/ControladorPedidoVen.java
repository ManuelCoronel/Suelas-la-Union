/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.exceptions.NonexistentEntityException;
import static NEGOCIO.FacturaVenta.calcularFactura;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
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
 * @author MANUEL
 */
@WebServlet(name = "ControladorPedidoVen", urlPatterns = {"/ControladorPedidoVen"})
public class ControladorPedidoVen extends HttpServlet {

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

    public void buscarPedidosVen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DAO.PedidoDeVentaJpaController pedidosDAO = new DAO.PedidoDeVentaJpaController();
        List<DTO.PedidoDeVenta> pedidos = pedidosDAO.findPedidoDeVentaEntities();
        request.getSession().setAttribute("pedidosBuscadosVenta", pedidos);
        DAO.EstadoJpaController estadosDAO = new DAO.EstadoJpaController();
        List<DTO.Estado> listaEstados = estadosDAO.findEstadoEntities();
        request.getSession().setAttribute("tiposDeEstados", listaEstados);
        response.sendRedirect("vistas/jsp/listaPedidoVen.jsp");
    }

    public void editarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DAO.PedidoDeVentaJpaController pedidoDAO = new DAO.PedidoDeVentaJpaController();
        DTO.PedidoDeVenta pedido = pedidoDAO.findPedidoDeVenta(id);
        request.getSession().setAttribute("pedidoEditar", pedido);
        DAO.TipoEntregaJpaController tipos = new DAO.TipoEntregaJpaController();
        List<DTO.TipoEntrega> lista = tipos.findTipoEntregaEntities();
        request.getSession().setAttribute("tiposDeEntrega", lista);
        DAO.EstadoJpaController estadosDAO = new DAO.EstadoJpaController();
        List<DTO.Estado> listaEstados = estadosDAO.findEstadoEntities();
        request.getSession().setAttribute("tiposDeEstados", listaEstados);
        response.sendRedirect("vistas/jsp/editarPedidoDeVenta.jsp");
    }

    public void editarPedidoDeVenta(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, Exception {
        int idEstado = Integer.parseInt(request.getParameter("opcionesTipoDeEstados"));
        String cedula = request.getParameter("cedula");
        DAO.ClienteJpaController clienteDAO = new DAO.ClienteJpaController();
        DAO.PedidoDeVentaJpaController pedidoDAO = new DAO.PedidoDeVentaJpaController();
        DAO.TipoEntregaJpaController tipoEntregaDAO = new DAO.TipoEntregaJpaController();
        DAO.EstadoJpaController estadosDAO = new DAO.EstadoJpaController();
        DTO.Estado estadoDTO = estadosDAO.findEstado(idEstado);
        DTO.PedidoDeVenta pedido = (DTO.PedidoDeVenta) request.getSession().getAttribute("pedidoEditar");
        DTO.TipoEntrega tiposEntrega = tipoEntregaDAO.findTipoEntrega(Integer.parseInt(request.getParameter("opcionesTipoEntrega")));
        DTO.Cliente cliente = clienteDAO.findCliente(cedula);
        Date fechaOrden = Date.valueOf(request.getParameter("fechaOrden"));
        Date fechaFactura = Date.valueOf(request.getParameter("fechaFactura"));
        Date fechaPlazo = Date.valueOf(request.getParameter("fechaPlazo"));
        pedido.setClienteCedula(cliente);
        pedido.setFechaOrden(fechaOrden);
        pedido.setFechaEntregaFactura(fechaFactura);
        pedido.setFechaPlazo(fechaPlazo);
        pedido.setEstadoId(estadoDTO);
        pedido.setTipoEntregaId(tiposEntrega);
        pedidoDAO.edit(pedido);
        buscarPedidosVen(request, response);
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
        if (action.equalsIgnoreCase("listar")) {
            buscarPedidosVen(request, response);
        }
        if (action.equalsIgnoreCase("editar")) {
            editarPedido(request, response);
        }
        if (action.equalsIgnoreCase("EditarPedidoVenta")) {
            try {
                editarPedidoDeVenta(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoVen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("cargarTiposEntrega")) {
            cargarTipoDeEntrega(request, response);
        }
        if (action.equalsIgnoreCase("AgregarProductoVenta")) {
            irDepartamento(request, response);
        }
        if (action.equals("GenerarPedidoVen")) {
            GenerarPedidoVen(request, response);
        }
        if (action.equalsIgnoreCase("eliminarProducto")) {
            try {
                eliminarProductoPedido(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorPedidoVen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoVen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("Agregar")) {
            try {
                AgregarProductoPedido(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoVen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarProductoPedido(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, IOException, Exception {
        int productoId = Integer.parseInt(request.getParameter("id"));
        int pedidoId = Integer.parseInt(request.getSession().getAttribute("pedidoVentaActual") + "");
        DAO.ProductoJpaController productoDAO = new DAO.ProductoJpaController();
        DAO.ProductoPedidoVentaJpaController productoVentaDAO = new DAO.ProductoPedidoVentaJpaController();
        DTO.Producto productoDTO = productoDAO.findProducto(productoId);
        DTO.ProductoPedidoVentaPK productoVentaDTO = new DTO.ProductoPedidoVentaPK(productoId, pedidoId);
        DTO.ProductoPedidoVenta productoVenta = productoVentaDAO.findProductoPedidoVenta(productoVentaDTO);
        productoDTO = productoDAO.findProducto(productoId);
        productoDTO.setCantidadDisponible(productoDTO.getCantidadDisponible() + productoVenta.getCantidad());
        productoDAO.edit(productoDTO);
        DTO.ProductoPedidoVentaPK producto = new DTO.ProductoPedidoVentaPK(productoId, pedidoId);
        productoVentaDAO.destroy(producto);
        listaProductosVenActual(request, response);
        response.sendRedirect("vistas/jsp/productoPedidoVenta.jsp");
    }

    public void irDepartamento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String producto = request.getParameter("productoTomado");
        if (producto.equalsIgnoreCase("1")) {
            response.sendRedirect("ControladorPlantilla?accion=buscar");
        }
        if (producto.equalsIgnoreCase("2")) {
            response.sendRedirect("ControladorSuelas?accion=buscar&tipo=2");
        }
        if (producto.equalsIgnoreCase("3")) {
            response.sendRedirect("ControladorSuelas?accion=buscar&tipo=3");
        }
        if (producto.equalsIgnoreCase("4")) {
            response.sendRedirect("ControladorTira?accion=buscar");
        }
        if (producto.equalsIgnoreCase("5")) {
            response.sendRedirect("ControladorSalpa?accion=listar");
        }
    }

    public void cargarTipoDeEntrega(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DAO.TipoEntregaJpaController tipos = new DAO.TipoEntregaJpaController();
        List<DTO.TipoEntrega> lista = tipos.findTipoEntregaEntities();
        request.getSession().setAttribute("tiposDeEntrega", lista);
        response.sendRedirect("vistas/jsp/crearPedidoDeVenta.jsp");
    }

    public void GenerarPedidoVen(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedula");
        DAO.ClienteJpaController clienteDAO = new DAO.ClienteJpaController();
        DAO.PedidoDeVentaJpaController pedidoDAO = new DAO.PedidoDeVentaJpaController();
        DAO.EstadoJpaController estadoDAO = new DAO.EstadoJpaController();
        DAO.TipoEntregaJpaController tipoEntregaDAO = new DAO.TipoEntregaJpaController();
        DTO.PedidoDeVenta pedido = new DTO.PedidoDeVenta();
        DTO.Personal personal = (DTO.Personal) request.getSession().getAttribute("personalActual");
        DTO.TipoEntrega tiposEntrega = tipoEntregaDAO.findTipoEntrega(Integer.parseInt(request.getParameter("opcionesTipoEntrega")));
        DTO.Cliente cliente = clienteDAO.findCliente(cedula);
        Date fechaOrden = Date.valueOf(request.getParameter("fechaOrden"));
        Date fechaFactura = Date.valueOf(request.getParameter("fechaFactura"));
        Date fechaPlazo = Date.valueOf(request.getParameter("fechaPlazo"));
        pedido.setClienteCedula(cliente);
        pedido.setFechaOrden(fechaOrden);
        pedido.setFechaEntregaFactura(fechaFactura);
        pedido.setFechaPlazo(fechaPlazo);
        pedido.setPersonalId(personal);
        pedido.setEstadoId(estadoDAO.findEstado(1));
        pedido.setTipoEntregaId(tiposEntrega);
        pedidoDAO.create(pedido);
        request.getSession().setAttribute("pedidoVentaActual", pedidoDAO.findPedidoDeVentaEntities().get(pedidoDAO.findPedidoDeVentaEntities().size() - 1).getId());
        response.sendRedirect("vistas/jsp/productoPedidoVenta.jsp");
    }

    public void buscarPedidoPorParametros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedula");
        int estado = Integer.parseInt(request.getParameter("opcionesTipoDeEstados"));
        try {
            List<DTO.PedidoDeVenta> pedidos = NEGOCIO.PedidoDeVentaNegocio.busquedaPedidosPorParametros(cedula, estado);
            if (pedidos == null) {
                pedidos = new ArrayList<>();
            }
            request.getSession().setAttribute("pedidosBuscadosVenta", pedidos);
            response.sendRedirect("vistas/jsp/listaPedidoVen.jsp");
        } catch (IOException e) {
            buscarPedidosVen(request, response);
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
            buscarPedidoPorParametros(request, response);
        }
        if (action.equalsIgnoreCase("AgregarProductoVenta")) {
            irDepartamento(request, response);
        }
        if (action.equalsIgnoreCase("finalizarPedido")) {
            try {
                finalizarPedido(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoVen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void finalizarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException, Exception {
        int id = Integer.parseInt((request.getSession().getAttribute("pedidoVentaActual") + ""));
        double totalPedido = calcularFactura(id);
        DAO.PedidoDeVentaJpaController pedidoDAO = new DAO.PedidoDeVentaJpaController();
        DTO.PedidoDeVenta pedido = pedidoDAO.findPedidoDeVenta(id);
        pedido.setTotal(totalPedido);
        pedidoDAO.edit(pedido);
        request.getSession().removeAttribute("pedidoVentaActual");
        request.getSession().removeAttribute("listaPPVActual");
        response.sendRedirect("ControladorPedidoVen?accion=listar");
    }

    public void AgregarProductoPedido(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException, Exception {
        DAO.ProductoJpaController productoDAO = new DAO.ProductoJpaController();
        DAO.ProductoPedidoVentaJpaController productosPedidosDAO = new DAO.ProductoPedidoVentaJpaController();
        DAO.PedidoDeVentaJpaController pedidoVentaDAO = new DAO.PedidoDeVentaJpaController();
        DTO.ProductoPedidoVenta productoPedido = new DTO.ProductoPedidoVenta();
        double cantidad = Double.parseDouble((request.getParameter("cantidadLlevar")));
        int productoId = Integer.parseInt(request.getParameter("id"));
        DTO.Producto producto = productoDAO.findProducto(productoId);
        int idPedidoVenta = Integer.parseInt(request.getSession().getAttribute("pedidoVentaActual") + "");
        DTO.PedidoDeVenta pedidoActual = pedidoVentaDAO.findPedidoDeVenta(idPedidoVenta);
        producto.setCantidadDisponible(producto.getCantidadDisponible() - cantidad);
        productoDAO.edit(producto);
        productoPedido.setCantidad(cantidad);
        productoPedido.setPedidoDeVenta(pedidoActual);
        productoPedido.setProducto(producto);
        productoPedido.setTotal(cantidad * producto.getPrecio());
        try {
            productosPedidosDAO.create(productoPedido);
        } catch (Exception ex) {
            Logger.getLogger(ControladorPedidoVen.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaProductosVenActual(request, response);
        response.sendRedirect("vistas/jsp/productoPedidoVenta.jsp");
    }

    public void listaProductosVenActual(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = (int) request.getSession().getAttribute("pedidoVentaActual");
        DAO.PedidoDeVentaJpaController pedidosDAO = new DAO.PedidoDeVentaJpaController();
        List<DTO.ProductoPedidoVenta> pedidos = pedidosDAO.findPedidoDeVenta(id).getProductoPedidoVentaList();
        request.getSession().setAttribute("listaPPVActual", pedidos);
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
