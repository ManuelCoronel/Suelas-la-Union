<%-- 
    Document   : menuSiderbar
    Created on : 9/12/2020, 08:56:44 PM
    Author     : ronal
--%>

<%@page import="DTO.Personal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>

        <aside class="menu-sidebar d-none d-lg-block">
            <%
                Personal user = (Personal) request.getSession().getAttribute("personalActual");
                int admin = 2;
            %>
            <div class="logo">
                <a href="#">
                    <img src="../images/icon/logo.png" alt="Suelas la union" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li>
                            <a href="dashboard.jsp">
                                <i class="fas fa-home"></i>Inicio</a>
                        </li>
                        <li >
                            <a href="../../ControladorDepartamentos?accion=mostrarDisponibilidad">
                                <i class="fas fa-th-large"></i>Departamentos</a>
                        </li>
                        <li>
                            <a href="../../ProductoPedidoProveedor?accion=listar">
                                <i class="fas fa-users"></i>Productos Pedido Proovedor</a>
                        </li>
                        <li>
                            <a href="../../ControladorCliente?accion=listar">

                                <i class="fas fa-users"></i>Clientes</a>
                        </li>
                        <li>
                            <a href="../../ControladorProductoPedidoVenta?accion=listar">
                                <i class="fas fa-users"></i>Productos Pedidos de Venta</a>
                        </li>
                        <li>
                            <a href="../../ControladorPedidoVen?accion=listar">
                                <i class="fas fa-users"></i>Pedidos de Venta</a>
                        </li>
                        <li>
                            <a href="../../ControladorDevolucionPedidoVenta?accion=listar">
                                <i class="fas fa-users"></i>Devolucion Cliente</a>
                        </li>
                        <li>
                            <a href="../../ControladorDevolucionClienteProducto?accion=listar">

                                <i class="fas fa-users"></i>Devolucion Cliente Producto</a>
                        </li>
                        <% if (user.getRolId().getId() == admin) {%>
                        <li>
                            <a href="../../Proveedor?accion=listar">
                                <i class="fas fa-users"></i>Proveedores</a>
                        </li>
                        <li>
                            <a href="../../ControladorPedidoProv?accion=buscar">
                                <i class="fas fa-plus"></i>Pedidos de Proveedores </a>
                        </li>
                        <li>
                            <a href="../../ControladorPersonal?accion=listar">
                                <i class="fas fa-users"></i>Personal</a>
                        </li>
                        <li>
                            <a href="../../ControladorDevolucionProveedor?accion=listar">
                                <i class="fas fa-users"></i>Devolución a Proveedores</a>
                        </li>

                        <%}%>
                    </ul>
                </nav>
            </div>
        </aside>


    </body>
</html>
