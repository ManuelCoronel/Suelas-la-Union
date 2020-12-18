/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ProveedorJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Proveedor;
import static NEGOCIO.proveedorNegocio.busquePorParametros;
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
 * @author CAMILA
 */
@WebServlet(name = "Proveedor", urlPatterns = {"/Proveedor"})
public class ControladorProveedor extends HttpServlet {

    ProveedorJpaController proveedorDAO;

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
    public void listarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        proveedorDAO = new ProveedorJpaController();
        List<Proveedor> proveedor = (List<Proveedor>) proveedorDAO.findProveedorEntities();
        request.getSession().setAttribute("listaProveedor", proveedor);
        response.sendRedirect("vistas/jsp/listaProveedor.jsp");
    }

    public void redireccionando(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("vistas/jsp/crearProveedor.jsp");
    }

    public void crearProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Proveedor dto = new Proveedor();
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String empresa = request.getParameter("empresa");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");
        dto.setNombre(nombre);
        dto.setCedula(cedula);
        dto.setEmpresa(empresa);
        dto.setTelefono(telefono);
        dto.setCorreo(correo);
        dto.setDireccion(direccion);
        proveedorDAO.create(dto);
        List<Proveedor> proveedor = proveedorDAO.findProveedorEntities();
        request.getSession().setAttribute("listaProveedor", proveedor);
        response.sendRedirect("vistas/jsp/listaProveedor.jsp");
    }

    public void edtiarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedula");
        Proveedor proveedorEditar = new Proveedor();
        proveedorEditar = proveedorDAO.findProveedor(cedula);
        request.getSession().setAttribute("editarProveedor", proveedorEditar);
        response.sendRedirect("vistas/jsp/editarProveedor.jsp");
    }

    public void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Proveedor proveedorDTO = new Proveedor();
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String empresa = request.getParameter("empresa");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");
        proveedorDTO = proveedorDAO.findProveedor(cedula);
        proveedorDTO.setNombre(nombre);
        proveedorDTO.setCedula(cedula);
        proveedorDTO.setEmpresa(empresa);
        proveedorDTO.setTelefono(telefono);
        proveedorDTO.setCorreo(correo);
        proveedorDTO.setDireccion(direccion);
        proveedorDAO.edit(proveedorDTO);
        List<Proveedor> proveedores = proveedorDAO.findProveedorEntities();
        request.getSession().setAttribute("listaProveedor", proveedores);
        response.sendRedirect("vistas/jsp/listaProveedor.jsp");
    }

    public void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException, IllegalOrphanException {
        String cedula = request.getParameter("cedula");
        proveedorDAO.destroy(cedula);
        List<Proveedor> proveedor = proveedorDAO.findProveedorEntities();
        request.getSession().setAttribute("listaProveedor", proveedor);
        response.sendRedirect("vistas/jsp/listaProveedor.jsp");
    }

    public void buscarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedulaCliente");
        List<Proveedor> proveedor;
        proveedor = busquePorParametros(cedula);
        request.getSession().setAttribute("listaProveedor", proveedor);
        response.sendRedirect("vistas/jsp/listaProveedor.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            listarProveedor(request, response);
        }
        if (action.equalsIgnoreCase("formcreate")) {
            redireccionando(request, response);
        }
        if (action.equalsIgnoreCase("Guardar")) {
            try {
                crearProveedor(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("formupdate")) {
            edtiarProveedor(request, response);
        }
        if (action.equalsIgnoreCase("Actualizar")) {
            try {
                actualizarProveedor(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("Delete")) {
            try {
                eliminarProveedor(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ControladorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equals("Buscar")) {
            buscarProveedor(request, response);
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
