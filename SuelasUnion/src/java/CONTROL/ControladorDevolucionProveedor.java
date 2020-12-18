/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.DevolucionProveedorJpaController;
import DAO.PedidoProveedorJpaController;
import DAO.ProveedorJpaController;
import DAO.exceptions.PreexistingEntityException;
import DTO.DevolucionProveedor;
import DTO.Proveedor;
import static NEGOCIO.DevolucionProveedorNegocio.busquePorParametros;
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
@WebServlet(name = "ControladorDevolucionProveedor", urlPatterns = {"/ControladorDevolucionProveedor"})
public class ControladorDevolucionProveedor extends HttpServlet {

    DevolucionProveedorJpaController devolucionDAO = new DevolucionProveedorJpaController();

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
        List<DevolucionProveedor> devolucion = devolucionDAO.findDevolucionProveedorEntities();
        request.getSession().setAttribute("listaDev", devolucion);
        response.sendRedirect("vistas/jsp/listaDevolucionProveedor.jsp");
    }

    public void hacerDevolucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProveedorJpaController proveedor = new ProveedorJpaController();
        List<Proveedor> devolucion = proveedor.findProveedorEntities();
        request.getSession().setAttribute("devoluciones", devolucion);
        response.sendRedirect("vistas/jsp/registrarDevolucionProveedor.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listarDevolucion(request, response);
        }
        if (action.equalsIgnoreCase("formcreate")) {
            hacerDevolucion(request, response);

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws DAO.exceptions.PreexistingEntityException
     * @throws IOException if an I/O error occurs
     */
    public void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, PreexistingEntityException, Exception {
        DevolucionProveedor devolucionDTO = new DevolucionProveedor();
        double precio;
        int cantidad;
        String descripcion;
        int claseProducto = Integer.parseInt(request.getParameter("producto"));
        descripcion = request.getParameter("descripcion");
        Date fechaRegistro = Date.valueOf(request.getParameter("fechaRegistro"));
        Date fechaFin = Date.valueOf(request.getParameter("fechaFin"));
        cantidad = Integer.parseInt(request.getParameter("cantidad"));
        precio = Double.parseDouble(request.getParameter("precio"));
        int id = Integer.parseInt(request.getParameter("idproveedor"));
        if (claseProducto == 1 || claseProducto == 2 || claseProducto == 3 || claseProducto == 4 || claseProducto == 5) {
            PedidoProveedorJpaController pedidoPDAO = new PedidoProveedorJpaController();
            DTO.PedidoProveedor ppdto = pedidoPDAO.findPedidoProveedor(id);
            double calcular = (cantidad * precio);
            devolucionDTO.setDescripcion(descripcion);
            devolucionDTO.setFechaInicio(fechaRegistro);
            devolucionDTO.setFechaFin(fechaFin);
            devolucionDTO.setValorTotal(calcular);
            devolucionDTO.setPedidoProveedorId(ppdto);
            devolucionDAO.create(devolucionDTO);
        }
        response.sendRedirect("vistas/jsp/listaDevolucionProveedor.jsp");
    }

    public void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        List<DevolucionProveedor> devolucion;
        devolucion = busquePorParametros(id);
        request.getSession().setAttribute("listaDev", devolucion);
        response.sendRedirect("vistas/jsp/listaDevolucionProveedor.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("AgregarDevolucion")) {
            try {
                agregarProducto(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorDevolucionProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("Buscar")) {
            buscar(request, response);
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
