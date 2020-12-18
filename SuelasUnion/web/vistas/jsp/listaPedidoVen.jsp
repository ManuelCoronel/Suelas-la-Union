
<%@page import="DTO.PedidoProveedor"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DTO.Cliente"%>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>


        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Lista pedidos de venta</title>

        <!-- Fontfaces CSS-->
        <link href="../css/font-face.css" rel="stylesheet" media="all">
        <link href="../vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="../vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="../vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="../vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- ../vendor CSS-->
        <link href="../vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="../vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="../vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="../vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="../vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="../vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="../vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="../css/theme.css" rel="stylesheet" media="all">


    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <jsp:include page="headerMobile.html" flush="true"/>
            <!-- END HEADER MOBILE-->

            <!-- MENU SIDEBAR-->
            <jsp:include page="menuSiderbar.jsp" flush="true"/>
            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <!-- HEADER DESKTOP-->
                <jsp:include page="headerDesktop.jsp" flush="true"/>
                <!-- END HEADER DESKTOP-->

                <!-- MAIN CONTENT-->
                <div class="container">
                    <div class="main-content">
                        <div class="row">
                            <div class="col-sm">
                                <h1>Pedidos de venta</h1>
                            </div>
                        </div><br>
                        <form action="../../ControladorPedidoVen"  method="POST">
                            <div class="table-responsive table-responsive-data2">
                                <!-- filtros -->
                                <div class="row">
                                    <div class="col col-sm-3">
                                        <span class="label label-default">Buscar pedido</span>
                                        <input class="form-control" type="text" name="cedula"  placeholder="Cedula">
                                    </div>
                                    <div class="col col-sm-2 mt-4">
                                        <select name="opcionesTipoDeEstados" class="form-control">
                                            <option  value="0">Estados</option>
                                            <%
                                                List<DTO.Estado> listEstado = (List<DTO.Estado>) request.getSession().getAttribute("tiposDeEstados");
                                                for (DTO.Estado tipoEstado : listEstado) {
                                            %>
                                            <option  value="<%=tipoEstado.getId()%>"><%=tipoEstado.getDescripcion()%></option>
                                            <% }%></select>
                                    </div>
                                    <div class="col col-sm-2">
                                        <input class="btn btn-primary" type="submit" name="accion" value="Buscar"  style="margin-top: 25px;">
                                    </div>
                                    <div class="col col-sm-3">
                                        <a   class="btn btn-danger" href="../../ControladorPedidoVen?accion=cargarTiposEntrega"> crear pedido</a>
                                    </div>
                                </div>
                                <!-- filtros -->
                            </div>
                        </form>

                        <!-- Aquí empieza la tabla con los campos -->
                        <div class="row m-t-30">
                            <div class="col-md-12">
                                <!-- DATA TABLE-->
                                <div class="table-responsive m-b-40">
                                    <table class="table table-borderless table-data3">
                                        <thead>
                                            <tr>
                                                <th class="text-center">#</th>
                                                <th class="text-center">Cedula</th>
                                                <th class="text-center">Fecha orden</th>
                                                <th class="text-center">Fecha Factura</th>
                                                <th class="text-center">Fecha Plazo</th>
                                                <th class="text-center">Codigo Pedido</th>
                                                <th class="text-center">Tipo entrega</th>
                                                <th class="text-center">Realizado</th>  
                                                <th class="text-center">Total</th>
                                                <th class="text-center">Estado</th>              
                                                <th class="text-center"></th>  
                                                <th class="text-center"></th> 
                                            </tr>
                                        </thead>
                                        <%
                                            List<DTO.PedidoDeVenta> peddios = (List<DTO.PedidoDeVenta>) request.getSession().getAttribute("pedidosBuscadosVenta");
                                            int indice = 1;
                                            for (DTO.PedidoDeVenta dto : peddios) {
                                        %>
                                        <tbody>
                                            <tr>
                                                <td class="text-center"><%= indice%></td>
                                                <td class="text-center"><%=dto.getClienteCedula().getCedula()%></td>
                                                <td class="text-center"><%=dto.getFechaOrden().getDate()+"/"+dto.getFechaOrden().getMonth()+"/"+(dto.getFechaOrden().getYear()+1900) %></td>
                                                <td class="text-center"><%=(dto.getFechaEntregaFactura() != null) ? dto.getFechaEntregaFactura().getDay()+"/"+dto.getFechaEntregaFactura().getMonth()+"/"+(dto.getFechaEntregaFactura().getYear()+1900) : "-"%></td>
                                                <td class="text-center"><%=(dto.getFechaPlazo() != null) ? dto.getFechaPlazo().getDay()+"/"+dto.getFechaPlazo().getMonth()+"/"+(dto.getFechaPlazo().getYear()+1900) : "-"%></td>
                                                <td class="text-center"><%=dto.getId()%></td>
                                                <td class="text-center"><%=dto.getTipoEntregaId().getDescripcion()%></td>
                                                <td class="text-center"><%=dto.getPersonalId().getNombre() + " " + dto.getPersonalId().getApellido()%></td>
                                                <td class="text-center"><%=dto.getTotal()%></td>
                                                <td class="text-center"><%=dto.getEstadoId().getDescripcion()%></td>
                                                <td><a class="btn btn-primary" href="../../ControladorPedidoVen?accion=editar&id=<%=dto.getId()%>"> Editar</a></td>                             
                                                <td><a  href="../../ControlPDF?accion=pdf&id=<%=dto.getId()%>">Generar Factura</a></td>
                                            </tr>
                                            <%indice++;
                                                }%>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- END DATA TABLE-->
                            </div>
                        </div>

                        <!-- Aquí termina la tabla-->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="copyright">
                                    <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Jquery JS-->
        <script src="../vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="../vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="../vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- ../vendor JS       -->
        <script src="../vendor/slick/slick.min.js">
        </script>
        <script src="../vendor/wow/wow.min.js"></script>
        <script src="../vendor/animsition/animsition.min.js"></script>
        <script src="../vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>
        <script src="../vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="../vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="../vendor/circle-progress/circle-progress.min.js"></script>
        <script src="../vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="../vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="../vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="../js/main.js"></script>

    </body>

</html>
