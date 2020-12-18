/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ClienteJpaController;
import DAO.PlantillaJpaController;
import DAO.ProductoJpaController;
import DAO.SalpaJpaController;
import DAO.SuelaJpaController;
import DAO.TipoClienteJpaController;
import DAO.TiraJpaController;
import DTO.Cliente;
import DTO.Plantilla;
import DTO.Producto;
import DTO.Salpa;
import DTO.Suela;
import DTO.TipoCliente;
import DTO.Tira;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author ..
 */
@WebServlet(name = "loginCliente", urlPatterns = {"/loginCliente"})
public class loginCliente extends HttpServlet {

    ClienteJpaController clienteDAO = new ClienteJpaController();
    TipoClienteJpaController tipoDAO = new TipoClienteJpaController();
    ProductoJpaController pdao = new ProductoJpaController();
    List<Producto> productos = new ArrayList<>();
    SuelaJpaController sdao = new SuelaJpaController();
    List<Suela> suelas = sdao.findSuelaEntities();
    SalpaJpaController salpasdao = new SalpaJpaController();
    List<Salpa> salpas = salpasdao.findSalpaEntities();
    PlantillaJpaController plantilladao = new PlantillaJpaController();
    List<Plantilla> plantillas = plantilladao.findPlantillaEntities();
    TiraJpaController tirasdao = new TiraJpaController();
    List<Tira> tiras = tirasdao.findTiraEntities();

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
            out.println("<title>Servlet loginCliente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        if (action.equalsIgnoreCase("salir")) {
            request.getSession().removeAttribute("personalActual");
            response.sendRedirect("index.jsp");
        } else if (action.equalsIgnoreCase("catalogo")) {
            request.getSession().setAttribute("suelas", suelas);
            request.getSession().setAttribute("salpas", salpas);
            request.getSession().setAttribute("plantillas", plantillas);
            request.getSession().setAttribute("tiras", tiras);
            response.sendRedirect("vistas/jsp/catalogo.jsp");
        } else if (action.equalsIgnoreCase("salir")) {
            request.getSession().removeAttribute("personalActual");
            response.sendRedirect("index.jsp");
        } else if (action.equalsIgnoreCase("crearCliente")) {
            crearCliente(request, response);
        } else if (action.equalsIgnoreCase("guardar")) {
            try {
                GuardarCliente(request, response);
            } catch (Exception ex) {
                Logger.getLogger(loginCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void GuardarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        java.util.Date fechaActual = new java.util.Date();
        DTO.Cliente dto = new DTO.Cliente();
        String cedula = request.getParameter("txtCedula");
        String telefono = request.getParameter("txtTelefono");
        String direccion = request.getParameter("txtDireccion");
        String nombre = request.getParameter("txtNombre");
        String correo = request.getParameter("txtCorreo");
        Date fechaNacimiento = Date.valueOf(request.getParameter("txtFechaNacimiento"));
        TipoCliente tipoC = tipoDAO.findTipoCliente(Integer.parseInt(request.getParameter("opciones")));
        dto.setCedula(cedula);
        dto.setTelefono(telefono);
        dto.setDireccion(direccion);
        dto.setNombre(nombre);
        dto.setCorreo(correo);
        dto.setFechaRegistro(fechaActual);
        dto.setFechaNacimiento(fechaNacimiento);
        dto.setTipoClienteIdTipoCliente(tipoC);
        clienteDAO.create(dto);
        List<DTO.Cliente> clientes = clienteDAO.findClienteEntities();
        request.getSession().setAttribute("listaClientes", clientes);
        response.sendRedirect("vistas/jsp/loginCliente.jsp");
    }

    public void crearCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<TipoCliente> tiposClientes = tipoDAO.findTipoClienteEntities();
        request.getSession().setAttribute("tiposDeClientes", tiposClientes);
        response.sendRedirect("vistas/jsp/registroCliente.jsp");
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
        String correo = request.getParameter("correo");
        String cedula = request.getParameter("cedula");
        ClienteJpaController clienteDAO = new ClienteJpaController();
        List<Cliente> clientes = clienteDAO.findClienteEntities();
        Cliente personalActual = null;
        for (Cliente c : clientes) {
            if (c.getCorreo().equals(correo) && c.getCedula().equals(cedula)) {
                personalActual = c;
            }
        }
        if (personalActual == null) {
            request.getSession().setAttribute("INVALIDO", true);
            response.sendRedirect("vistas/jsp/loginCliente.jsp");
        } else {
            request.getSession().setAttribute("personalActual", personalActual);
            response.sendRedirect("vistas/jsp/indexCliente.jsp");
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
