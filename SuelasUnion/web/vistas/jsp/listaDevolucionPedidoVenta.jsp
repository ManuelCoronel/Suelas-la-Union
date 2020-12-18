
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
        <title>Devolución Cliente</title>

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
                                <h1>Devolución Cliente</h1>
                            </div>
                        </div><br>
                        <form action="../../ControladorDevolucionPedidoVenta"  method="POST">
                            <div class="table-responsive table-responsive-data2">
                                <!-- filtros -->
                                <div class="row">
                                    <div class="col col-sm-3">
                                        <span class="label label-default">Buscar devolución</span>
                                        <input class="form-control" type="text" name="idDevolucion"  placeholder="id devolución">
                                    </div>
                                    <div class="col col-sm-2">
                                        <input class="btn btn-primary" href="../../ControladorDevolucionPedidoVenta?accion=buscar" type="submit" name="accion" value="Buscar"  style="margin-top: 25px;">
                                        
                                    </div>
                                    <div class="col col-sm-3">

                                        <a class="btn btn-success" style="margin-top: 25px;"  href="../../ControladorDevolucionPedidoVenta?accion=formcreate">Registrar devolución</a>
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
                                                <th class="text-center">Cliente</th>
                                                <th class="text-center">Cédula</th>
                                         
                                                <th class="text-center">Descripción</th>
                                                <th class="text-center">Fecha Inicio</th>
                                                <th class="text-center">Fecha Fin</th>
                                                <th class="text-center"># Pedido</th>

                                            </tr>
                                        </thead>

                                        <%
                                            System.out.println(request.getSession().getAttribute("listaDevolucionCliente"));
                                            List<DTO.DevolucionCliente> list = (List<DTO.DevolucionCliente>) request.getSession().getAttribute("listaDevolucionCliente");
                                            int indice = 1;
                                            for (DTO.DevolucionCliente elem : list) {
                                        %>
                                        
                                        <tbody>
                                            <tr>
                                                <td class="text-center"><%=elem.getId()%></td>
                                                <td class="text-center"><%=elem.getPedidoDeVentaIdPedidoDeVenta().getClienteCedula().getNombre() %></td>
                                                <td class="text-center"><%=elem.getPedidoDeVentaIdPedidoDeVenta().getClienteCedula().getCedula() %></td>
                                                <td class="text-center"><%=elem.getDescripcion()%></td>
                                                <td class="text-center"><%=elem.getFechaInicio()%></td>
                                                <td class="text-center"><%=elem.getFechaFin() %></td>
                                                <td class="text-center"><%=elem.getPedidoDeVentaIdPedidoDeVenta().getId()%></td>


                                            </tr>
                                            <%
                                                    indice++;
                                                }
                                            %>
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
