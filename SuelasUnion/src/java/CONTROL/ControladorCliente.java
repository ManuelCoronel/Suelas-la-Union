/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ClienteJpaController;
import DAO.TipoClienteJpaController;
import DTO.Cliente;
import DTO.*;
import DAO.exceptions.NonexistentEntityException;
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
@WebServlet(name = "ControladorCliente", urlPatterns = {"/ControladorCliente"})
public class ControladorCliente extends HttpServlet {

    ClienteJpaController clienteDAO = new ClienteJpaController();
    TipoClienteJpaController tipoDAO = new TipoClienteJpaController();

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
    public void listarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Cliente> clientes = (List<Cliente>) clienteDAO.findClienteEntities();
        request.getSession().setAttribute("listaClientes", clientes);
        response.sendRedirect("vistas/jsp/listaClientes.jsp");
    }

    public void crearCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TipoCliente> tiposClientes = tipoDAO.findTipoClienteEntities();
        request.getSession().setAttribute("tiposDeClientes", tiposClientes);
        response.sendRedirect("vistas/jsp/crearCliente.jsp");
    }

    public void GuardarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        DTO.Cliente dto = new DTO.Cliente();
        String cedula = request.getParameter("txtCedula");
        String telefono = request.getParameter("txtTelefono");
        String direccion = request.getParameter("txtDireccion");
        String nombre = request.getParameter("txtNombre");
        String correo = request.getParameter("txtCorreo");
        Date fechaRegistro = Date.valueOf(request.getParameter("txtFechaRegistro"));
        Date fechaNacimiento = Date.valueOf(request.getParameter("txtFechaNacimiento"));
        TipoCliente tipoC = tipoDAO.findTipoCliente(Integer.parseInt(request.getParameter("opciones")));
        dto.setCedula(cedula);
        dto.setTelefono(telefono);
        dto.setDireccion(direccion);
        dto.setNombre(nombre);
        dto.setCorreo(correo);
        dto.setFechaRegistro(fechaRegistro);
        dto.setFechaNacimiento(fechaNacimiento);
        dto.setTipoClienteIdTipoCliente(tipoC);
        clienteDAO.create(dto);
        List<DTO.Cliente> clientes = clienteDAO.findClienteEntities();
        request.getSession().setAttribute("listaClientes", clientes);
        response.sendRedirect("vistas/jsp/listaClientes.jsp");
    }

    public void editarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedula");
        Cliente clienteEditar = new Cliente();
        clienteEditar = clienteDAO.findCliente(cedula);
        List<TipoCliente> tiposClientes = tipoDAO.findTipoClienteEntities();
        request.getSession().setAttribute("tiposDeClientes", tiposClientes);
        request.getSession().setAttribute("editarCliente", clienteEditar);
        response.sendRedirect("vistas/jsp/editarCliente.jsp");
    }

    public void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Cliente clienteDTO = new Cliente();
        String cedula = request.getParameter("txtCedula");
        String telefono = request.getParameter("txtTelefono");
        String direccion = request.getParameter("txtDireccion");
        String nombre = request.getParameter("txtNombre");
        String correo = request.getParameter("txtCorreo");
        Date fechaRegistro = Date.valueOf(request.getParameter("txtFechaRegistro"));
        Date fechaNacimiento = Date.valueOf(request.getParameter("txtFechaNacimiento"));
        TipoCliente tipoC = tipoDAO.findTipoCliente(Integer.parseInt(request.getParameter("opciones")));
        clienteDTO = clienteDAO.findCliente(cedula);
        clienteDTO.setCedula(cedula);
        clienteDTO.setTelefono(telefono);
        clienteDTO.setDireccion(direccion);
        clienteDTO.setNombre(nombre);
        clienteDTO.setCorreo(correo);
        clienteDTO.setFechaRegistro(fechaRegistro);
        clienteDTO.setFechaNacimiento(fechaNacimiento);
        clienteDTO.setTipoClienteIdTipoCliente(tipoC);
        clienteDAO.edit(clienteDTO);
        List<DTO.Cliente> clientes = clienteDAO.findClienteEntities();
        request.getSession().setAttribute("listaClientes", clientes);
        response.sendRedirect("vistas/jsp/listaClientes.jsp");
    }

    public void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException {
        String cedula = request.getParameter("cedula");
        clienteDAO.destroy(cedula);
        List<DTO.Cliente> clientes = clienteDAO.findClienteEntities();
        request.getSession().setAttribute("listaClientes", clientes);
        response.sendRedirect("vistas/jsp/listaClientes.jsp");
    }

    public void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedulaCliente");
        DTO.Cliente cliente = new DTO.Cliente();
        cliente = clienteDAO.findCliente(cedula);
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(cliente);
        request.getSession().setAttribute("listaClientes", clientes);
        response.sendRedirect("vistas/jsp/listaClientes.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listarCliente(request, response);
        }
        if (action.equalsIgnoreCase("formcreate")) {
            crearCliente(request, response);
        }
        if (action.equalsIgnoreCase("Guardar")) {
            try {
                GuardarCliente(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("formupdate")) {
            editarCliente(request, response);
        }
        if (action.equalsIgnoreCase("Actualizar")) {
            try {
                actualizarCliente(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("Delete")) {
            try {
                eliminarCliente(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equals("Buscar")) {
            buscarCliente(request, response);
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
