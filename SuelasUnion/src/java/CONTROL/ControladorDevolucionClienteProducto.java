/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.DevolucionClienteJpaController;
import DAO.DevolucionClienteProductoJpaController;
import DAO.ProductoJpaController;
import DTO.DevolucionCliente;
import DTO.Producto;
import java.io.IOException;
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
 * @author LENOVO
 */
@WebServlet(name = "ControladorDevolucionClienteProducto", urlPatterns = {"/ControladorDevolucionClienteProducto"})
public class ControladorDevolucionClienteProducto extends HttpServlet {

    DevolucionClienteProductoJpaController dcpDAO = new DevolucionClienteProductoJpaController();
    DevolucionClienteJpaController DevolucionClienteDAO = new DevolucionClienteJpaController();
    ProductoJpaController ProductoDAO = new ProductoJpaController();

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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DTO.DevolucionClienteProducto> ppp = (List<DTO.DevolucionClienteProducto>) dcpDAO.findDevolucionClienteProductoEntities();
        request.getSession().setAttribute("listaDevolucionClienteProducto", ppp);
        response.sendRedirect("vistas/jsp/devolucionClienteProducto.jsp");
    }

    public void crearDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DevolucionCliente> pedidos = DevolucionClienteDAO.findDevolucionClienteEntities();
        request.getSession().setAttribute("pedidosPro", pedidos);
        List<Producto> productos = ProductoDAO.findProductoEntities();
        request.getSession().setAttribute("productosPro", productos);
        response.sendRedirect("vistas/jsp/crearDevolucionClienteProducto.jsp");
    }

    public void GuardarDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        DTO.DevolucionClienteProducto dcp = new DTO.DevolucionClienteProducto();
        DevolucionCliente devclien = DevolucionClienteDAO.findDevolucionCliente(Integer.parseInt(request.getParameter("opciones")));
        Producto producto = ProductoDAO.findProducto(Integer.parseInt(request.getParameter("opciones2")));
        Integer cantidad = Integer.parseInt(request.getParameter("Cantidad"));
        dcp.setCantidad(cantidad);
        dcp.setDevolucionCliente(devclien);
        dcp.setProducto(producto);
        dcpDAO.create(dcp);
        List<DTO.DevolucionClienteProducto> devolucion = dcpDAO.findDevolucionClienteProductoEntities();
        request.getSession().setAttribute("listaDevolucionClienteProducto", devolucion);
        response.sendRedirect("vistas/jsp/devolucionClienteProducto.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listar(request, response);
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
        response.sendRedirect("vistas/jsp/devolucionClienteProducto.jsp");
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
