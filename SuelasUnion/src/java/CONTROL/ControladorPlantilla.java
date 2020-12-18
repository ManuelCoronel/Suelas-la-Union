/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.PlantillaJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Plantilla;
import static NEGOCIO.PlantillaNegocio.buscandoPlantilla;
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
@WebServlet(name = "ControladorPlantilla", urlPatterns = {"/ControladorPlantilla"})
public class ControladorPlantilla extends HttpServlet {

    PlantillaJpaController plantillaDAO = new PlantillaJpaController();

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
    public void buscarPlantilla(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String talla, modelo;
        modelo = request.getParameter("modelo");
        talla = request.getParameter("talla");
        List<Plantilla> p = buscandoPlantilla(modelo, talla);
        request.removeAttribute("listaPlantilla");
        request.getSession().setAttribute("listaPlantilla", p);
        response.sendRedirect("vistas/jsp/MostrandoPlantillas.jsp");
    }

    public void edtiarPlantilla(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = (Integer.parseInt(request.getParameter("id")));
        Plantilla editar = new Plantilla();
        editar = plantillaDAO.findPlantilla(id);
        request.getSession().setAttribute("editarPlantilla", editar);
        response.sendRedirect("vistas/jsp/actualizarPlantilla.jsp");
    }

    public void actualizarPlantilla(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        Plantilla dto = new Plantilla();
        int id = (Integer.parseInt(request.getParameter("id")));
        String modelo = request.getParameter("modelo");
        String talla = request.getParameter("talla");
        dto = plantillaDAO.findPlantilla(id);
        dto.setProductoId(id);
        dto.setModelo(modelo);
        dto.setTalla(talla);
        plantillaDAO.edit(dto);
        List<Plantilla> p = plantillaDAO.findPlantillaEntities();
        request.getSession().setAttribute("listaPlantilla", p);
        response.sendRedirect("vistas/jsp/MostrandoPlantillas.jsp");
    }

    public void eliminarPlantilla(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException {
        int id = (Integer.parseInt(request.getParameter("id")));
        plantillaDAO.destroy(id);
        List<Plantilla> plantilla = plantillaDAO.findPlantillaEntities();
        request.getSession().setAttribute("listaPlantilla", plantilla);
        response.sendRedirect("vistas/jsp/MostrandoPlantillas.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Buscar")) {
            buscarPlantilla(request, response);
        }
        if (accion.equalsIgnoreCase("toList")) {
            buscarPlantilla(request, response);
        }
        if (accion.equalsIgnoreCase("eliminar")) {
            try {
                eliminarPlantilla(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (accion.equalsIgnoreCase("formeditar")) {
            edtiarPlantilla(request, response);
        }
        if (accion.equalsIgnoreCase("Actualizar")) {
            try {
                actualizarPlantilla(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPlantilla.class.getName()).log(Level.SEVERE, null, ex);
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
