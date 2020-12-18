

<%@page import="DTO.ProductoPedidoProveedor"%>
<%@page import="DTO.Tira"%>
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
        <title>Producto Pedido Proveedor</title>

        <!-- Favicons -->
        <link rel="icon" type="image/x-icon" href="../images/icon/logo.ico">

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

                <div class="main-content">
                    <div class="section__content section__content--p30"><h1 style="margin-left: 20px;margin-bottom: 50px;">Producto Pedido Proveedor</h1>
                        <div class="container-fluid">
                            <!-- Aquí empieza lo de buscar -->
                            <form action="../../ProductoPedidoProveedor"  method="POST">
                                <div class="table-responsive table-responsive-data2">
                                    <!-- filtros -->
                                    <div class="row">
                                        <div class="col col-sm-3">
                                            <span class="label label-default">Buscar productos del  pedido</span>
                                            <input class="form-control" type="text" name="idPedido"  placeholder="id pedido">
                                        </div>
                                        <div class="col col-sm-2">
                                            <input class="btn btn-primary" type="submit" name="accion" value="Buscar"  style="margin-top: 25px;">
                                        </div>



                                    </div>
                                    <!-- filtros -->
                                </div>
                            </form>


                            <!-- Aquí empieza la tabla con los campos -->
                            <div class="row m-t-30">
                                <div class="col-md-12">
                                    <div class="table-responsive m-b-40">

                                        <table class="table table-borderless table-data3">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">#</th>
                                                    <th class="text-center">FECHA</th>
                                                    <th class="text-center">PRODUCTO</th>
                                                    <th class="text-center">CANTIDAD</th>
                                                    <th class="text-center">PRECIO</th>
                                                    <th class="text-center">TOTAL</th>
                                                    <th class="text-center">CODIGO PEDIDO</th>
                                                     <th class="text-center">PROVEEDOR</th>  

                                                </tr>
                                            </thead>
                                            <%
                                                System.out.println(request.getSession().getAttribute("listaProductosPedidoProveedor"));
                                                List<ProductoPedidoProveedor> list = (List<ProductoPedidoProveedor>) request.getSession().getAttribute("listaProductosPedidoProveedor");
                                                int indice = 1;
                                                for (ProductoPedidoProveedor elem : list) {
                                            %>
                                                <%
                                                String tipo = elem.getProducto().getTipoProducto();
                                                String nombreProducto = "";
                                                if (tipo.equals("1")) {
                                                    nombreProducto = "Plantilla" + " " + elem.getProducto().getPlantilla().getModelo();
                                                }
                                                if (tipo.equals("2")) {
                                                    nombreProducto = "Suela PVC" + " " + elem.getProducto().getSuela().getModelo();
                                                }
                                                if (tipo.equals("3")) {
                                                    nombreProducto = "Suela Expanso" + " " + elem.getProducto().getSuela().getModelo();
                                                }
                                                if (tipo.equals("4")) {
                                                    nombreProducto = "Tira" + " " + elem.getProducto().getTira().getModelo();
                                                }
                                                if (tipo.equals("5")) {
                                                    nombreProducto = "Salpa";
                                                }
                                            %>
                                            <tbody>
                                                <tr>

                                                    <td class="text-center"><%=indice%></td>
                                                    <td class="text-center"><%=elem.getPedidoProveedor().getFecha()%></td>
                                                    <td class="text-center"><%= nombreProducto%></td>
                                                    <td class="text-center"><%=elem.getCantidad()%></td>
                                                    <td class="text-center"><%=elem.getPrecio()%></td>
                                                    <td class="text-center"><%=elem.getTotal()%></td>
                                                    <td class="text-center"><%=elem.getPedidoProveedor().getId()%></td>


                                                    <td class="text-center"><%=elem.getPedidoProveedor().getProveedorCedula().getEmpresa()%></td>
                                                 

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
