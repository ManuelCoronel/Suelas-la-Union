
<%@page import="DTO.Producto"%>
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
        <title>Dashboard</title>

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
                <!-- HEADER DESKTOP-->

                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section_content section_content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="overview-wrap">
                                        <h2 class="title-1">Resumen</h2>

                                    </div>
                                </div>
                            </div>
                            <div class="row m-t-25">
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c1">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-account-o"></i>
                                                </div>
                                                <div class="text" style="text-align:center;">
                                                    <h2><%=request.getSession().getAttribute("totalClientes")%></h2>
                                                    <span>Clientes</span>
                                                    <br><br><br>
                                                </div>
                                            </div>
                                            <!--  grafica dentro del 1er overview en dashboard index
                                                    <div class="overview-chart">
                                                        <canvas id="widgetChart1"></canvas>
                                                    </div>
                                            -->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c3">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-calendar-note"></i>
                                                </div>
                                                <div class="text" style="text-align:center;">
                                                    <h2>5</h2>
                                                    <span>Departamentos </span>
                                                    <br><br><br>
                                                </div>
                                            </div>
                                            <!-- <div class="overview-chart">
                                                        <canvas id="widgetChart3"></canvas>
                                                    </div> -->
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-lg-3">
                                    <div class="overview-item overview-item--c2">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <div class="icon">
                                                    <i class="zmdi zmdi-shopping-cart"></i>
                                                </div>
                                                <div class="text" style="text-align:center;">
                                                    <h2><%=request.getSession().getAttribute("totalProductosDisponibles")%></h2>
                                                    <span>Productos</span>
                                                    <br><br><br>
                                                </div>
                                            </div>
                                            <!-- <div class="overview-chart">
                                                        <canvas id="widgetChart2"></canvas>
                                                    </div> -->
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 col-lg-3">

                                    <div class="overview-item overview-item--c4">
                                        <div class="overview__inner">
                                            <div class="overview-box clearfix">
                                                <a href="../../ControladorDashboard?accion=reportegeneral">
                                                    <div class="icon">
                                                        <i class="zmdi zmdi-money"></i>
                                                    </div>
                                                    <div class="text">
                                                        <h2>$ <%=request.getSession().getAttribute("totalventas")%></h2>
                                                        <span>Ventas</span>
                                                        <br><br><br>
                                                    </div>
                                                </a>
                                            </div>
                                            <!-- <div class="overview-chart">
                                                        <canvas id="widgetChart4"></canvas>
                                                    </div> -->
                                        </div>
                                    </div>

                                </div>


                            </div>

                            <div class="row">
                                <div class="col-lg-4">
                                    <h2 class="title-1 m-b-25">PRODUCTOS MÁS VENDIDOS</h2>
                                    <hr>
                                    <div class="table-responsive table--no-card m-b-40">
                                        <table class="table table-borderless table-striped table-earning">

                                            <thead>
                                                <tr>
                                                    <th>Modelo</th>
                                                    <th>Talla/Cantidad</th>
                                                    <th>Precio</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int cont = 1;
                                                    List<Producto> list = (List<Producto>) (request.getSession().getAttribute("mostrarCincoUltimos"));

                                                    for (int i = 0; i < list.size(); i++) {
                                                        if (list.get(i).getPlantilla() != null) {
                                                %> 
                                                <tr> 
                                                    <td><%= list.get(i).getPlantilla().getModelo()%></td>
                                                    <td><%= list.get(i).getPlantilla().getTalla()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getSalpa() != null) {
                                                    %>
                                                <tr> 
                                                    <td>Salpa <%= list.get(i).getId()%></td>
                                                    <td><%= list.get(i).getCantidadDisponible()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getTira() != null) {
                                                    %>
                                                <tr> 
                                                    <td><%= list.get(i).getTira().getModelo()%></td>
                                                    <td><%= list.get(i).getTira().getAnchura()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getSuela() != null) {
                                                    %>
                                                <tr> 
                                                    <td><%= list.get(i).getSuela().getModelo()%></td>
                                                    <td><%= list.get(i).getSuela().getTalla()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>

                                                    <%
                                                            }
                                                            if (cont == 5) {
                                                                break;
                                                            }
                                                            cont++;
                                                        }
                                                    %>                                            
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <h2 class="title-1 m-b-25">ULTIMAS VENTAS</h2>
                                    <hr>
                                    <div class="table-responsive table--no-card m-b-40">
                                        <table class="table table-borderless table-striped table-earning">
                                            <thead>
                                                <tr>
                                                    <th>Modelo</th>
                                                    <th>Talla/Cantidad</th>
                                                    <th>Precio</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    cont = 1;

                                                    for (int i = list.size() / 2; i >= 0; i--) {
                                                        if (list.get(i).getPlantilla() != null) {
                                                %> 
                                                <tr> 
                                                    <td><%= list.get(i).getPlantilla().getModelo()%></td>
                                                    <td><%= list.get(i).getPlantilla().getTalla()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getSalpa() != null) {
                                                    %>
                                                <tr> 
                                                    <td>Salpa <%= list.get(i).getId()%></td>
                                                    <td><%= list.get(i).getCantidadDisponible()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getTira() != null) {
                                                    %>
                                                <tr> 
                                                    <td><%= list.get(i).getTira().getModelo()%></td>
                                                    <td><%= list.get(i).getTira().getAnchura()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getSuela() != null) {
                                                    %>
                                                <tr> 
                                                    <td><%= list.get(i).getSuela().getModelo()%></td>
                                                    <td><%= list.get(i).getSuela().getTalla()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>

                                                    <%
                                                            }
                                                            if (cont == 5) {
                                                                break;
                                                            }
                                                            cont++;
                                                        }
                                                    %>                                            
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <h2 class="title-1 m-b-25">PRODUCTOS RECIENTES</h2>
                                    <hr>
                                    <div class="table-responsive table--no-card m-b-40">
                                        <table class="table table-borderless table-striped table-earning">
                                            <thead>
                                                <tr>
                                                    <th>Modelo</th>
                                                    <th>Talla/Cantidad</th>
                                                    <th>Precio</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    cont = 1;
                                                    for (int i = list.size() - 1; i >= 0; i--) {
                                                        if (list.get(i).getPlantilla() != null) {
                                                %> 
                                                <tr> 
                                                    <td><%= list.get(i).getPlantilla().getModelo()%></td>
                                                    <td><%= list.get(i).getPlantilla().getTalla()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getSalpa() != null) {
                                                    %>
                                                <tr> 
                                                    <td>Salpa <%= list.get(i).getId()%></td>
                                                    <td><%= list.get(i).getCantidadDisponible()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getTira() != null) {
                                                    %>
                                                <tr> 
                                                    <td><%= list.get(i).getTira().getModelo()%></td>
                                                    <td><%= list.get(i).getTira().getAnchura()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>
                                                    <%
                                                    } else if (list.get(i).getSuela() != null) {
                                                    %>
                                                <tr> 
                                                    <td><%= list.get(i).getSuela().getModelo()%></td>
                                                    <td><%= list.get(i).getSuela().getTalla()%></td>
                                                    <td><%= list.get(i).getPrecio()%></td>
                                                <tr>

                                                    <%
                                                            }
                                                            if (cont == 5) {
                                                                break;
                                                            }
                                                            cont++;
                                                        }
                                                    %>                                            
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="copyright">
                                        <p>Copyright ©️ 2018 Colorlib. All rights reserved. Template by <a
                                                href="https://colorlib.com">Colorlib</a>.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT-->
                <!-- END PAGE CONTAINER-->
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
<!-- end document-->