/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import DAO.ColorJpaController;
import DAO.PedidoProveedorJpaController;
import DAO.ProductoJpaController;
import DAO.SuelaJpaController;
import DAO.TipoJpaController;
import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.Plantilla;
import DTO.Producto;
import DTO.Proveedor;
import DTO.Tira;
import static NEGOCIO.FacturaPedidoProv.calcularFacturaProv;
import static NEGOCIO.PlantillaNegocio.buscandoPlantilla;
import static NEGOCIO.PlantillaNegocio.existePlantilla;
import static NEGOCIO.PlantillaNegocio.obtenerIdPlantilla;
import static NEGOCIO.ProductoNegocio.obtenerUltimoIdProducto;
import static NEGOCIO.Suelas.existeSuela;
import static NEGOCIO.Suelas.obtenerSuelaId;
import static NEGOCIO.TirasNegocio.existeTira;
import static NEGOCIO.TirasNegocio.obtenerTiraId;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "ControladorPedidoProv", urlPatterns = {"/ControladorPedidoProv"})
public class ControladorPedidoProv extends HttpServlet {

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
            out.println("<title>Servlet ControladorPedidoProv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorPedidoProv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws IllegalOrphanException, NonexistentEntityException, IOException {
        DAO.PedidoProveedorJpaController pedidoDAO = new DAO.PedidoProveedorJpaController();
        int id = Integer.parseInt(request.getParameter("id"));
        pedidoDAO.destroy(id);
        buscar(request, response);
    }

    public void buscar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PedidoProveedorJpaController PedidoProvDAO = new PedidoProveedorJpaController();
        List<DTO.PedidoProveedor> pedidos = PedidoProvDAO.findPedidoProveedorEntities();
        request.getSession().setAttribute("PedidosProvBuscados", pedidos);
        response.sendRedirect("vistas/jsp/listaPedidoProv.jsp");
    }

    public void crearPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DAO.ProveedorJpaController proveedoresDAO = new DAO.ProveedorJpaController();
        List<DTO.Proveedor> proveedores = proveedoresDAO.findProveedorEntities();
        request.getSession().setAttribute("listaProveedores", proveedores);
        response.sendRedirect("vistas/jsp/registrarPedidoProveedor.jsp");
    }

    public void GuardarPedidoPedidoProv(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        DTO.PedidoProveedor pedidoProveedor = new DTO.PedidoProveedor();
        DAO.ProveedorJpaController proveedorDAO = new DAO.ProveedorJpaController();
        DAO.PedidoProveedorJpaController pedidoDAO = new DAO.PedidoProveedorJpaController();
        String cedulaProv = request.getParameter("opcionesProveedor");
        DTO.Proveedor proveedor = proveedorDAO.findProveedor(cedulaProv);
        Date fechaRegistro = Date.valueOf(request.getParameter("fechaRegistro"));

        pedidoProveedor.setFecha(fechaRegistro);
        pedidoProveedor.setProveedorCedula(proveedor);

        pedidoDAO.create(pedidoProveedor);
        request.getSession().setAttribute("pedidoProveedorActualId", pedidoDAO.findPedidoProveedorEntities().get(pedidoDAO.findPedidoProveedorEntities().size() - 1).getId());
        request.getSession().setAttribute("proveedorPedidoActual", proveedor);
        request.getSession().setAttribute("pedidoProveedorActual", pedidoProveedor);
        response.sendRedirect("vistas/jsp/agregarProductos.jsp");

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
            buscar(request, response);
        }
        if (action.equalsIgnoreCase("formcreate")) {
            crearPedido(request, response);
        }
        if (action.equalsIgnoreCase("eliminarProductoActual")) {
            try {
                eliminarProductoActual(request, response);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("eliminarPedido")) {
            try {
                eliminarPedido(request, response);
            } catch (IllegalOrphanException | NonexistentEntityException ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void eliminarProductoActual(HttpServletRequest request, HttpServletResponse response) throws NonexistentEntityException, IOException, Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        int idPedido = Integer.parseInt(request.getSession().getAttribute("pedidoProveedorActualId") + "");
        DAO.ProductoPedidoProveedorJpaController PDAO = new DAO.ProductoPedidoProveedorJpaController();
        DTO.ProductoPedidoProveedorPK productoProvPK = new DTO.ProductoPedidoProveedorPK(id, idPedido);
        DAO.ProductoJpaController proDAO = new DAO.ProductoJpaController();
        DTO.Producto proDTO = proDAO.findProducto(id);
        proDTO.setCantidadDisponible(proDTO.getCantidadDisponible() - PDAO.findProductoPedidoProveedor(productoProvPK).getCantidad());
        proDAO.edit(proDTO);
        PDAO.destroy(productoProvPK);
        listarSubLista(request, response);
        response.sendRedirect("vistas/jsp/agregarProductos.jsp");
    }

    public void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException, PreexistingEntityException, Exception {
        double cantidad, precio;
        String talla, modelo, tipo, ancho;
        int color;
        int claseProducto = Integer.parseInt(request.getParameter("producto"));
        ProductoJpaController productoDAO = new ProductoJpaController();
        cantidad = Double.parseDouble(request.getParameter("cantidad"));
        precio = Double.parseDouble(request.getParameter("precio"));
        Proveedor proveedor = (DTO.Proveedor) request.getSession().getAttribute("proveedorPedidoActual");
        DTO.Producto producto = new Producto();
        producto.setCantidadDisponible(cantidad);
        producto.setPrecio(precio);
        producto.setTipoProducto(claseProducto + "");
        producto.setProveedorCedula(proveedor);
        if (claseProducto == 1) {
            DAO.PlantillaJpaController plantillaDAO = new DAO.PlantillaJpaController();
            talla = request.getParameter("talla");
            modelo = request.getParameter("modelo");
            tipo = request.getParameter("tipo");
            DTO.Plantilla plantilla;
            if (existePlantilla(modelo, talla)) {
                plantilla = plantillaDAO.findPlantilla(obtenerIdPlantilla(modelo, talla));
                producto = productoDAO.findProducto(plantilla.getProductoId());
                producto.setCantidadDisponible(producto.getCantidadDisponible() + cantidad);
                productoDAO.edit(producto);
            } else {
                plantilla = new DTO.Plantilla();
                plantilla.setTalla(talla);
                plantilla.setModelo(modelo);
                productoDAO.create(producto);
                plantilla.setProductoId(claseProducto);
                plantilla.setProductoId(obtenerUltimoIdProducto());
                plantilla.setProducto(producto);
                plantillaDAO.create(plantilla);
            }
        }
        if (claseProducto == 2 || claseProducto == 3) {
            SuelaJpaController suelaDAO = new SuelaJpaController();
            TipoJpaController tipoDAO = new TipoJpaController();
            ColorJpaController colorDao = new ColorJpaController();
            talla = request.getParameter("talla");
            modelo = request.getParameter("modelo");
            tipo = request.getParameter("tipo");
            String tipoSuela = claseProducto + "";
            color = Integer.parseInt(request.getParameter("color"));
            DTO.Tipo tipoDTO = tipoDAO.findTipo(Integer.parseInt(tipo));
            DTO.Color colorDTO = colorDao.findColor(color);
            DTO.Suela suela = new DTO.Suela();
            if (existeSuela(modelo, tipoSuela, talla, color)) {
                suela = suelaDAO.findSuela(obtenerSuelaId(modelo, tipoSuela, talla, color));
                producto = productoDAO.findProducto(suela.getProductoId());
                producto.setCantidadDisponible(producto.getCantidadDisponible() + cantidad);
                productoDAO.edit(producto);
            } else {
                productoDAO.create(producto);
                suela.setTalla(talla);
                suela.setTipoId(tipoDTO);
                suela.setModelo(modelo);
                suela.setColorId(colorDTO);
                suela.setProductoId(obtenerUltimoIdProducto());
                suela.setTipoSuela(tipoSuela);
                suela.setProducto(producto);
                suelaDAO.create(suela);
            }
        }
        if (claseProducto == 4) {
            DAO.TiraJpaController tiraDAO = new DAO.TiraJpaController();
            TipoJpaController tipoDAO = new TipoJpaController();
            ColorJpaController colorDao = new ColorJpaController();
            modelo = request.getParameter("modelo");
            ancho = request.getParameter("ancho");
            String anchura = request.getParameter("ancho");
            color = Integer.parseInt(request.getParameter("color"));
            DTO.Color colorDTO = colorDao.findColor(color);
            Tira tira = new Tira();
            if (existeTira(modelo, anchura, color)) {
                tira = tiraDAO.findTira(obtenerTiraId(modelo, anchura, color));
                producto = productoDAO.findProducto(tira.getProductoId());
                producto.setCantidadDisponible(producto.getCantidadDisponible() + cantidad);
                productoDAO.edit(producto);
            } else {
                productoDAO.create(producto);
                tira.setModelo(modelo);
                tira.setColorId(colorDTO);
                tira.setAnchura(anchura);
                tira.setProductoId(obtenerUltimoIdProducto());
                tira.setProducto(producto);
                tiraDAO.create(tira);
            }
        }
        if (claseProducto == 5) {
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
            producto = productoDAO.findProducto(1);
            producto.setCantidadDisponible(producto.getCantidadDisponible() + cantidad);
            productoDAO.edit(producto);
        }
        guardarProductoPedido(request, response, cantidad, precio, producto);
    }

    public void guardarProductoPedido(HttpServletRequest request, HttpServletResponse response, double cantidad, double precio, Producto producto) throws Exception {
        DTO.ProductoPedidoProveedor ppp = new DTO.ProductoPedidoProveedor();
        DAO.ProductoPedidoProveedorJpaController pppDAO = new DAO.ProductoPedidoProveedorJpaController();
        ppp.setProducto(producto);
        DTO.PedidoProveedor pedidoProv = (DTO.PedidoProveedor) request.getSession().getAttribute("pedidoProveedorActual");
        ppp.setPedidoProveedor(pedidoProv);
        ppp.setTotal(precio * cantidad);
        ppp.setCantidad(cantidad);
        ppp.setPrecio(precio);
        pppDAO.create(ppp);
        listarSubLista(request, response);
        response.sendRedirect("vistas/jsp/agregarProductos.jsp");
    }

    public void listarSubLista(HttpServletRequest request, HttpServletResponse response) {
        int pedidoId = Integer.parseInt(request.getSession().getAttribute("pedidoProveedorActualId") + "");
        DAO.PedidoProveedorJpaController pedidoProveedorDAO = new DAO.PedidoProveedorJpaController();
        List<DTO.ProductoPedidoProveedor> listaPP = (List<DTO.ProductoPedidoProveedor>) pedidoProveedorDAO.findPedidoProveedor(pedidoId).getProductoPedidoProveedorList();
        request.getSession().setAttribute("listaProductosPedidoProveedorActual", listaPP);
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
        if (action.equalsIgnoreCase("guardarPedidoProv")) {
            try {
                GuardarPedidoPedidoProv(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("Buscar Pedido")) {
        }
        if (action.equalsIgnoreCase("finalizarPedido")) {
            DTO.PedidoProveedor pedidoProv = (DTO.PedidoProveedor) request.getSession().getAttribute("pedidoProveedorActual");
            DAO.PedidoProveedorJpaController pedidoDAO = new DAO.PedidoProveedorJpaController();
            double factura = calcularFacturaProv(pedidoProv);
            pedidoProv.setTotal(factura);
            DAO.PedidoProveedorJpaController pedido = new DAO.PedidoProveedorJpaController();
            DTO.PedidoProveedor PedidoPrvo2 = pedidoDAO.findPedidoProveedor(pedidoProv.getId());
            PedidoPrvo2.setTotal(factura);
            request.getSession().removeAttribute("listaProductosPedidoProveedorActual");
            try {
                pedido.edit(PedidoPrvo2);
                response.sendRedirect("ControladorPedidoProv?accion=buscar");
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("AgregarProducto")) {
            try {
                agregarProducto(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ControladorPedidoProv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action.equalsIgnoreCase("BuscarPorCedula")) {
            buscarPorCedula(request, response);
            response.sendRedirect("vistas/jsp/listaPedidoProv.jsp");
        }
    }

    public void buscarPorCedula(HttpServletRequest request, HttpServletResponse response) {
        DAO.PedidoProveedorJpaController pedidosDAO = new DAO.PedidoProveedorJpaController();
        DAO.ProveedorJpaController proveedorDAO = new DAO.ProveedorJpaController();
        String cedula = request.getParameter("cedula");
        List<DTO.PedidoProveedor> pedidos = null;
        if (cedula.equalsIgnoreCase("")) {
            pedidos = pedidosDAO.findPedidoProveedorEntities();
        } else {
            pedidos = proveedorDAO.findProveedor(cedula).getPedidoProveedorList();
            if (pedidos == null) {
                pedidosDAO.findPedidoProveedorEntities();
            }
        }
        request.getSession().setAttribute("PedidosProvBuscados", pedidos);
    }

    public void actualizarDatos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Plantilla> p = buscandoPlantilla("", "");
        request.getSession().setAttribute("listaPlantilla", p);
        response.sendRedirect("vistas/jsp/listaPedidoProv.jsp");
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
