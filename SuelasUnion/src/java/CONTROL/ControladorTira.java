/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ColorJpaController;
import DAO.TiraJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Color;
import DTO.Tira;
import static NEGOCIO.TirasNegocio.busquePorParametros;
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
 * @author MANUEL
 */
@WebServlet(name = "ControladorTira", urlPatterns = {"/ControladorTira"})
public class ControladorTira extends HttpServlet {

    TiraJpaController tiraDAO = new TiraJpaController();
    ColorJpaController colorDAO = new ColorJpaController();

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

    public void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anchura, modelo;
        modelo = request.getParameter("modelo");
        anchura = request.getParameter("anchura");
        List<DTO.Tira> tiras = busquePorParametros(modelo, anchura);
        request.getSession().setAttribute("tirasBuscadas", tiras);
        response.sendRedirect("vistas/jsp/tiras.jsp");
    }

    public void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException {
        Integer productoId = Integer.parseInt(request.getParameter("ProductoId"));
        tiraDAO.destroy(productoId);
        List<DTO.Tira> tiras = tiraDAO.findTiraEntities();
        request.getSession().setAttribute("tirasBuscadas", tiras);
        response.sendRedirect("vistas/jsp/tiras.jsp");
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer productoId = Integer.parseInt(request.getParameter("ProductoId"));
        Tira tiraEditar = new Tira();
        tiraEditar = tiraDAO.findTira(productoId);
        List<Color> colores = colorDAO.findColorEntities();
        request.getSession().setAttribute("colores", colores);
        request.getSession().setAttribute("editarTira", tiraEditar);
        response.sendRedirect("vistas/jsp/editarTira.jsp");
    }

    public void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Tira tiraDTO = new Tira();
        Integer productoId = Integer.parseInt(request.getParameter("ProductoId"));
        String modelo = request.getParameter("txtModelo");
        String anchura = request.getParameter("txtAnchura");
        Color color = colorDAO.findColor(Integer.parseInt(request.getParameter("opciones")));
        tiraDTO = tiraDAO.findTira(productoId);
        tiraDTO.setModelo(modelo);
        tiraDTO.setAnchura(anchura);
        tiraDTO.setColorId(color);
        tiraDAO.edit(tiraDTO);
        List<DTO.Tira> tira = tiraDAO.findTiraEntities();
        request.getSession().setAttribute("tirasBuscadas", tira);
        response.sendRedirect("vistas/jsp/tiras.jsp");
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
        if (accion.equalsIgnoreCase("buscar")) {
            buscar(request, response);
        }
        if (accion.equalsIgnoreCase("eliminar")) {
            try {
                eliminar(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equalsIgnoreCase("editar")) {
            editar(request, response);
        }
        if (accion.equalsIgnoreCase("Actualizar")) {
            try {
                actualizar(request, response);
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
