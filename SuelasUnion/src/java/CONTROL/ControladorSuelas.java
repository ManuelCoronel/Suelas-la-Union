/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ColorJpaController;
import DAO.SuelaJpaController;
import DAO.TipoJpaController;
import DAO.exceptions.NonexistentEntityException;
import DTO.Color;
import DTO.Suela;
import DTO.Tipo;
import static NEGOCIO.Suelas.busquedaPorParametros;
import static NEGOCIO.Suelas.obtenerSuelasPorTipo;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControladorSuelas", urlPatterns = {"/ControladorSuelas"})
public class ControladorSuelas extends HttpServlet {

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
            out.println("<title>Servlet ControladorSuelas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorSuelas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void buscarPorTipo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipoSuela = request.getParameter("tipo");
        String modelo = request.getParameter("modelo");
        String talla = request.getParameter("talla");
        String color = request.getParameter("color");
        List<Suela> suelas = busquedaPorParametros(obtenerSuelasPorTipo(tipoSuela), modelo, talla, color);
        request.getSession().setAttribute("suelasListar", suelas);
        if (tipoSuela.equalsIgnoreCase("2")) {
            response.sendRedirect("vistas/jsp/suelasPVC.jsp");
        } else {
            response.sendRedirect("vistas/jsp/suelasExpanso.jsp");
        }
    }

    public void editar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SuelaJpaController suelaDAO = new SuelaJpaController();
        TipoJpaController tipoDAO = new TipoJpaController();
        ColorJpaController colorDAO = new ColorJpaController();
        int idSuela = Integer.parseInt(request.getParameter("id"));
        Suela suela = suelaDAO.findSuela(idSuela);
        List<Tipo> tipos = tipoDAO.findTipoEntities();
        List<Color> colores = colorDAO.findColorEntities();
        request.getSession().setAttribute("colorLista", colores);
        request.getSession().setAttribute("tiposLista", tipos);
        request.getSession().setAttribute("editarSuela", suela);
        response.sendRedirect("vistas/jsp/editarSuela.jsp");
    }

    public void guardar(HttpServletRequest request, HttpServletResponse response) throws IOException, NonexistentEntityException, Exception {
        SuelaJpaController suelaDAO = new SuelaJpaController();
        TipoJpaController tipoDAO = new TipoJpaController();
        ColorJpaController colorDAO = new ColorJpaController();
        int id = Integer.parseInt(request.getParameter("idSuela"));
        String modelo = (request.getParameter("modelo"));
        String talla = request.getParameter("talla");
        String tipoId = request.getParameter("opcionesTipo");
        String colorId = request.getParameter("opcionesColor");
        String precio = request.getParameter("precio");
        String cantidad = request.getParameter("cantidad");
        Suela suela = new Suela();
        suela = suelaDAO.findSuela((Integer) id);
        Tipo tipo = tipoDAO.findTipo(Integer.parseInt(tipoId));
        Color color = colorDAO.findColor(Integer.parseInt(colorId));
        suela.setModelo(modelo);
        suela.setTalla(talla);
        suela.setTipoId(tipo);
        suela.getProducto().setPrecio(Double.parseDouble(precio));
        suela.setColorId(color);
        suela.getProducto().setCantidadDisponible(Double.parseDouble(cantidad));
        suelaDAO.edit(suela);
        List<Suela> suelas = busquedaPorParametros(obtenerSuelasPorTipo(suela.getTipoSuela()), "", "", "");
        request.getSession().setAttribute("suelasListar", suelas);
        if (suela.getTipoSuela().equalsIgnoreCase("2")) {
            response.sendRedirect("vistas/jsp/suelasPVC.jsp");
        } else {
            response.sendRedirect("vistas/jsp/suelasExpanso.jsp");
        }
    }

    public void eliminar(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SuelaJpaController suelaDAO = new SuelaJpaController();
        Suela suela = suelaDAO.findSuela(id);
        String tipoSuela = suela.getTipoSuela();
        suelaDAO.destroy(id);
        List<Suela> suelas = busquedaPorParametros(obtenerSuelasPorTipo(suela.getTipoSuela()), "", "", "");
        request.getSession().setAttribute("suelasListar", suelas);
        if (tipoSuela.equalsIgnoreCase("2")) {
            response.sendRedirect("vistas/jsp/suelasPVC.jsp");
        } else {
            response.sendRedirect("vistas/jsp/suelasExpanso.jsp");
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
        if (action.equalsIgnoreCase("buscar")) {
            buscarPorTipo(request, response);
        }
        if (action.equalsIgnoreCase("formeditar")) {
            editar(request, response);
        }
        if (action.equalsIgnoreCase("Actualizar")) {
            try {
                guardar(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorSuelas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("eliminar")) {
            try {
                eliminar(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorSuelas.class.getName()).log(Level.SEVERE, null, ex);
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
