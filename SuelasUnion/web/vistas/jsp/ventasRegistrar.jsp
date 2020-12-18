
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
        <title>Registrar Venta</title>

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
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <!-- aca empieza -->
                                <div class="col-md-7">
                                    <!-- registrar venta -->
                                    <div class="col-md-12">
                                        <div class="overview-wrap">
                                            <h2 class="title-1">Registrar venta</h2>
                                        </div>
                                    </div>
                                    <div class="row form-group" style="margin-left: 1px;">
                                        <div class="col-md-12"> <br>
                                            <select onchange="cambiarProducto()" name="producto" id="producto"
                                                    class="form-control">
                                                <option value="0" selected>Seleccionar producto</option>
                                                <option value="1">Plantillas</option>
                                                <option value="2">Suelas pvc</option>
                                                <option value="3">Suelas expanso</option>
                                                <option value="4">Tiras</option>
                                                <option value="5">Salpas</option>
                                            </select>
                                            <div class="row" id="produc"></div>
                                        </div>
                                        <div style="display: flex;">
                                            <div style="display: flex;">
                                                <div style="margin-left: 300px; margin-top: 25px;">
                                                    <input style="background-color: #fd8e2f; color: white;" class="btn"
                                                           type="submit" value="Agregar otro" onclick="Agrega()">
                                                </div>
                                                <div style="margin-left: 40px; margin-top: 25px;">
                                                    <input style="background-color: #fd8e2f; color: white;" class="btn"
                                                           type="submit" value="Confirmar">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- aca termina -->

                                <!-- resumen de venta -->
                                <div class="col-md-5">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="overview-wrap">
                                                <h2 class="title-1">Resumen de la venta</h2>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="au-card au-card--bg-orange au-card-top-countries m-b-30">
                                                <div class="au-card-inner">
                                                    <div class="table-responsive">
                                                        <table id="tablaProductos" class="table table-top-countries">
                                                            <tbody>
                                                                <tr>
                                                                    <td>Producto</td>
                                                                    <td>Descripcion</td>
                                                                    <td>Precio</td>
                                                                    <td>Eliminar</td>
                                                                </tr>
                                                            </tbody>

                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- fin resumen de venta -->
                            </div>
                        </div>
                    </div>
                    <!-- END MAIN CONTENT-->
                </div>
                <!-- END PAGE CONTAINER-->

            </div>
            <!-- producto  -->
            <script src="../js/producto.js"></script>
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
