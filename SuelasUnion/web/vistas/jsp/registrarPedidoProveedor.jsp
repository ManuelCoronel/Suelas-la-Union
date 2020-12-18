

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
        <title>Reigstrar Pedido Proveedor</title>

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
        <!-- css de suelas-->
        <link rel="stylesheet" href="../css/suelasPVC.css">


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
                                <div class="col-md-12">
                                    <!-- registrar venta -->
                                    <div class="row form-group" style="margin-left: 1px;">
                                        <div class="col-md-12">
                                            <div class="overview-wrap">
                                                <h2 class="title-1 mb-3">Seleccionar Proveedor</h2>
                                            </div>
                                        </div>
                                        <form class="col-md-10" action="../../ControladorPedidoProv" method="POST">
                                            <div class="row form-group">
                                                <div class="col col-md-12">
                                                    <div class="input-group">

                                                        <select name="opcionesProveedor" class="form-control">

                                                            <%

                                                                List<DTO.Proveedor> list = (List<DTO.Proveedor>) request.getSession().getAttribute("listaProveedores");

                                                                for (DTO.Proveedor prov : list) {
                                                            %>
                                                            <option  value="<%=prov.getCedula()%>"><%=prov.getEmpresa()%></option>
                                                            <% }%></select>



                                                        <div class="input-group-btn">

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>    
                                            <h2 class="title-1 mb-3">Fecha</h2> <br>
                                            <input class="form-control" type="date" name="fechaRegistro" value="" required>
                                            <div class="row form-group col-12 col-md-12"> <br>

                                                <div class="row" id="produc"></div>
                                            </div>
                                            <div class="row d-flex justify-content-center" >
                                                <button type="submit"  class="btn btn-primary w-25"  name="accion" value="guardarPedidoProv" >Crear</button>
                                            </div>
                                        </form>
                                    </div>
                                </div> <!-- aca termina -->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END PAGE CONTAINER-->

                <!-- producto  -->
                <script src="../js/agregarProducto.js"></script>


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
