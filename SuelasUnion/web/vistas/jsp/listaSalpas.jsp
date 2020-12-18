
<%@page import="DTO.Salpa"%>
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
        <title>Salpas</title>

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
                    <div class="section__content section__content--p30"><h1 style="margin-left: 20px;margin-bottom: 50px;">Salpas</h1>
                        <div class="container-fluid">


                            <div class="row m-t-30">
                                <div class="col-md-6">
                                    <!-- DATA TABLE-->
                                    <div class="table-responsive m-b-40">

                                        <table class="table table-borderless table-data3">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Unidades</th>



                                                    <%     Object ob = request.getSession().getAttribute("pedidoVentaActual");
                                                        if (ob != null) {

                                                    %> 
                                                    <th class="text-center">Cantidad a llevar</th>
                                                    <th></th>

                                                    <%                                                        }

                                                    %>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%                                                    System.out.println(request.getSession().getAttribute("listaSalpas"));
                                                    List<Salpa> list = (List<Salpa>) request.getSession().getAttribute("listaSalpas");
                                                    for (Salpa elem : list) {
                                                %>

                                                <tr>
                                                    <td><%= elem.getProducto().getId()%></td>
                                                    <td><%=elem.getProducto().getCantidadDisponible()%></td>





                                                    <%

                                                        if (ob != null) {

                                                    %> 

                                            <form  action="../../ControladorPedidoVen" method="GET"> 


                                                <td class="text-center">

                                                    <input name="cantidadLlevar" type="number" placeholder="0" max="<%=elem.getProducto().getCantidadDisponible()%>" min="0" class="form-control">
                                                </td>
                                                <td class="text-center">
                                                    <input type="submit" style="height: 40px; width: 80px;margin-top: 20px;" name="accion" value="Agregar" class="btn btn-primary btn-sm float-right">
                                                </td>



                                                <input type="text" hidden="true" style="height: 40px; width: 80px;margin-top: 20px;" name="id" value="<%=elem.getProducto().getId()%>" class="btn btn-primary btn-sm float-right">



                                            </form>
                                            <%
                                                }
                                            %>
                                            </tr>

                                            <%
                                                }
                                            %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- END DATA TABLE-->
                                </div>
                            </div>
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
