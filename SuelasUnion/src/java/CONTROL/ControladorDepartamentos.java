/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.*;
import static NEGOCIO.SuelasExpanso.calcularCantidadSuelasExpanso;
import static NEGOCIO.SuelasPVC.calcularCantidadSuelasPVC;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANUEL
 */
@WebServlet(name = "ControladorDepartamentos", urlPatterns = {"/ControladorDepartamentos"})
public class ControladorDepartamentos extends HttpServlet {

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
            out.println("<title>Servlet ControladorDepartamentos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorDepartamentos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void mostrarDisponibildad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TiraJpaController tiraDAO = new TiraJpaController();
        SalpaJpaController salpaDAO = new SalpaJpaController();
        SuelaJpaController suelasDAO = new SuelaJpaController();
        PlantillaJpaController plantillaDAO = new PlantillaJpaController();
        request.getSession().setAttribute("departamentoTirasDis", tiraDAO.findTiraEntities().size());
        request.getSession().setAttribute("departamentoSalpasDis", salpaDAO.findSalpaEntities().size());
        request.getSession().setAttribute("departamentosSuelasExpansoDis", calcularCantidadSuelasExpanso());
        request.getSession().setAttribute("departamentosSuelasPVCDis", calcularCantidadSuelasPVC());
        request.getSession().setAttribute("departamentosPlantillaDis", plantillaDAO.findPlantillaEntities().size());
        response.sendRedirect("vistas/jsp/departamentos.jsp");
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
        if (action.equalsIgnoreCase("mostrarDisponibilidad")) {
            mostrarDisponibildad(request, response);
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
