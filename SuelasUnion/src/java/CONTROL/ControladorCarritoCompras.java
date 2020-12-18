/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ProductoJpaController;
import DTO.Producto;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Ronald
 */
@WebServlet(name = "ControladorCarritoCompras", urlPatterns = {"/ControladorCarritoCompras"})
public class ControladorCarritoCompras extends HttpServlet {

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
            out.println("<title>Servlet ControladorCarritoCompras</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorCarritoCompras at " + request.getContextPath() + "</h1>");
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
        if (action.equalsIgnoreCase("+Agregar")) {
            try {
                addCarrito(request, response);
            } catch (Exception ex) {
                Logger.getLogger(loginCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("eliminar")) {
            eliminarItem(request, response);
            response.sendRedirect("vistas/jsp/carritoCompras.jsp");
        }
    }

    public void addCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
        ProductoJpaController pro = new ProductoJpaController();
        int productosEnCarrito = 0;
        List<Producto> productosCarrito = null;
        List<Integer> cantidadesCarrito = null;
        if (request.getSession().getAttribute("productosCarrito") == null) {
            productosCarrito = new ArrayList<>();
            cantidadesCarrito = new ArrayList<>();
        } else {
            productosCarrito = (List<Producto>) request.getSession().getAttribute("productosCarrito");
            cantidadesCarrito = (List<Integer>) request.getSession().getAttribute("listaCantidad");
        }
        if (!productosCarrito.contains(pro.findProducto(Integer.parseInt(request.getParameter("producto"))))) {
            productosCarrito.add(pro.findProducto(Integer.parseInt(request.getParameter("producto"))));
            cantidadesCarrito.add(Integer.parseInt(request.getParameter("cantidad")));
        } else {
            int i = (productosCarrito.indexOf(pro.findProducto(Integer.parseInt(request.getParameter("producto")))));
            int nuevaCantidad = Integer.parseInt(request.getParameter("cantidad"));
            cantidadesCarrito.set(i, cantidadesCarrito.get(i) + nuevaCantidad);
        }
        sumarSubTotal(request, response);
        productosEnCarrito = productosCarrito.size();
        request.getSession().setAttribute("productosEnCarrito", productosEnCarrito);
        request.getSession().setAttribute("listaCantidad", cantidadesCarrito);
        request.getSession().setAttribute("productosCarrito", productosCarrito);
        response.sendRedirect("vistas/jsp/catalogo.jsp");
    }

    public void restarSubTotal(HttpServletRequest request, HttpServletResponse response, double precio, double cantidad) {
        double total = Double.parseDouble(request.getSession().getAttribute("subTotalCarrito") + "");
        total -= cantidad * precio;
        request.getSession().setAttribute("subTotalCarrito", total);
    }

    public void sumarSubTotal(HttpServletRequest request, HttpServletResponse response) {
        double total = 0;
        if (request.getSession().getAttribute("subTotalCarrito") != null) {
            total = Double.parseDouble(request.getSession().getAttribute("subTotalCarrito") + "");
        }
        double cantidad = Double.parseDouble(Double.parseDouble(request.getParameter("cantidad")) + "");
        ProductoJpaController pro = new ProductoJpaController();
        DTO.Producto producto = (DTO.Producto) pro.findProducto(Integer.parseInt(request.getParameter("producto")));
        double precio = producto.getPrecio();
        total += cantidad * precio;
        request.getSession().setAttribute("subTotalCarrito", total);
    }

    protected void eliminarItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPro = Integer.parseInt(request.getParameter("id"));
        ProductoJpaController pro = new ProductoJpaController();
        List<Producto> productosCarrito = (List<Producto>) request.getSession().getAttribute("productosCarrito");;
        List<Integer> cantidadesCarrito = (List<Integer>) request.getSession().getAttribute("listaCantidad");
        double cantidad = (double) (cantidadesCarrito.get(productosCarrito.indexOf(pro.findProducto(idPro))));
        restarSubTotal(request, response, pro.findProducto(idPro).getPrecio(), cantidad);
        cantidadesCarrito.remove(productosCarrito.indexOf(pro.findProducto(idPro)));
        productosCarrito.remove(pro.findProducto(idPro));
        request.getSession().setAttribute("listaCantidad", cantidadesCarrito);
        request.getSession().setAttribute("productosCarrito", productosCarrito);
        request.getSession().setAttribute("productosEnCarrito", productosCarrito.size());
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
