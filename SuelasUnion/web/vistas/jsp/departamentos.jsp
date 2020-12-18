

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
        <title>Departamentos</title>

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
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <section class="statistic statistic2">
                                        <div class="container">
                                            <div class="row">
                                                <a class="col-md-6 col-lg-4" href="../../ControladorSuelas?accion=buscar&tipo=2">
                                                    <div class="statistic__item statistic__item--gris">
                                                        <h2 class="number"> SUELAS PVC</h2>
                                                        <p style="color: black;">Modelos en existencia</p>
                                                        <%
                                                            int pvc = (Integer) (request.getSession().getAttribute("departamentosSuelasPVCDis"));
                                                        %>
                                                        <h3><%= pvc%></h3>
                                                        <div class="icon">
                                                            <i class="zmdi zmdi-calendar-note"></i>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a class="col-md-6 col-lg-4" href="../../ControladorSuelas?accion=buscar&tipo=3">
                                                    <div class="statistic__item statistic__item--green">
                                                        <h2 class="number"> SUELAS EN EXPANSO</h2>
                                                        <p style="color: black;">Productos en existencia</p>
                                                        <%
                                                            int exp = (Integer) (request.getSession().getAttribute("departamentosSuelasExpansoDis"));

                                                        %>
                                                        <h3><%= exp%></h3>
                                                        <div class="icon">
                                                            <i class="zmdi zmdi-calendar-note"></i>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a class="col-md-6 col-lg-4" href="../../ControladorTira?accion=buscar">
                                                    <div class="statistic__item statistic__item--pink">
                                                        <h2 class="number"> TIRAS</h2>
                                                        <p style="color: black;">Productos en existencia</p>
                                                        <%

                                                            int tiras = (Integer) (request.getSession().getAttribute("departamentoTirasDis"));


                                                        %>
                                                        <h3><%=tiras%></h3>
                                                        <div class="icon">
                                                            <i class="zmdi zmdi-calendar-note"></i>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                            <div class="row">
                                                <a class="col-md-6 col-lg-4 offset-2" href="../../ControladorPlantilla?accion=buscar">
                                                    <div class="statistic__item statistic__item--yellow">
                                                        <h2 class="number"> PLANTILLAS</h2>
                                                        <p style="color: black;">Productos en existencia</p>
                                                        <%
                                                            int plantillas = (Integer) (request.getSession().getAttribute("departamentosPlantillaDis"));
                                                        %>
                                                        <h3>
                                                            <%= plantillas%>
                                                        </h3>
                                                        <div class="icon">
                                                            <i class="zmdi zmdi-calendar-note"></i>
                                                        </div>
                                                    </div>
                                                </a>
                                                <a class="col-md-6 col-lg-4" href="../../ControladorSalpa?accion=listar">
                                                    <div class="statistic__item statistic__item--yellow">
                                                        <h2 class="number"> SALPAS</h2>
                                                        <p style="color: black;">Productos en existencia</p>                                                       
                                                        <%
                                                            int salpas = (Integer) (request.getSession().getAttribute("departamentoSalpasDis"));

                                                        %>
                                                        <h3><%= salpas%></h3>
                                                        <div class="icon">
                                                            <i class="zmdi zmdi-calendar-note"></i>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                    </section>
                                    <!-- ACA TERMINA TODO -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT-->
            </div>
            <!-- END PAGE CONTAINER-->

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
