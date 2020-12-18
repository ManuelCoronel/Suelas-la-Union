/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.DevolucionClienteJpaController;
import DAO.PedidoDeVentaJpaController;
import DTO.DevolucionCliente;
import DTO.PedidoDeVenta;
import static NEGOCIO.DevolucionNegocio.busquePorParametros;
import java.io.IOException;
import java.sql.Date;
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
 * @author CAMILA
 */
@WebServlet(name = "ControladorDevolucionPedidoVenta", urlPatterns = {"/ControladorDevolucionPedidoVenta"})
public class ControladorDevolucionPedidoVenta extends HttpServlet {

    DevolucionClienteJpaController devolucionClieneDAO = new DevolucionClienteJpaController();
    PedidoDeVentaJpaController PedidoDeVentaDAO = new PedidoDeVentaJpaController();

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
    public void listarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DTO.DevolucionCliente> d = (List<DTO.DevolucionCliente>) devolucionClieneDAO.findDevolucionClienteEntities();
        request.getSession().setAttribute("listaDevolucionCliente", d);
        response.sendRedirect("vistas/jsp/listaDevolucionPedidoVenta.jsp");
    }

    public void crearDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<PedidoDeVenta> pedidos = PedidoDeVentaDAO.findPedidoDeVentaEntities();
        request.getSession().setAttribute("pedidos", pedidos);
        response.sendRedirect("vistas/jsp/crearDevolucionPedidoVenta.jsp");
    }

    public void GuardarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        DTO.DevolucionCliente dc = new DTO.DevolucionCliente();
        PedidoDeVenta pedven = PedidoDeVentaDAO.findPedidoDeVenta(Integer.parseInt(request.getParameter("opciones")));
        String descripción = request.getParameter("txtDescripcion");
        Date fechaInicio = Date.valueOf(request.getParameter("FechaInicio"));
        Date fechaFin = Date.valueOf(request.getParameter("FechaFin"));
        dc.setDescripcion(descripción);
        dc.setFechaInicio(fechaInicio);
        dc.setFechaFin(fechaFin);
        dc.setPedidoDeVentaIdPedidoDeVenta(pedven);
        devolucionClieneDAO.create(dc);
        List<DTO.DevolucionCliente> devolucion = devolucionClieneDAO.findDevolucionClienteEntities();
        request.getSession().setAttribute("listaDevolucionCliente", devolucion);
        response.sendRedirect("vistas/jsp/listaDevolucionPedidoVenta.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listarDevolucion(request, response);
        }
        if (action.equalsIgnoreCase("formcreate")) {
            crearDevolucion(request, response);
        }
        if (action.equalsIgnoreCase("Guardar")) {
            try {
                GuardarDevolucion(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
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
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("buscar")) {
            buscarPorId(request, response);
        }
    }

    public void buscarPorId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("idDevolucion");
        List<DevolucionCliente> devoluciones;
        devoluciones = busquePorParametros(id);
        request.getSession().setAttribute("listaDevolucionCliente", devoluciones);
        response.sendRedirect("vistas/jsp/listaDevolucionPedidoVenta.jsp");
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
