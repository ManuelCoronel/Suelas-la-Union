/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.EstadoJpaController;
import DTO.Cliente;
import DTO.Estado;
import DTO.PedidoDeVenta;
import NEGOCIO.PedidoDeVentaNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author
 */
@WebServlet(name = "ControladorCompras", urlPatterns = {"/ControladorCompras"})
public class ControladorCompras extends HttpServlet {

    EstadoJpaController estadodao = new EstadoJpaController();
    List<Estado> estados = estadodao.findEstadoEntities();

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
            out.println("<title>Servlet ControladorCompras</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCompras at " + request.getContextPath() + "</h1>");
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
        if (action.equalsIgnoreCase("listar")) {
            request.getSession().setAttribute("tiposDeEstados", estados);
            response.sendRedirect("vistas/jsp/compras.jsp");
        }
    }

    public void buscarPedidoPorParametros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int estado = Integer.parseInt(request.getParameter("opcionesTipoDeEstados"));
        Cliente cliente = (Cliente) request.getSession().getAttribute("personalActual");
        try {
            List<PedidoDeVenta> pedidos = PedidoDeVentaNegocio.busquedaPedidosPorParametrosII(cliente, estado);
            if (pedidos == null) {
                pedidos = new ArrayList<>();
            }
            request.getSession().setAttribute("pedidosBuscadosVenta", pedidos);
        } catch (Exception e) {
            System.out.println("Error");
        }
        response.sendRedirect("vistas/jsp/compras.jsp");
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
            buscarPedidoPorParametros(request, response);
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
