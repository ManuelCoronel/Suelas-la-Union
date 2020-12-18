/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.PersonalJpaController;
import DAO.RolJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Personal;
import DTO.Rol;
import static NEGOCIO.PersonalNegocio.busquePorParametros;
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
@WebServlet(name = "ControladorPersonal", urlPatterns = {"/ControladorPersonal"})
public class ControladorPersonal extends HttpServlet {

    PersonalJpaController personalDAO = new PersonalJpaController();
    RolJpaController rolDAO = new RolJpaController();

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
    public void listarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Personal> personal = (List<Personal>) personalDAO.findPersonalEntities();
        request.getSession().setAttribute("listaPersonal", personal);
        response.sendRedirect("vistas/jsp/listaPersonal.jsp");
    }

    public void crearPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Rol> roles = rolDAO.findRolEntities();
        request.getSession().setAttribute("roles", roles);
        response.sendRedirect("vistas/jsp/crearPersonal.jsp");
    }

    public void GuardarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        DTO.Personal dto = new DTO.Personal();
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String usuario = request.getParameter("txtUsuario");
        String contrasena = request.getParameter("txtContrasena");
        Rol rol = rolDAO.findRol(Integer.parseInt(request.getParameter("opciones")));
        dto.setNombre(nombre);
        dto.setApellido(apellido);
        dto.setUsuario(usuario);
        dto.setContrasena(contrasena);
        dto.setRolId(rol);
        personalDAO.create(dto);
        List<DTO.Personal> personal = personalDAO.findPersonalEntities();
        request.getSession().setAttribute("listaPersonal", personal);
        response.sendRedirect("vistas/jsp/listaPersonal.jsp");
    }

    public void editarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Personal personalEditar = new Personal();
        personalEditar = personalDAO.findPersonal(id);
        List<Rol> roles = rolDAO.findRolEntities();
        request.getSession().setAttribute("roles", roles);
        request.getSession().setAttribute("editarPersonal", personalEditar);
        response.sendRedirect("vistas/jsp/editarPersonal.jsp");
    }

    public void actualizarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Personal personalDTO = new Personal();
        Integer id = Integer.parseInt(request.getParameter("Id"));
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String usuario = request.getParameter("txtUsuario");
        String contrasena = request.getParameter("txtContrasena");
        Rol rol = rolDAO.findRol(Integer.parseInt(request.getParameter("opciones")));
        personalDTO = personalDAO.findPersonal(id);
        personalDTO.setNombre(nombre);
        personalDTO.setApellido(apellido);
        personalDTO.setUsuario(usuario);
        personalDTO.setContrasena(contrasena);
        personalDTO.setRolId(rol);
        personalDAO.edit(personalDTO);
        List<DTO.Personal> personal = personalDAO.findPersonalEntities();
        request.getSession().setAttribute("listaPersonal", personal);
        response.sendRedirect("vistas/jsp/listaPersonal.jsp");
    }

    public void eliminarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException, IllegalOrphanException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        personalDAO.destroy(id);
        List<DTO.Personal> personal = personalDAO.findPersonalEntities();
        request.getSession().setAttribute("listaPersonal", personal);
        response.sendRedirect("vistas/jsp/listaPersonal.jsp");
    }

    public void buscarPersonal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("NombrePersonal");
        List<Personal> personas;
        personas = busquePorParametros(nombre);
        request.getSession().setAttribute("listaPersonal", personas);
        response.sendRedirect("vistas/jsp/listaPersonal.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            listarPersonal(request, response);
        }
        if (action.equalsIgnoreCase("formcreate")) {
            crearPersonal(request, response);
        }
        if (action.equalsIgnoreCase("Guardar")) {
            try {
                GuardarPersonal(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("formupdate")) {
            editarPersonal(request, response);
        }
        if (action.equalsIgnoreCase("Actualizar")) {
            try {
                actualizarPersonal(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("Delete")) {
            try {
                eliminarPersonal(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ControladorPersonal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equals("Buscar")) {
            buscarPersonal(request, response);
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
